package com.infnet.avaliacao.facade.impl;

import com.infnet.avaliacao.business.facade.impl.TemplatePerguntaFacadeImpl;
import com.infnet.avaliacao.business.service.TemplateAvaliacaoTopicoPerguntaService;
import com.infnet.avaliacao.business.service.TemplatePerguntaService;
import com.infnet.avaliacao.dto.impl.TemplatePerguntaDTO;
import com.infnet.avaliacao.entity.TemplatePergunta;
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
public class TemplatePerguntaFacadeImplUnitTest {

	@InjectMocks
	private TemplatePerguntaFacadeImpl templatePerguntaFacadeImpl;

	@Mock
	private TemplatePerguntaService templatePerguntaService;

	@Mock
	private TemplateAvaliacaoTopicoPerguntaService templateAvaliacaoTopicoPerguntaService;

	@Mock
	private Pageable pageable;

	@Test
	public void testFindAll(){
		List<TemplatePerguntaDTO> templatePerguntaDTOList = new ArrayList<>();
		templatePerguntaDTOList.add(this.createTemplatePerguntaDTO(3L));
		templatePerguntaDTOList.add(this.createTemplatePerguntaDTO(5L));

		List<TemplatePergunta> templatePerguntaList = new ArrayList<>();
		templatePerguntaList.add(this.createTemplatePergunta(3L));
		templatePerguntaList.add(this.createTemplatePergunta(5L));
		Mockito.when(this.templatePerguntaService.findAll()).thenReturn(templatePerguntaList);
		Assert.assertNotNull(this.templatePerguntaFacadeImpl.findAll());
		Assert.assertEquals(templatePerguntaDTOList, this.templatePerguntaFacadeImpl.findAll());
	}

	@Test
	public void testFindAllPaginated(){
		List<TemplatePerguntaDTO> templateTopicoDTOList = new ArrayList<>();
		templateTopicoDTOList.add(this.createTemplatePerguntaDTO(2L));
		templateTopicoDTOList.add(this.createTemplatePerguntaDTO(4L));
		templateTopicoDTOList.add(this.createTemplatePerguntaDTO(6L));
		Page<TemplatePerguntaDTO> pageLisDto = new PageImpl<>(templateTopicoDTOList, this.pageable, templateTopicoDTOList.size());

		List<TemplatePergunta> templateTopicoList = new ArrayList<>();
		templateTopicoList.add(this.createTemplatePergunta(2L));
		templateTopicoList.add(this.createTemplatePergunta(4L));
		templateTopicoList.add(this.createTemplatePergunta(6L));
		Page<TemplatePergunta> pageList = new PageImpl<>(templateTopicoList, this.pageable, templateTopicoList.size());

		Mockito.when(this.templatePerguntaService.findAllPaginated(this.pageable)).thenReturn(pageList);
		Assert.assertNotNull(this.templatePerguntaFacadeImpl.findAllPaginated(this.pageable));
		Assert.assertEquals(pageLisDto, this.templatePerguntaFacadeImpl.findAllPaginated(this.pageable));
	}

	@Test(expected = NullParameterException.class)
	public void testFindAllPaginatedFailedPageableNull(){
		this.templatePerguntaFacadeImpl.findAllPaginated(null);
	}

	@Test
	public void testSave(){
		this.templatePerguntaFacadeImpl.save(this.createTemplatePerguntaDTO(3L));
	}

	@Test(expected = NullParameterException.class)
	public void testSaveFailedDtoNull(){
		this.templatePerguntaFacadeImpl.save(null);
	}

	@Test
	public void testFindById(){
		Mockito.when(this.templatePerguntaService.findById(2L)).thenReturn(this.createTemplatePergunta(2L));
		Assert.assertNotNull(this.templatePerguntaFacadeImpl.findById(2L));
		Assert.assertEquals(this.createTemplatePerguntaDTO(2L), this.templatePerguntaFacadeImpl.findById(2L));
	}

	@Test(expected = NullParameterException.class)
	public void testFindByIdFailedIdNull(){
		this.templatePerguntaFacadeImpl.findById(null);
	}

	@Test
	public void testGetListaTemplatesPerguntasPorId(){
		List<Long> idsTemplateTopico = new ArrayList<>();
		idsTemplateTopico.add(3L);
		idsTemplateTopico.add(5L);

		List<TemplatePerguntaDTO> templatePerguntaDTOList = new ArrayList<>();
		templatePerguntaDTOList.add(this.createTemplatePerguntaDTO(3L));
		templatePerguntaDTOList.add(this.createTemplatePerguntaDTO(5L));

		List<TemplatePergunta> templatePerguntaList = new ArrayList<>();
		templatePerguntaList.add(this.createTemplatePergunta(3L));
		templatePerguntaList.add(this.createTemplatePergunta(5L));

		Mockito.when(this.templatePerguntaService.getListaTemplatesPerguntasPorId(idsTemplateTopico)).thenReturn(templatePerguntaList);
		Assert.assertNotNull(this.templatePerguntaFacadeImpl.getListaTemplatesPerguntasPorId(idsTemplateTopico));
		Assert.assertEquals(templatePerguntaDTOList, this.templatePerguntaFacadeImpl.getListaTemplatesPerguntasPorId(idsTemplateTopico));
	}

	@Test(expected = NullParameterException.class)
	public void testGetListaTemplatesPerguntasPorIdFailedIdsTemplateTopico(){
		this.templatePerguntaFacadeImpl.getListaTemplatesPerguntasPorId(null);
	}

	/**
	 * Métodos foram criados para auxiliar nos testes; ou seja; diminuir a codificação dos mesmos.
	 */

	private TemplatePerguntaDTO createTemplatePerguntaDTO(Long id){
		TemplatePerguntaDTO templatePerguntaDTO = new TemplatePerguntaDTO();
		templatePerguntaDTO.setId(id);
		return templatePerguntaDTO;
	}

	private TemplatePergunta createTemplatePergunta(Long id){
		TemplatePergunta templatePergunta = new TemplatePergunta();
		templatePergunta.setId(id);
		return templatePergunta;
	}
}
