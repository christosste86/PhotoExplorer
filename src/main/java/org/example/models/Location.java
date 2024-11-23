package org.example.models;

import javax.persistence.*;

@Entity
@Table(name = "Locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private Double latitude;
    private Double longitude;
    private String country_code;
    private String country;
    private String state;
    private String postcode;
    private String county;
    private String municipality;
    private String city;
    private String village;
    private String suburb;
    private String road;
    private String house_number;
    private String shop;
    private String tourism;

//    @OneToMany(mappedBy = "location")
//    private List<Photo> photos = new ArrayList<>();

    public Location() {
    }

//    public void setPhoto(Photo photo){
//        photos.add(photo);
//        photo.setLocation(this);
//    }

    public long getId() {
        return id;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getCountry_code() {
        return country_code;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getCounty() {
        return county;
    }

    public String getMunicipality() {
        return municipality;
    }

    public String getCity() {
        return city;
    }

    public String getSuburb() {
        return suburb;
    }

    public String getRoad() {
        return road;
    }

    public String getHouse_number() {
        return house_number;
    }

    public String getShop() {
        return shop;
    }

    public String getTourism() {
        return tourism;
    }

    public String getVillage() {
        return village;
    }

    public void setTourism(String tourism) {
        this.tourism = tourism;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public void setHouse_number(String house_number) {
        this.house_number = house_number;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }


    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", country_code='" + country_code + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", postcode='" + postcode + '\'' +
                ", county='" + county + '\'' +
                ", municipality='" + municipality + '\'' +
                ", city='" + city + '\'' +
                ", village='" + village + '\'' +
                ", suburb='" + suburb + '\'' +
                ", road='" + road + '\'' +
                ", house_number='" + house_number + '\'' +
                ", shop='" + shop + '\'' +
                ", tourism='" + tourism + '\'' +
                '}';
    }
}
