package com.infnet.avaliacao.entity.domain;

/**
 * Classe que representa os valores possiveis para Perfil.
 */
public enum PerfilEnum {

    ADM("Administrador"),
    SEC("Secretaria");

    private String id;

    PerfilEnum(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
