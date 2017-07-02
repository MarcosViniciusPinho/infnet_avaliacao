package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.dto.impl.UsuarioDTO;
import com.infnet.avaliacao.entity.Usuario;
import com.infnet.avaliacao.exception.BusinessException;
import com.infnet.avaliacao.exception.NullParameterException;
import com.infnet.avaliacao.repository.UsuarioRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioServiceImplUnitTest {

	@InjectMocks
	private UsuarioServiceImpl usuarioServiceImpl;

	@Mock
	private UsuarioRepository usuarioRepository;


	@Test
	public void testValidate(){
		UsuarioDTO usuarioDTO = this.createUsuarioDTO(4L);
		Usuario usuario = this.createUsuario(4L);
		Mockito.when(usuarioRepository.findByLogin(usuarioDTO.getLogin())).thenReturn(usuario);
		this.usuarioServiceImpl.validate(usuarioDTO);
	}

	@Test(expected = BusinessException.class)
	public void testValidateFailedLoginExistente(){
		UsuarioDTO usuarioDTO = this.createUsuarioDTO(4L);
		Usuario usuario = this.createUsuario(5L);
		Mockito.when(usuarioRepository.findByLogin(usuarioDTO.getLogin())).thenReturn(usuario);
		this.usuarioServiceImpl.validate(usuarioDTO);
	}

	@Test(expected = NullParameterException.class)
	public void testValidateFailedUsuarioDTONull(){
		this.usuarioServiceImpl.validate(null);
	}

	@Test
	public void testDelete(){
		this.usuarioServiceImpl.delete(4L);
	}

	@Test(expected = NullParameterException.class)
	public void testDeleteFailedIdNull(){
		this.usuarioServiceImpl.delete(null);
	}


	/**
	 * Métodos foram criados para auxiliar nos testes; ou seja; diminuir a codificação dos mesmos.
	 */

	private UsuarioDTO createUsuarioDTO(Long id){
		return UsuarioDTO.toDto(this.createUsuario(id));
	}

	private Usuario createUsuario(Long id){
		Usuario usuario = new Usuario();
		usuario.setId(id);
		usuario.setLogin("Login");
		return usuario;
	}

}
