package com.petalert.dto;

import com.petalert.model.RolCuidador;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CuidadorRequest {
    @NotBlank
    private String fullName;
    @NotNull
    private RolCuidador role;
    @NotBlank
    private String identityDocument;
    @NotBlank
    private String contactPhone;
    private String contactEmail;
    private List<String> acceptedSpecies = new ArrayList<>();
    private List<String> acceptedSizes = new ArrayList<>();
    private boolean medicationAllowed;
    private boolean receivesLostPetAlerts;
    @Valid
    @NotNull
    private UbicacionRequest location;

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public RolCuidador getRole() { return role; }
    public void setRole(RolCuidador role) { this.role = role; }
    public String getIdentityDocument() { return identityDocument; }
    public void setIdentityDocument(String identityDocument) { this.identityDocument = identityDocument; }
    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }
    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }
    public List<String> getAcceptedSpecies() { return acceptedSpecies; }
    public void setAcceptedSpecies(List<String> acceptedSpecies) { this.acceptedSpecies = acceptedSpecies; }
    public List<String> getAcceptedSizes() { return acceptedSizes; }
    public void setAcceptedSizes(List<String> acceptedSizes) { this.acceptedSizes = acceptedSizes; }
    public boolean isMedicationAllowed() { return medicationAllowed; }
    public void setMedicationAllowed(boolean medicationAllowed) { this.medicationAllowed = medicationAllowed; }
    public boolean isReceivesLostPetAlerts() { return receivesLostPetAlerts; }
    public void setReceivesLostPetAlerts(boolean receivesLostPetAlerts) { this.receivesLostPetAlerts = receivesLostPetAlerts; }
    public UbicacionRequest getLocation() { return location; }
    public void setLocation(UbicacionRequest location) { this.location = location; }
}
