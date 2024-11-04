package org.example.connections.db.models;

import javax.persistence.*;

@Entity
@Table(name = "Photos")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String imagePath;
    private int width;
    private int height;
    private String cameraModel;
    private String ownerName;
    private String Artist;
    private String bodySerialNumber;
    private String dateTime;
    private String hostComputer;

    public Photo() {
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setImagePath(String imagePath) {
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

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setHostComputer(String hostComputer) {
        this.hostComputer = hostComputer;
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
