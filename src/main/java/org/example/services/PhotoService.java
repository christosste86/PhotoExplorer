package org.example.services;

import org.example.connections.db.daos.GenericDao;
import org.example.models.Photo;
import org.example.connections.db.services.GenericService;
import org.example.connections.metadata.ImgMetadata;
import java.nio.file.Path;
import java.util.List;

public class PhotoService {
    private GenericDao<Photo, Long> photoDao = new GenericDao<>(Photo.class);
    private GenericService<Photo, Long> photoService = new GenericService<>(photoDao);
    private Photo photoObject = new Photo();

    //call PhotoService with path and create photo Object
    public PhotoService(Path photoPath) {
        createPhotoObjectFromPath(photoPath);
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
    private void createPhotoObjectFromPath(Path photoPath){
        ImgMetadata photoData = new ImgMetadata(photoPath.toString());
        this.photoObject.setLongitude(photoData.getLongitude());
        this.photoObject.setLatitude(photoData.getLatitude());
        this.photoObject.setImagePath(photoData.getPath());
        this.photoObject.setWidth(photoData.getWidth());
        this.photoObject.setHeight(photoData.getHeight());
        this.photoObject.setCameraModel(photoData.getModel());
        this.photoObject.setOwnerName(photoData.getCameraOwner());
        this.photoObject.setArtist(photoData.getArtist());
        this.photoObject.setBodySerialNumber(photoData.getBodySerialNumber());
        this.photoObject.setDateTime(photoData.getDateTime());
        this.photoObject.setHostComputer(photoData.getHostname());
    }

}
