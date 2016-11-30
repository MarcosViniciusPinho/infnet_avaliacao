package com.infnet.avaliacao.entity.domain;

/**
 * Classe que representa os valores possiveis para Perfil.
 */
public enum PerfilEnum {

    ADM("usuario.select.administrador"),
    SEC("usuario.select.secretaria");

    private String id;

    PerfilEnum(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
