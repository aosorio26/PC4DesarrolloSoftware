package com.petalert.service.alertas;

import com.petalert.model.AvisoAlerta;
import com.petalert.model.ReportePerdido;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GestorAlertas {
    public List<AvisoAlerta> notificar(ReportePerdido reporte, List<DestinoConDistancia> destinos) {
        List<AvisoAlerta> avisos = new ArrayList<>();
        for (DestinoConDistancia item : destinos) {
            avisos.add(item.destino().recibirAviso(reporte, item.distanciaKm()));
        }
        return avisos;
    }

    public record DestinoConDistancia(DestinoAlerta destino, double distanciaKm) {
    }
}
