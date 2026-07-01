package com.petalert.service.validacion;

import com.petalert.model.Cuidador;

public class RevisionDocumento extends PasoRevisionCuidador {
    @Override
    protected void check(Cuidador cuidador) {
        String dni = cuidador.getIdentityDocument();
        if (dni == null || !dni.coincide("\\d{8}")) {
            throw new IllegalArgumentException("El documento de identidad debe tener 8 digitos numericos.");
        }
    }
}
