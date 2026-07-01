package com.petalert.repository;

import com.petalert.model.CatalogoMascota;
import com.petalert.model.IntencionBusqueda;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CatalogoMascotaRepository extends MongoRepository<CatalogoMascota, String> {
    List<CatalogoMascota> findByListingTypeAndActiveTrue(IntencionBusqueda listingType);
}
