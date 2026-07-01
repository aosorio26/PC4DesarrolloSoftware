package com.petalert.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "cuidadors")
public class Cuidador {
    @Id
    private String id;
    private String fullName;
    private RolCuidador role;
    private String identityDocument;
    private boolean identityVerified;
    private String contactPhone;
    private String contactEmail;
    private RestriccionesServicio restrictions;
    private boolean receivesLostPetAlerts;
    private PuntoUbicacion location;
    private List<Resena> reviews = new ArrayList<>();
    private Instant createdAt;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public RolCuidador getRole() { return role; }
    public void setRole(RolCuidador role) { this.role = role; }
    public String getIdentityDocument() { return identityDocument; }
    public void setIdentityDocument(String identityDocument) { this.identityDocument = identityDocument; }
    public boolean isIdentityVerified() { return identityVerified; }
    public void setIdentityVerified(boolean identityVerified) { this.identityVerified = identityVerified; }
    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }
    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }
    public RestriccionesServicio getRestrictions() { return restrictions; }
    public void setRestrictions(RestriccionesServicio restrictions) { this.restrictions = restrictions; }
    public boolean isReceivesLostPetAlerts() { return receivesLostPetAlerts; }
    public void setReceivesLostPetAlerts(boolean receivesLostPetAlerts) { this.receivesLostPetAlerts = receivesLostPetAlerts; }
    public PuntoUbicacion getLocation() { return location; }
    public void setLocation(PuntoUbicacion location) { this.location = location; }
    public List<Resena> getResenas() { return reviews; }
    public void setResenas(List<Resena> reviews) { this.reviews = reviews; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public double getAverageRating() {
        if (reviews == null || reviews.isEmpty()) {
            return 0.0;
        }
        return reviews.stream().mapToInt(Resena::getRating).average().orElse(0.0);
    }
}
