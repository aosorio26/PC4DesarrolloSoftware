package com.petalert.service.busqueda;

import com.petalert.dto.BusquedaRequest;
import com.petalert.dto.BusquedaResponse;
import com.petalert.dto.ResultadoBusqueda;
import com.petalert.service.busqueda.OperacionBusquedaMascota;
import com.petalert.service.busqueda.SelectorTipoBusqueda;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BusquedaMascotaService {
    private final SelectorTipoBusqueda selectorTipoBusqueda;

    public BusquedaMascotaService(SelectorTipoBusqueda selectorTipoBusqueda) {
        this.selectorTipoBusqueda = selectorTipoBusqueda;
    }

    public BusquedaResponse buscar(BusquedaRequest request) {
        long inicio = System.currentTimeMillis();
        OperacionBusquedaMascota operacion = selectorTipoBusqueda.elegir(request.getIntent());
        List<ResultadoBusqueda> resultados = operacion.buscar(request);
        return new BusquedaResponse(
                request.getIntent().name(),
                operacion.nombreFuente(),
                resultados,
                System.currentTimeMillis() - inicio
        );
    }
}
