package com.petalert.dto;

import com.petalert.model.PuntoUbicacion;
import com.petalert.model.EstadoReporte;

import java.time.Instant;

public class ReportePerdidoPublicoResponse {
    private String id;
    private String petName;
    private String species;
    private String breed;
    private String color;
    private String description;
    private PuntoUbicacion location;
    private double alertRadiusKm;
    private EstadoReporte status;
    private Instant createdAt;
    private int avisosSent;
    private String ownerPrivacy;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getPetName() { return petName; }
    public void setPetName(String petName) { this.petName = petName; }
    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }
    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public PuntoUbicacion getLocation() { return location; }
    public void setLocation(PuntoUbicacion location) { this.location = location; }
    public double getAlertRadiusKm() { return alertRadiusKm; }
    public void setAlertRadiusKm(double alertRadiusKm) { this.alertRadiusKm = alertRadiusKm; }
    public EstadoReporte getStatus() { return status; }
    public void setStatus(EstadoReporte status) { this.status = status; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
    public int getNotificationsSent() { return avisosSent; }
    public void setNotificationsSent(int avisosSent) { this.avisosSent = avisosSent; }
    public String getOwnerPrivacy() { return ownerPrivacy; }
    public void setOwnerPrivacy(String ownerPrivacy) { this.ownerPrivacy = ownerPrivacy; }
}
