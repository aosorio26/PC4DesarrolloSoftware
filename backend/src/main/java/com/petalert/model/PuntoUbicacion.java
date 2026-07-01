package com.petalert.model;

public class PuntoUbicacion {
    private double latitude;
    private double longitude;
    private String addressLabel;

    public PuntoUbicacion() {
    }

    public PuntoUbicacion(double latitude, double longitude, String addressLabel) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.addressLabel = addressLabel;
    }

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

    public String getAddressLabel() {
        return addressLabel;
    }

    public void setAddressLabel(String addressLabel) {
        this.addressLabel = addressLabel;
    }
}
