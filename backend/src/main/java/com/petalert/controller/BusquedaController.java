package com.petalert.controller;

import com.petalert.dto.BusquedaRequest;
import com.petalert.dto.BusquedaResponse;
import com.petalert.service.busqueda.BusquedaMascotaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/busqueda")
@CrossOrigin(origins = "*")
public class BusquedaController {
    private final BusquedaMascotaService busquedaMascotaService;

    public BusquedaController(BusquedaMascotaService busquedaMascotaService) {
        this.busquedaMascotaService = busquedaMascotaService;
    }

    @PostMapping
    public BusquedaResponse buscar(@Valid @RequestBody BusquedaRequest request) {
        return busquedaMascotaService.buscar(request);
    }
}
