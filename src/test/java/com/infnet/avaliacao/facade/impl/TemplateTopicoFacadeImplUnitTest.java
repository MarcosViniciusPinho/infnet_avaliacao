package com.infnet.avaliacao.facade.impl;

import com.infnet.avaliacao.business.facade.impl.TemplateTopicoFacadeImpl;
import com.infnet.avaliacao.business.service.TemplateAvaliacaoTopicoPerguntaService;
import com.infnet.avaliacao.business.service.TemplateTopicoService;
import com.infnet.avaliacao.dto.impl.TemplateTopicoDTO;
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
		templateTopicoDTOList.add(this.createTemplateTopicoDTO(3L));
		templateTopicoDTOList.add(this.createTemplateTopicoDTO(5L));

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
		templateTopicoDTOList.add(this.createTemplateTopicoDTO(2L));
		templateTopicoDTOList.add(this.createTemplateTopicoDTO(4L));
		templateTopicoDTOList.add(this.createTemplateTopicoDTO(6L));
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

	private TemplateTopicoDTO createTemplateTopicoDTO(Long id){
		TemplateTopicoDTO templateTopicoDTO = new TemplateTopicoDTO();
		templateTopicoDTO.setId(id);
		return templateTopicoDTO;
	}

	private TemplateTopico createTemplateTopico(Long id){
		TemplateTopico templateTopico = new TemplateTopico();
		templateTopico.setId(id);
		return templateTopico;
	}

}
