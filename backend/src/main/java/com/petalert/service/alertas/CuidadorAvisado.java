package com.petalert.service.alertas;

import com.petalert.model.AvisoAlerta;
import com.petalert.model.Cuidador;
import com.petalert.model.ReportePerdido;

import java.time.Instant;

public class CuidadorAvisado implements DestinoAlerta {
    private final Cuidador cuidador;

    public CuidadorAvisado(Cuidador cuidador) {
        this.cuidador = cuidador;
    }

    @Override
    public AvisoAlerta recibirAviso(ReportePerdido reporte, double distanciaKm) {
        String mensaje = "Alerta cercana: " + reporte.getPetName() + " fue reporteada como mascota perdida.";
        return new AvisoAlerta(
                cuidador.getId(),
                cuidador.getFullName(),
                "PANEL_CUIDADOR",
                Math.round(distanciaKm * 100.0) / 100.0,
                Instant.now(),
                mensaje
        );
    }
}
