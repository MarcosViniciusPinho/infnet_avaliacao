package com.infnet.avaliacao.facade.impl;

import com.infnet.avaliacao.business.facade.impl.TemplatePerguntaFacadeImpl;
import com.infnet.avaliacao.business.service.TemplateAvaliacaoTopicoPerguntaService;
import com.infnet.avaliacao.business.service.TemplatePerguntaService;
import com.infnet.avaliacao.dto.impl.TemplatePerguntaDTO;
import com.infnet.avaliacao.entity.TemplatePergunta;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
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
		templatePerguntaDTOList.add(this.createTemplatePerguntaDto(3L));
		templatePerguntaDTOList.add(this.createTemplatePerguntaDto(5L));

		List<TemplatePergunta> templatePerguntaList = new ArrayList<>();
		templatePerguntaList.add(this.createTemplatePergunta(3L));
		templatePerguntaList.add(this.createTemplatePergunta(5L));
		Mockito.when(this.templatePerguntaService.findAll()).thenReturn(templatePerguntaList);
		Assert.assertNotNull(this.templatePerguntaFacadeImpl.findAll());
		Assert.assertEquals(templatePerguntaDTOList, this.templatePerguntaFacadeImpl.findAll());
	}


	/**
	 * Métodos foram criados para auxiliar nos testes; ou seja; diminuir a codificação dos mesmos.
	 */

	private TemplatePerguntaDTO createTemplatePerguntaDto(Long id){
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
