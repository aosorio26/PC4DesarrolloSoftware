package com.petalert.service.validacion;

import com.petalert.model.Cuidador;

public class RevisionRestricciones extends PasoRevisionCuidador {
    @Override
    protected void check(Cuidador cuidador) {
        if (cuidador.getRestrictions() == null || cuidador.getRestrictions().getAcceptedSpecies() == null
                || cuidador.getRestrictions().getAcceptedSpecies().isEmpty()) {
            throw new IllegalArgumentException("El cuidador debe indicar al menos una especie aceptada.");
        }
    }
}
