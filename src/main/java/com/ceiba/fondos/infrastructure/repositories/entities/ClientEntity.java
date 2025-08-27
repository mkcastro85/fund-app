package com.ceiba.fondos.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Client {
    private String id;
    private String nombre;
    private Double saldo;
    private List<Fund> funds;
}
