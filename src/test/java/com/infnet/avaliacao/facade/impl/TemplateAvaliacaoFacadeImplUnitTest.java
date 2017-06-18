package com.infnet.avaliacao.facade.impl;

import com.infnet.avaliacao.business.facade.impl.TemplateAvaliacaoFacadeImpl;
import com.infnet.avaliacao.business.service.TemplateAvaliacaoService;
import com.infnet.avaliacao.dto.impl.TemplateAvaliacaoDTO;
import com.infnet.avaliacao.entity.TemplateAvaliacao;
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
public class TemplateAvaliacaoFacadeImplUnitTest {

	@InjectMocks
	private TemplateAvaliacaoFacadeImpl templateAvaliacaoFacadeImpl;

	@Mock
	private TemplateAvaliacaoService templateAvaliacaoService;

	@Mock
	private Pageable pageable;

	@Test
	public void testFindAll(){
		List<TemplateAvaliacaoDTO> templateAvaliacaoDTOList = new ArrayList<>();
		templateAvaliacaoDTOList.add(this.createTemplateAvaliacaoDTO(3L));
		templateAvaliacaoDTOList.add(this.createTemplateAvaliacaoDTO(9L));
		templateAvaliacaoDTOList.add(this.createTemplateAvaliacaoDTO(6L));

		List<TemplateAvaliacao> templateAvaliacaoList = new ArrayList<>();
		templateAvaliacaoList.add(this.createTemplateAvaliacao(3L));
		templateAvaliacaoList.add(this.createTemplateAvaliacao(9L));
		templateAvaliacaoList.add(this.createTemplateAvaliacao(6L));

		Mockito.when(this.templateAvaliacaoService.findAll()).thenReturn(templateAvaliacaoList);
		Assert.assertNotNull(this.templateAvaliacaoFacadeImpl.findAll());
		Assert.assertEquals(templateAvaliacaoDTOList, this.templateAvaliacaoFacadeImpl.findAll());
	}

	/**
	 * Métodos foram criados para auxiliar nos testes; ou seja; diminuir a codificação dos mesmos.
	 */

	private TemplateAvaliacao createTemplateAvaliacao(Long id){
		TemplateAvaliacao templateAvaliacao = new TemplateAvaliacao();
		templateAvaliacao.setId(id);
		return templateAvaliacao;
	}

	private TemplateAvaliacaoDTO createTemplateAvaliacaoDTO(Long id){
		TemplateAvaliacaoDTO templateAvaliacaoDTO = new TemplateAvaliacaoDTO();
		templateAvaliacaoDTO.setId(id);
		return templateAvaliacaoDTO;
	}


}
