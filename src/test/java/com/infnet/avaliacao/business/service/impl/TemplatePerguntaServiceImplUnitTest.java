package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.entity.TemplatePergunta;
import com.infnet.avaliacao.exception.NullParameterException;
import com.infnet.avaliacao.repository.TemplateAvaliacaoTopicoPerguntaRepository;
import com.infnet.avaliacao.repository.TemplatePerguntaRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TemplatePerguntaServiceImplUnitTest {

	@InjectMocks
	private TemplatePerguntaServiceImpl templatePerguntaServiceImpl;

	@Mock
	private TemplatePerguntaRepository templatePerguntaRepository;

	@Mock
	private TemplateAvaliacaoTopicoPerguntaRepository templateAvaliacaoTopicoPerguntaRepository;

	@Test
	public void testGetListaTemplatesPerguntasPorId(){
		List<TemplatePergunta> templatePerguntaList = new ArrayList<>();
		templatePerguntaList.add(this.createTemplatePergunta(1L));
		templatePerguntaList.add(this.createTemplatePergunta(6L));
		templatePerguntaList.add(this.createTemplatePergunta(8L));

		List<Long> idsTemplateTopico = new ArrayList<>();
		idsTemplateTopico.add(5L);
		idsTemplateTopico.add(9L);
		idsTemplateTopico.add(6L);

		Mockito.when(this.templatePerguntaRepository.findByIdIn(idsTemplateTopico)).thenReturn(templatePerguntaList);
		Assert.assertNotNull(this.templatePerguntaServiceImpl.getListaTemplatesPerguntasPorId(idsTemplateTopico));
		Assert.assertEquals(templatePerguntaList, this.templatePerguntaServiceImpl.getListaTemplatesPerguntasPorId(idsTemplateTopico));
	}

	@Test(expected = NullParameterException.class)
	public void testGetListaTemplatesPerguntasPorIdFailedIdsTemplateTopicoNull(){
		this.templatePerguntaServiceImpl.getListaTemplatesPerguntasPorId(null);
	}

	/**
	 * Métodos foram criados para auxiliar nos testes; ou seja; diminuir a codificação dos mesmos.
	 */

	private TemplatePergunta createTemplatePergunta(Long id){
		TemplatePergunta templatePergunta = new TemplatePergunta();
		templatePergunta.setId(id);
		return templatePergunta;
	}

}
