package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.dto.impl.TemplateAvaliacaoDTO;
import com.infnet.avaliacao.dto.impl.TemplateAvaliacaoTopicoPerguntaDTO;
import com.infnet.avaliacao.dto.impl.TemplatePerguntaDTO;
import com.infnet.avaliacao.dto.impl.TemplateTopicoDTO;
import com.infnet.avaliacao.entity.TemplateAvaliacao;
import com.infnet.avaliacao.entity.TemplateAvaliacaoTopicoPergunta;
import com.infnet.avaliacao.entity.TemplatePergunta;
import com.infnet.avaliacao.entity.TemplateTopico;
import com.infnet.avaliacao.exception.NullParameterException;
import com.infnet.avaliacao.repository.TemplateAvaliacaoTopicoPerguntaRepository;
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

	@Test
	public void testProduceAssociativeClass(){
		List<TemplateAvaliacaoTopicoPerguntaDTO> templateAvaliacaoTopicoPerguntaDTOList = new ArrayList<>();
		templateAvaliacaoTopicoPerguntaDTOList.add(this.createTemplateAvaliacaoTopicoPerguntaDTO(3L));
		templateAvaliacaoTopicoPerguntaDTOList.add(this.createTemplateAvaliacaoTopicoPerguntaDTO(null));

		List<TemplateAvaliacaoTopicoPergunta> templateAvaliacaoTopicoPerguntaList = new ArrayList<>();
		templateAvaliacaoTopicoPerguntaList.add(this.createTemplateAvaliacaoTopicoPergunta(3L));
		templateAvaliacaoTopicoPerguntaList.add(this.createTemplateAvaliacaoTopicoPergunta(7L));
		templateAvaliacaoTopicoPerguntaList.add(this.createTemplateAvaliacaoTopicoPergunta(5L));

		TemplateAvaliacaoTopicoPergunta templateAvaliacaoTopicoPergunta = this.createTemplateAvaliacaoTopicoPergunta(3L);

		List<TemplatePerguntaDTO> templatePerguntaDTOList = new ArrayList<>();
		templatePerguntaDTOList.add(this.createTemplatePerguntaDTO(4L));
		templatePerguntaDTOList.add(this.createTemplatePerguntaDTO(8L));

		TemplateAvaliacaoDTO templateAvaliacaoDTO = new TemplateAvaliacaoDTO();
		templateAvaliacaoDTO.setId(4L);

		TemplateTopicoDTO templateTopicoDTO = new TemplateTopicoDTO();
		templateTopicoDTO.setId(2L);
		List<Long> ids = new ArrayList<>();
		ids.add(1L);
		ids.add(9L);
		ids.add(5L);
		templateTopicoDTO.setIdsTemplatePerguntaSelecionados(ids);

		Mockito.when(this.templateAvaliacaoTopicoPerguntaRepository.findAllByTemplateAvaliacaoAndTemplateTopicoEquals(
				templateAvaliacaoTopicoPergunta.getTemplateAvaliacao(),
				templateAvaliacaoTopicoPergunta.getTemplateTopico())).thenReturn(templateAvaliacaoTopicoPerguntaList);

		Mockito.when(this.templateAvaliacaoTopicoPerguntaRepository.
				findByTemplateAvaliacaoAndTemplateTopicoAndTemplatePerguntaEquals(templateAvaliacaoDTO.toEntity(),
						templateAvaliacaoTopicoPergunta.getTemplateTopico(), templateAvaliacaoTopicoPergunta.getTemplatePergunta())).thenReturn(templateAvaliacaoTopicoPergunta);

		Assert.assertNotNull(this.templateAvaliacaoTopicoPerguntaServiceImpl.produceAssociativeClass(templatePerguntaDTOList,
				templateTopicoDTO, templateAvaliacaoDTO));
		Assert.assertEquals(templateAvaliacaoTopicoPerguntaDTOList, this.templateAvaliacaoTopicoPerguntaServiceImpl.produceAssociativeClass(templatePerguntaDTOList,
				templateTopicoDTO, templateAvaliacaoDTO));
	}

	@Test(expected = NullParameterException.class)
	public void testProduceAssociativeClassFailedTemplatePerguntaDTOListNull(){
		TemplateAvaliacaoDTO templateAvaliacaoDTO = new TemplateAvaliacaoDTO();
		templateAvaliacaoDTO.setId(4L);

		TemplateTopicoDTO templateTopicoDTO = new TemplateTopicoDTO();
		templateTopicoDTO.setId(2L);

		this.templateAvaliacaoTopicoPerguntaServiceImpl.produceAssociativeClass(null,
				templateTopicoDTO, templateAvaliacaoDTO);
	}

	@Test(expected = NullParameterException.class)
	public void testProduceAssociativeClassFailedTemplateTopicoDTONull(){
		TemplateAvaliacaoDTO templateAvaliacaoDTO = new TemplateAvaliacaoDTO();
		templateAvaliacaoDTO.setId(4L);

		List<TemplatePerguntaDTO> templatePerguntaDTOList = new ArrayList<>();
		templatePerguntaDTOList.add(this.createTemplatePerguntaDTO(4L));
		templatePerguntaDTOList.add(this.createTemplatePerguntaDTO(8L));

		this.templateAvaliacaoTopicoPerguntaServiceImpl.produceAssociativeClass(templatePerguntaDTOList,
				null, templateAvaliacaoDTO);
	}

	@Test(expected = NullParameterException.class)
	public void testProduceAssociativeClassFailedTemplateAvaliacaoDTONull(){
		TemplateTopicoDTO templateTopicoDTO = new TemplateTopicoDTO();
		templateTopicoDTO.setId(2L);

		List<TemplatePerguntaDTO> templatePerguntaDTOList = new ArrayList<>();
		templatePerguntaDTOList.add(this.createTemplatePerguntaDTO(4L));
		templatePerguntaDTOList.add(this.createTemplatePerguntaDTO(8L));

		this.templateAvaliacaoTopicoPerguntaServiceImpl.produceAssociativeClass(templatePerguntaDTOList,
				templateTopicoDTO, null);
	}

	/**
	 * Métodos foram criados para auxiliar nos testes; ou seja; diminuir a codificação dos mesmos.
	 */

	private TemplateAvaliacaoTopicoPerguntaDTO createTemplateAvaliacaoTopicoPerguntaDTO(Long id){
		return TemplateAvaliacaoTopicoPerguntaDTO.toDto(this.createTemplateAvaliacaoTopicoPergunta(id));
	}

	private TemplateAvaliacaoTopicoPergunta createTemplateAvaliacaoTopicoPergunta(Long id){
		TemplateAvaliacao templateAvaliacao = new TemplateAvaliacao();
		templateAvaliacao.setId(4L);

		TemplateAvaliacaoTopicoPergunta templateAvaliacaoTopicoPergunta = new TemplateAvaliacaoTopicoPergunta();
		templateAvaliacaoTopicoPergunta.setId(id);
		templateAvaliacaoTopicoPergunta.setTemplateAvaliacao(templateAvaliacao);
		templateAvaliacaoTopicoPergunta.setTemplateTopico(this.createTemplateTopico(2L));
		templateAvaliacaoTopicoPergunta.setTemplatePergunta(this.createTemplatePergunta(4L));
		templateAvaliacaoTopicoPergunta.setAtivo(Boolean.TRUE);
		return templateAvaliacaoTopicoPergunta;
	}

	private TemplatePerguntaDTO createTemplatePerguntaDTO(Long id){
		return TemplatePerguntaDTO.toDto(this.createTemplatePergunta(id));
	}

	private TemplatePergunta createTemplatePergunta(Long id){
		TemplatePergunta templatePergunta = new TemplatePergunta();
		templatePergunta.setId(id);
		return templatePergunta;
	}

	private TemplateTopico createTemplateTopico(Long id){
		TemplateTopico templateTopico = new TemplateTopico();
		templateTopico.setId(id);
		return templateTopico;
	}

}
