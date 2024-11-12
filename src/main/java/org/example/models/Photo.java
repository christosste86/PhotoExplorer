package org.example.models;

import org.example.connections.db.services.GenericService;
import org.example.services.LocationServices;

import javax.persistence.*;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Photos")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Path imagePath;
    private int width;
    private int height;
    private String cameraModel;
    private String ownerName;
    private String Artist;
    private String bodySerialNumber;
    private LocalDateTime dateTime;
    private String hostComputer;
    private double latitude;
    private double longitude;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    public Photo() {
    }

    public long getId() {
        return id;
    }

    public String getCameraModel() {
        return cameraModel;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getArtist() {
        return Artist;
    }

    public String getBodySerialNumber() {
        return bodySerialNumber;
    }

    public String getHostComputer() {
        return hostComputer;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Path getImagePath() {
        return imagePath;
    }

    public void setLocation(Location location){
        this.location = location;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setImagePath(Path imagePath) {
        this.imagePath = imagePath;
    }

    public void setCameraModel(String cameraModel) {
        this.cameraModel = cameraModel;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setArtist(String artist) {
        Artist = artist;
    }

    public void setBodySerialNumber(String bodySerialNumber) {
        this.bodySerialNumber = bodySerialNumber;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setHostComputer(String hostComputer) {
        this.hostComputer = hostComputer;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", imagePath='" + imagePath + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", cameraModel='" + cameraModel + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", Artist='" + Artist + '\'' +
                ", bodySerialNumber='" + bodySerialNumber + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", hostComputer='" + hostComputer + '\'' +
                '}';
    }
}
