package org.example.services;

import org.example.models.Location;
import org.example.models.Photo;

import java.nio.file.Path;
import java.time.LocalDate;

public class MovePhoto {
    private Path destinationPath;
    private String year;
    private String season;
    private String mapLocation;
    private String photoName;

    private String getMapLocationString(Location locatio){
        return String.format("%s(%s)", locatio.getCountry_code(), locatio.getCounty());
    }

    private String getSeason(LocalDate localDate) {
        if (localDate.getMonthValue() == 12 && localDate.getDayOfMonth() >= 21) {
            return "Winter";
        }
        if (localDate.getMonthValue() >= 9 && localDate.getDayOfMonth() >= 22) {
            return "Autumn";
        }
        if (localDate.getMonthValue() >= 6 && localDate.getDayOfMonth() >= 21) {
            return "Summer";
        }
        if (localDate.getMonthValue() >= 3 && localDate.getDayOfMonth() >= 20) {
            return "Spring";
        } else {
            return "Winter";
        }
    }

    private String getYear(LocalDate localDate){
        return localDate.getYear() + "";
    }

    private String cityVillage(Location location){
        if(!location.getCity().isEmpty()){
            return location.getCity();
        }else if(!location.getVillage().isEmpty()){
            return location.getVillage();
        }else if(!location.getMunicipality().isEmpty()){
            return location.getMunicipality();
        }else if(!location.getCounty().isEmpty()){
            return location.getCountry();
        }return null;
    }

    private String getPhotoName(Location location){
        return String.format("(Brno, Vídeňská 815/89a, Albert)");
    }
}
