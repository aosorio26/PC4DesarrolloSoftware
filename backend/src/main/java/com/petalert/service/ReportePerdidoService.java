package com.petalert.service;

import com.petalert.dto.ReportePerdidoPublicoResponse;
import com.petalert.dto.ReportePerdidoRequest;
import com.petalert.model.AvisoAlerta;
import com.petalert.model.ReportePerdido;
import com.petalert.model.EstadoReporte;
import com.petalert.service.reporte.ArmadorReportePerdido;
import com.petalert.service.reporte.VistaPublicaReporte;
import com.petalert.service.ubicacion.FuenteUbicacion;
import com.petalert.repository.ReportePerdidoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportePerdidoService {
    private final ReportePerdidoRepository repository;
    private final AlertasService alertasService;
    private final VistaPublicaReporte vistaPublicaReporte;
    private final FuenteUbicacion locationProvider;
    private final double defaultRadiusKm;

    public ReportePerdidoService(ReportePerdidoRepository repository,
                                AlertasService alertasService,
                                VistaPublicaReporte vistaPublicaReporte,
                                FuenteUbicacion locationProvider,
                                @Value("${app.alert.default-radius-km:1.0}") double defaultRadiusKm) {
        this.repository = repository;
        this.alertasService = alertasService;
        this.vistaPublicaReporte = vistaPublicaReporte;
        this.locationProvider = locationProvider;
        this.defaultRadiusKm = defaultRadiusKm;
    }

    public ReportePerdidoPublicoResponse create(ReportePerdidoRequest request) {
        ReportePerdido reporte = new ArmadorReportePerdido()
                .fromRequest(request, defaultRadiusKm)
                .activeNow()
                .build();

        reporte.getLocation().setAddressLabel(locationProvider.describe(reporte.getLocation()));
        List<AvisoAlerta> avisos = alertasService.distribuir(reporte);
        reporte.setNotifications(avisos);
        ReportePerdido guardado = repository.save(reporte);
        return vistaPublicaReporte.generarVista(guardado);
    }

    public List<ReportePerdidoPublicoResponse> listActive() {
        return repository.findByStatus(EstadoReporte.ACTIVA).stream()
                .map(vistaPublicaReporte::generarVista)
                .toList();
    }

    public ReportePerdidoPublicoResponse getPublic(String id) {
        ReportePerdido reporte = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reporte no encontrado"));
        return vistaPublicaReporte.generarVista(reporte);
    }

    public ReportePerdidoPublicoResponse resolve(String id) {
        ReportePerdido reporte = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reporte no encontrado"));
        reporte.setStatus(EstadoReporte.RESUELTA);
        return vistaPublicaReporte.generarVista(repository.save(reporte));
    }
}
