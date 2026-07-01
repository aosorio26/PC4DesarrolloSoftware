package com.petalert.service;

import com.petalert.dto.AvistamientoRequest;
import com.petalert.model.PuntoUbicacion;
import com.petalert.model.ReportePerdido;
import com.petalert.model.Avistamiento;
import com.petalert.repository.ReportePerdidoRepository;
import com.petalert.repository.AvistamientoRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class AvistamientoService {
    private final AvistamientoRepository repository;
    private final ReportePerdidoRepository reporteRepository;
    private final UsuarioService userAccountService;

    public AvistamientoService(AvistamientoRepository repository,
                           ReportePerdidoRepository reporteRepository,
                           UsuarioService userAccountService) {
        this.repository = repository;
        this.reporteRepository = reporteRepository;
        this.userAccountService = userAccountService;
    }

    public Avistamiento create(AvistamientoRequest request) {
        Avistamiento sighting = new Avistamiento();
        sighting.setReportId(request.getReportId());
        sighting.setSpecies(request.getSpecies());
        sighting.setBreed(request.getBreed());
        sighting.setColor(request.getColor());
        sighting.setNotes(request.getNotes());
        sighting.setFinderContact(request.getFinderContact());
        sighting.setLocation(new PuntoUbicacion(
                request.getLocation().getLatitude(),
                request.getLocation().getLongitude(),
                request.getLocation().getAddressLabel()
        ));
        sighting.setCreatedAt(Instant.now());
        Avistamiento guardado = repository.save(sighting);
        notifyOwnerIfLinked(guardado);
        return guardado;
    }

    public List<Avistamiento> list() { return repository.findAll(); }

    public List<Avistamiento> listByReport(String reporteId) { return repository.findByReportId(reporteId); }

    private void notifyOwnerIfLinked(Avistamiento sighting) {
        if (sighting.getReportId() == null || sighting.getReportId().isBlank()) return;
        reporteRepository.findById(sighting.getReportId()).ifPresent(reporte -> {
            String ownerUserId = reporte.getOwnerUserId();
            if (ownerUserId == null || ownerUserId.isBlank()) return;
            String contact = sighting.getFinderContact() == null || sighting.getFinderContact().isBlank()
                    ? "El ciudadano no dejó contacto."
                    : "Contacto voluntario: " + sighting.getFinderContact();
            userAccountService.addInboxMessage(ownerUserId,
                    "Nuevo avistamiento para " + reporte.getPetName() + ". " +
                    "Referencia: " + (sighting.getLocation() == null ? "Sin referencia" : sighting.getLocation().getAddressLabel()) + ". " +
                    "Notas: " + sighting.getNotes() + ". " + contact);
        });
    }
}
