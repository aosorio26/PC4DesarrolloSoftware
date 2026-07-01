package com.petalert.dto;

import com.petalert.model.IntencionBusqueda;
import jakarta.validation.constraints.NotNull;

public class BusquedaRequest {
    @NotNull
    private IntencionBusqueda intent;
    private String species;
    private String breed;
    private String color;
    private UbicacionRequest location;

    public IntencionBusqueda getIntent() { return intent; }
    public void setIntent(IntencionBusqueda intent) { this.intent = intent; }
    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }
    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public UbicacionRequest getLocation() { return location; }
    public void setLocation(UbicacionRequest location) { this.location = location; }
}
