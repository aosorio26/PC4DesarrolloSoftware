package com.petalert.service.alertas;

import com.petalert.model.AvisoAlerta;
import com.petalert.model.ReportePerdido;

public interface DestinoAlerta {
    AvisoAlerta recibirAviso(ReportePerdido reporte, double distanciaKm);
}
