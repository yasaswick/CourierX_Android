package com.courierx.courierx;

public class TrackInfo {
    String date;
    String location;

    public TrackInfo() {
    }

    public TrackInfo(String date, String location) {
        this.date = date;
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

