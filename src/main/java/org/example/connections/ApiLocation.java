package org.example.connections;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ApiLocation {
    //https://geocode.maps.co/reverse?lat=latitude&lon=longitude&api_key=672494c88b52e639833548cvjf55303

    public static void main(String[] args) {
        String url = "https://geocode.maps.co/reverse?lat=49.176538&lon=16.597093972222222&api_key=672494c88b52e639833548cvjf55303"; // Replace with your API endpoint

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
            } else {
                System.out.println("Error: " + response.getStatusLine().getStatusCode());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
