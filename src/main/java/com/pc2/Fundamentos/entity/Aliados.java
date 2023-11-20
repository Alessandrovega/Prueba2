package com.pc2.Fundamentos.entity;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Aliados {
    @JsonProperty("Apertura")
    private String apertura;

    @JsonProperty("Categoria")
    private String categoria;

    @JsonProperty("Codigo")
    private String codigo;

    @JsonProperty("Negocio")
    private String negocio;
}
