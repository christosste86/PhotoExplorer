package org.example.services;

import org.example.connections.api.GeocodeService;
import org.example.connections.db.daos.GenericDao;
import org.example.models.Location;
import org.example.connections.db.services.GenericService;
import org.example.connections.photos.PhotoData;
import org.example.models.Photo;

import java.nio.file.Path;
import java.util.List;

public class LocationServices {
    private GenericDao<Location, Long> locationDao = new GenericDao<>(Location.class);
    private GenericService<Location, Long> locationService = new GenericService<>(locationDao);
    private Location locationObject = new Location();
    private Photo photoObject = new Photo();

    public LocationServices(Location location){
    }

    public LocationServices(Path photoPath) {
        PhotoService photoService = new PhotoService(photoPath);
        this.photoObject = photoService.getPhotoObject();
        System.out.println(photoObject.getLatitude() +" "+ photoObject.getLongitude());
        GeocodeService geocodeService = new GeocodeService(photoObject.getLatitude(), photoObject.getLongitude());
        this.locationObject = geocodeService.getLocation();
        this.photoObject.setLocation(saveToDBAndGetObject());
         //make condition if path exist
    }

    private boolean isDuplicateObject(double longitude, double latitude){
        if(filteredLocationByLonAndLat(longitude, latitude).isEmpty()){
            return false;
        }else return true;
    }

    private List<Location> filteredLocationByLonAndLat(double longitude, double latitude){
        return locationService.getAll()
                .stream()
                .filter(o-> o.getLongitude() == longitude && o.getLatitude() == latitude)
                .toList();
    }

    public Location saveToDBAndGetObject(){
        if (!isDuplicateObject(locationObject.getLongitude(), locationObject.getLatitude())){
            locationService.save(this.locationObject);
            return locationObject;
        }else return filteredLocationByLonAndLat(locationObject.getLongitude(), locationObject.getLatitude()).get(0);
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
