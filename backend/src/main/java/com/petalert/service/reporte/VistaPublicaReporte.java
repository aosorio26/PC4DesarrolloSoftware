package com.petalert.service.reporte;

import com.petalert.dto.ReportePerdidoPublicoResponse;
import com.petalert.model.ReportePerdido;
import org.springframework.stereotype.Component;

@Component
public class VistaPublicaReporte {
    public ReportePerdidoPublicoResponse generarVista(ReportePerdido reporte) {
        ReportePerdidoPublicoResponse response = new ReportePerdidoPublicoResponse();
        response.setId(reporte.getId());
        response.setPetName(reporte.getPetName());
        response.setSpecies(reporte.getSpecies());
        response.setBreed(reporte.getBreed());
        response.setColor(reporte.getColor());
        response.setDescription(reporte.getDescription());
        response.setLocation(reporte.getLocation());
        response.setAlertRadiusKm(reporte.getAlertRadiusKm());
        response.setStatus(reporte.getStatus());
        response.setCreatedAt(reporte.getCreatedAt());
        response.setNotificationsSent(reporte.getNotifications() == null ? 0 : reporte.getNotifications().size());
        response.setOwnerPrivacy("Datos del dueno ocultos. El contacto se realiza por mediacion interna del sistema.");
        return response;
    }
}
