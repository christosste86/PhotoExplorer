package org.example.services;

import org.example.connections.api.GeocodeService;
import org.example.connections.db.daos.GenericDao;
import org.example.models.Location;
import org.example.connections.db.services.GenericService;
import org.example.models.Photo;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LocationServices {
    private GenericDao<Location, Long> locationDao = new GenericDao<>(Location.class);
    private GenericService<Location, Long> locationService = new GenericService<>(locationDao);
    private Location locationObject;
    private Photo photoObject;

    public LocationServices(Path photoPath) {
        PhotoService photoService = new PhotoService(photoPath);
        this.photoObject = photoService.getPhotoObject();
        GeocodeService geocodeService = new GeocodeService(photoObject.getLatitude(), photoObject.getLongitude());
        this.locationObject = geocodeService.getLocationObject();
    }

    public Location getLocationObject() {
        return locationObject;
    }

    public Photo getPhotoObject() {
        return photoObject;
    }

    private boolean isDuplicateObjectInDB(){
        return !filteredLocationList().isEmpty();
    }

    private List<Location> filteredLocationList(){
        try {
            return locationService.getAll()
                    .stream()
                    .filter(l -> l.getLatitude() == this.locationObject.getLatitude() && l.getLongitude() == this.locationObject.getLongitude())
                    .toList();
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

    private Location locationFromDB(){
        if(isDuplicateObjectInDB()){
            return filteredLocationList().get(0);
        }return this.locationObject;
    }

    public void saveToDB(){
        if(!isDuplicateObjectInDB()){
            locationService.save(this.locationObject);
        }
    }

    public void updateToDB(){
        locationService.update(this.locationObject);
    }

    public void deleteFromDB(){
        locationService.delete(this.locationObject);
    }

    public void getByIDFromDB(long id){
        locationService.getById(id);
    }
}
