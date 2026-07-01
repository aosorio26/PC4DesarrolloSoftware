package com.petalert.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "sightings")
public class Avistamiento {
    @Id
    private String id;
    private String reporteId;
    private String species;
    private String breed;
    private String color;
    private String notes;
    private String finderContact;
    private PuntoUbicacion location;
    private Instant createdAt;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getReportId() { return reporteId; }
    public void setReportId(String reporteId) { this.reporteId = reporteId; }
    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }
    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public String getFinderContact() { return finderContact; }
    public void setFinderContact(String finderContact) { this.finderContact = finderContact; }
    public PuntoUbicacion getLocation() { return location; }
    public void setLocation(PuntoUbicacion location) { this.location = location; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
