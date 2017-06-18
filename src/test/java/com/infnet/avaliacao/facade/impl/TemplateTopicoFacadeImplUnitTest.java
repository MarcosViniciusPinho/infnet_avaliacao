package com.infnet.avaliacao.facade.impl;

import com.infnet.avaliacao.business.facade.impl.TemplateTopicoFacadeImpl;
import com.infnet.avaliacao.business.service.TemplateAvaliacaoTopicoPerguntaService;
import com.infnet.avaliacao.business.service.TemplateTopicoService;
import com.infnet.avaliacao.dto.impl.TemplateAvaliacaoTopicoPerguntaDTO;
import com.infnet.avaliacao.dto.impl.TemplateTopicoDTO;
import com.infnet.avaliacao.entity.TemplateAvaliacao;
import com.infnet.avaliacao.entity.TemplatePergunta;
import com.infnet.avaliacao.entity.TemplateTopico;
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
public class TemplateTopicoFacadeImplUnitTest {

	@InjectMocks
	private TemplateTopicoFacadeImpl templateTopicoFacadeImpl;

	@Mock
	private TemplateTopicoService templateTopicoService;

	@Mock
	private TemplateAvaliacaoTopicoPerguntaService templateAvaliacaoTopicoPerguntaService;

	@Mock
	private Pageable pageable;

	@Test
	public void testFindAll(){
		List<TemplateTopicoDTO> templateTopicoDTOList = new ArrayList<>();
		templateTopicoDTOList.add(this.createTemplateTopicoDTO(3L, null));
		templateTopicoDTOList.add(this.createTemplateTopicoDTO(5L, null));

		List<TemplateTopico> templateTopicoList = new ArrayList<>();
		templateTopicoList.add(this.createTemplateTopico(3L));
		templateTopicoList.add(this.createTemplateTopico(5L));
		Mockito.when(this.templateTopicoService.findAll()).thenReturn(templateTopicoList);
		Assert.assertNotNull(this.templateTopicoFacadeImpl.findAll());
		Assert.assertEquals(templateTopicoDTOList, this.templateTopicoFacadeImpl.findAll());
	}

	@Test
	public void testFindAllPaginated(){
		List<TemplateTopicoDTO> templateTopicoDTOList = new ArrayList<>();
		templateTopicoDTOList.add(this.createTemplateTopicoDTO(2L, null));
		templateTopicoDTOList.add(this.createTemplateTopicoDTO(4L, null));
		templateTopicoDTOList.add(this.createTemplateTopicoDTO(6L, null));
		Page<TemplateTopicoDTO> pageLisDto = new PageImpl<>(templateTopicoDTOList, this.pageable, templateTopicoDTOList.size());

		List<TemplateTopico> templateTopicoList = new ArrayList<>();
		templateTopicoList.add(this.createTemplateTopico(2L));
		templateTopicoList.add(this.createTemplateTopico(4L));
		templateTopicoList.add(this.createTemplateTopico(6L));
		Page<TemplateTopico> pageList = new PageImpl<>(templateTopicoList, this.pageable, templateTopicoList.size());

		Mockito.when(this.templateTopicoService.findAllPaginated(this.pageable)).thenReturn(pageList);
		Assert.assertNotNull(this.templateTopicoFacadeImpl.findAllPaginated(this.pageable));
		Assert.assertEquals(pageLisDto, this.templateTopicoFacadeImpl.findAllPaginated(this.pageable));
	}

	@Test(expected = NullParameterException.class)
	public void testFindAllPaginatedFailedPageableNull(){
		this.templateTopicoFacadeImpl.findAllPaginated(null);
	}

	@Test
	public void testSave(){
		List<TemplateAvaliacaoTopicoPerguntaDTO> templateAvaliacaoTopicoPerguntaDTOList = new ArrayList<>();
		templateAvaliacaoTopicoPerguntaDTOList.add(this.createTemplateAvaliacaoTopicoPerguntaDTO(8L));
		templateAvaliacaoTopicoPerguntaDTOList.add(this.createTemplateAvaliacaoTopicoPerguntaDTO(3L));
		templateAvaliacaoTopicoPerguntaDTOList.add(this.createTemplateAvaliacaoTopicoPerguntaDTO(7L));
		this.templateTopicoFacadeImpl.save(this.createTemplateTopicoDTO(2L, templateAvaliacaoTopicoPerguntaDTOList));
	}

	@Test(expected = NullParameterException.class)
	public void testSaveFailedDtoNull(){
		this.templateTopicoFacadeImpl.save(null);
	}

	@Test(expected = NullParameterException.class)
	public void testSaveFailedTemplateAvaliacaoTopicoPerguntaListNull(){
		this.templateTopicoFacadeImpl.save(this.createTemplateTopicoDTO(2L, null));
	}

	@Test
	public void testFindById(){
		Mockito.when(this.templateTopicoService.findById(2L)).thenReturn(this.createTemplateTopico(2L));
		Assert.assertNotNull(this.templateTopicoFacadeImpl.findById(2L));
		Assert.assertEquals(this.createTemplateTopicoDTO(2L, null), this.templateTopicoFacadeImpl.findById(2L));
	}

	@Test(expected = NullParameterException.class)
	public void testFindByIdFailedIdNull(){
		this.templateTopicoFacadeImpl.findById(null);
	}

	private TemplateAvaliacaoTopicoPerguntaDTO createTemplateAvaliacaoTopicoPerguntaDTO(Long id){
		TemplateAvaliacao templateAvaliacao = new TemplateAvaliacao();
		templateAvaliacao.setId(1L);

		TemplateTopico templateTopico = new TemplateTopico();
		templateTopico.setId(2L);

		TemplatePergunta templatePergunta = new TemplatePergunta();
		templatePergunta.setId(5L);

		TemplateAvaliacaoTopicoPerguntaDTO templateAvaliacaoTopicoPerguntaDTO = new TemplateAvaliacaoTopicoPerguntaDTO();
		templateAvaliacaoTopicoPerguntaDTO.setId(id);
		templateAvaliacaoTopicoPerguntaDTO.setTemplateAvaliacao(templateAvaliacao);
		templateAvaliacaoTopicoPerguntaDTO.setTemplateTopico(templateTopico);
		templateAvaliacaoTopicoPerguntaDTO.setTemplatePergunta(templatePergunta);

		return templateAvaliacaoTopicoPerguntaDTO;
	}

	private TemplateTopicoDTO createTemplateTopicoDTO(Long id, List<TemplateAvaliacaoTopicoPerguntaDTO> templateAvaliacaoTopicoPerguntaDTOList){
		TemplateTopicoDTO templateTopicoDTO = new TemplateTopicoDTO();
		templateTopicoDTO.setId(id);
		templateTopicoDTO.setTemplateAvaliacaoTopicoPerguntaDTOList(templateAvaliacaoTopicoPerguntaDTOList);
		return templateTopicoDTO;
	}

	private TemplateTopico createTemplateTopico(Long id){
		TemplateTopico templateTopico = new TemplateTopico();
		templateTopico.setId(id);
		return templateTopico;
	}

}
