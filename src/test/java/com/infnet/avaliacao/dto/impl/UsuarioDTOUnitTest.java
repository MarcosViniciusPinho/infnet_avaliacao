package com.infnet.avaliacao.dto.impl;

import com.infnet.avaliacao.entity.Perfil;
import com.infnet.avaliacao.entity.Usuario;
import com.infnet.avaliacao.exception.NullParameterException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void testConvertListEntityToListDto(){
        List<UsuarioDTO> usuarioDTOList = new ArrayList<>();
        usuarioDTOList.add(this.createUsuarioDTO(2L));
        usuarioDTOList.add(this.createUsuarioDTO(6L));
        usuarioDTOList.add(this.createUsuarioDTO(4L));

        List<Usuario> usuarioList = new ArrayList<>();
        usuarioList.add(this.createUsuario(2L));
        usuarioList.add(this.createUsuario(6L));
        usuarioList.add(this.createUsuario(4L));

        Assert.assertNotNull(UsuarioDTO.convertListEntityToListDto(usuarioList));
        Assert.assertEquals(usuarioDTOList, UsuarioDTO.convertListEntityToListDto(usuarioList));
    }

    @Test
    public void testConvertListEntityToListDtoEmpty(){
        Assert.assertNotNull(UsuarioDTO.convertListEntityToListDto(new ArrayList<>()));
        Assert.assertEquals(new ArrayList<UsuarioDTO>(), UsuarioDTO.convertListEntityToListDto(new ArrayList<>()));
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
