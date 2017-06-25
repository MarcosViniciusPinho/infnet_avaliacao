package com.infnet.avaliacao.business.facade.impl;

import com.infnet.avaliacao.business.service.PerfilService;
import com.infnet.avaliacao.business.service.UsuarioService;
import com.infnet.avaliacao.dto.impl.UsuarioDTO;
import com.infnet.avaliacao.entity.Perfil;
import com.infnet.avaliacao.entity.Usuario;
import com.infnet.avaliacao.exception.NullParameterException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioFacadeImplUnitTest {

	@InjectMocks
	private UsuarioFacadeImpl usuarioFacadeImpl;

	@Mock
	private UsuarioService usuarioService;

	@Mock
	private PerfilService perfilService;

	@Mock
	private Pageable pageable;

	@Test
	public void testSave(){
		this.usuarioFacadeImpl.save(new UsuarioDTO());
	}

	@Test(expected = NullParameterException.class)
	public void testSaveFailedUsuarioNull(){
		this.usuarioFacadeImpl.save(null);
	}

	@Test
	public void testFindById(){
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(1L);

		Usuario usuario = new Usuario();
		usuario.setId(1L);

		Mockito.when(this.usuarioService.findById(1L)).thenReturn(usuario);
		Assert.assertNotNull(this.usuarioFacadeImpl.findById(1L));
		Assert.assertEquals(usuarioDTO, this.usuarioFacadeImpl.findById(1L));
	}

	@Test
	public void testFindByIdNaoSaoIguais(){
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(1L);

		Mockito.when(this.usuarioService.findById(1L)).thenReturn(new Usuario());
		Assert.assertNotNull(this.usuarioFacadeImpl.findById(1L));
		Assert.assertNotEquals(usuarioDTO, this.usuarioFacadeImpl.findById(1L));
	}

	@Test(expected = NullParameterException.class)
	public void testFindByIdFailedIdNull(){
		this.usuarioFacadeImpl.findById(null);
	}

	@Test
	public void testDelete(){
		this.usuarioFacadeImpl.delete(1L);
	}

	@Test(expected = NullParameterException.class)
	public void testDeleteFaildeIdNull(){
		this.usuarioFacadeImpl.delete(null);
	}

	@Test
	public void testFindAllPerfil(){
		List<Perfil> perfilList = new ArrayList<>();
		perfilList.add(this.createPerfil(2L));
		perfilList.add(this.createPerfil(1L));
		perfilList.add(this.createPerfil(5L));
		Mockito.when(this.perfilService.findAll()).thenReturn(perfilList);
		Assert.assertNotNull(this.usuarioFacadeImpl.findAllPerfil());
		Assert.assertEquals(perfilList, this.usuarioFacadeImpl.findAllPerfil());
	}

	@Test
	public void testFindAll(){
		List<UsuarioDTO> usuarioDTOList = new ArrayList<>();
		usuarioDTOList.add(this.createUsuarioDTO(1L));
		usuarioDTOList.add(this.createUsuarioDTO(6L));
		usuarioDTOList.add(this.createUsuarioDTO(3L));

		List<Usuario> usuarioList = new ArrayList<>();
		usuarioList.add(this.createUsuario(1L));
		usuarioList.add(this.createUsuario(6L));
		usuarioList.add(this.createUsuario(3L));

		Mockito.when(this.usuarioService.findAll()).thenReturn(usuarioList);
		Assert.assertNotNull(this.usuarioFacadeImpl.findAll());
		Assert.assertEquals(usuarioDTOList, this.usuarioFacadeImpl.findAll());
	}

	@Test
	public void testFindAllPaginated(){
		List<Usuario> usuarioList = new ArrayList<>();
		usuarioList.add(this.createUsuario(2L));
		usuarioList.add(this.createUsuario(4L));
		Page<Usuario> pageList = new PageImpl<>(usuarioList, this.pageable, usuarioList.size());

		List<UsuarioDTO> usuarioDTOList = new ArrayList<>();
		usuarioDTOList.add(this.createUsuarioDTO(2L));
		usuarioDTOList.add(this.createUsuarioDTO(4L));
		Page<UsuarioDTO> pageLisDto = new PageImpl<>(usuarioDTOList, this.pageable, usuarioDTOList.size());

		Mockito.when(this.usuarioService.findAllPaginated(this.pageable)).thenReturn(pageList);
		Assert.assertNotNull(this.usuarioFacadeImpl.findAllPaginated(this.pageable));
		Assert.assertEquals(pageLisDto, this.usuarioFacadeImpl.findAllPaginated(this.pageable));
	}

	@Test(expected = NullParameterException.class)
	public void testFindAllPaginatedFailedPageableNull(){
		this.usuarioFacadeImpl.findAllPaginated(null);
	}

	/**
	 * Métodos foram criados para auxiliar nos testes; ou seja; diminuir a codificação dos mesmos.
	 */

	private Usuario createUsuario(Long id){
		Usuario usuario = new Usuario();
		usuario.setId(id);
		return usuario;
	}

	private UsuarioDTO createUsuarioDTO(Long id){
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(id);
		return usuarioDTO;
	}

	private Perfil createPerfil(Long id){
		Perfil perfil = new Perfil();
		perfil.setId(id);
		return perfil;
	}

}
