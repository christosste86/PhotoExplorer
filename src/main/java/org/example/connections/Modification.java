package org.example.connections;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public interface Modification{

    //create subfolders
    default void makeSubFolders(String[] subdirectories, Path targetDirectory){
        for(String path : subdirectories){
            targetDirectory = Path.of(targetDirectory + File.separator + path);
            if(!Files.exists(targetDirectory)){
                try {
                    Files.createDirectory(targetDirectory);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
