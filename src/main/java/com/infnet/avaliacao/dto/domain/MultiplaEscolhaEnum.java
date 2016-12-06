package com.infnet.avaliacao.dto.domain;

public enum MultiplaEscolhaEnum {

    CONCORDO_TOTALMENTE("avaliacao.concordo.totalmente"),
    CONCORDO("avaliacao.concordo"),
    NAO_CONCORDO_NEM_DISCORDO("avaliacao.nao.concordo"),
    DISCORDO("avaliacao.discordo"),
    DISCORDO_TOTALMENTE("avaliacao.discordo.totalmente"),
    NAO_SEI_AVALIAR("avaliacao.nao.sei.avaliar");

    private String id;

    MultiplaEscolhaEnum(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
