package com.infnet.avaliacao.dto.domain;

public enum MultiplaEscolhaEnum {

    CONCORDO_TOTALMENTE("template.avaliacao.concordo.totalmente"),
    CONCORDO("template.avaliacao.concordo"),
    NAO_CONCORDO_NEM_DISCORDO("template.avaliacao.nao.concordo"),
    DISCORDO("template.avaliacao.discordo"),
    DISCORDO_TOTALMENTE("template.avaliacao.discordo.totalmente"),
    NAO_SEI_AVALIAR("template.avaliacao.nao.sei.avaliar");

    private String id;

    MultiplaEscolhaEnum(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
