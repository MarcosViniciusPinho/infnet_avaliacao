package com.infnet.avaliacao.dto.domain;

public enum MultiplaEscolhaEnum {

    CONCORDO_TOTALMENTE("Concordo totalmente"),
    CONCORDO("Concordo"),
    NAO_CONCORDO_NEM_DISCORDO("Não concordo nem discordo"),
    DISCORDO("Discordo"),
    DISCORDO_TOTALMENTE("Discordo totalmente"),
    NAO_SEI_AVALIAR("Não sei avaliar");

    private String id;

    MultiplaEscolhaEnum(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
