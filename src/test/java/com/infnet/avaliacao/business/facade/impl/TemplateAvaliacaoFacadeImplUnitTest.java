package com.infnet.avaliacao.business.facade.impl;

import com.infnet.avaliacao.business.service.TemplateAvaliacaoService;
import com.infnet.avaliacao.dto.impl.TemplateAvaliacaoDTO;
import com.infnet.avaliacao.entity.TemplateAvaliacao;
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

	@Test
	public void testFindAllPaginated(){
		List<TemplateAvaliacaoDTO> templateAvaliacaoDTOList = new ArrayList<>();
		templateAvaliacaoDTOList.add(this.createTemplateAvaliacaoDTO(2L));
		templateAvaliacaoDTOList.add(this.createTemplateAvaliacaoDTO(4L));
		templateAvaliacaoDTOList.add(this.createTemplateAvaliacaoDTO(6L));
		Page<TemplateAvaliacaoDTO> pageLisDto = new PageImpl<>(templateAvaliacaoDTOList, this.pageable, templateAvaliacaoDTOList.size());

		List<TemplateAvaliacao> templateAvaliacaoList = new ArrayList<>();
		templateAvaliacaoList.add(this.createTemplateAvaliacao(2L));
		templateAvaliacaoList.add(this.createTemplateAvaliacao(4L));
		templateAvaliacaoList.add(this.createTemplateAvaliacao(6L));
		Page<TemplateAvaliacao> pageList = new PageImpl<>(templateAvaliacaoList, this.pageable, templateAvaliacaoList.size());

		Mockito.when(this.templateAvaliacaoService.findAllPaginated(this.pageable)).thenReturn(pageList);
		Assert.assertNotNull(this.templateAvaliacaoFacadeImpl.findAllPaginated(this.pageable));
		Assert.assertEquals(pageLisDto, this.templateAvaliacaoFacadeImpl.findAllPaginated(this.pageable));
	}

	@Test(expected = NullParameterException.class)
	public void testFindAllPaginatedFailedPageableNull(){
		this.templateAvaliacaoFacadeImpl.findAllPaginated(null);
	}

	@Test
	public void testSave(){
		this.templateAvaliacaoFacadeImpl.save(this.createTemplateAvaliacaoDTO(2L));
	}

	@Test(expected = NullParameterException.class)
	public void testSaveFailedDtoNull(){
		this.templateAvaliacaoFacadeImpl.save(null);
	}

	@Test
	public void testFindById(){
		Mockito.when(this.templateAvaliacaoService.findById(2L)).thenReturn(this.createTemplateAvaliacao(2L));
		Assert.assertNotNull(this.templateAvaliacaoFacadeImpl.findById(2L));
		Assert.assertEquals(this.createTemplateAvaliacaoDTO(2L), this.templateAvaliacaoFacadeImpl.findById(2L));
	}

	@Test(expected = NullParameterException.class)
	public void testFindByIdFailedIdNull(){
		this.templateAvaliacaoFacadeImpl.findById(null);
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
