package com.petalert.repository;

import com.petalert.model.ReportePerdido;
import com.petalert.model.EstadoReporte;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReportePerdidoRepository extends MongoRepository<ReportePerdido, String> {
    List<ReportePerdido> findByStatus(EstadoReporte status);
    List<ReportePerdido> findBySpeciesIgnoreCaseAndStatus(String species, EstadoReporte status);
}
