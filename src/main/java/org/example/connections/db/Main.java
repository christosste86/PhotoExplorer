package org.example.connections.db;


import org.example.connections.db.daos.GenericDao;
import org.example.connections.db.models.Location;
import org.example.connections.db.services.GenericService;

public class Main {
    public static void main(String[] args) {

        GenericDao<Location, Long> locationDao = new GenericDao<>(Location.class);
        GenericService<Location, Long> locationService = new GenericService<>(locationDao);

        Location photoLocation = new Location();

        locationService.save(photoLocation);

        locationService.update(photoLocation);
        System.out.println(locationService.getById(1L));

        //birdService.delete(bird);
    }
}

