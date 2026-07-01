package com.petalert.service.validacion;

import com.petalert.model.Cuidador;

public class RevisionRol extends PasoRevisionCuidador {
    @Override
    protected void check(Cuidador cuidador) {
        if (cuidador.getRole() == null) {
            throw new IllegalArgumentException("El cuidador debe seleccionar un rol valido.");
        }
    }
}
