package com.infnet.avaliacao.controller;

import com.infnet.avaliacao.business.facade.TemplateAvaliacaoFacade;
import com.infnet.avaliacao.business.facade.TemplatePerguntaFacade;
import com.infnet.avaliacao.business.facade.TemplateTopicoFacade;
import com.infnet.avaliacao.controller.util.ApplicationConstant;
import com.infnet.avaliacao.controller.util.PathConstant;
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

	@Mock
	private Pageable pageable;

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

	@Test
	public void testPrepareUpdate(){
		Long idAvaliacao = 3L;
		Model model = new ExtendedModelMap();
		TemplateTopicoDTO templateTopicoDTO = new TemplateTopicoDTO();
		templateTopicoDTO.setId(1L);

		TemplateAvaliacaoDTO templateAvaliacaoDTO = new TemplateAvaliacaoDTO();
		templateAvaliacaoDTO.setId(3L);

		List<TemplatePerguntaDTO> templatePerguntaDTOList = new ArrayList<>();
		templatePerguntaDTOList.add(this.createTemplatePerguntaDTO(1L));
		templatePerguntaDTOList.add(this.createTemplatePerguntaDTO(3L));

		Page<TemplatePerguntaDTO> templatePerguntaDtoPage = new PageImpl<>(templatePerguntaDTOList, pageable, templatePerguntaDTOList.size());

		Mockito.when(this.templateTopicoFacade.findById(templateTopicoDTO.getId())).thenReturn(templateTopicoDTO);
		Mockito.when(this.templateAvaliacaoFacade.findById(idAvaliacao)).thenReturn(templateAvaliacaoDTO);
		Mockito.when(this.templatePerguntaFacade.findAllComCheckedPerguntasMarcadas(
				templateTopicoDTO, templateAvaliacaoDTO, pageable)).thenReturn(templatePerguntaDtoPage);
		Assert.assertNotNull(this.templateTopicoController.prepareUpdate(1L, idAvaliacao, model, this.pageable));
		Assert.assertEquals("/template/avaliacao/topico/form", this.templateTopicoController.prepareUpdate(1L, idAvaliacao, model, this.pageable));
		Assert.assertEquals(templateTopicoDTO.getIdAvaliacao(), idAvaliacao);
		Assert.assertEquals(templateAvaliacaoDTO, model.asMap().get("templateAvaliacaoDTO"));
		Assert.assertEquals(templateTopicoDTO, model.asMap().get("templateTopicoDTO"));
		Assert.assertEquals(templatePerguntaDtoPage, model.asMap().get(ApplicationConstant.LISTAR_TEMPLATE_PERGUNTA));
	}

	@Test(expected = NullParameterException.class)
	public void testPrepareUpdateFailedIdNull(){
		Model model = new ExtendedModelMap();
		this.templateTopicoController.prepareUpdate(null, 3L, model, this.pageable);
	}

	@Test(expected = NullParameterException.class)
	public void testPrepareUpdateFailedIdAvaliacaoNull(){
		Model model = new ExtendedModelMap();
		this.templateTopicoController.prepareUpdate(1L, null, model, this.pageable);
	}

	@Test(expected = NullParameterException.class)
	public void testPrepareUpdateFailedModelNull(){
		this.templateTopicoController.prepareUpdate(1L, 3L, null, this.pageable);
	}

	@Test(expected = NullParameterException.class)
	public void testPrepareUpdateFailedPageableNull(){
		Model model = new ExtendedModelMap();
		this.templateTopicoController.prepareUpdate(1L, 3L, model, null);
	}

	@Test
	public void testPrepareError(){
		Long idAvaliacao = 5L;
		Model model = new ExtendedModelMap();
		TemplateTopicoDTO templateTopicoDTO = new TemplateTopicoDTO();
		templateTopicoDTO.setId(1L);
		TemplateAvaliacaoDTO templateAvaliacaoDTO = new TemplateAvaliacaoDTO();
		templateAvaliacaoDTO.setId(3L);
		Mockito.when(this.templateTopicoFacade.findById(templateTopicoDTO.getId())).thenReturn(templateTopicoDTO);
		Mockito.when(this.templateAvaliacaoFacade.findById(idAvaliacao)).thenReturn(templateAvaliacaoDTO);
		Assert.assertNotNull(this.templateTopicoController.prepareError(1L, idAvaliacao, model, this.pageable));
		Assert.assertEquals("/template/avaliacao/topico/form", this.templateTopicoController.prepareError(1L, idAvaliacao, model, this.pageable));
		Assert.assertEquals(templateTopicoDTO.getIdAvaliacao(), idAvaliacao);
		Assert.assertEquals(templateTopicoDTO, model.asMap().get("templateTopicoDTO"));
		Assert.assertEquals(templateAvaliacaoDTO, model.asMap().get("templateAvaliacaoDTO"));
	}

	@Test(expected = NullParameterException.class)
	public void testPrepareErrorFailedIdNull(){
		Model model = new ExtendedModelMap();
		this.templateTopicoController.prepareError(null, 5L, model, this.pageable);
	}

	@Test(expected = NullParameterException.class)
	public void testPrepareErrorFailedIdAvaliacaoNull(){
		Model model = new ExtendedModelMap();
		this.templateTopicoController.prepareError(1L, null, model, this.pageable);
	}

	@Test(expected = NullParameterException.class)
	public void testPrepareErrorFailedModelNull(){
		this.templateTopicoController.prepareError(1L, 5L, null, this.pageable);
	}

	@Test(expected = NullParameterException.class)
	public void testPrepareErrorFailedPageableNull(){
		Model model = new ExtendedModelMap();
		this.templateTopicoController.prepareError(1L, 5L, model, null);
	}

	@Test
	public void testGetRedirectViewEdit(){
		Assert.assertNotNull(this.templateTopicoController.getRedirectViewEdit());
		Assert.assertEquals("redirect:/template/avaliacao/topico/edit/{id}/avaliacao/{idAvaliacao}", this.templateTopicoController.getRedirectViewEdit());
	}

	@Test
	public void testGetRedirectViewError(){
		Assert.assertNotNull(this.templateTopicoController.getRedirectViewError());
		Assert.assertEquals("redirect:/template/avaliacao/topico/error/{id}/avaliacao/{idAvaliacao}", this.templateTopicoController.getRedirectViewError());
	}

	@Test
	public void testOnLoadViewPaginated(){
		Model model = new ExtendedModelMap();

		List<TemplatePerguntaDTO> templatePerguntaDTOList = new ArrayList<>();
		templatePerguntaDTOList.add(this.createTemplatePerguntaDTO(5L));
		templatePerguntaDTOList.add(this.createTemplatePerguntaDTO(7L));

		Page<TemplatePerguntaDTO> templatePerguntaDtoPage = new PageImpl<>(templatePerguntaDTOList, pageable, templatePerguntaDTOList.size());

		Mockito.when(templatePerguntaFacade.findAllPaginated(pageable)).thenReturn(templatePerguntaDtoPage);
		this.templateTopicoController.onLoadViewPaginated(model, this.pageable);
		Assert.assertEquals(templatePerguntaDtoPage, model.asMap().get(ApplicationConstant.LISTAR_TEMPLATE_PERGUNTA));
	}

	@Test(expected = NullParameterException.class)
	public void testOnLoadViewPaginatedFailedModelNull(){
		this.templateTopicoController.onLoadViewPaginated(null, this.pageable);
	}

	@Test(expected = NullParameterException.class)
	public void testOnLoadViewPaginatedFailedPageableNull(){
		Model model = new ExtendedModelMap();
		this.templateTopicoController.onLoadViewPaginated(model, null);
	}

	@Test
	public void testGetPathView(){
		Assert.assertNotNull(this.templateTopicoController.getPathView());
		Assert.assertEquals(PathConstant.PATH_TEMPLATE_TOPICO, this.templateTopicoController.getPathView());
	}

	@Test
	public void testGetFacade(){
		Assert.assertNotNull(this.templateTopicoController.getFacade());
		Assert.assertEquals(this.templateTopicoFacade, this.templateTopicoController.getFacade());
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
