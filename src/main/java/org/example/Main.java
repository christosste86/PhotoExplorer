package org.example;

import org.example.connections.FtpConnect;
import org.example.services.ModifyPhoto;

import java.io.File;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {

        FtpConnect motorolaConnect = new FtpConnect("192.168.1.13",2221, "christos", "android");
        motorolaConnect.connect();
        motorolaConnect.listFiles();
        motorolaConnect.workingDirectory("DCIM");
//        Path myPhotoPath = Path.of("U:\\BackUp\\Photos\\2024\\Spring\\okres Brno-město\\2024.3.24-20.0.13..(moto g71 5G) in [okres Brno-město, null, null, null].jpg");
//        Path destinationSource =  Path.of("C:\\Users\\chris\\Pictures\\organizator");
//
//        ModifyPhoto modifyPhoto = new ModifyPhoto(myPhotoPath,destinationSource);
//        modifyPhoto.moveFile();
//        LocationServices myPhotoLocation = new LocationServices(myPhotoPath);
    }
}