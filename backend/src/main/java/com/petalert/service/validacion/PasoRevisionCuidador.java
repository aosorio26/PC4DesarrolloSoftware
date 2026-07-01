package com.petalert.service.validacion;

import com.petalert.model.Cuidador;

public abstract class PasoRevisionCuidador {
    private PasoRevisionCuidador next;

    public PasoRevisionCuidador linkWith(PasoRevisionCuidador next) {
        this.next = next;
        return next;
    }

    public final void validate(Cuidador cuidador) {
        check(cuidador);
        if (next != null) {
            next.validate(cuidador);
        }
    }

    protected abstract void check(Cuidador cuidador);
}
