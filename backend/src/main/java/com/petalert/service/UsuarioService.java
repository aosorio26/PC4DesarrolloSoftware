package com.petalert.service;

import com.petalert.dto.LoginRequest;
import com.petalert.dto.RegistroUsuarioRequest;
import com.petalert.model.Usuario;
import com.petalert.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;
    public UsuarioService(UsuarioRepository repository) { this.repository = repository; }

    public Usuario register(RegistroUsuarioRequest request) {
        repository.findByEmailIgnoreCase(request.getEmail()).ifPresent(u -> { throw new IllegalArgumentException("Ya existe una cuenta con ese correo"); });
        Usuario user = new Usuario();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        String dni = request.getIdentityDocument();
        if (dni == null || !dni.coincide("\\d{8}")) {
            throw new IllegalArgumentException("Para verificar la cuenta, el DNI debe tener 8 digitos numericos.");
        }
        user.setPhone(request.getPhone());
        user.setIdentityDocument(dni);
        user.setIdentityVerified(true);
        user.setPassword(request.getPassword());
        user.setCreatedAt(Instant.now());
        return sanitize(repository.save(user));
    }

    public Usuario login(LoginRequest request) {
        Usuario user = repository.findByEmailIgnoreCase(request.getEmail()).orElseThrow(() -> new IllegalArgumentException("Correo o clave incorrectos"));
        if (!user.getPassword().equals(request.getPassword())) throw new IllegalArgumentException("Correo o clave incorrectos");
        return sanitize(user);
    }

    public Usuario get(String id) { return sanitize(repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"))); }
    public List<Usuario> list() { return repository.findAll().stream().map(this::sanitize).toList(); }

    public void addInboxMessage(String userId, String mensaje) {
        repository.findById(userId).ifPresent(user -> {
            user.getInbox().add(0, mensaje);
            repository.save(user);
        });
    }

    private Usuario sanitize(Usuario user) {
        Usuario copy = new Usuario();
        copy.setId(user.getId());
        copy.setFullName(user.getFullName());
        copy.setEmail(user.getEmail());
        copy.setPhone(user.getPhone());
        copy.setIdentityDocument(user.getIdentityDocument());
        copy.setIdentityVerified(user.isIdentityVerified());
        copy.setPassword(null);
        copy.setCreatedAt(user.getCreatedAt());
        copy.setInbox(user.getInbox());
        return copy;
    }
}
