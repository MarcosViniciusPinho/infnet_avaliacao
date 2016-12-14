package com.infnet.avaliacao.dto;

import java.io.Serializable;

public interface IDTO<T> extends Serializable {

    /**
     * Método que converte um dto para uma entidade.
     * @return T
     */
    T toEntity();

    /**
     * Obtém o identificador deste DTO.
     * @return o identificador deste DTO.
     */
    Long getId();


}
