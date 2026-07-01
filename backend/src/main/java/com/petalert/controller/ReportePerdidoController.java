package com.petalert.controller;

import com.petalert.dto.ReportePerdidoPublicoResponse;
import com.petalert.dto.ReportePerdidoRequest;
import com.petalert.service.ReportePerdidoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reportes-perdidos")
@CrossOrigin(origins = "*")
public class ReportePerdidoController {
    private final ReportePerdidoService service;

    public ReportePerdidoController(ReportePerdidoService service) {
        this.service = service;
    }

    @PostMapping
    public ReportePerdidoPublicoResponse create(@Valid @RequestBody ReportePerdidoRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<ReportePerdidoPublicoResponse> listActive() {
        return service.listActive();
    }

    @GetMapping("/{id}")
    public ReportePerdidoPublicoResponse get(@PathVariable String id) {
        return service.getPublic(id);
    }

    @PatchMapping("/{id}/resolver")
    public ReportePerdidoPublicoResponse resolve(@PathVariable String id) {
        return service.resolve(id);
    }
}
