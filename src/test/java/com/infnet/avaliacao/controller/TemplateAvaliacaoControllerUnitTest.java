package com.infnet.avaliacao.controller;

import com.infnet.avaliacao.business.facade.TemplateAvaliacaoFacade;
import com.infnet.avaliacao.business.facade.TemplateTopicoFacade;
import com.infnet.avaliacao.controller.util.ApplicationConstant;
import com.infnet.avaliacao.controller.util.PathConstant;
import com.infnet.avaliacao.controller.wrapper.PerguntaAssociadaWrapper;
import com.infnet.avaliacao.dto.impl.TemplateAvaliacaoDTO;
import com.infnet.avaliacao.dto.impl.TemplateAvaliacaoTopicoPerguntaDTO;
import com.infnet.avaliacao.dto.impl.TemplatePerguntaDTO;
import com.infnet.avaliacao.dto.impl.TemplateTopicoDTO;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TemplateAvaliacaoControllerUnitTest {

	@InjectMocks
	private TemplateAvaliacaoController templateAvaliacaoController;

	@Mock
	private TemplateAvaliacaoFacade templateAvaliacaoFacade;

	@Mock
	private TemplateTopicoFacade templateTopicoFacade;

	@Mock
	private Pageable pageable;

	@Mock
	private RedirectAttributes redirectAttributes;

	@Test
	public void testList(){
		List<TemplateAvaliacaoDTO> templateAvaliacaoDTOList = new ArrayList<>();
		templateAvaliacaoDTOList.add(new TemplateAvaliacaoDTO());
		ModelAndView modelViemEsperado = new ModelAndView("/template/avaliacao/list");
		Page<TemplateAvaliacaoDTO> pageList = new PageImpl<>(templateAvaliacaoDTOList, this.pageable, 1);
		modelViemEsperado.addObject(ApplicationConstant.LISTAR_TEMPLATE_AVALIACAO, pageList);
		Mockito.when(this.templateAvaliacaoFacade.findAllPaginated(this.pageable)).thenReturn(pageList);
		Assert.assertNotNull(this.templateAvaliacaoController.list(this.pageable));
		Assert.assertEquals(modelViemEsperado.getModel(), this.templateAvaliacaoController.list(this.pageable).getModel());
		Assert.assertEquals(modelViemEsperado.getViewName(), this.templateAvaliacaoController.list(this.pageable).getViewName());
	}

	@Test(expected = NullParameterException.class)
	public void testListFailed(){
		this.templateAvaliacaoController.list(null);
	}

	@Test
	public void testOnForm(){
		TemplateTopicoDTO templateTopicoDTO = new TemplateTopicoDTO();
		templateTopicoDTO.setId(2L);
		List<TemplateTopicoDTO> templateTopicoDTOList = new ArrayList<>();
		templateTopicoDTOList.add(templateTopicoDTO);
		Model model = new ExtendedModelMap();
		TemplateAvaliacaoDTO entity = new TemplateAvaliacaoDTO();
		entity.setId(5L);
		List<Long> ids = new ArrayList<>();
		ids.add(1L);
		entity.setIdsTemplateTopicoSelecionados(ids);
		Mockito.when(this.templateTopicoFacade.getListaTemplatesTopicosPorId(ids)).thenReturn(templateTopicoDTOList);
		this.templateAvaliacaoController.onForm(entity, model, redirectAttributes);
		Assert.assertEquals(templateTopicoDTOList, entity.getTemplateTopicoDTOList());
//		Assert.assertEquals(entity.getId(), redirectAttributes.asMap().get("id"));
	}

	@Test(expected = NullParameterException.class)
	public void testOnFormFailedTemplateNull(){
		Model model = new ExtendedModelMap();
		this.templateAvaliacaoController.onForm(null, model, redirectAttributes);
	}

	@Test(expected = NullParameterException.class)
	public void testOnFormFailedModelNull(){
		this.templateAvaliacaoController.onForm(new TemplateAvaliacaoDTO(), null, redirectAttributes);
	}

	@Test(expected = NullParameterException.class)
	public void testOnFormFailedRedirectNull(){
		Model model = new ExtendedModelMap();
		this.templateAvaliacaoController.onForm(new TemplateAvaliacaoDTO(), model, null);
	}

	@Test
	public void testOnLoadViewPaginated(){
		Model model = new ExtendedModelMap();
		List<TemplateTopicoDTO> templateTopicoDTOList = new ArrayList<>();
		templateTopicoDTOList.add(new TemplateTopicoDTO());
		Page<TemplateTopicoDTO> pageList = new PageImpl<>(templateTopicoDTOList, this.pageable, 1);
		Mockito.when(templateTopicoFacade.findAllPaginated(this.pageable)).thenReturn(pageList);
		this.templateAvaliacaoController.onLoadViewPaginated(model, this.pageable);
		Assert.assertEquals(pageList, model.asMap().get(ApplicationConstant.LISTAR_TEMPLATE_TOPICO));
	}

	@Test(expected = NullParameterException.class)
	public void testOnLoadViewPaginatedFailedModelNull(){
		this.templateAvaliacaoController.onLoadViewPaginated(null, this.pageable);
	}

	@Test(expected = NullParameterException.class)
	public void testOnLoadViewPaginatedFailedPageableNull(){
		Model model = new ExtendedModelMap();
		this.templateAvaliacaoController.onLoadViewPaginated(model, null);
	}

	@Test
	public void testOnLoadView(){
		Model model = new ExtendedModelMap();
		List<TemplateTopicoDTO> templateTopicoDTOList = new ArrayList<>();
		templateTopicoDTOList.add(new TemplateTopicoDTO());
		Mockito.when(templateTopicoFacade.findAll()).thenReturn(templateTopicoDTOList);
		this.templateAvaliacaoController.onLoadView(model);
		Assert.assertEquals(templateTopicoDTOList, model.asMap().get(ApplicationConstant.LISTAR_TEMPLATE_TOPICO));
	}

	@Test(expected = NullParameterException.class)
	public void testOnLoadViewFailed(){
		this.templateAvaliacaoController.onLoadView(null);
	}

	@Test
	public void testPrepareUpdate(){
		Long id = 1L;
		List<Long> ids = new ArrayList<>();
		ids.add(id);
		Model model = new ExtendedModelMap();
		TemplateAvaliacaoDTO templateAvaliacaoDTO = new TemplateAvaliacaoDTO();
		TemplateTopicoDTO templateTopicoDTO = new TemplateTopicoDTO();
		templateTopicoDTO.setId(id);
		List<TemplateTopicoDTO> templateTopicoDTOList = new ArrayList<>();
		templateTopicoDTOList.add(templateTopicoDTO);
		templateAvaliacaoDTO.setTemplateTopicoDTOList(templateTopicoDTOList);
		Mockito.when(this.templateAvaliacaoFacade.findById(id)).thenReturn(templateAvaliacaoDTO);
		Assert.assertNotNull(this.templateAvaliacaoController.prepareUpdate(id, model, pageable));
		Assert.assertEquals(ids, templateAvaliacaoDTO.getIdsTemplateTopicoSelecionados());
		Assert.assertEquals("/template/avaliacao/form", this.templateAvaliacaoController.prepareUpdate(id, model, pageable));
	}

	@Test(expected = NullParameterException.class)
	public void testPrepareUpdateFailedIdNull(){
		Model model = new ExtendedModelMap();
		this.templateAvaliacaoController.prepareUpdate(null, model, pageable);
	}

	@Test(expected = NullParameterException.class)
	public void testPrepareUpdateFailedModelNull(){
		this.templateAvaliacaoController.prepareUpdate(1L, null, pageable);
	}

	@Test(expected = NullParameterException.class)
	public void testPrepareUpdateFailedPageableNull(){
		Model model = new ExtendedModelMap();
		this.templateAvaliacaoController.prepareUpdate(1L, model, null);
	}

	@Test
	public void testPrepareError(){
		Model model = new ExtendedModelMap();
		TemplateAvaliacaoDTO templateAvaliacaoDTO = new TemplateAvaliacaoDTO();
		templateAvaliacaoDTO.setId(1L);
		Mockito.when(this.templateAvaliacaoFacade.findById(templateAvaliacaoDTO.getId())).thenReturn(templateAvaliacaoDTO);
		Assert.assertNotNull(this.templateAvaliacaoController.prepareError(templateAvaliacaoDTO.getId(), model, pageable));
		Assert.assertEquals(templateAvaliacaoDTO, model.asMap().get("templateAvaliacaoDTO"));
		Assert.assertEquals("/template/avaliacao/form", this.templateAvaliacaoController.prepareError(templateAvaliacaoDTO.getId(), model, pageable));
	}

	@Test(expected = NullParameterException.class)
	public void testPrepareErrorFailedIdNull(){
		Model model = new ExtendedModelMap();
		this.templateAvaliacaoController.prepareError(null, model, pageable);
	}

	@Test(expected = NullParameterException.class)
	public void testPrepareErrorFailedModelNull(){
		this.templateAvaliacaoController.prepareError(1L, null, pageable);
	}

	@Test(expected = NullParameterException.class)
	public void testPrepareErrorFailedPageableNull(){
		Model model = new ExtendedModelMap();
		this.templateAvaliacaoController.prepareError(1L, model, null);
	}

	@Test
	public void testPrepareDetail(){
		Model model = new ExtendedModelMap();
		TemplateAvaliacaoDTO templateAvaliacaoDTO = new TemplateAvaliacaoDTO();
		templateAvaliacaoDTO.setId(1L);
		TemplateTopicoDTO templateTopicoDTO = new TemplateTopicoDTO();
		templateTopicoDTO.setId(2L);
		TemplatePerguntaDTO templatePerguntaDTO = new TemplatePerguntaDTO();
		templatePerguntaDTO.setId(3L);
		TemplateAvaliacaoTopicoPerguntaDTO templateAvaliacaoTopicoPerguntaDTO = new TemplateAvaliacaoTopicoPerguntaDTO();
		templateAvaliacaoTopicoPerguntaDTO.setId(3L);
		templateAvaliacaoTopicoPerguntaDTO.setAtivo(Boolean.TRUE);
		templateAvaliacaoTopicoPerguntaDTO.setTemplateTopico(templateTopicoDTO.toEntity());
		templateAvaliacaoTopicoPerguntaDTO.setTemplateAvaliacao(templateAvaliacaoDTO.toEntity());
		templateAvaliacaoTopicoPerguntaDTO.setTemplatePergunta(templatePerguntaDTO.toEntity());
		List<TemplateAvaliacaoTopicoPerguntaDTO> templateAvaliacaoTopicoPerguntaDTOList = new ArrayList<>();
		templateAvaliacaoTopicoPerguntaDTOList.add(templateAvaliacaoTopicoPerguntaDTO);
		templateTopicoDTO.setTemplateAvaliacaoTopicoPerguntaDTOList(templateAvaliacaoTopicoPerguntaDTOList);
		List<TemplateTopicoDTO> templateTopicoDTOList = new ArrayList<>();
		templateTopicoDTOList.add(templateTopicoDTO);
		templateAvaliacaoDTO.setTemplateTopicoDTOList(templateTopicoDTOList);
		List<TemplatePerguntaDTO> templatePerguntaDTOList = new ArrayList<>();
		templatePerguntaDTOList.add(templatePerguntaDTO);
		PerguntaAssociadaWrapper perguntaAssociadaWrapper = new PerguntaAssociadaWrapper();
		perguntaAssociadaWrapper.setTemplateTopicoDTO(templateTopicoDTO);
		perguntaAssociadaWrapper.setTemplatePerguntaDTOList(templatePerguntaDTOList);
		List<PerguntaAssociadaWrapper> perguntaAssociadaWrapperList = new ArrayList<>();
		perguntaAssociadaWrapperList.add(perguntaAssociadaWrapper);
		Mockito.when(this.templateAvaliacaoFacade.findById(1L)).thenReturn(templateAvaliacaoDTO);
		Assert.assertNotNull(this.templateAvaliacaoController.prepareDetail(1L, model));
		Assert.assertEquals(templateAvaliacaoDTO, model.asMap().get("templateAvaliacaoDTO"));
		Assert.assertEquals(perguntaAssociadaWrapperList, model.asMap().get("perguntaAssociadaWrapperList"));
		Assert.assertEquals("/template/avaliacao/detail", this.templateAvaliacaoController.prepareDetail(1L, model));
	}

	@Test(expected = NullParameterException.class)
	public void testPrepareDetailFailedIdNull(){
		Model model = new ExtendedModelMap();
		this.templateAvaliacaoController.prepareDetail(null, model);
	}

	@Test(expected = NullParameterException.class)
	public void testPrepareDetailFailedModelNull(){
		this.templateAvaliacaoController.prepareDetail(1L, null);
	}

	@Test
	public void testGetPathView(){
		Assert.assertNotNull(this.templateAvaliacaoController.getPathView());
		Assert.assertEquals(PathConstant.PATH_TEMPLATE_AVALIACAO, this.templateAvaliacaoController.getPathView());
	}

	@Test
	public void testGetRedirectViewError(){
		Assert.assertNotNull(this.templateAvaliacaoController.getRedirectViewError());
		Assert.assertEquals("redirect:/template/avaliacao/error/{id}", this.templateAvaliacaoController.getRedirectViewError());
	}

	@Test
	public void testGetRedirectViewEdit(){
		Assert.assertNotNull(this.templateAvaliacaoController.getRedirectViewEdit());
		Assert.assertEquals("redirect:/template/avaliacao/edit/{id}", this.templateAvaliacaoController.getRedirectViewEdit());
	}

	@Test
	public void testGetFacade(){
		Assert.assertNotNull(this.templateAvaliacaoController.getFacade());
		Assert.assertEquals(this.templateAvaliacaoFacade, this.templateAvaliacaoController.getFacade());
	}

}
