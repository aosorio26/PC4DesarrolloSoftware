package com.petalert.service.busqueda;

import com.petalert.model.IntencionBusqueda;
import com.petalert.service.busqueda.BuscarEnAdopciones;
import com.petalert.service.busqueda.BuscarEnPerdidos;
import com.petalert.service.busqueda.BuscarEnCriaderos;
import com.petalert.service.busqueda.OperacionBusquedaMascota;
import org.springframework.stereotype.Component;

@Component
public class SelectorTipoBusqueda {
    private final BuscarEnAdopciones busquedaAdopcion;
    private final BuscarEnCriaderos busquedaVenta;
    private final BuscarEnPerdidos busquedaPerdidos;

    public SelectorTipoBusqueda(BuscarEnAdopciones busquedaAdopcion,
                                 BuscarEnCriaderos busquedaVenta,
                                 BuscarEnPerdidos busquedaPerdidos) {
        this.busquedaAdopcion = busquedaAdopcion;
        this.busquedaVenta = busquedaVenta;
        this.busquedaPerdidos = busquedaPerdidos;
    }

    public OperacionBusquedaMascota elegir(IntencionBusqueda intent) {
        return switch (intent) {
            case ADOPCION -> busquedaAdopcion;
            case VENTA -> busquedaVenta;
            case VERIFICAR_PERDIDA -> busquedaPerdidos;
        };
    }
}
