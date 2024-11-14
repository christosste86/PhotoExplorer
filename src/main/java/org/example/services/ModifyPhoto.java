package org.example.services;

import org.example.connections.FtpClient;
import org.example.connections.JsonFile;
import org.example.connections.Modification;
import org.example.connections.Verifications;
import org.example.models.Location;
import org.example.models.Photo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

public class ModifyPhoto implements Verifications, Modification {
    JsonFile jsonFile = new JsonFile();
    private Path targetDirectory = Path.of(jsonFile.getFileExplorerTargetDirectory());
    private Location locationObject;
    private Photo photoObject;
    private final String[] subDirectories = {getYear()+"", getSeason(), getMapLocationString()};

    public ModifyPhoto() {
    }

    public ModifyPhoto(Path photoPath, Path destinationSource) {
        LocationServices locationServices = new LocationServices(photoPath);
        this.targetDirectory = destinationSource;
        this.locationObject = locationServices.getLocationObject();
        this.photoObject = locationServices.getPhotoObject();
        System.out.println(this.locationObject);
    }

    //output looks like cz (Jihomoravský kraj)
    private String getMapLocationString(){
        return String.format("%s (%s)", this.locationObject.getCountry_code(), this.locationObject.getCounty());
    }

    //output season of date  Winter, Autumn, Summer, Spring
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

    //get year of date
    private int getYear(){
        return this.photoObject.getDateTime().getYear();
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

    //get information about who take the photo
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

    //get time 2024.3.24(20.16.6) by date
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

    //get house number format: 265-89b
    private String houseNumber(){
        if (this.locationObject.getHouse_number() != null){
            return this.locationObject.getHouse_number().replace("/", "-");
        }return this.locationObject.getHouse_number();
    }

    //get if the position is in Shop or touristic place
    private String shopTourism(){
        if (this.locationObject.getTourism() != null){
            return this.locationObject.getTourism();
        }if (this.locationObject.getShop() != null){
            return this.locationObject.getShop();
        }return null;
    }

    //get finally name if coordinates are not null
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
        return Path.of(this.targetDirectory +
                File.separator +
                getYear() +
                File.separator +
                getSeason() +
                File.separator +
                getMapLocationString() +
                File.separator +
                getPhotoName());
    }

    //move and rename fileName by location and photoDetails
    public void moveFile(){
        makeSubFolders(this.subDirectories, this.targetDirectory);
        try {
            Files.move(this.photoObject.getImagePath(),
                    photoDestinationPath(),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //copy rename fileName by location and photoDetails
    public void copyFile(){
        makeSubFolders(this.subDirectories, this.targetDirectory);
        try {
            Files.copy(this.photoObject.getImagePath(),
                    photoDestinationPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

    }
}
