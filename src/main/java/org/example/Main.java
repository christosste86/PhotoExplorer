package org.example;

import org.example.connections.db.daos.GenericDao;
import org.example.models.Location;
import org.example.connections.db.services.GenericService;
import org.example.services.LocationServices;
import org.example.services.PhotoService;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {

//        FtpConnect motorolaConnect = new FtpConnect("192.168.0.198",2221, "christos", "android");
        Path myPhotoPath = Path.of("U:\\BackUp\\Photos\\2024\\Spring\\okres Brno-město\\3.24(33)_Brno_Vídeňská.jpg");
        PhotoService myPhoto = new PhotoService(myPhotoPath);
        LocationServices myPhotoLocation = new LocationServices(myPhotoPath);
    }
}