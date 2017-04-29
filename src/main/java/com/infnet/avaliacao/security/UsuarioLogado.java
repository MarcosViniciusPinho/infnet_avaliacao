package com.infnet.avaliacao.security;

import com.infnet.avaliacao.dto.impl.UsuarioDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UsuarioLogado extends User{

    private UsuarioDTO usuarioDTO;

    public UsuarioLogado(UsuarioDTO usuarioDTO, Collection<? extends GrantedAuthority> authorities) {
        super(usuarioDTO.getLogin(), usuarioDTO.getSenha(), authorities);
        this.usuarioDTO = usuarioDTO;
    }

    public UsuarioDTO getUsuarioDTO() {
        return usuarioDTO;
    }
}
