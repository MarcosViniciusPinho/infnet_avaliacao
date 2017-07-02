package com.infnet.avaliacao.dto.impl;

import com.infnet.avaliacao.entity.Perfil;
import com.infnet.avaliacao.entity.Usuario;
import com.infnet.avaliacao.exception.NullParameterException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioDTOUnitTest {

    @Test
    public void testToEntity(){
        Usuario usuarioEsperado = this.createUsuario(3L);
        UsuarioDTO usuarioDTO = this.createUsuarioDTO(3L);
        Assert.assertNotNull(usuarioDTO.toEntity());
        Assert.assertEquals(usuarioEsperado, usuarioDTO.toEntity());
    }

    @Test
    public void testToDto(){
        Usuario usuario = this.createUsuario(3L);
        UsuarioDTO usuarioDTOEsperado = this.createUsuarioDTO(3L);
        Assert.assertNotNull(UsuarioDTO.toDto(usuario));
        Assert.assertEquals(usuarioDTOEsperado, UsuarioDTO.toDto(usuario));
    }

    @Test(expected = NullParameterException.class)
    public void testToDtoUsuarioNull(){
        UsuarioDTO.toDto(null);
    }

    /**
     * Métodos foram criados para auxiliar nos testes; ou seja; diminuir a codificação dos mesmos.
     */

    private Usuario createUsuario(Long id){
        Perfil perfil = new Perfil();
        perfil.setId(2L);
        perfil.setNome("Perfil");

        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNome("Marcos");
        usuario.setLogin("123");
        usuario.setSenha("Senha");
        usuario.setPerfil(perfil);
        return usuario;
    }

    private UsuarioDTO createUsuarioDTO(Long id){
        Perfil perfil = new Perfil();
        perfil.setId(2L);
        perfil.setNome("Perfil");

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(id);
        usuarioDTO.setNome("Marcos");
        usuarioDTO.setLogin("123");
        usuarioDTO.setSenha("Senha");
        usuarioDTO.setPerfil(perfil);
        return usuarioDTO;
    }

}
