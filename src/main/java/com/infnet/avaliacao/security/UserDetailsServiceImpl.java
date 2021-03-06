package com.infnet.avaliacao.security;

import com.infnet.avaliacao.dto.impl.UsuarioDTO;
import com.infnet.avaliacao.entity.Usuario;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;
import com.infnet.avaliacao.repository.UsuarioRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        ParameterExceptionUtil.validateObjectNull(login);
        Usuario usuario = this.usuarioRepository.findByLogin(login);
        if(usuario == null){
            throw new UsernameNotFoundException("login.erro.usuario.nao.encontrado");
        }
        UsuarioDTO usuarioDTO = UsuarioDTO.toDto(usuario);
        return new UsuarioLogado(usuarioDTO, getPermissoes(usuarioDTO));
    }

    private Collection<? extends GrantedAuthority> getPermissoes(UsuarioDTO usuarioDTO) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        usuarioDTO.getPerfil().getRoleList().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getNome().toUpperCase())));
        return authorities;
    }

}
