package org.example.connections.api;

import org.example.models.Location;
import org.example.services.LocationServices;
import org.json.JSONObject;

import java.util.Iterator;

public class GeocodeService {
    private Double latitude;
    private Double longitude;

    private final String apiKey = "672494c88b52e639833548cvjf55303";
    private ApiConnect geocode;
    private Location locationObject =new Location();


    public GeocodeService() {
    }

    public GeocodeService(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        if(latitude !=null && longitude !=null) {
            String url = String.format("https://geocode.maps.co/reverse?lat=%s&lon=%s&api_key=%s", this.latitude, this.longitude, this.apiKey);
            this.geocode = new ApiConnect(url);
            addApiAddressToLocationObject();
        }
    }

    public Location getLocationObject() {
        return this.locationObject;
    }

    private JSONObject locationObject() {
        return new JSONObject(geocode.getJsonString());
    }

    private JSONObject addressObject() {
        return locationObject().getJSONObject("address");
    }

    private void addApiAddressToLocationObject() {
        Iterator<String> keys = addressObject().keys();
        while (keys.hasNext()) {
            String key = keys.next();
            setShop(key);
            setHouseNumber(key);
            setRoad(key);
            setSuburb(key);
            setVillage(key);
            setCity(key);
            setMunicipality(key);
            setCounty(key);
            setState(key);
            setPostcode(key);
            setCountry(key);
            setTourism(key);
            setCountryCod(key);
        }
        this.locationObject.setLatitude(this.latitude);
        this.locationObject.setLongitude(this.longitude);
    }

    private void setCountryCod(String key) {
        if (key.equals("country_code")) {
            this.locationObject.setCountry_code(addressObject().get(key).toString());
        }else{this.locationObject.setCountry_code(null);}
    }

    private void setTourism(String key){
        if (key.equals("tourism")) {
            this.locationObject.setTourism(addressObject().get(key).toString());
        }else{this.locationObject.setTourism(null);}
    }

    private void setShop(String key){
        if (key.equals("shop")) {
            this.locationObject.setShop(addressObject().get(key).toString());
        }else{this.locationObject.setShop(null);}
    }
    private void setHouseNumber(String key){
        if (key.equals("house_number")) {
            this.locationObject.setHouse_number(addressObject().get(key).toString());
        }else{this.locationObject.setHouse_number(null);}
    }

    private void setRoad(String key){
        if (key.equals("road")) {
            this.locationObject.setRoad(addressObject().get(key).toString());
        }else{this.locationObject.setRoad(null);}
    }

    private void setSuburb(String key){
        if (key.equals("suburb")) {
            this.locationObject.setSuburb(addressObject().get(key).toString());
        }else{this.locationObject.setSuburb(null);}
    }
    private void setVillage(String key){
        if (key.equals("village")) {
            this.locationObject.setVillage(addressObject().get(key).toString());
        }else{this.locationObject.setVillage(null);}
    }

    private void setCity(String key){
        if (key.equals("city")) {
            this.locationObject.setCity(addressObject().get(key).toString());
        }else{this.locationObject.setCity(null);}
    }

    private void setMunicipality(String key){
        if (key.equals("municipality")) {
            this.locationObject.setMunicipality(addressObject().get(key).toString());
        }else{this.locationObject.setMunicipality(null);}
    }

    private void setCounty(String key){
        if (key.equals("county")) {
            this.locationObject.setCounty(addressObject().get(key).toString());
        }else{this.locationObject.setCounty(null);}
    }

    private void setState(String key){
        if (key.equals("state")) {
            this.locationObject.setState(addressObject().get(key).toString());
        }else{this.locationObject.setState(null);}
    }
    private void setCountry(String key){
        if (key.equals("country_code")) {
            this.locationObject.setCountry(addressObject().get(key).toString());
        }else{this.locationObject.setCountry(null);}
    }
    private void setPostcode(String key){
        if (key.equals("postcode")) {
            this.locationObject.setPostcode(addressObject().get(key).toString());
        }else{this.locationObject.setPostcode(null);}
    }
}

