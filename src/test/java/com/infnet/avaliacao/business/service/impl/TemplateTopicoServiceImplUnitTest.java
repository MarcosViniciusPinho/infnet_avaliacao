package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.entity.TemplateTopico;
import com.infnet.avaliacao.exception.NullParameterException;
import com.infnet.avaliacao.repository.TemplateTopicoRepository;
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
public class TemplateTopicoServiceImplUnitTest {

	@InjectMocks
	private TemplateTopicoServiceImpl templateTopicoServiceImpl;

	@Mock
	private TemplateTopicoRepository templateTopicoRepository;

	@Test
	public void testGetListaTemplatesTopicosPorId(){
		List<Long> idsTemplateTopico = new ArrayList<>();
		idsTemplateTopico.add(3L);
		idsTemplateTopico.add(7L);
		idsTemplateTopico.add(1L);

		List<TemplateTopico> templateTopicoList = new ArrayList<>();
		templateTopicoList.add(this.createTemplateTopico(3L));
		templateTopicoList.add(this.createTemplateTopico(7L));
		templateTopicoList.add(this.createTemplateTopico(1L));

		Mockito.when(this.templateTopicoRepository.findByIdIn(idsTemplateTopico)).thenReturn(templateTopicoList);
		Assert.assertNotNull(this.templateTopicoServiceImpl.getListaTemplatesTopicosPorId(idsTemplateTopico));
		Assert.assertEquals(templateTopicoList, this.templateTopicoServiceImpl.getListaTemplatesTopicosPorId(idsTemplateTopico));
	}

	@Test(expected = NullParameterException.class)
	public void testGetListaTemplatesTopicosPorIdFailedIdsTemplateTopicoNull(){
		this.templateTopicoServiceImpl.getListaTemplatesTopicosPorId(null);
	}

	/**
	 * Métodos foram criados para auxiliar nos testes; ou seja; diminuir a codificação dos mesmos.
	 */

	private TemplateTopico createTemplateTopico(Long id){
		TemplateTopico templateTopico = new TemplateTopico();
		templateTopico.setId(id);
		return templateTopico;
	}


}
