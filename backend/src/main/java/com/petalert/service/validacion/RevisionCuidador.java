package com.petalert.service.validacion;

import org.springframework.stereotype.Component;

@Component
public class RevisionCuidador {
    public PasoRevisionCuidador create() {
        PasoRevisionCuidador first = new RevisionDocumento();
        first.linkWith(new RevisionRol())
                .linkWith(new RevisionRestricciones());
        return first;
    }
}
