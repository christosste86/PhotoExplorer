package org.example;

import org.example.connections.ApiLocation;
import org.example.connections.PhotoExplorer;
import org.example.connections.photos.PhotoDetails;
import org.example.connections.db.daos.GenericDao;
import org.example.connections.db.models.Location;
import org.example.connections.db.services.GenericService;

public class Main {
    public static void main(String[] args) {
        GenericDao<Location, Long> LocationDao = new GenericDao<>(Location.class);
        GenericService<Location, Long> LocationService = new GenericService<>(LocationDao);


//        FtpConnect motorolaConnect = new FtpConnect("192.168.0.198",2221, "christos", "android");
        PhotoExplorer motorolaPhone = new PhotoExplorer("Z:\\BackUp\\Photos\\2024\\Spring");
        motorolaPhone.getPhotoFilesList().forEach(img -> {
            PhotoDetails photoDetails = new PhotoDetails(img.toString());
            if(photoDetails.getLatitude() > 0) {
                System.out.println(photoDetails.getLatitude());
                System.out.println(photoDetails.getLongitude());
                ApiLocation geocode = new ApiLocation(photoDetails.getLatitude(), photoDetails.getLongitude());

                System.out.println(geocode.getCountry());
                Location location = new Location();
                location.setCountry(geocode.getCountry());
                location.setState(geocode.getState());
                location.setCounty(geocode.getCounty());
                location.setMunicipality(geocode.getMunicipality());
                location.setCity(geocode.getCity());
                location.setSuburb(geocode.getSuburb());
                location.setLatitude(geocode.getLatitude());
                location.setLongitude(geocode.getLongitude());
                location.setCountry_code(geocode.getCountry_code());
                location.setHouse_number(geocode.getHouse_number());
                location.setPostcode(geocode.getPostcode());
                location.setRoad(geocode.getRoad());
                location.setShop(geocode.getShop());

                LocationService.save(location);
            }
        });
    }


//    public static boolean getQuery(double latitude, double Longitude){
//        boolean exists = false;
//        // Open Hibernate session
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            // Create a query to count occurrences of the name
//            Query<Long> query = session.createQuery(
//                    "SELECT COUNT(l.id) FROM Location l WHERE l.latitude = :latitude AND l.longitude = :longitude", Long.class);
//            query.setParameter("latitude", latitude);
//            query.setParameter("longitude", longitude);
//
//            // Get the result (count of records with the given name)
//            Long count = query.uniqueResult();
//            exists = (count != null && count > 0);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return exists;
//    }

    public static void sendToMySqlLocationAndPathDetails(){

    }
}