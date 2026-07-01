package com.petalert.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReportePerdidoRequest {
    @NotBlank
    private String petName;
    @NotBlank
    private String species;
    private String breed;
    private String color;
    @NotBlank
    private String description;
    @NotBlank
    private String ownerName;
    @NotBlank
    private String ownerContact;
    private String ownerUserId;
    private Double alertRadiusKm;
    @Valid
    @NotNull
    private UbicacionRequest location;

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
    public Double getAlertRadiusKm() { return alertRadiusKm; }
    public void setAlertRadiusKm(Double alertRadiusKm) { this.alertRadiusKm = alertRadiusKm; }
    public UbicacionRequest getLocation() { return location; }
    public void setLocation(UbicacionRequest location) { this.location = location; }
}
