package org.example.connections.api;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ApiConnect {
    private String url;
    private String jsonString;

    public ApiConnect(String url) {
        this.url = url;
        this.jsonString = jsonString();
    }

    public String getJsonString() {
        return jsonString;
    }

    private String jsonString() {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(this.url);
            System.out.println(this.url);

            // Optional: set headers (if the API requires authentication or specific headers)
            request.addHeader("Accept", "application/json");
            request.addHeader("Authorization", "Bearer YOUR_ACCESS_TOKEN");

            // Execute the request
            HttpResponse response = httpClient.execute(request);

            // Check for successful response code
            if (response.getStatusLine().getStatusCode() == 200) {
                Thread.sleep(1000);
                // Parse response
                return EntityUtils.toString(response.getEntity());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
