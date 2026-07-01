package com.petalert.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "lost_pet_reportes")
public class ReportePerdido {
    @Id
    private String id;
    private String petName;
    private String species;
    private String breed;
    private String color;
    private String description;
    private String ownerName;
    private String ownerContact;
    private String ownerUserId;
    private PuntoUbicacion location;
    private double alertRadiusKm;
    private EstadoReporte status;
    private Instant createdAt;
    private List<AvisoAlerta> avisos = new ArrayList<>();

    public ReportePerdido() {
    }

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
    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
    public String getOwnerContact() { return ownerContact; }
    public void setOwnerContact(String ownerContact) { this.ownerContact = ownerContact; }
    public String getOwnerUserId() { return ownerUserId; }
    public void setOwnerUserId(String ownerUserId) { this.ownerUserId = ownerUserId; }
    public PuntoUbicacion getLocation() { return location; }
    public void setLocation(PuntoUbicacion location) { this.location = location; }
    public double getAlertRadiusKm() { return alertRadiusKm; }
    public void setAlertRadiusKm(double alertRadiusKm) { this.alertRadiusKm = alertRadiusKm; }
    public EstadoReporte getStatus() { return status; }
    public void setStatus(EstadoReporte status) { this.status = status; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
    public List<AvisoAlerta> getNotifications() { return avisos; }
    public void setNotifications(List<AvisoAlerta> avisos) { this.avisos = avisos; }
}
