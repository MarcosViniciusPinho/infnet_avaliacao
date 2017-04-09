package com.infnet.avaliacao.security;

import com.infnet.avaliacao.entity.Usuario;
import com.infnet.avaliacao.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = this.usuarioRepository.findByLogin(login);
        if(usuario == null){
            throw new UsernameNotFoundException("login.erro.usuario.nao.encontrado");
        }
        return new User(usuario.getLogin(), usuario.getSenha(), new HashSet<>());
    }
//
//    public static void main(String[] args){
//        BCryptPasswordEncoder criptografar = new BCryptPasswordEncoder();
//        System.out.println(criptografar.encode("maria"));
//    }

}
