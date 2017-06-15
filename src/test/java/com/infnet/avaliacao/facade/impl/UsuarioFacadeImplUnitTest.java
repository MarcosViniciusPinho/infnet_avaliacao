package com.infnet.avaliacao.facade.impl;

import com.infnet.avaliacao.business.facade.impl.UsuarioFacadeImpl;
import com.infnet.avaliacao.business.service.PerfilService;
import com.infnet.avaliacao.business.service.UsuarioService;
import com.infnet.avaliacao.dto.impl.UsuarioDTO;
import com.infnet.avaliacao.exception.NullParameterException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioFacadeImplUnitTest {

	@InjectMocks
	private UsuarioFacadeImpl usuarioFacadeImpl;

	@Mock
	private UsuarioService usuarioService;

	@Mock
	private PerfilService perfilService;


	@Test
	public void testSave(){
		this.usuarioFacadeImpl.save(new UsuarioDTO());
	}

	@Test(expected = NullParameterException.class)
	public void testSaveFailedUsuarioNull(){
		this.usuarioFacadeImpl.save(null);
	}

}
