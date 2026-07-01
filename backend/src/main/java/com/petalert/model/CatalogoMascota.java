package com.petalert.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "catalog_pet_listings")
public class CatalogoMascota {
    @Id
    private String id;
    private IntencionBusqueda listingType;
    private String organizationName;
    private String organizationType;
    private String contactName;
    private String contactPhone;
    private String contactEmail;
    private String petName;
    private String species;
    private String breed;
    private String color;
    private String ageDescription;
    private String description;
    private String certificationCode;
    private boolean active = true;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public IntencionBusqueda getListingType() { return listingType; }
    public void setListingType(IntencionBusqueda listingType) { this.listingType = listingType; }
    public String getOrganizationName() { return organizationName; }
    public void setOrganizationName(String organizationName) { this.organizationName = organizationName; }
    public String getOrganizationType() { return organizationType; }
    public void setOrganizationType(String organizationType) { this.organizationType = organizationType; }
    public String getContactName() { return contactName; }
    public void setContactName(String contactName) { this.contactName = contactName; }
    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }
    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }
    public String getPetName() { return petName; }
    public void setPetName(String petName) { this.petName = petName; }
    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }
    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public String getAgeDescription() { return ageDescription; }
    public void setAgeDescription(String ageDescription) { this.ageDescription = ageDescription; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getCertificationCode() { return certificationCode; }
    public void setCertificationCode(String certificationCode) { this.certificationCode = certificationCode; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
