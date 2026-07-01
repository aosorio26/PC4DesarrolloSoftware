package com.petalert.service;

import com.petalert.model.AvisoAlerta;
import com.petalert.model.Cuidador;
import com.petalert.model.ReportePerdido;
import com.petalert.service.alertas.GestorAlertas;
import com.petalert.service.alertas.CuidadorAvisado;
import com.petalert.service.ubicacion.CalculadoraDistancia;
import com.petalert.repository.CuidadorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlertasService {
    private final CuidadorRepository cuidadorRepository;
    private final CalculadoraDistancia calculadoraDistancia;
    private final GestorAlertas gestorAlertas;

    public AlertasService(CuidadorRepository cuidadorRepository,
                        CalculadoraDistancia calculadoraDistancia,
                        GestorAlertas gestorAlertas) {
        this.cuidadorRepository = cuidadorRepository;
        this.calculadoraDistancia = calculadoraDistancia;
        this.gestorAlertas = gestorAlertas;
    }

    public List<AvisoAlerta> distribuir(ReportePerdido reporte) {
        List<GestorAlertas.DestinoConDistancia> destinos = new ArrayList<>();
        List<Cuidador> cuidadores = cuidadorRepository.findByReceivesLostPetAlertsTrueAndIdentityVerifiedTrue();

        for (Cuidador cuidador : cuidadores) {
            double distancia = calculadoraDistancia.distanciaKm(reporte.getLocation(), cuidador.getLocation());
            if (distancia <= reporte.getAlertRadiusKm()) {
                destinos.add(new GestorAlertas.DestinoConDistancia(new CuidadorAvisado(cuidador), distancia));
            }
        }
        return gestorAlertas.notificar(reporte, destinos);
    }
}
