package com.infnet.avaliacao.exception.util;

import com.infnet.avaliacao.exception.NullParameterException;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;
import java.util.Optional;

/**
 * Classe que serve para validar se os parametros sao nulos ou vazios.
 */
public class ParameterExceptionUtil {

    private ParameterExceptionUtil() {}

    /**
     * Método que serve para validar se o objeto informado é nulo.
     * @param valor valor
     */
    public static void validateObjectNull(Object valor){
        Optional<Object> valorOther = Optional.ofNullable(valor);
        if(!valorOther.isPresent()){
            throw new NullParameterException("Parametro objeto nulo.");
        }
    }

    /**
     * Método que serve para validar se a lista informada é nula.
     * @param lista lista
     */
    public static void validateCollectionNull(Collection lista){
        if(CollectionUtils.isEmpty(lista)) {
            throw new NullParameterException("Parametro collection nulo.");
        }
    }

}
