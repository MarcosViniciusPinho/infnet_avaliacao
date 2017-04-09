package com.infnet.avaliacao.dto.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CriptografiaUtil {

    public static String getSenhaCriptografada(String senha, Long id){
        if(StringUtils.isNotEmpty(senha) && id == null){
            BCryptPasswordEncoder criptografar = new BCryptPasswordEncoder();
            return criptografar.encode(senha);
        }
        return senha;
    }

}
