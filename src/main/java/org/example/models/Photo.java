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
    private Integer width;
    private Integer height;
    private String cameraModel;
    private String ownerName;
    private String artist;
    private String bodySerialNumber;
    private LocalDateTime dateTime;
    private String hostComputer;
    private Double latitude;
    private Double longitude;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    public Photo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Path getImagePath() {
        return imagePath;
    }

    public void setImagePath(Path imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getCameraModel() {
        return cameraModel;
    }

    public void setCameraModel(String cameraModel) {
        this.cameraModel = cameraModel;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getBodySerialNumber() {
        return bodySerialNumber;
    }

    public void setBodySerialNumber(String bodySerialNumber) {
        this.bodySerialNumber = bodySerialNumber;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getHostComputer() {
        return hostComputer;
    }

    public void setHostComputer(String hostComputer) {
        this.hostComputer = hostComputer;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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
