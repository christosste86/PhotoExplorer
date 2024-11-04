package org.example.connections.api;

import org.example.connections.PhotoExplorer;
import org.example.connections.photos.PhotoDetails;
import org.example.connections.db.daos.GenericDao;
import org.example.connections.db.models.Location;
import org.example.connections.db.services.GenericService;
import org.json.JSONObject;

import java.util.Iterator;

public class GeocodeCoordinatesApiService {
    private double latitude;
    private double longitude;
    private final String apiKey = "672494c88b52e639833548cvjf55303";
    private String url;
    private ApiHttpConnection geocode;
    private GenericDao<Location, Long> locationDao = new GenericDao<>(Location.class);
    private GenericService<Location, Long> locationService = new GenericService<>(locationDao);

    public GeocodeCoordinatesApiService(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.url = String.format("https://geocode.maps.co/reverse?lat=%s&lon=%s&api_key=%s",this.latitude, this.longitude, this.apiKey);
        this.geocode=new ApiHttpConnection(this.url);
    }

    private JSONObject locationObject(){
        return new JSONObject(geocode.getJsonString());
    }

    private JSONObject addressObject(){
        return locationObject().getJSONObject("address");
    }

    public void saveLocation(Location location){
        addApiAddressToLocationObject(location);
        addCoordinatesToLocationObjects(location);
        locationService.save(location);
    }

    private void addCoordinatesToLocationObjects(Location location){
        location.setLatitude(this.latitude);
        location.setLongitude(this.longitude);
    }

    private void addApiAddressToLocationObject(Location location){
        Iterator<String> keys = addressObject().keys();
        while (keys.hasNext()) {
            String key = keys.next();
            if(key.equals("shop")){location.setShop(addressObject().get(key).toString());};
            if(key.equals("house_number")){location.setHouse_number(addressObject().get(key).toString());};
            if(key.equals("road")){location.setRoad(addressObject().get(key).toString());};
            if(key.equals("suburb")){location.setSuburb(addressObject().get(key).toString());};
            if(key.equals("village")){location.setVillage(addressObject().get(key).toString());};
            if(key.equals("city")){location.setCity(addressObject().get(key).toString());};
            if(key.equals("municipality")){location.setMunicipality(addressObject().get(key).toString());};
            if(key.equals("county")){location.setCounty(addressObject().get(key).toString());};
            if(key.equals("state")){location.setState(addressObject().get(key).toString());};
            if(key.equals("postcode")){location.setPostcode(addressObject().get(key).toString());};
            if(key.equals("country")){location.setCountry(addressObject().get(key).toString());};
            if(key.equals("country_code")){location.setCountry_code(addressObject().get(key).toString());};
        }
    }

    public static void main(String[] args) {
        PhotoExplorer photoDirectory = new PhotoExplorer("Z:\\BackUp\\Photos\\2024\\Winter\\okres Brno-město");
        photoDirectory.getPhotoFilesList().forEach(img->{
            PhotoDetails photoDetail = new PhotoDetails(img.toString());
            photoDetail.savePhotoDetails();
            GeocodeCoordinatesApiService geoLocation = new GeocodeCoordinatesApiService(photoDetail.getLatitude(), photoDetail.getLongitude());
            geoLocation.saveLocation(new Location());
        });
    }
}

