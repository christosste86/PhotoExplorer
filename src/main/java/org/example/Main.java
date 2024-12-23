package org.example;

import org.example.connections.FileExplorer;
import org.example.connections.FtpClient;
import org.example.services.ModifyPhoto;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        FileExplorer photosDirectory = new FileExplorer();
        photosDirectory.getListOfPhotosFiles().forEach(photoPath -> {
            ModifyPhoto modifyPhoto = new ModifyPhoto(photoPath);
        });
    }
}