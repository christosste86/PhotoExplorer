package org.example.connections;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;

public class ApiLocation {
    //https://geocode.maps.co/reverse?lat=latitude&lon=longitude&api_key=672494c88b52e639833548cvjf55303
    private String url = "https://geocode.maps.co/reverse?";
    private double latitude;
    private double longitude;
    private String apiKey = "672494c88b52e639833548cvjf55303";

    private String country_code;
    private String country;
    private String postcode;
    private String state;
    private String county;
    private String municipality;
    private String city;
    private String suburb;
    private String road;
    private String house_number;
    private String shop;

    public ApiLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        generateLocation();
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCountry_code() {
        return country_code;
    }

    public String getCountry() {
        return country;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getState() {
        return state;
    }

    public String getCounty() {
        return county;
    }

    public String getMunicipality() {
        return municipality;
    }

    public String getCity() {
        return city;
    }

    public String getSuburb() {
        return suburb;
    }

    public String getRoad() {
        return road;
    }

    public String getHouse_number() {
        return house_number;
    }

    public String getShop() {
        return shop;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void generateLocation() {
        String url = this.url+"lat="+this.latitude+"&lon="+this.longitude+"&api_key="+this.apiKey; // Replace with your API endpoint

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);

            // Optional: set headers (if the API requires authentication or specific headers)
            request.addHeader("Accept", "application/json");
            request.addHeader("Authorization", "Bearer YOUR_ACCESS_TOKEN");

            // Execute the request
            HttpResponse response = httpClient.execute(request);

            // Check for successful response code
            if (response.getStatusLine().getStatusCode() == 200) {
                // Parse response
                String jsonResponse = EntityUtils.toString(response.getEntity());
                Thread.sleep(1000);
                // Parse JSON and retrieve specific values
                JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();

                objects(jsonObject);

            } else {
                System.out.println("Error: " + response.getStatusLine().getStatusCode());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void objects(JsonObject jsonObject){
        country_codeObject(jsonObject);
        countryObject(jsonObject);
        postcodeObject(jsonObject);
        stateObject(jsonObject);
        countyObject(jsonObject);
        municipalityObject(jsonObject);
        cityObject(jsonObject);
        suburbObject(jsonObject);
        roadObject(jsonObject);
        house_numberObject(jsonObject);
        shopObject(jsonObject);
    }

    public void country_codeObject(JsonObject jsonObject) {
        try {
            this.country_code = jsonObject.getAsJsonObject("address").get("country_code").getAsString();
        } catch (Exception e) {
            this.country_code = null;
        }
    }

    public void countryObject(JsonObject jsonObject) {
        try {
            this.country = jsonObject.getAsJsonObject("address").get("country").getAsString();
        } catch (Exception e) {
            this.country = null;
        }
    }

    public void postcodeObject(JsonObject jsonObject) {
        try {
            this.postcode = jsonObject.getAsJsonObject("address").get("postcode").getAsString();
        } catch (Exception e) {
            this.postcode = null;
        }
    }

    public void stateObject(JsonObject jsonObject) {
        try {
            this.state = jsonObject.getAsJsonObject("address").get("state").getAsString();
        } catch (Exception e) {
            this.state = null;
        }
    }

    public void countyObject(JsonObject jsonObject) {
        try {
            this.county = jsonObject.getAsJsonObject("address").get("county").getAsString();
        } catch (Exception e) {
            this.county = null;
        }
    }

    public void municipalityObject(JsonObject jsonObject) {
        try {
            this.municipality = jsonObject.getAsJsonObject("address").get("municipality").getAsString();
        } catch (Exception e) {
            this.municipality = null;
        }
    }

    public void suburbObject(JsonObject jsonObject) {
        try {
            this.suburb = jsonObject.getAsJsonObject("address").get("suburb").getAsString();
        } catch (Exception e) {
            this.suburb = null;
        }
    }

    public void roadObject(JsonObject jsonObject) {
        try {
            this.road = jsonObject.getAsJsonObject("address").get("road").getAsString();
        } catch (Exception e) {
            this.road = null;
        }
    }

    public void cityObject(JsonObject jsonObject) {
        try {
            this.city = jsonObject.getAsJsonObject("address").get("city").getAsString();
        } catch (Exception e) {
            this.city = null;
        }
    }

    public void house_numberObject(JsonObject jsonObject) {
        try {
            this.house_number = jsonObject.getAsJsonObject("address").get("house_number").getAsString();
        } catch (Exception e) {
            this.house_number = null;
        }
    }

    public void shopObject(JsonObject jsonObject) {
        try {
            this.shop = jsonObject.getAsJsonObject("address").get("shop").getAsString();
        } catch (Exception e) {
            this.shop = null;
        }
    }
}
