package com.petalert.model;

import java.util.ArrayList;
import java.util.List;

public class RestriccionesServicio {
    private List<String> acceptedSpecies = new ArrayList<>();
    private List<String> acceptedSizes = new ArrayList<>();
    private boolean medicationAllowed;

    public List<String> getAcceptedSpecies() { return acceptedSpecies; }
    public void setAcceptedSpecies(List<String> acceptedSpecies) { this.acceptedSpecies = acceptedSpecies; }
    public List<String> getAcceptedSizes() { return acceptedSizes; }
    public void setAcceptedSizes(List<String> acceptedSizes) { this.acceptedSizes = acceptedSizes; }
    public boolean isMedicationAllowed() { return medicationAllowed; }
    public void setMedicationAllowed(boolean medicationAllowed) { this.medicationAllowed = medicationAllowed; }
}
