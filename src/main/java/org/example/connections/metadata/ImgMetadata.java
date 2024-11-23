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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class ImgMetadata {

    private final Path imagePath;

    public ImgMetadata(Path imagePath) {
        this.imagePath = imagePath;
    }

    private Metadata metadata(){
        try {
            return ImageMetadataReader.readMetadata(Files.newInputStream(imagePath));
        } catch (ImageProcessingException | IOException e) {
            return null;
        }
    }

    public Path getImagePath() {
        return imagePath;
    }

    private JpegDirectory jpegDirectory(){
        if(metadata() == null){
            return null;
        }
        return metadata().getFirstDirectoryOfType(JpegDirectory.class);
    }

    private ExifIFD0Directory exifIFD0Directory (){
        if(metadata() == null){
            return null;
        }
        return metadata().getFirstDirectoryOfType(ExifIFD0Directory.class);
    }

    private GpsDirectory gpsDirectory() {
        if(metadata() == null){
            return null;
        }
        return metadata().getFirstDirectoryOfType(GpsDirectory.class);
    }

    public Double getLatitude(){
        if(gpsDirectory() == null){
            return null;
        }if(Objects.requireNonNull(gpsDirectory()).getTagCount() <= 0){
            return null;
        }return Objects.requireNonNull(gpsDirectory()).getGeoLocation().getLatitude();
    }

    public Double getLongitude(){
        if(gpsDirectory() == null) {
            return null;
        }if(Objects.requireNonNull(gpsDirectory()).getTagCount() <= 0){
            return null;
        }
        return Objects.requireNonNull(gpsDirectory()).getGeoLocation().getLongitude();
    }

    public Integer getWidth(){
        try {
            if (jpegDirectory() == null) {
                return null;
            }return jpegDirectory().getImageWidth();
        } catch (MetadataException e) {
            return null;
        }
    }

    public Integer getHeight(){
        try {
            if (jpegDirectory() == null) {
                return null;
            }return jpegDirectory().getImageHeight();
        } catch (MetadataException e) {
            return null;
        }
    }

    public String getModel(){
        if(exifIFD0Directory() == null){
            return null;
        }
        if(exifIFD0Directory().containsTag(ExifIFD0Directory.TAG_MODEL)){
            return exifIFD0Directory().getString(ExifIFD0Directory.TAG_MODEL);
        }return null;
    }

    public String getCameraOwner(){
        if(exifIFD0Directory() == null){
            return null;
        }
        if(exifIFD0Directory().containsTag(ExifIFD0Directory.TAG_CAMERA_OWNER_NAME)) {
            return exifIFD0Directory().getString(ExifIFD0Directory.TAG_CAMERA_OWNER_NAME);
        }return null;
    }

    public String getArtist(){
        if(exifIFD0Directory() == null){
            return null;
        }
        if(exifIFD0Directory().containsTag(ExifIFD0Directory.TAG_ARTIST)) {
            return exifIFD0Directory().getString(ExifIFD0Directory.TAG_ARTIST);
        }return null;
    }

    public String getBodySerialNumber(){
        if(exifIFD0Directory() == null){
            return null;
        }
        if(exifIFD0Directory().containsTag(ExifIFD0Directory.TAG_BODY_SERIAL_NUMBER)) {
            return exifIFD0Directory().getString(ExifIFD0Directory.TAG_BODY_SERIAL_NUMBER);
        }return null;
    }

    public LocalDateTime  getDateTimeOriginal(){
        if(exifIFD0Directory() == null){
            return null;
        }
        if(exifIFD0Directory().containsTag(ExifIFD0Directory.TAG_DATETIME_ORIGINAL)) {
            Date date = exifIFD0Directory().getDate(ExifIFD0Directory.TAG_DATETIME_ORIGINAL);
            return date.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
        }return null;
    }

    public LocalDateTime getModificationDate() {
        try {
            FileTime modificationDate = Files.getLastModifiedTime(this.imagePath);
            Instant instant = modificationDate.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            return LocalDateTime.ofInstant(instant, zoneId);
        } catch (IOException e) {
            return null;
        }
    }

    public LocalDateTime getDateTime(){
        if(exifIFD0Directory() == null){
            return null;
        }
        if(exifIFD0Directory().containsTag(ExifIFD0Directory.TAG_DATETIME)) {
            Date date = exifIFD0Directory().getDate(ExifIFD0Directory.TAG_DATETIME);
            return date.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
        }return null;
    }

    public LocalDateTime getDateTimeDigital(){
        if(exifIFD0Directory() == null){
            return null;
        }
        if(exifIFD0Directory().containsTag(ExifIFD0Directory.TAG_DATETIME_DIGITIZED)) {
            Date date = exifIFD0Directory().getDate(ExifIFD0Directory.TAG_DATETIME_DIGITIZED);
            return date.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
        }return null;
    }

    public String getHostname(){
        if(exifIFD0Directory() == null){
            return null;
        }
        if(exifIFD0Directory().containsTag(ExifIFD0Directory.TAG_HOST_COMPUTER)) {
            return exifIFD0Directory().getString(ExifIFD0Directory.TAG_HOST_COMPUTER);
        }return null;
    }
}