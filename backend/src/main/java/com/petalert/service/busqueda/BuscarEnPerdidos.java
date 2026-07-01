package com.petalert.service.busqueda;

import com.petalert.dto.BusquedaRequest;
import com.petalert.dto.ResultadoBusqueda;
import com.petalert.model.ReportePerdido;
import com.petalert.model.EstadoReporte;
import com.petalert.repository.ReportePerdidoRepository;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class BuscarEnPerdidos implements OperacionBusquedaMascota {
    private final ReportePerdidoRepository repository;

    public BuscarEnPerdidos(ReportePerdidoRepository repository) {
        this.repository = repository;
    }

    @Override
    public String nombreFuente() {
        return "Alertas activas";
    }

    @Override
    public List<ResultadoBusqueda> buscar(BusquedaRequest request) {
        return repository.findByStatus(EstadoReporte.ACTIVA).stream()
                .filter(reporte -> coincide(reporte.getSpecies(), request.getSpecies()))
                .filter(reporte -> coincide(reporte.getBreed(), request.getBreed()))
                .filter(reporte -> coincide(reporte.getColor(), request.getColor()))
                .map(this::aResultado)
                .sorted(Comparator.comparingDouble(ResultadoBusqueda::getScore).reversed())
                .toList();
    }

    private ResultadoBusqueda aResultado(ReportePerdido reporte) {
        double score = 0.70;
        if (reporte.getBreed() != null && !reporte.getBreed().isBlank()) {
            score += 0.12;
        }
        if (reporte.getColor() != null && !reporte.getColor().isBlank()) {
            score += 0.10;
        }
        return new ResultadoBusqueda(
                "Alertas activas",
                reporte.getPetName(),
                reporte.getSpecies(),
                reporte.getBreed(),
                reporte.getColor(),
                reporte.getDescription(),
                "Datos del dueno anonimizados",
                Math.min(score, 0.95)
        );
    }

    private boolean coincide(String value, String query) {
        return query == null || query.isBlank() || value == null || value.equalsIgnoreCase(query.trim());
    }
}
