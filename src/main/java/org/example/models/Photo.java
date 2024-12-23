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

    private Path imagePath = null;
    private Integer width = null;
    private Integer height = null;
    private String cameraModel = null;
    private String ownerName = null;
    private String artist = null;
    private String bodySerialNumber = null;
    private LocalDateTime dateTime = null;
    private String hostComputer = null;
    private Double latitude = null;
    private Double longitude = null;

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
        return artist;
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

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public void setHeight(Integer height) {
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
        artist = artist;
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

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
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
                ", Artist='" + artist + '\'' +
                ", bodySerialNumber='" + bodySerialNumber + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", hostComputer='" + hostComputer + '\'' +
                '}';
    }
}
