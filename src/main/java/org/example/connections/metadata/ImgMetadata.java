package org.example.connections.metadata;

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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ImgMetadata {

    private String imagePath;

    public ImgMetadata(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


    private Metadata metadata(){
        try {
            return ImageMetadataReader.readMetadata(new File(this.imagePath));
        } catch (ImageProcessingException | IOException e) {
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
            if (metadata().getFirstDirectoryOfType(GpsDirectory.class).getTagCount() > 0) {
                return gpsDirectory().getGeoLocation().getLatitude();
            }
        }return null;
    }

    public Double getLongitude(){
        if (gpsDirectory() !=null){
            if (metadata().getFirstDirectoryOfType(GpsDirectory.class).getTagCount() > 0){
                return gpsDirectory().getGeoLocation().getLongitude();
            }
        }return null;
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
        }return null;
    }

    public String getCameraOwner(){
        if(exifIFD0Directory().containsTag(ExifIFD0Directory.TAG_CAMERA_OWNER_NAME)) {
            return exifIFD0Directory().getString(ExifIFD0Directory.TAG_CAMERA_OWNER_NAME);
        }return null;
    }

    public String getArtist(){
        if(exifIFD0Directory().containsTag(ExifIFD0Directory.TAG_ARTIST)) {
            return exifIFD0Directory().getString(ExifIFD0Directory.TAG_ARTIST);
        }return null;
    }

    public String getBodySerialNumber(){
        if(exifIFD0Directory().containsTag(ExifIFD0Directory.TAG_BODY_SERIAL_NUMBER)) {
            return exifIFD0Directory().getString(ExifIFD0Directory.TAG_BODY_SERIAL_NUMBER);
        }return null;
    }

    public LocalDateTime  getDateTimeOriginal(){
        if(exifIFD0Directory().containsTag(ExifIFD0Directory.TAG_DATETIME_ORIGINAL)) {
            Date date = exifIFD0Directory().getDate(ExifIFD0Directory.TAG_DATETIME_ORIGINAL);
            return date.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
        }return null;
    }


    public LocalDateTime getDateTime(){
        if(exifIFD0Directory().containsTag(ExifIFD0Directory.TAG_DATETIME)) {
            Date date = exifIFD0Directory().getDate(ExifIFD0Directory.TAG_DATETIME);
            return date.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
        }return null;
    }

    public LocalDateTime getDateTimeDigital(){
        if(exifIFD0Directory().containsTag(ExifIFD0Directory.TAG_DATETIME_DIGITIZED)) {
            Date date = exifIFD0Directory().getDate(ExifIFD0Directory.TAG_DATETIME_DIGITIZED);
            return date.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
        }return null;
    }

    public String getHostname(){
        if(exifIFD0Directory().containsTag(ExifIFD0Directory.TAG_HOST_COMPUTER)) {
            return exifIFD0Directory().getString(ExifIFD0Directory.TAG_HOST_COMPUTER);
        }return null;
    }
}