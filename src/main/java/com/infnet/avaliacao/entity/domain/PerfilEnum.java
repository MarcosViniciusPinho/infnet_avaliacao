package com.infnet.avaliacao.entity.domain;

/**
 * Classe que representa os valores possiveis para PerfilEnum.
 */
public enum PerfilEnum {

    ADM("ADMINISTRADOR"),
    SEC("SECRETARIA");

    private String id;

    PerfilEnum(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
