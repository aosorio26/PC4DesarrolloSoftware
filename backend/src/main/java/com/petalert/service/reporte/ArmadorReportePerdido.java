package com.petalert.service.reporte;

import com.petalert.dto.ReportePerdidoRequest;
import com.petalert.model.PuntoUbicacion;
import com.petalert.model.ReportePerdido;
import com.petalert.model.EstadoReporte;

import java.time.Instant;

public class ArmadorReportePerdido {
    private final ReportePerdido reporte = new ReportePerdido();

    public ArmadorReportePerdido fromRequest(ReportePerdidoRequest request, double defaultRadiusKm) {
        reporte.setPetName(request.getPetName());
        reporte.setSpecies(request.getSpecies());
        reporte.setBreed(request.getBreed());
        reporte.setColor(request.getColor());
        reporte.setDescription(request.getDescription());
        reporte.setOwnerName(request.getOwnerName());
        reporte.setOwnerContact(request.getOwnerContact());
        reporte.setOwnerUserId(request.getOwnerUserId());
        reporte.setAlertRadiusKm(request.getAlertRadiusKm() == null ? defaultRadiusKm : request.getAlertRadiusKm());
        reporte.setLocation(new PuntoUbicacion(
                request.getLocation().getLatitude(),
                request.getLocation().getLongitude(),
                request.getLocation().getAddressLabel()
        ));
        return this;
    }

    public ArmadorReportePerdido activeNow() {
        reporte.setStatus(EstadoReporte.ACTIVA);
        reporte.setCreatedAt(Instant.now());
        return this;
    }

    public ReportePerdido build() {
        return reporte;
    }
}
