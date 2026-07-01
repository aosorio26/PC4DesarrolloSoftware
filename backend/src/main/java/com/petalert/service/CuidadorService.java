package com.petalert.service;

import com.petalert.dto.CuidadorPublicoResponse;
import com.petalert.dto.CuidadorRequest;
import com.petalert.dto.ResenaRequest;
import com.petalert.model.Cuidador;
import com.petalert.model.PuntoUbicacion;
import com.petalert.model.Resena;
import com.petalert.model.RestriccionesServicio;
import com.petalert.model.Usuario;
import com.petalert.service.validacion.RevisionCuidador;
import com.petalert.repository.CuidadorRepository;
import com.petalert.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class CuidadorService {
    private final CuidadorRepository repository;
    private final UsuarioRepository userAccountRepository;
    private final RevisionCuidador validationPipeline;

    public CuidadorService(CuidadorRepository repository,
                            UsuarioRepository userAccountRepository,
                            RevisionCuidador validationPipeline) {
        this.repository = repository;
        this.userAccountRepository = userAccountRepository;
        this.validationPipeline = validationPipeline;
    }

    public Cuidador create(CuidadorRequest request) {
        Cuidador cuidador = new Cuidador();
        cuidador.setFullName(request.getFullName());
        cuidador.setRole(request.getRole());
        cuidador.setIdentityDocument(request.getIdentityDocument());
        cuidador.setContactPhone(request.getContactPhone());
        cuidador.setContactEmail(request.getContactEmail());
        cuidador.setReceivesLostPetAlerts(request.isReceivesLostPetAlerts());
        cuidador.setCreatedAt(Instant.now());
        cuidador.setLocation(new PuntoUbicacion(
                request.getLocation().getLatitude(),
                request.getLocation().getLongitude(),
                request.getLocation().getAddressLabel()
        ));

        RestriccionesServicio restrictions = new RestriccionesServicio();
        restrictions.setAcceptedSpecies(request.getAcceptedSpecies());
        restrictions.setAcceptedSizes(request.getAcceptedSizes());
        restrictions.setMedicationAllowed(request.isMedicationAllowed());
        cuidador.setRestrictions(restrictions);

        validationPipeline.create().validate(cuidador);
        cuidador.setIdentityVerified(true);
        return repository.save(cuidador);
    }

    public List<CuidadorPublicoResponse> list(String viewerUserId) {
        boolean canSeeContact = isVerifiedUser(viewerUserId);
        return repository.findAll().stream()
                .map(cuidador -> toPublicResponse(cuidador, canSeeContact))
                .toList();
    }

    public Cuidador toggleAlerts(String id, boolean enabled) {
        Cuidador cuidador = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cuidador no encontrado"));
        cuidador.setReceivesLostPetAlerts(enabled);
        return repository.save(cuidador);
    }

    public Cuidador addResena(String id, ResenaRequest request) {
        Cuidador cuidador = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cuidador no encontrado"));
        cuidador.getResenas().add(new Resena(
                request.getOwnerName(),
                request.getRating(),
                request.getComment(),
                Instant.now()
        ));
        return repository.save(cuidador);
    }

    private boolean isVerifiedUser(String viewerUserId) {
        if (viewerUserId == null || viewerUserId.isBlank()) return false;
        Optional<Usuario> user = userAccountRepository.findById(viewerUserId);
        return user.map(Usuario::isIdentityVerified).orElse(false);
    }

    private CuidadorPublicoResponse toPublicResponse(Cuidador cuidador, boolean canSeeContact) {
        CuidadorPublicoResponse response = new CuidadorPublicoResponse();
        response.setId(cuidador.getId());
        response.setFullName(cuidador.getFullName());
        response.setRole(cuidador.getRole());
        response.setIdentityVerified(cuidador.isIdentityVerified());
        response.setRestrictions(cuidador.getRestrictions());
        response.setReceivesLostPetAlerts(cuidador.isReceivesLostPetAlerts());
        response.setLocation(cuidador.getLocation());
        response.setAverageRating(cuidador.getAverageRating());
        response.setContactVisible(canSeeContact);
        if (canSeeContact) {
            response.setContactPhone(cuidador.getContactPhone());
            response.setContactEmail(cuidador.getContactEmail());
            response.setContactMessage("Contacto disponible para usuario verificado.");
        } else {
            response.setContactMessage("Inicia sesion con una cuenta verificada para ver el contacto.");
        }
        return response;
    }
}
