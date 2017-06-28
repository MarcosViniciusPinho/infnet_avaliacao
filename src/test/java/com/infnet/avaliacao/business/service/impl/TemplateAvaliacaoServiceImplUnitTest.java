package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.dto.impl.TemplateAvaliacaoDTO;
import com.infnet.avaliacao.dto.impl.TemplateTopicoDTO;
import com.infnet.avaliacao.exception.CampoObrigatorioException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TemplateAvaliacaoServiceImplUnitTest {

	@InjectMocks
	private TemplateAvaliacaoServiceImpl templateAvaliacaoServiceImpl;

	@Test
	public void testValidate(){
		List<TemplateTopicoDTO> templateTopicoDTOList = new ArrayList<>();
		templateTopicoDTOList.add(this.createTemplateTopicoDTO(4L));
		templateTopicoDTOList.add(this.createTemplateTopicoDTO(5L));
		templateTopicoDTOList.add(this.createTemplateTopicoDTO(9L));

		TemplateAvaliacaoDTO templateAvaliacaoDTO = new TemplateAvaliacaoDTO();
		templateAvaliacaoDTO.setTemplateTopicoDTOList(templateTopicoDTOList);
		this.templateAvaliacaoServiceImpl.validate(templateAvaliacaoDTO);
	}

	@Test(expected = CampoObrigatorioException.class)
	public void testValidateFailedCamposObrigatorios(){
		TemplateAvaliacaoDTO templateAvaliacaoDTO = new TemplateAvaliacaoDTO();
		this.templateAvaliacaoServiceImpl.validate(templateAvaliacaoDTO);
	}

	/**
	 * Métodos foram criados para auxiliar nos testes; ou seja; diminuir a codificação dos mesmos.
	 */

	private TemplateTopicoDTO createTemplateTopicoDTO(Long id){
		TemplateTopicoDTO templateTopicoDTO = new TemplateTopicoDTO();
		templateTopicoDTO.setId(id);
		return templateTopicoDTO;
	}


}
