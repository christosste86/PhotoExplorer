package org.example.connections;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.GpsDirectory;

import java.io.File;
import java.util.Optional;

public class PhotoLocation {

    private String imagePath;
    private double latitude;
    private double longitude;

    public PhotoLocation(String imagePath) {
        this.imagePath = imagePath;
        getLatLong();
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void getLatLong() {
        File imageFile = new File(this.imagePath); // Replace with your image path

        try {
            Metadata metadata = ImageMetadataReader.readMetadata(imageFile);
            Optional<GpsDirectory> gpsDirectory = metadata.getDirectoriesOfType(GpsDirectory.class).stream().findFirst();

            if (gpsDirectory.isPresent()) {
                GpsDirectory directory = gpsDirectory.get();
                this.latitude = directory.getGeoLocation().getLatitude();
                this.longitude = directory.getGeoLocation().getLongitude();

                //System.out.println("Latitude: " + latitude);
                //System.out.println("Longitude: " + longitude);
            } else {
                System.out.println("No GPS data found in image.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}