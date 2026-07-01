package com.petalert.service.busqueda;

import com.petalert.dto.BusquedaRequest;
import com.petalert.dto.ResultadoBusqueda;

import java.util.List;

public interface OperacionBusquedaMascota {
    String nombreFuente();
    List<ResultadoBusqueda> buscar(BusquedaRequest request);
}
