package org.example.services;

import org.example.connections.api.GeocodeService;
import org.example.connections.db.daos.GenericDao;
import org.example.models.Location;
import org.example.connections.db.services.GenericService;
import org.example.connections.photos.PhotoData;

import java.nio.file.Path;

public class LocationServices {
    private GenericDao<Location, Long> locationDao = new GenericDao<>(Location.class);
    private GenericService<Location, Long> locationService = new GenericService<>(locationDao);
    private Location locationObject = new Location();

    public LocationServices(Path photoPath) {
        PhotoData photoData = new PhotoData(photoPath.toString());
        GeocodeService geocodeService = new GeocodeService(photoData.getLatitude(), photoData.getLongitude());
    }

    public void saveToDB(){
        locationService.save(this.locationObject);
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
