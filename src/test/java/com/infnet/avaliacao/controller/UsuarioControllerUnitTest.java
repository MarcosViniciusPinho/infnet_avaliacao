package com.infnet.avaliacao.controller;

import com.infnet.avaliacao.business.facade.UsuarioFacade;
import com.infnet.avaliacao.controller.util.ApplicationConstant;
import com.infnet.avaliacao.dto.impl.UsuarioDTO;
import com.infnet.avaliacao.entity.Perfil;
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
import org.springframework.ui.ExtendedModelMap;
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

	@Test
	public void testOnList(){
		List<UsuarioDTO> usuarioDTOList = new ArrayList<>();
		usuarioDTOList.add(new UsuarioDTO());
		ModelAndView modelViemEsperado = new ModelAndView("/cadastro/usuario/list");
		Page<UsuarioDTO> pageList = new PageImpl<>(usuarioDTOList, this.pageable, 1);
		modelViemEsperado.addObject(ApplicationConstant.LISTAR_USUARIOS, pageList);
		Mockito.when(this.usuarioFacade.findAllPaginated(this.pageable)).thenReturn(pageList);
		Assert.assertNotNull(this.usuarioController.onList(this.pageable));
		Assert.assertEquals(modelViemEsperado.getModel(), this.usuarioController.onList(this.pageable).getModel());
		Assert.assertEquals(modelViemEsperado.getViewName(), this.usuarioController.onList(this.pageable).getViewName());
	}

	@Test(expected = NullParameterException.class)
	public void testOnListFailed(){
		this.usuarioController.onList(null);
	}

	@Test
	public void testOnPrepareCreate(){
		Model model = new ExtendedModelMap();
		Assert.assertNotNull(this.usuarioController.onPrepareCreate(model));
		Assert.assertEquals("/cadastro/usuario/form", this.usuarioController.onPrepareCreate(model));
		Assert.assertEquals(new ArrayList<Perfil>(), model.asMap().get(ApplicationConstant.LISTAR_PERFIS));
		Assert.assertTrue(model.containsAttribute("usuarioDTO"));
	}

	@Test(expected = NullParameterException.class)
	public void testOnPrepareCreateFailed(){
		this.usuarioController.onPrepareCreate(null);
	}

	@Test
	public void testOnPrepareUpdateOrDetailSucess(){
		Model model = new ExtendedModelMap();
		Long id = 1L;
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(id);
		Mockito.when(this.usuarioFacade.findById(id)).thenReturn(usuarioDTO);
		Assert.assertNotNull(this.usuarioController.onPrepareUpdateOrDetail("/cadastro/usuario/form", id, model));
		Assert.assertEquals("/cadastro/usuario/form", usuarioController.onPrepareUpdateOrDetail("/cadastro/usuario/form", 1L, model));
		Assert.assertEquals(new ArrayList<Perfil>(), model.asMap().get(ApplicationConstant.LISTAR_PERFIS));
		Assert.assertEquals(usuarioDTO, model.asMap().get("usuarioDTO"));
	}

	@Test(expected = NullParameterException.class)
	public void testOnPrepareUpdateOrDetailFailedIdNUll(){
		Model model = new ExtendedModelMap();
		Assert.assertNotNull(this.usuarioController.onPrepareUpdateOrDetail("/cadastro/usuario/form", null, model));
	}

	@Test(expected = NullParameterException.class)
	public void testOnPrepareUpdateOrDetailFailedViewNUll(){
		Model model = new ExtendedModelMap();
		Assert.assertNotNull(this.usuarioController.onPrepareUpdateOrDetail(null, 1L, model));
	}

	@Test(expected = NullParameterException.class)
	public void testOnPrepareUpdateOrDetailFailedModelNUll(){
		Assert.assertNotNull(this.usuarioController.onPrepareUpdateOrDetail("/cadastro/usuario/form", 1L, null));
	}

	@Test
	public void testOnDelete(){
		this.usuarioController.onDelete(1L);
	}

	@Test(expected = NullParameterException.class)
	public void testOnDeleteFailed(){
		this.usuarioController.onDelete(null);
	}

	@Test
	public void testOnLoadView(){
		Model model = new ExtendedModelMap();
		this.usuarioController.onLoadView(model);
		Assert.assertEquals(new ArrayList<Perfil>(), model.asMap().get(ApplicationConstant.LISTAR_PERFIS));
	}

	@Test(expected = NullParameterException.class)
	public void testOnLoadViewFailed(){
		this.usuarioController.onLoadView(null);
	}
}
