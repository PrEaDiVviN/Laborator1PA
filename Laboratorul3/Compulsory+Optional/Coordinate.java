package com.company;

public class Coordinate {
    private double longitude;
    private double latitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Coordinate(double longitude, double latitude) {
        setLatitude(latitude);
        setLongitude(longitude);
    }

    public Coordinate() {
    }
}
