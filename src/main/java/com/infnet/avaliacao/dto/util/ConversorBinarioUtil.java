package com.infnet.avaliacao.dto.util;

import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;

/**
 * Classe criada pois houve a necessidade de pegar os caracteres especiais (',' e '-') de suas formas binárias vindos da tela.
 * Portando esta classe faz a descodificação e os retorna para suas formas de origem (',' e '-').
 */
public class ConversorBinarioUtil {//NOSONAR desnecessário implementação que acusa o sonar.

    private static final String BINARIO_VIRGULA = "00101100";
    private static final String BINARIO_HIFEN = "00101101";

    /**
     * Método que faz a conversão de binário para o caracter especial (',' e '-')
     * @param valor valor
     * @return String
     */
    public static String converterBinaryToCharacterInString(String valor){
        ParameterExceptionUtil.validateObjectNull(valor);
       return valor.replaceAll(BINARIO_VIRGULA, ",").replaceAll(BINARIO_HIFEN, "-");
    }

}
