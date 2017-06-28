package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.dto.impl.TemplateAvaliacaoTopicoPerguntaDTO;
import com.infnet.avaliacao.exception.NullParameterException;
import com.infnet.avaliacao.repository.TemplateAvaliacaoTopicoPerguntaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TemplateAvaliacaoTopicoPerguntaServiceImplUnitTest {

	@InjectMocks
	private TemplateAvaliacaoTopicoPerguntaServiceImpl templateAvaliacaoTopicoPerguntaServiceImpl;

	@Mock
	private TemplateAvaliacaoTopicoPerguntaRepository templateAvaliacaoTopicoPerguntaRepository;

	@Test
	public void testSave(){
		List<TemplateAvaliacaoTopicoPerguntaDTO> templateAvaliacaoTopicoPerguntaDTOList = new ArrayList<>();
		templateAvaliacaoTopicoPerguntaDTOList.add(this.createTemplateAvaliacaoTopicoPerguntaDTO(3L));
		templateAvaliacaoTopicoPerguntaDTOList.add(this.createTemplateAvaliacaoTopicoPerguntaDTO(8L));
		templateAvaliacaoTopicoPerguntaDTOList.add(this.createTemplateAvaliacaoTopicoPerguntaDTO(1L));
		this.templateAvaliacaoTopicoPerguntaServiceImpl.save(templateAvaliacaoTopicoPerguntaDTOList);
	}

	@Test(expected = NullParameterException.class)
	public void testSaveFailedTemplateAvaliacaoTopicoPerguntaDTOListNull(){
		this.templateAvaliacaoTopicoPerguntaServiceImpl.save(null);
	}

	/**
	 * Métodos foram criados para auxiliar nos testes; ou seja; diminuir a codificação dos mesmos.
	 */

	private TemplateAvaliacaoTopicoPerguntaDTO createTemplateAvaliacaoTopicoPerguntaDTO(Long id){
		TemplateAvaliacaoTopicoPerguntaDTO templateAvaliacaoTopicoPerguntaDTO = new TemplateAvaliacaoTopicoPerguntaDTO();
		templateAvaliacaoTopicoPerguntaDTO.setId(id);
		return templateAvaliacaoTopicoPerguntaDTO;
	}

}
