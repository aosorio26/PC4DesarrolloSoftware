package com.petalert.service.busqueda;

import com.petalert.dto.BusquedaRequest;
import com.petalert.dto.ResultadoBusqueda;
import com.petalert.model.CatalogoMascota;
import com.petalert.model.IntencionBusqueda;
import com.petalert.repository.CatalogoMascotaRepository;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class BuscarEnAdopciones implements OperacionBusquedaMascota {
    private final CatalogoMascotaRepository repository;

    public BuscarEnAdopciones(CatalogoMascotaRepository repository) {
        this.repository = repository;
    }

    @Override
    public String nombreFuente() {
        return "Catalogo de adopcion";
    }

    @Override
    public List<ResultadoBusqueda> buscar(BusquedaRequest request) {
        return repository.findByListingTypeAndActiveTrue(IntencionBusqueda.ADOPCION).stream()
                .filter(item -> coincide(item.getSpecies(), request.getSpecies()))
                .filter(item -> coincide(item.getBreed(), request.getBreed()))
                .filter(item -> coincide(item.getColor(), request.getColor()))
                .map(item -> aResultado(item, 0.88))
                .sorted(Comparator.comparingDouble(ResultadoBusqueda::getScore).reversed())
                .toList();
    }

    private ResultadoBusqueda aResultado(CatalogoMascota item, double puntajeBase) {
        String detalle = item.getDescription() + " Edad: " + item.getAgeDescription() + ".";
        return new ResultadoBusqueda(
                item.getOrganizationName(),
                item.getPetName(),
                item.getSpecies(),
                item.getBreed(),
                item.getColor(),
                detalle,
                "Contacto de la organizacion disponible para adopcion responsable.",
                item.getOrganizationName(),
                item.getOrganizationType(),
                item.getContactName(),
                item.getContactPhone(),
                item.getContactEmail(),
                puntaje(item, puntajeBase)
        );
    }

    private double puntaje(CatalogoMascota item, double puntajeBase) {
        double puntaje = puntajeBase;
        if (item.getBreed() != null && !item.getBreed().isBlank()) puntaje += 0.04;
        if (item.getColor() != null && !item.getColor().isBlank()) puntaje += 0.03;
        return Math.min(puntaje, 0.95);
    }

    private boolean coincide(String value, String query) {
        return query == null || query.isBlank() || value == null || value.toLowerCase().contains(query.trim().toLowerCase());
    }
}
