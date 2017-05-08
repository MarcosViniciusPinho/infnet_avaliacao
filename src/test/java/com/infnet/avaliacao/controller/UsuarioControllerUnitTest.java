package com.infnet.avaliacao.controller;

import com.infnet.avaliacao.business.facade.UsuarioFacade;
import com.infnet.avaliacao.dto.impl.UsuarioDTO;
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
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioControllerUnitTest {

	@InjectMocks
	private UsuarioController usuarioController;

	@Mock
	private UsuarioFacade usuarioFacade;

	@Mock
	private Pageable pageable;

	@Mock
	private Model model;

	@Test
	public void testOnList(){
		List<UsuarioDTO> usuarioDTOList = new ArrayList<>();
		usuarioDTOList.add(new UsuarioDTO());
		ModelAndView modelViemEsperado = new ModelAndView("/cadastro/usuario/list");
		Page<UsuarioDTO> pageList = new PageImpl<>(usuarioDTOList, pageable, 1);
		modelViemEsperado.addObject("listarUsuarios", pageList);
		Mockito.when(this.usuarioFacade.findAllPaginated(pageable)).thenReturn(pageList);
		Assert.assertNotNull(this.usuarioController.onList(pageable));
		Assert.assertEquals(modelViemEsperado.getModel(), this.usuarioController.onList(pageable).getModel());
		Assert.assertEquals(modelViemEsperado.getViewName(), this.usuarioController.onList(pageable).getViewName());
	}

	@Test
	public void testOnPrepareCreate(){
		String urlEsperada = "/cadastro/usuario/form";
		Assert.assertNotNull(this.usuarioController.onPrepareCreate(model));
		Assert.assertEquals(urlEsperada, this.usuarioController.onPrepareCreate(model));
	}

}
