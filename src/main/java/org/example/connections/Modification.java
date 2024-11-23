package org.example.connections;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public interface Modification extends Verifications{

    //choose photo destination path if photo is located or unplaced
    default Path destinationPath(Double latitude, Double longitude, Path locatedPath, Path unplacedPath){
        if(isLocated(latitude, longitude)){
            return locatedPath;
        }return unplacedPath;
    }

    //move and rename fileName by location and photoDetails
    default void moveFile(Path filePath, Double latitude, Double longitude, Path locatedPath, Path unplacedPath){
        Path destinationPath = destinationPath(latitude,longitude,locatedPath,unplacedPath);
        try {
            Files.move(filePath,
                    destinationPath,
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //copy and rename fileName by location and photoDetails
    default void copyFile(Path filePath, Double latitude, Double longitude, Path locatedPath, Path unplacedPath){
        Path destinationPath = destinationPath(latitude,longitude,locatedPath,unplacedPath);
        try {
            Files.copy(filePath,
                    destinationPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

