package com.petalert.dto;

public class ResultadoBusqueda {
    private String source;
    private String title;
    private String species;
    private String breed;
    private String color;
    private String detail;
    private String contactPolicy;
    private String organizationName;
    private String organizationType;
    private String contactName;
    private String contactPhone;
    private String contactEmail;
    private double score;

    public ResultadoBusqueda() {
    }

    public ResultadoBusqueda(String source, String title, String species, String breed, String color, String detail, String contactPolicy, double score) {
        this(source, title, species, breed, color, detail, contactPolicy, null, null, null, null, null, score);
    }

    public ResultadoBusqueda(String source, String title, String species, String breed, String color, String detail, String contactPolicy,
                        String organizationName, String organizationType, String contactName, String contactPhone, String contactEmail, double score) {
        this.source = source;
        this.title = title;
        this.species = species;
        this.breed = breed;
        this.color = color;
        this.detail = detail;
        this.contactPolicy = contactPolicy;
        this.organizationName = organizationName;
        this.organizationType = organizationType;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
        this.contactEmail = contactEmail;
        this.score = score;
    }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }
    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public String getDetail() { return detail; }
    public void setDetail(String detail) { this.detail = detail; }
    public String getContactPolicy() { return contactPolicy; }
    public void setContactPolicy(String contactPolicy) { this.contactPolicy = contactPolicy; }
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
    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }
}
