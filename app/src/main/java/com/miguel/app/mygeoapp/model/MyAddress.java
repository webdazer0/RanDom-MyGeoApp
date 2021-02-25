package com.miguel.app.mygeoapp.model;

public class MyAddress {
    private String latitude;
    private String longitude;
    private String address;
    private int ID;

    public MyAddress(String latitude, String longitude, String address, int ID) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.ID = ID;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
