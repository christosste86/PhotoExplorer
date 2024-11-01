package org.example;

import org.example.connections.ApiLocation;
import org.example.connections.FtpConnect;
import org.example.connections.PhotoExplorer;
import org.example.connections.PhotoLocation;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        FtpConnect motorolaConnect = new FtpConnect("192.168.0.198",2221, "christos", "android");
        PhotoExplorer motorolaPhone = new PhotoExplorer("U:\\BackUp\\Photos\\2024\\Spring");
        ApiLocation geocode = new ApiLocation();
        motorolaPhone.getPhotoFiles().forEach(img -> {
            PhotoLocation photoDetails = new PhotoLocation(img.toString());
            geocode.setLatitude(photoDetails.getLatitude());
            geocode.setLongitude(photoDetails.getLongitude());
            geocode.generateLocation();
        });
    }
}