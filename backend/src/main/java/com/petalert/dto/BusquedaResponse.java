package com.petalert.dto;

import java.util.List;

public class BusquedaResponse {
    private String intent;
    private String engine;
    private List<ResultadoBusqueda> resultados;
    private long responseTimeMs;

    public BusquedaResponse(String intent, String engine, List<ResultadoBusqueda> resultados, long responseTimeMs) {
        this.intent = intent;
        this.engine = engine;
        this.resultados = resultados;
        this.responseTimeMs = responseTimeMs;
    }

    public String getIntent() { return intent; }
    public String getEngine() { return engine; }
    public List<ResultadoBusqueda> getResults() { return resultados; }
    public long getResponseTimeMs() { return responseTimeMs; }
}
