package com.petalert.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AvistamientoRequest {
    private String reporteId;
    @NotBlank
    private String species;
    private String breed;
    private String color;
    @NotBlank
    private String notes;
    private String finderContact;
    @Valid
    @NotNull
    private UbicacionRequest location;

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
    public UbicacionRequest getLocation() { return location; }
    public void setLocation(UbicacionRequest location) { this.location = location; }
}
