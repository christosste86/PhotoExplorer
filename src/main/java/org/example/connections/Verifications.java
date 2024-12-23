package org.example.connections;

import java.nio.file.Files;
import java.nio.file.Path;

public interface Verifications {

    //check if file is image type
    default boolean isImgFile(String fileName){
        String[] imgTypes = {"jpg", "jpeg", "heic"};
        for (String type : imgTypes) {
            if (fileName.toLowerCase().endsWith(type)) {
                return true;
            }
        }return false;
    }

    //check if file is Video type
    default boolean isVideoFile(String ftpFileName) {
        String[] videoTypes = {"avi", "mpg", "mpeg", "mkv", "ts", "mov", "webm", "flv", "wmv", "3gp", "hevc", "h265", "av1", "asf", "ogv"};
        for(String type: videoTypes){
            if(ftpFileName.toLowerCase().endsWith(type)){
                return true;
            }
        }return false;
    }

    default <T> boolean isArrayEmpty(T[] array){
        for(T e:array){
            if(e != null){
                return false;
            }
        }return true;
    }

    default boolean isPathExist(Path path){
        return Files.exists(path);
    }

    //check if Photo include coordinates
    default boolean isLocated(Double latitude, Double longitude){
        if(latitude == null && longitude == null){
            return false;
        }else return true;
    }


}
