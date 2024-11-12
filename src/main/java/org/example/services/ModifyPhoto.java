package org.example.services;

import org.example.models.Location;
import org.example.models.Photo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

public class ModifyPhoto {
    private Path destinationSource;
    private Location locationObject;
    private Photo photoObject;

    public ModifyPhoto(Path photoPath, Path destinationSource) {
        LocationServices locationServices = new LocationServices(photoPath);
        this.destinationSource = destinationSource;
        this.locationObject = locationServices.getLocationObject();
        this.photoObject = locationServices.getPhotoObject();
        System.out.println(this.locationObject);
    }

    private String getMapLocationString(){
        return String.format("%s (%s)", this.locationObject.getCountry_code(), this.locationObject.getCounty());
    }

    private String getSeason() {
        if (this.photoObject.getDateTime().getMonthValue() == 12 && this.photoObject.getDateTime().getDayOfMonth() >= 21) {
            return "Winter";
        }
        if (this.photoObject.getDateTime().getMonthValue() >= 9 && this.photoObject.getDateTime().getDayOfMonth() >= 22) {
            return "Autumn";
        }
        if (this.photoObject.getDateTime().getMonthValue() >= 6 && this.photoObject.getDateTime().getDayOfMonth() >= 21) {
            return "Summer";
        }
        if (this.photoObject.getDateTime().getMonthValue() >= 3 && this.photoObject.getDateTime().getDayOfMonth() >= 20) {
            return "Spring";
        } else {
            return "Winter";
        }
    }

    private String getYear(){
        return this.photoObject.getDateTime().getYear() + "";
    }

    private String cityVillage(){
        if(this.locationObject.getCity() != null){
            return this.locationObject.getCity();
        }else if(this.locationObject.getVillage() != null){
            return this.locationObject.getVillage();
        }else if(this.locationObject.getMunicipality() != null){
            return this.locationObject.getMunicipality();
        }else if(this.locationObject.getCounty() != null){
            return this.locationObject.getCountry();
        }return null;
    }


    private String photographer(){
        if(this.photoObject.getOwnerName() != null){
            return this.photoObject.getOwnerName();
        } else if(this.photoObject.getHostComputer() != null){
            return this.photoObject.getHostComputer();
        } else if(this.photoObject.getCameraModel() != null){
            return this.photoObject.getCameraModel();
        }else if(this.photoObject.getBodySerialNumber()  != null){
            return this.photoObject.getBodySerialNumber();
        }return null;
    }

    private String timeFormat(){
        String dateFormat = String.format("%s.%s.%s(%s.%s.%s)",
                this.photoObject.getDateTime().getYear(),
                this.photoObject.getDateTime().getMonthValue(),
                this.photoObject.getDateTime().getDayOfMonth(),
                this.photoObject.getDateTime().getHour(),
                this.photoObject.getDateTime().getMinute(),
                this.photoObject.getDateTime().getSecond());
        return String.format("%s",dateFormat);
    }

    private <T> boolean isArrayEmpty(T[] array){
        for(T e:array){
            if(e != null){
                return false;
            }
        }return true;
    }

    private String houseNumber(){
        if (this.locationObject.getHouse_number() != null){
            return this.locationObject.getHouse_number().replace("/", "-");
        }return this.locationObject.getHouse_number();
    }

    private String shopTourism(){
        if (this.locationObject.getTourism() != null){
            return this.locationObject.getTourism();
        }if (this.locationObject.getShop() != null){
            return this.locationObject.getShop();
        }return null;
    }

    private String getPhotoName(){
        String[] details = {cityVillage(), this.locationObject.getRoad(), houseNumber(), this.locationObject.getCountry_code()};
        return String.format("%s%s%s%s%s",
                timeFormat(),
                shopTourism() == null ? "" : String.format(" %s",shopTourism()),
                photographer() == null ? "" : String.format(" (%s)",photographer()),
                isArrayEmpty(details) ? "" : String.format(" in %s", Arrays.toString(details)),
                ".jpg");

    }

    private Path photoDestinationPath(){
        return Path.of(this.destinationSource +
                File.separator +
                getYear() +
                File.separator +
                getSeason() +
                File.separator +
                getMapLocationString() +
                File.separator +
                getPhotoName());
    }

    private boolean isPathExist(Path path){
        return Files.exists(path);
    }

    private void makeSubFolders(){
        String[] subdirectories = {getYear(), getSeason(), getMapLocationString()};
        Path destinationPath = this.destinationSource;
        for(String path : subdirectories){
            destinationPath = Path.of(destinationPath + File.separator + path);
            if(!isPathExist(destinationPath)){
                try {
                    Files.createDirectory(destinationPath);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void moveFile(){
        makeSubFolders();
        try {
            Files.move(this.photoObject.getImagePath(),
                    photoDestinationPath(),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
