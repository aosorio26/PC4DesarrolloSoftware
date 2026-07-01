package com.petalert.service.ubicacion;

import com.petalert.model.PuntoUbicacion;
import org.springframework.stereotype.Component;

@Component
public class UbicacionMapa implements FuenteUbicacion {
    @Override
    public String describe(PuntoUbicacion point) {
        if (point == null) {
            return "Ubicacion no definida";
        }
        if (point.getAddressLabel() != null && !point.getAddressLabel().isBlank()) {
            return point.getAddressLabel();
        }
        return "Coordenadas seleccionadas en el mapa: " + point.getLatitude() + ", " + point.getLongitude();
    }
}
