package org.example.services;

import org.example.connections.FileExplorer;
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
import java.util.stream.Stream;

public class ModifyPhoto implements Verifications, Modification {
    private final JsonFile jsonFile = new JsonFile();
    private final Path targetDirectory = Path.of(jsonFile.getFileExplorerTargetDirectory());
    private Path photoPath;
    private Location locationObject = new Location();
    private Photo photoObject = new Photo();

    public ModifyPhoto() {
    }

    public ModifyPhoto(Path photoPath) {
        this.photoPath = photoPath;
        PhotoService photoService = new PhotoService(this.photoPath);
        LocationServices locationServices = new LocationServices(this.photoPath);
        this.photoObject = photoService.getPhotoObject();
        this.locationObject = locationServices.getLocationObject();
    }



    public Photo getPhotoObject() {
        return photoObject;
    }

    //output format (Jihomoravský kraj)
    private String getMapLocationString(){
        return String.format("%s (%s)",
                countryCode(),
                this.locationObject.getCounty());
    }

    private String countryCode() {
        if (this.locationObject.getCountry_code() != null) {
            return this.locationObject.getCountry_code();
        } else if (this.locationObject.getCountry() != null) {
            return this.locationObject.getCountry();
        }return "";
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

    //choose a location by city, village, municipality, county
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
    private String owner(){
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
    private String getFileLocatedName(){
        return String.format("%s%s%s%s%s",
                timeFormat(),
                shopTourism() == null ? "" : String.format(" %s",shopTourism()),
                owner() == null ? "" : String.format(" (%s)",owner()),
                isArrayEmpty(addressDetails()) ? "" : String.format(" in [%s]", address()),
                getExtension());
    }

    private String[] addressDetails(){
        return new String[] {cityVillage(), this.locationObject.getRoad(), houseNumber(), this.locationObject.getCountry()};
    }

    private String address(){
        String address = "";
        for (int i = 0; i < addressDetails().length; i++) {
            if(addressDetails()[i] == null){
                address += "";
            }
            else if (addressDetails()[i] != null && i < addressDetails().length - 1) {
                address += addressDetails()[i] + ", ";
            } else{
                address += addressDetails()[addressDetails().length - 1];
            }
        }
        return address.toString();
    }

    //get extension of file
    private String getExtension(){
        String pathName = this.photoObject.getImagePath().toString();
        int lastDot = pathName.lastIndexOf(".");
        if(lastDot != -1){
            return pathName.substring(lastDot);
        }return null;
    }

    //get finally name if coordinates are null
    private String getFileUnplacedName(){
        return String.format("%s%s%s",
                timeFormat(),
                owner() == null ? "" : String.format("-%s",owner()),
                getExtension());
    }


    //Generate path from two strings[]
    private Path fileDestinationPath(String[] sourceSubDirectories){
        String filePath = "";
        for (int i = 0; i < sourceSubDirectories.length; i++) {
                filePath += sourceSubDirectories[i] + File.separator;
        }return Path.of(filePath);
    }

    private String[] locatedSubDirectories() {
        return new String[] {
                this.targetDirectory.toString(),
                String.valueOf(getYear()),
                getSeason(),
                getMapLocationString(),
                this.locationObject.getMunicipality(),
                getFileLocatedName()};
    }
    private String[] unplacedSubDirectories() {
        return new String[] {
                this.targetDirectory.toString(),
                String.valueOf(getYear()),
                getSeason(),
                "unplaced",
                getFileUnplacedName()};
    }

    private String[] getSubFoldersOfPath() {
        if(isLocated(photoObject.getLatitude(), photoObject.getLongitude())){
            return locatedSubDirectories();
        }else return unplacedSubDirectories();
    }

    //create subfolders
    private void makeSubFolders(){
        String path = "";
        for(String folder: getSubFoldersOfPath()){
            path += folder;
            if(!Files.exists(Path.of(path))){
                try {
                    Files.createDirectory(Path.of(path));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            path += File.separator;
        }
    }

    //generate located Photo path
    private Path photoLocatedDestinationPath(){
        return fileDestinationPath(this.locatedSubDirectories());
    }

    //Generate unplaced Photo path
    private Path photoUnplacedDestinationPath(){
        return fileDestinationPath(this.unplacedSubDirectories());
    }

    public void moveFile(){
        makeSubFolders();
        moveFile(this.photoObject.getImagePath(),
                this.photoObject.getLatitude(),
                this.photoObject.getLongitude(),
                photoLocatedDestinationPath(),
                photoUnplacedDestinationPath());
    }

    public static void main(String[] args) {
        FileExplorer fileExplorer = new FileExplorer();
        fileExplorer.getListOfPhotosFiles().forEach(photo ->{
            ModifyPhoto modifyPhoto = new ModifyPhoto(photo);
            modifyPhoto.moveFile();
        });
    }
}
