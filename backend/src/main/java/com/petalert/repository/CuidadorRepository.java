package com.petalert.repository;

import com.petalert.model.Cuidador;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CuidadorRepository extends MongoRepository<Cuidador, String> {
    List<Cuidador> findByReceivesLostPetAlertsTrueAndIdentityVerifiedTrue();
}
