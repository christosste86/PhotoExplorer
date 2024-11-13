package org.example;

import org.example.connections.FtpConnect;
import org.example.connections.PhotoExplorer;
import org.example.services.ModifyPhoto;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {

        //Ftp Connect
        FtpConnect motorolaConnect = new FtpConnect("192.168.1.13",2221, "christos", "android");
        motorolaConnect.connect();
        ModifyPhoto ftpModifyPhoto = new ModifyPhoto();

        Path destinationSource =  Path.of("U:\\BackUp\\Photos");
        motorolaConnect.downloadFiles("/DCIM/Camera",destinationSource);
        motorolaConnect.disconnect();






//        LocationServices myPhotoLocation = new LocationServices(myPhotoPath);
    }
}