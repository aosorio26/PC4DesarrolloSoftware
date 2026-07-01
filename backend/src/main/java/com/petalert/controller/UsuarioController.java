package com.petalert.controller;

import com.petalert.dto.LoginRequest;
import com.petalert.dto.RegistroUsuarioRequest;
import com.petalert.model.Usuario;
import com.petalert.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {
    private final UsuarioService service;
    public UsuarioController(UsuarioService service) { this.service = service; }
    @PostMapping("/registro") public Usuario register(@Valid @RequestBody RegistroUsuarioRequest request) { return service.register(request); }
    @PostMapping("/login") public Usuario login(@Valid @RequestBody LoginRequest request) { return service.login(request); }
    @GetMapping("/{id}") public Usuario get(@PathVariable String id) { return service.get(id); }
    @GetMapping public List<Usuario> list() { return service.list(); }
}
