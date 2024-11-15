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

    default String[] getSubFoldersOfPath(Path path){
        List<String> subFolders = new ArrayList<>();
        Path parts = Paths.get(path.toString());
        parts.forEach(part -> {
            if(Files.isDirectory(part)){
                subFolders.add(part.toString());
            }
        });
        return subFolders.toArray(subFolders.toArray(new String[0]));
    }


    //create subfolders
    default void makeSubFolders(Path fullPathFile){
        String subDirectories = null;
        for (int i = 0; i < getSubFoldersOfPath(fullPathFile).length; i++) {
            if(i < getSubFoldersOfPath(fullPathFile).length-1) {
                subDirectories = subDirectories = getSubFoldersOfPath(fullPathFile)[i];
                if (!Files.exists(Path.of(subDirectories))) {
                    try {
                        Files.createDirectory(Path.of(subDirectories));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }


    //choose photo destination path if photo is located or unplaced
    default Path destinationPath(Double latitude, Double longitude, Path locatedPath, Path unplacedPath){
        if(isLocated(latitude, longitude)){
            return locatedPath;
        }return unplacedPath;
    }

    //move and rename fileName by location and photoDetails
    default void moveFile(Path filePath, Double latitude, Double longitude, Path locatedPath, Path unplacedPath){
        Path destinationPath = destinationPath(latitude,longitude,locatedPath,unplacedPath);
        System.out.println(destinationPath);
        makeSubFolders(destinationPath);
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
        makeSubFolders(destinationPath);
        try {
            Files.copy(filePath,
                    destinationPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

