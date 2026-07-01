package com.petalert.controller;

import com.petalert.dto.CuidadorPublicoResponse;
import com.petalert.dto.CuidadorRequest;
import com.petalert.dto.ResenaRequest;
import com.petalert.model.Cuidador;
import com.petalert.service.CuidadorService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cuidadores")
@CrossOrigin(origins = "*")
public class CuidadorController {
    private final CuidadorService service;

    public CuidadorController(CuidadorService service) {
        this.service = service;
    }

    @PostMapping
    public Cuidador create(@Valid @RequestBody CuidadorRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<CuidadorPublicoResponse> list(@RequestParam(required = false) String viewerUserId) {
        return service.list(viewerUserId);
    }

    @PatchMapping("/{id}/alertas")
    public Cuidador toggleAlerts(@PathVariable String id, @RequestParam boolean enabled) {
        return service.toggleAlerts(id, enabled);
    }

    @PostMapping("/{id}/resenas")
    public Cuidador addResena(@PathVariable String id, @Valid @RequestBody ResenaRequest request) {
        return service.addResena(id, request);
    }
}
