package org.example.connections.api;

import org.example.models.Location;
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
            setBuilding(key);
        }
        this.locationObject.setLatitude(this.latitude);
        this.locationObject.setLongitude(this.longitude);
    }

    private void setCountryCod(String key) {
        if (key.equals("country_code")) {
            this.locationObject.setCountry_code(addressObject().get(key).toString());
        }
    }

    private void setTourism(String key){
        if (key.equals("tourism")) {
            this.locationObject.setTourism(addressObject().get(key).toString());
        }
    }

    private void setShop(String key){
        if (key.equals("shop")) {
            this.locationObject.setShop(addressObject().get(key).toString());
        }
    }
    private void setHouseNumber(String key){
        if (key.equals("house_number")) {
            this.locationObject.setHouse_number(addressObject().get(key).toString());
        }
    }

    private void setRoad(String key){
        if (key.equals("road")) {
            this.locationObject.setRoad(addressObject().get(key).toString());
        }
    }

    private void setSuburb(String key){
        if (key.equals("suburb")) {
            this.locationObject.setSuburb(addressObject().get(key).toString());
        }
    }
    private void setVillage(String key){
        if (key.equals("village")) {
            this.locationObject.setVillage(addressObject().get(key).toString());
        }
    }

    private void setCity(String key){
        if (key.equals("city")) {
            this.locationObject.setCity(addressObject().get(key).toString());
        }
    }

    private void setMunicipality(String key){
        if (key.equals("municipality")) {
            this.locationObject.setMunicipality(addressObject().get(key).toString());
        }
    }

    private void setCounty(String key){
        if (key.equals("county")) {
            this.locationObject.setCounty(addressObject().get(key).toString());
        }
    }

    private void setState(String key){
        if (key.equals("state")) {
            this.locationObject.setState(addressObject().get(key).toString());
        }
    }
    private void setCountry(String key){
        if (key.equals("country")) {
            this.locationObject.setCountry(addressObject().get(key).toString());
        }
    }
    private void setPostcode(String key){
        if (key.equals("postcode")) {
            this.locationObject.setPostcode(addressObject().get(key).toString());
        }
    }

    private void setBuilding(String key){
        if (key.equals("building")) {
            this.locationObject.setBuilding(addressObject().get(key).toString());
        }
    }

    public static void main(String[] args) {

    }
}


