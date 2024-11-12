package org.example.connections.api;

import org.example.models.Location;
import org.example.services.LocationServices;
import org.json.JSONObject;

import java.util.Iterator;

public class GeocodeService {
    private double latitude;
    private double longitude;

    private final String apiKey = "672494c88b52e639833548cvjf55303";
    private ApiConnect geocode;
    private Location location = new Location();


    public GeocodeService() {
    }

    public GeocodeService(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        String url = String.format("https://geocode.maps.co/reverse?lat=%s&lon=%s&api_key=%s", this.latitude, this.longitude, this.apiKey);
        this.geocode = new ApiConnect(url);
        addApiAddressToLocationObject();
    }

    public Location getLocation() {
        return location;
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
        }
        this.location.setLatitude(this.latitude);
        this.location.setLongitude(this.longitude);
    }
    private void setShop(String key){
        if (key.equals("shop")) {
            this.location.setShop(addressObject().get(key).toString());
        }
    }
    private void setHouseNumber(String key){
        if (key.equals("house_number")) {
            location.setHouse_number(addressObject().get(key).toString());
        }
    }

    private void setRoad(String key){
        if (key.equals("road")) {
            location.setRoad(addressObject().get(key).toString());
        }
    }

    private void setSuburb(String key){
        if (key.equals("suburb")) {
            location.setSuburb(addressObject().get(key).toString());
        }
    }
    private void setVillage(String key){
        if (key.equals("village")) {
            location.setVillage(addressObject().get(key).toString());
        }
    }

    private void setCity(String key){
        if (key.equals("city") || key.equals("town")) {
            location.setCity(addressObject().get(key).toString());
        }
    }

    private void setMunicipality(String key){
        if (key.equals("municipality")) {
            location.setMunicipality(addressObject().get(key).toString());
        }
    }

    private void setCounty(String key){
        if (key.equals("county")) {
            location.setCounty(addressObject().get(key).toString());
        }
    }

    private void setState(String key){
        if (key.equals("state")) {
            location.setState(addressObject().get(key).toString());
        }
    }
    private void setCountry(String key){
        if (key.equals("country_code")) {
            location.setCountry_code(addressObject().get(key).toString());
        }
    }
    private void setPostcode(String key){
        if (key.equals("postcode")) {
            location.setPostcode(addressObject().get(key).toString());
        }
    }

}

