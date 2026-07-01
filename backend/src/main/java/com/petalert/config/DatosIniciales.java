package com.petalert.config;

import com.petalert.model.Cuidador;
import com.petalert.model.RolCuidador;
import com.petalert.model.CatalogoMascota;
import com.petalert.model.PuntoUbicacion;
import com.petalert.model.Resena;
import com.petalert.model.IntencionBusqueda;
import com.petalert.model.RestriccionesServicio;
import com.petalert.model.Usuario;
import com.petalert.repository.CuidadorRepository;
import com.petalert.repository.CatalogoMascotaRepository;
import com.petalert.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class DatosIniciales implements CommandLineRunner {
    private final CatalogoMascotaRepository catalogRepository;
    private final CuidadorRepository cuidadorRepository;
    private final UsuarioRepository userAccountRepository;

    public DatosIniciales(CatalogoMascotaRepository catalogRepository, CuidadorRepository cuidadorRepository, UsuarioRepository userAccountRepository) {
        this.catalogRepository = catalogRepository;
        this.cuidadorRepository = cuidadorRepository;
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public void run(String... args) {
        seedCatalog();
        seedCuidadors();
        seedDemoUser();
    }

    private void seedCatalog() {
        if (catalogRepository.count() > 0) return;
        catalogRepository.saveAll(List.of(
                listing(IntencionBusqueda.ADOPCION, "ONG Patitas Lima", "ONG", "Rosa Valle", "987654321", "adopciones@patitaslima.org", "Toby", "Perro", "Mestizo", "Marron", "1 año", "Sociable, vacunado y de tamaño mediano.", null),
                listing(IntencionBusqueda.ADOPCION, "Refugio Surco", "Protectora", "Miguel Campos", "956111222", "contacto@refugiosurco.org", "Nala", "Perro", "Mestizo", "Negro", "8 meses", "Cachorra rescatada, juguetona y apta para departamento.", null),
                listing(IntencionBusqueda.ADOPCION, "Huellitas San Borja", "Protectora", "Carla Rivas", "944333222", "huellitas@sanborja.org", "Max", "Perro", "Labrador mestizo", "Dorado", "2 años", "Tranquilo, esterilizado y con buen trato con niños.", null),
                listing(IntencionBusqueda.VENTA, "Criadero Canino Miraflores", "Criadero certificado", "Laura Paredes", "965444111", "ventas@caninomiraflores.pe", "Bruno", "Perro", "Golden Retriever", "Dorado", "3 meses", "Cuenta con control veterinario y registro de procedencia.", "CERT-MUNI-2026-018"),
                listing(IntencionBusqueda.VENTA, "Criadero Legal La Molina", "Criadero certificado", "Diego Salas", "976555333", "informes@criadolamolina.pe", "Milo", "Perro", "Shih Tzu", "Blanco", "4 meses", "Entrega con carnet de vacunas y contrato de venta responsable.", "CERT-MUNI-2026-027")
        ));
    }

    private void seedCuidadors() {
        Cuidador a = cuidador("Andrea Rojas", RolCuidador.CUIDADOR_SOLIDARIO, "98765432", "999111222", "andrea.cuida@example.com", List.of("Perro", "Gato"), List.of("Pequeño", "Mediano"), false, true, -12.046374, -77.042793, "Miraflores");
        a.getResenas().add(new Resena("Usuario verificado", 5, "Muy responsable y puntual.", Instant.now()));
        Cuidador b = cuidador("Carlos Vega", RolCuidador.PROFESIONAL, "87654321", "988777666", "carlos.vega@example.com", List.of("Perro"), List.of("Mediano", "Grande"), true, true, -12.088900, -77.005200, "San Borja");
        b.getResenas().add(new Resena("Usuario verificado", 4, "Buen manejo de perros grandes.", Instant.now()));

        List<Cuidador> existing = cuidadorRepository.findAll();
        if (existing.stream().noneMatch(c -> "Andrea Rojas".equalsIgnoreCase(c.getFullName()))) {
            cuidadorRepository.save(a);
        }
        if (existing.stream().noneMatch(c -> "Carlos Vega".equalsIgnoreCase(c.getFullName()))) {
            cuidadorRepository.save(b);
        }
    }

    private void seedDemoUser() {
        if (userAccountRepository.findByEmailIgnoreCase("demo@petalert.pe").isPresent()) return;
        Usuario user = new Usuario();
        user.setFullName("Usuario Demo");
        user.setEmail("demo@petalert.pe");
        user.setPhone("999888777");
        user.setIdentityDocument("12345678");
        user.setIdentityVerified(true);
        user.setPassword("demo123");
        user.setCreatedAt(Instant.now());
        userAccountRepository.save(user);
    }

    private CatalogoMascota listing(IntencionBusqueda type, String organizationName, String organizationType,
                                      String contactName, String contactPhone, String contactEmail,
                                      String petName, String species, String breed, String color,
                                      String ageDescription, String description, String certificationCode) {
        CatalogoMascota item = new CatalogoMascota();
        item.setListingType(type);
        item.setOrganizationName(organizationName);
        item.setOrganizationType(organizationType);
        item.setContactName(contactName);
        item.setContactPhone(contactPhone);
        item.setContactEmail(contactEmail);
        item.setPetName(petName);
        item.setSpecies(species);
        item.setBreed(breed);
        item.setColor(color);
        item.setAgeDescription(ageDescription);
        item.setDescription(description);
        item.setCertificationCode(certificationCode);
        item.setActive(true);
        return item;
    }

    private Cuidador cuidador(String fullName, RolCuidador role, String dni, String phone, String email,
                                List<String> species, List<String> sizes, boolean medicationAllowed,
                                boolean receivesAlerts, double latitude, double longitude, String address) {
        Cuidador cuidador = new Cuidador();
        cuidador.setFullName(fullName);
        cuidador.setRole(role);
        cuidador.setIdentityDocument(dni);
        cuidador.setIdentityVerified(true);
        cuidador.setContactPhone(phone);
        cuidador.setContactEmail(email);
        cuidador.setReceivesLostPetAlerts(receivesAlerts);
        cuidador.setCreatedAt(Instant.now());
        cuidador.setLocation(new PuntoUbicacion(latitude, longitude, address));
        RestriccionesServicio restrictions = new RestriccionesServicio();
        restrictions.setAcceptedSpecies(species);
        restrictions.setAcceptedSizes(sizes);
        restrictions.setMedicationAllowed(medicationAllowed);
        cuidador.setRestrictions(restrictions);
        return cuidador;
    }
}
