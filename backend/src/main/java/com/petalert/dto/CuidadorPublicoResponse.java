package com.petalert.dto;

import com.petalert.model.RolCuidador;
import com.petalert.model.PuntoUbicacion;
import com.petalert.model.RestriccionesServicio;

public class CuidadorPublicoResponse {
    private String id;
    private String fullName;
    private RolCuidador role;
    private boolean identityVerified;
    private RestriccionesServicio restrictions;
    private boolean receivesLostPetAlerts;
    private PuntoUbicacion location;
    private double averageRating;
    private boolean contactVisible;
    private String contactPhone;
    private String contactEmail;
    private String contactMessage;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public RolCuidador getRole() { return role; }
    public void setRole(RolCuidador role) { this.role = role; }
    public boolean isIdentityVerified() { return identityVerified; }
    public void setIdentityVerified(boolean identityVerified) { this.identityVerified = identityVerified; }
    public RestriccionesServicio getRestrictions() { return restrictions; }
    public void setRestrictions(RestriccionesServicio restrictions) { this.restrictions = restrictions; }
    public boolean isReceivesLostPetAlerts() { return receivesLostPetAlerts; }
    public void setReceivesLostPetAlerts(boolean receivesLostPetAlerts) { this.receivesLostPetAlerts = receivesLostPetAlerts; }
    public PuntoUbicacion getLocation() { return location; }
    public void setLocation(PuntoUbicacion location) { this.location = location; }
    public double getAverageRating() { return averageRating; }
    public void setAverageRating(double averageRating) { this.averageRating = averageRating; }
    public boolean isContactVisible() { return contactVisible; }
    public void setContactVisible(boolean contactVisible) { this.contactVisible = contactVisible; }
    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }
    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }
    public String getContactMessage() { return contactMessage; }
    public void setContactMessage(String contactMessage) { this.contactMessage = contactMessage; }
}
