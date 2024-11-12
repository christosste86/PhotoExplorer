package org.example.services;

import org.example.connections.db.daos.GenericDao;
import org.example.models.Photo;
import org.example.connections.db.services.GenericService;
import org.example.connections.photos.PhotoData;
import java.nio.file.Path;
import java.util.List;

public class PhotoService {
    private GenericDao<Photo, Long> photoDao = new GenericDao<>(Photo.class);
    private GenericService<Photo, Long> photoService = new GenericService<>(photoDao);
    private Photo photoObject = new Photo();

    //call PhotoService with path and create photo Object
    public PhotoService(Path photoPath) {
        this.photoObject = createPhotoObjectFromPath(photoPath);
    }

    public Photo getPhotoObject() {
        return photoObject;
    }

    //save photoObject to DB table
    public void saveToDB(){
        if(!isDuplicateObjectInDB()){
            photoService.save(this.photoObject);
        }

    }

    private boolean isDuplicateObjectInDB(){
        if(filteredListByPath().isEmpty()){
            return false;
        }else {
            return true;
        }
    }

    private List<Photo> filteredListByPath(){
        return photoService.getAll()
                .stream()
                .filter(o->o.getImagePath().equals(photoObject.getImagePath())).toList();
    }

    //delete photoObject from DB table
    public void deleteFromDB(){
        photoService.delete(this.photoObject);
    }

    //update photoObject to DB table
    public void updateToDB(){
        photoService.update(this.photoObject);
    }

    //get photoObject from DB by id
    public void getByIdFromDB(long id){
        photoService.getById(id);
    }

    //create photoObject from photo path
    private Photo createPhotoObjectFromPath(Path photoPath){
        PhotoData photoData = new PhotoData(photoPath.toString());
        Photo photoObject = new Photo();
        photoObject.setLongitude(photoData.getLongitude());
        photoObject.setLatitude(photoData.getLatitude());
        photoObject.setImagePath(photoData.getPath().toString());
        photoObject.setWidth(photoData.getWidth());
        photoObject.setHeight(photoData.getHeight());
        photoObject.setCameraModel(photoData.getCameraOwner());
        photoObject.setOwnerName(photoData.getCameraOwner());
        photoObject.setArtist(photoData.getArtist());
        photoObject.setBodySerialNumber(photoData.getBodySerialNumber());
        photoObject.setDateTime(photoData.getDateTime());
        photoObject.setHostComputer(photoData.getHostname());
        return photoObject;
    }

    private boolean checkDuplicatePhotoObject(){
        return false;
    }

}
