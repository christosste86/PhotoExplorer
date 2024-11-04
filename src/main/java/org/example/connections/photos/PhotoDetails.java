package org.example.connections.photos;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.GpsDirectory;
import com.drew.metadata.jpeg.JpegDirectory;
import org.example.connections.db.daos.GenericDao;
import org.example.connections.db.models.Location;
import org.example.connections.db.models.Photo;
import org.example.connections.db.services.GenericService;

import java.io.File;
import java.util.Optional;

public class PhotoDetails {

    private String imagePath;
    private double latitude;
    private double longitude;
    private Photo photo;
    private GenericDao<Photo, Long> photoDao = new GenericDao<>(Photo.class);
    private GenericService<Photo, Long> photoService = new GenericService<>(photoDao);

    public PhotoDetails(String imagePath) {
        this.imagePath = imagePath;
        this.photo = new Photo();
        getDetails();
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    private void getDetails() {
        File imageFile = new File(this.imagePath); // Replace with your image path

        try {
            Metadata metadata = ImageMetadataReader.readMetadata(imageFile);
            JpegDirectory jpegDirectory = metadata.getFirstDirectoryOfType(JpegDirectory.class);
            ExifIFD0Directory exifDirectory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
            Optional<GpsDirectory> gpsDirectory = metadata.getDirectoriesOfType(GpsDirectory.class).stream().findFirst();
            if (jpegDirectory != null) {
                this.photo.setWidth(jpegDirectory.getImageWidth());
                this.photo.setHeight(jpegDirectory.getImageHeight());
                this.photo.setImagePath(this.imagePath);
                if(exifDirectory.containsTag(ExifIFD0Directory.TAG_MODEL)){
                    this.photo.setCameraModel(exifDirectory.getString(ExifIFD0Directory.TAG_MODEL));
                }
                if(exifDirectory.containsTag(ExifIFD0Directory.TAG_CAMERA_OWNER_NAME)) {
                    this.photo.setOwnerName(exifDirectory.getString(ExifIFD0Directory.TAG_CAMERA_OWNER_NAME));
                }
                if(exifDirectory.containsTag(ExifIFD0Directory.TAG_ARTIST)) {
                    this.photo.setArtist(exifDirectory.getString(ExifIFD0Directory.TAG_ARTIST));
                }
                if(exifDirectory.containsTag(ExifIFD0Directory.TAG_BODY_SERIAL_NUMBER)) {
                    this.photo.setBodySerialNumber(exifDirectory.getString(ExifIFD0Directory.TAG_BODY_SERIAL_NUMBER));
                }
                if(exifDirectory.containsTag(ExifIFD0Directory.TAG_DATETIME_ORIGINAL)) {
                    this.photo.setDateTime(exifDirectory.getString(ExifIFD0Directory.TAG_DATETIME_ORIGINAL));
                } else if (exifDirectory.containsTag(ExifIFD0Directory.TAG_DATETIME)) {
                    this.photo.setDateTime(exifDirectory.getString(ExifIFD0Directory.TAG_DATETIME));
                } else if (exifDirectory.containsTag(ExifIFD0Directory.TAG_DATETIME_DIGITIZED)) {
                    this.photo.setDateTime(exifDirectory.getString(ExifIFD0Directory.TAG_DATETIME_DIGITIZED));
                }
                if(exifDirectory.containsTag(ExifIFD0Directory.TAG_HOST_COMPUTER)) {this.photo.setHostComputer(exifDirectory.getString(ExifIFD0Directory.TAG_HOST_COMPUTER));
                }
            }
            if (gpsDirectory.isPresent()) {
                GpsDirectory directory = gpsDirectory.get();
                this.latitude = directory.getGeoLocation().getLatitude();
                this.longitude = directory.getGeoLocation().getLongitude();
            } else {
                System.out.println("No GPS data found in image.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void savePhotoDetails(){
        System.out.println(this.photo);
        this.photoService.save(this.photo);
    }
}