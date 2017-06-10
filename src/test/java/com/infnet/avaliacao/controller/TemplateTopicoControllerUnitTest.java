package com.infnet.avaliacao.controller;

import com.infnet.avaliacao.business.facade.TemplateAvaliacaoFacade;
import com.infnet.avaliacao.business.facade.TemplatePerguntaFacade;
import com.infnet.avaliacao.business.facade.TemplateTopicoFacade;
import com.infnet.avaliacao.controller.util.ApplicationConstant;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TemplateTopicoControllerUnitTest {

	@InjectMocks
	private TemplateTopicoController templateTopicoController;

	@Mock
	private TemplateTopicoFacade templateTopicoFacade;

	@Mock
	private TemplatePerguntaFacade templatePerguntaFacade;

	@Mock
	private TemplateAvaliacaoFacade templateAvaliacaoFacade;

	@Test
	public void testOnForm(){
		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
		Model model = new ExtendedModelMap();
		TemplateTopicoDTO templateTopicoDTO = new TemplateTopicoDTO();
		templateTopicoDTO.setId(1L);
		templateTopicoDTO.setIdAvaliacao(2L);

		List<Long> idsPerguntasSelecionados = new ArrayList<>();
		idsPerguntasSelecionados.add(1L);
		idsPerguntasSelecionados.add(3L);

		templateTopicoDTO.setIdsTemplatePerguntaSelecionados(idsPerguntasSelecionados);

		List<TemplatePerguntaDTO> templatePerguntaDTOList = new ArrayList<>();
		templatePerguntaDTOList.add(this.createTemplatePerguntaDTO(1L));
		templatePerguntaDTOList.add(this.createTemplatePerguntaDTO(3L));

		TemplateAvaliacaoDTO templateAvaliacaoDTO = new TemplateAvaliacaoDTO();
		templateAvaliacaoDTO.setId(2L);

		List<TemplateAvaliacaoTopicoPerguntaDTO> templateAvaliacaoTopicoPerguntaDTOList = new ArrayList<>();
		templateAvaliacaoTopicoPerguntaDTOList.add(this.createTemplateAvaliacaoTopicoPerguntaDTO(3L));
		templateAvaliacaoTopicoPerguntaDTOList.add(this.createTemplateAvaliacaoTopicoPerguntaDTO(5L));

		Mockito.when(this.templatePerguntaFacade.getListaTemplatesPerguntasPorId(idsPerguntasSelecionados)).thenReturn(templatePerguntaDTOList);
		Mockito.when(this.templateAvaliacaoFacade.findById(templateTopicoDTO.getIdAvaliacao())).thenReturn(templateAvaliacaoDTO);
		Mockito.when(this.templatePerguntaFacade.getListaPerguntasAssociadasAoTopicoPorAvaliacao(
				templatePerguntaDTOList, templateTopicoDTO, templateAvaliacaoDTO)).thenReturn(templateAvaliacaoTopicoPerguntaDTOList);
		this.templateTopicoController.onForm(templateTopicoDTO, model, redirectAttributes);
		Assert.assertEquals(templateTopicoDTO.getTemplateAvaliacaoTopicoPerguntaDTOList(), templateAvaliacaoTopicoPerguntaDTOList);
		Assert.assertEquals(templateTopicoDTO.getId().toString(), redirectAttributes.asMap().get("id"));
		Assert.assertEquals(templateTopicoDTO.getIdAvaliacao().toString(), redirectAttributes.asMap().get("idAvaliacao"));
	}

	@Test(expected = NullParameterException.class)
	public void testOnFormFailedTemplateTopicoNull(){
		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
		Model model = new ExtendedModelMap();
		this.templateTopicoController.onForm(null, model, redirectAttributes);
	}

	@Test(expected = NullParameterException.class)
	public void testOnFormFailedModelNull(){
		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
		this.templateTopicoController.onForm(new TemplateTopicoDTO(), null, redirectAttributes);
	}

	@Test(expected = NullParameterException.class)
	public void testOnFormFailedRedirectNull(){
		Model model = new ExtendedModelMap();
		this.templateTopicoController.onForm(null, model, null);
	}

	@Test
	public void testOnLoadView(){
		Model model = new ExtendedModelMap();
		List<TemplatePerguntaDTO> templatePerguntaDTOList = new ArrayList<>();
		templatePerguntaDTOList.add(this.createTemplatePerguntaDTO(1L));
		templatePerguntaDTOList.add(this.createTemplatePerguntaDTO(2L));
		Mockito.when(this.templatePerguntaFacade.findAll()).thenReturn(templatePerguntaDTOList);
		this.templateTopicoController.onLoadView(model);
		Assert.assertEquals(templatePerguntaDTOList, model.asMap().get(ApplicationConstant.LISTAR_TEMPLATE_PERGUNTA));
	}

	@Test(expected = NullParameterException.class)
	public void testOnLoadFailedModelNullView(){
		this.templateTopicoController.onLoadView(null);
	}

	/**
	 * Métodos auxiliares dos testes para não precisar escrever muito código.
	 */

	private TemplatePerguntaDTO createTemplatePerguntaDTO(Long id){
		TemplatePerguntaDTO templatePerguntaDTO = new TemplatePerguntaDTO();
		templatePerguntaDTO.setId(id);
		return templatePerguntaDTO;
	}

	private TemplateAvaliacaoTopicoPerguntaDTO createTemplateAvaliacaoTopicoPerguntaDTO(Long id){
		TemplateAvaliacaoTopicoPerguntaDTO templateAvaliacaoTopicoPerguntaDTO = new TemplateAvaliacaoTopicoPerguntaDTO();
		templateAvaliacaoTopicoPerguntaDTO.setId(id);
		return templateAvaliacaoTopicoPerguntaDTO;
	}

}
