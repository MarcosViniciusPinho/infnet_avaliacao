package com.infnet.avaliacao.dto.impl;

import com.infnet.avaliacao.entity.Perfil;
import com.infnet.avaliacao.entity.Usuario;
import com.infnet.avaliacao.exception.NullParameterException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioDTOUnitTest {

    @Mock
    private Pageable pageable;

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
    public void testToDtoFailedUsuarioNull(){
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

    @Test
    public void testConvertPageEntityToPageDto(){
        List<UsuarioDTO> usuarioDTOList = new ArrayList<>();
        usuarioDTOList.add(this.createUsuarioDTO(7L));
        usuarioDTOList.add(this.createUsuarioDTO(2L));
        usuarioDTOList.add(this.createUsuarioDTO(5L));

        Page<UsuarioDTO> pageDtoList = new PageImpl<>(usuarioDTOList, pageable, usuarioDTOList.size());

        List<Usuario> usuarioList = new ArrayList<>();
        usuarioList.add(this.createUsuario(7L));
        usuarioList.add(this.createUsuario(2L));
        usuarioList.add(this.createUsuario(5L));

        Page<Usuario> pageList = new PageImpl<>(usuarioList, pageable, usuarioList.size());

        Assert.assertNotNull(UsuarioDTO.convertPageEntityToPageDto(pageList, this.pageable));
        Assert.assertEquals(pageDtoList, UsuarioDTO.convertPageEntityToPageDto(pageList, this.pageable));
    }

    @Test
    public void testConvertPageEntityToPageDtoEmpty(){
        Page<UsuarioDTO> pageDtoList = new PageImpl<>(new ArrayList<>(), pageable, 0);
        Page<Usuario> pageList = new PageImpl<>(new ArrayList<>(), pageable, 0);
        Assert.assertNotNull(UsuarioDTO.convertPageEntityToPageDto(pageList, this.pageable));
        Assert.assertEquals(pageDtoList, UsuarioDTO.convertPageEntityToPageDto(pageList, this.pageable));
    }

    @Test
    public void testLimparCampoSenha(){
        UsuarioDTO usuarioDTO = this.createUsuarioDTO(null);
        usuarioDTO.limparCampoSenha();
        Assert.assertNull(usuarioDTO.getSenha());
    }

    @Test
    public void testLimparCampoSenhaIdFornecido(){
        UsuarioDTO usuarioDTO = this.createUsuarioDTO(3L);
        usuarioDTO.limparCampoSenha();
        Assert.assertNotNull(usuarioDTO.getSenha());
    }

    @Test
    public void testIsLoginExistente(){
        Usuario usuario = this.createUsuario(3L);
        UsuarioDTO usuarioDTO = this.createUsuarioDTO(4L);
        Assert.assertTrue(usuarioDTO.isLoginExistente(usuario));
    }

    @Test
    public void testIsLoginExistenteComIdsIguais(){
        Usuario usuario = this.createUsuario(3L);
        UsuarioDTO usuarioDTO = this.createUsuarioDTO(3L);
        Assert.assertFalse(usuarioDTO.isLoginExistente(usuario));
    }

    @Test
    public void testResetarSenhaFailed(){
        UsuarioDTO usuarioDTO = this.createUsuarioDTO(null);
        usuarioDTO.resetarSenha();

        Assert.assertNotNull(usuarioDTO.getSenha());
        Assert.assertEquals("Senha", usuarioDTO.getSenha());
    }

    @Test
    public void testResetarSenha(){
        UsuarioDTO usuarioDTO = this.createUsuarioDTO(4L);
        usuarioDTO.resetarSenha();

        Assert.assertNotNull(usuarioDTO.getSenha());
        Assert.assertEquals("infnet123", usuarioDTO.getSenha());
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
