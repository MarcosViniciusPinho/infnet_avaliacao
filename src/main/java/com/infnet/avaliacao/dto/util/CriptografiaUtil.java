package com.infnet.avaliacao.dto.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CriptografiaUtil {//NOSONAR desnecessário implementação que acusa o sonar.

    public static String getSenhaCriptografada(String senha){
        if(StringUtils.isNotEmpty(senha)){
            BCryptPasswordEncoder criptografar = new BCryptPasswordEncoder();
            return criptografar.encode(senha.replace(",", ""));
        }
        return senha;
    }

}
