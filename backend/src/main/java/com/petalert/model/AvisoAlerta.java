package com.petalert.model;

import java.time.Instant;

public class AvisoAlerta {
    private String targetUserId;
    private String targetName;
    private String channel;
    private double distanciaKm;
    private Instant sentAt;
    private String mensaje;

    public AvisoAlerta() {
    }

    public AvisoAlerta(String targetUserId, String targetName, String channel, double distanciaKm, Instant sentAt, String mensaje) {
        this.targetUserId = targetUserId;
        this.targetName = targetName;
        this.channel = channel;
        this.distanciaKm = distanciaKm;
        this.sentAt = sentAt;
        this.mensaje = mensaje;
    }

    public String getTargetUserId() { return targetUserId; }
    public void setTargetUserId(String targetUserId) { this.targetUserId = targetUserId; }
    public String getTargetName() { return targetName; }
    public void setTargetName(String targetName) { this.targetName = targetName; }
    public String getChannel() { return channel; }
    public void setChannel(String channel) { this.channel = channel; }
    public double getDistanceKm() { return distanciaKm; }
    public void setDistanceKm(double distanciaKm) { this.distanciaKm = distanciaKm; }
    public Instant getSentAt() { return sentAt; }
    public void setSentAt(Instant sentAt) { this.sentAt = sentAt; }
    public String getMessage() { return mensaje; }
    public void setMessage(String mensaje) { this.mensaje = mensaje; }
}
