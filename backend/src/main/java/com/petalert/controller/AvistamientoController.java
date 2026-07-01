package com.petalert.controller;

import com.petalert.dto.AvistamientoRequest;
import com.petalert.model.Avistamiento;
import com.petalert.service.AvistamientoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/avistamientos")
@CrossOrigin(origins = "*")
public class AvistamientoController {
    private final AvistamientoService service;

    public AvistamientoController(AvistamientoService service) {
        this.service = service;
    }

    @PostMapping
    public Avistamiento create(@Valid @RequestBody AvistamientoRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<Avistamiento> list() {
        return service.list();
    }

    @GetMapping("/reporte/{reporteId}")
    public List<Avistamiento> listByReport(@PathVariable String reporteId) {
        return service.listByReport(reporteId);
    }
}
