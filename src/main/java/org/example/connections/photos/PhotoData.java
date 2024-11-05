package org.example.connections.photos;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.GpsDirectory;
import com.drew.metadata.jpeg.JpegDirectory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class PhotoData {

    private String imagePath;

    public PhotoData(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


    private Metadata metadata(){
        try {
            return ImageMetadataReader.readMetadata(new File(this.imagePath));
        } catch (ImageProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private JpegDirectory jpegDirectory(){
        return metadata().getFirstDirectoryOfType(JpegDirectory.class);
    }

    private ExifIFD0Directory exifIFD0Directory (){
        return metadata().getFirstDirectoryOfType(ExifIFD0Directory.class);
    }

    private GpsDirectory gpsDirectory() {
        return metadata().getFirstDirectoryOfType(GpsDirectory.class);
    }

    public Path getPath(){
        return Path.of(this.imagePath);
    }

    public Double getLatitude(){
        if (gpsDirectory() != null){
            return gpsDirectory().getGeoLocation().getLatitude();
        }else{
            return null;
        }
    }

    public Double getLongitude(){
        if (gpsDirectory() != null){
            return gpsDirectory().getGeoLocation().getLongitude();
        }else{
            return null;
        }
    }

    public Integer getWidth(){
        try {
            if (jpegDirectory() != null) {
                return jpegDirectory().getImageWidth();
            }
        } catch (MetadataException e) {
            throw new RuntimeException(e);
        }return null;
    }

    public Integer getHeight(){
        try {
            if (jpegDirectory() != null) {
                return jpegDirectory().getImageHeight();
            }
        } catch (MetadataException e) {
            throw new RuntimeException(e);
        }return null;
    }

    public String getModel(){
        if(exifIFD0Directory().containsTag(ExifIFD0Directory.TAG_MODEL)){
            return exifIFD0Directory().getString(ExifIFD0Directory.TAG_MODEL);
        }else{
            return null;
        }
    }

    public String getCameraOwner(){
        if(exifIFD0Directory().containsTag(ExifIFD0Directory.TAG_CAMERA_OWNER_NAME)) {
            return exifIFD0Directory().getString(ExifIFD0Directory.TAG_CAMERA_OWNER_NAME);
        }else{
            return null;
        }
    }

    public String getArtist(){
        if(exifIFD0Directory().containsTag(ExifIFD0Directory.TAG_ARTIST)) {
            return exifIFD0Directory().getString(ExifIFD0Directory.TAG_ARTIST);
        }else{
            return null;
        }
    }

    public String getBodySerialNumber(){
        if(exifIFD0Directory().containsTag(ExifIFD0Directory.TAG_BODY_SERIAL_NUMBER)) {
            return exifIFD0Directory().getString(ExifIFD0Directory.TAG_BODY_SERIAL_NUMBER);
        }else{
            return null;
        }
    }

    public String getDateTimeOriginal(){
        if(exifIFD0Directory().containsTag(ExifIFD0Directory.TAG_DATETIME_ORIGINAL)) {
            return exifIFD0Directory().getString(ExifIFD0Directory.TAG_DATETIME_ORIGINAL);
        }else{
            return null;
        }
    }

    public String getDateTime(){
        if(exifIFD0Directory().containsTag(ExifIFD0Directory.TAG_DATETIME)) {
            return exifIFD0Directory().getString(ExifIFD0Directory.TAG_DATETIME);
        }else{
            return null;
        }
    }

    public String getDateTimeDigital(){
        if(exifIFD0Directory().containsTag(ExifIFD0Directory.TAG_DATETIME_DIGITIZED)) {
            return exifIFD0Directory().getString(ExifIFD0Directory.TAG_DATETIME_DIGITIZED);
        }else{
            return null;
        }
    }

    public String getHostname(){
        if(exifIFD0Directory().containsTag(ExifIFD0Directory.TAG_HOST_COMPUTER)) {
            return exifIFD0Directory().getString(ExifIFD0Directory.TAG_HOST_COMPUTER);
        }else{
            return null;
        }
    }
}