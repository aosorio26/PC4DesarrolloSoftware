package com.petalert.repository;

import com.petalert.model.Avistamiento;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AvistamientoRepository extends MongoRepository<Avistamiento, String> {
    List<Avistamiento> findByReportId(String reporteId);
}
