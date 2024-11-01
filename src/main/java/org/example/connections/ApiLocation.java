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

    public ApiLocation() {
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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
                System.out.println("Response: " + jsonResponse);
                Thread.sleep(1000);
                // Parse JSON and retrieve specific values
                JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();

                String country = jsonObject.getAsJsonObject("address").get("country").getAsString();

                // Output the values

                System.out.println("Country: " + country);

            } else {
                System.out.println("Error: " + response.getStatusLine().getStatusCode());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
