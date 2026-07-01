package com.petalert.service.ubicacion;

import com.petalert.model.PuntoUbicacion;
import org.springframework.stereotype.Component;

@Component
public class CalculadoraDistancia {
    private static final double EARTH_RADIUS_KM = 6371.0;

    public double distanciaKm(PuntoUbicacion a, PuntoUbicacion b) {
        if (a == null || b == null) {
            return Double.MAX_VALUE;
        }
        double latDistance = Math.toRadians(b.getLatitude() - a.getLatitude());
        double lonDistance = Math.toRadians(b.getLongitude() - a.getLongitude());
        double inicioLat = Math.toRadians(a.getLatitude());
        double endLat = Math.toRadians(b.getLatitude());
        double h = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(inicioLat) * Math.cos(endLat)
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        return 2 * EARTH_RADIUS_KM * Math.atan2(Math.sqrt(h), Math.sqrt(1 - h));
    }
}
