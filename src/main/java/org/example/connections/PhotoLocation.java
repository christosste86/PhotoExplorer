package org.example.connections;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.GpsDirectory;

import java.io.File;
import java.util.Optional;

public class PhotoLocation {
    public static void main(String[] args) {
        File imageFile = new File("U:\\BackUp\\Photos\\2024\\Spring\\okres Brno-město\\3.24(33)_Brno_Vídeňská.jpg"); // Replace with your image path

        try {
            Metadata metadata = ImageMetadataReader.readMetadata(imageFile);
            Optional<GpsDirectory> gpsDirectory = metadata.getDirectoriesOfType(GpsDirectory.class).stream().findFirst();

            if (gpsDirectory.isPresent()) {
                GpsDirectory directory = gpsDirectory.get();
                double latitude = directory.getGeoLocation().getLatitude();
                double longitude = directory.getGeoLocation().getLongitude();

                System.out.println("Latitude: " + latitude);
                System.out.println("Longitude: " + longitude);
            } else {
                System.out.println("No GPS data found in image.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}