package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.dto.impl.TemplateAvaliacaoDTO;
import com.infnet.avaliacao.dto.impl.TemplatePerguntaDTO;
import com.infnet.avaliacao.dto.impl.TemplateTopicoDTO;
import com.infnet.avaliacao.entity.TemplateAvaliacao;
import com.infnet.avaliacao.entity.TemplateAvaliacaoTopicoPergunta;
import com.infnet.avaliacao.entity.TemplatePergunta;
import com.infnet.avaliacao.entity.TemplateTopico;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

	@Mock
	private Pageable pageable;

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

	@Test
	public void testFindAllComCheckedPerguntasMarcadas(){
		List<TemplatePerguntaDTO> templatePerguntaDTOList = new ArrayList<>();
		templatePerguntaDTOList.add(this.createTemplatePerguntaDTO(2L));
		templatePerguntaDTOList.add(this.createTemplatePerguntaDTO(9L));

		Page<TemplatePerguntaDTO> pageList = new PageImpl<>(templatePerguntaDTOList, this.pageable, templatePerguntaDTOList.size());

		TemplateAvaliacaoDTO templateAvaliacaoDTO = this.createTemplateAvaliacaoDTO(88L);
		TemplateTopicoDTO templateTopicoDTO = this.createTemplateTopicoDTO(92L);

		TemplateTopico templateTopico = this.createTemplateTopico(92L);
		TemplateAvaliacao templateAvaliacao = this.createTemplateAvaliacao(88L);

		TemplatePergunta templatePergunta = this.createTemplatePergunta(2L);

		TemplateAvaliacaoTopicoPergunta templateAvaliacaoTopicoPergunta = this.createTemplateAvaliacaoTopicoPergunta(1L,
				templateAvaliacao, templateTopico, templatePergunta);

		Mockito.when(this.templateAvaliacaoTopicoPerguntaRepository.
				findByTemplateAvaliacaoAndTemplateTopicoAndTemplatePerguntaEquals(templateAvaliacao,
						templateTopico, templatePergunta)).thenReturn(templateAvaliacaoTopicoPergunta);
		Assert.assertNotNull(this.templatePerguntaServiceImpl.findAllComCheckedPerguntasMarcadas(templateTopicoDTO, templateAvaliacaoDTO, pageList));
		Assert.assertEquals(pageList, this.templatePerguntaServiceImpl.findAllComCheckedPerguntasMarcadas(templateTopicoDTO, templateAvaliacaoDTO, pageList));
		Assert.assertEquals(Boolean.TRUE, this.templatePerguntaServiceImpl.findAllComCheckedPerguntasMarcadas(templateTopicoDTO,
				templateAvaliacaoDTO, pageList).getContent().get(0).isChecked());
		Assert.assertEquals(Boolean.FALSE, this.templatePerguntaServiceImpl.findAllComCheckedPerguntasMarcadas(templateTopicoDTO,
				templateAvaliacaoDTO, pageList).getContent().get(1).isChecked());
	}

	@Test(expected = NullParameterException.class)
	public void testFindAllComCheckedPerguntasMarcadasFailedTemplateTopicoDTONull(){
		List<TemplatePerguntaDTO> templatePerguntaDTOList = new ArrayList<>();
		templatePerguntaDTOList.add(this.createTemplatePerguntaDTO(2L));
		templatePerguntaDTOList.add(this.createTemplatePerguntaDTO(9L));

		Page<TemplatePerguntaDTO> pageList = new PageImpl<>(templatePerguntaDTOList, this.pageable, templatePerguntaDTOList.size());
		TemplateAvaliacaoDTO templateAvaliacaoDTO = this.createTemplateAvaliacaoDTO(88L);
		this.templatePerguntaServiceImpl.findAllComCheckedPerguntasMarcadas(null, templateAvaliacaoDTO, pageList);
	}

	@Test(expected = NullParameterException.class)
	public void testFindAllComCheckedPerguntasMarcadasFailedTemplateAvaliacaoDTONull(){
		List<TemplatePerguntaDTO> templatePerguntaDTOList = new ArrayList<>();
		templatePerguntaDTOList.add(this.createTemplatePerguntaDTO(2L));
		templatePerguntaDTOList.add(this.createTemplatePerguntaDTO(9L));

		Page<TemplatePerguntaDTO> pageList = new PageImpl<>(templatePerguntaDTOList, this.pageable, templatePerguntaDTOList.size());

		TemplateTopicoDTO templateTopicoDTO = this.createTemplateTopicoDTO(92L);

		this.templatePerguntaServiceImpl.findAllComCheckedPerguntasMarcadas(templateTopicoDTO, null, pageList);
	}

	@Test(expected = NullParameterException.class)
	public void testFindAllComCheckedPerguntasMarcadasFailedTemplatePerguntaDTOListNull(){
		TemplateTopicoDTO templateTopicoDTO = this.createTemplateTopicoDTO(92L);
		TemplateAvaliacaoDTO templateAvaliacaoDTO = this.createTemplateAvaliacaoDTO(88L);

		this.templatePerguntaServiceImpl.findAllComCheckedPerguntasMarcadas(templateTopicoDTO, templateAvaliacaoDTO, null);
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

	private TemplateAvaliacao createTemplateAvaliacao(Long id){
		TemplateAvaliacao templateAvaliacao = new TemplateAvaliacao();
		templateAvaliacao.setId(id);
		return templateAvaliacao;
	}

	private TemplateAvaliacaoDTO createTemplateAvaliacaoDTO(Long id){
		return TemplateAvaliacaoDTO.toDto(this.createTemplateAvaliacao(id));
	}

	private TemplateTopico createTemplateTopico(Long id){
		TemplateTopico templateTopico = new TemplateTopico();
		templateTopico.setId(id);
		return templateTopico;
	}

	private TemplateTopicoDTO createTemplateTopicoDTO(Long id){
		return TemplateTopicoDTO.toDto(this.createTemplateTopico(id));
	}

	private TemplateAvaliacaoTopicoPergunta createTemplateAvaliacaoTopicoPergunta(Long id, TemplateAvaliacao templateAvaliacao,
																				  TemplateTopico templateTopico, TemplatePergunta templatePergunta){
		TemplateAvaliacaoTopicoPergunta templateAvaliacaoTopicoPergunta = new TemplateAvaliacaoTopicoPergunta();
		templateAvaliacaoTopicoPergunta.setId(id);
		templateAvaliacaoTopicoPergunta.setTemplateAvaliacao(templateAvaliacao);
		templateAvaliacaoTopicoPergunta.setTemplateTopico(templateTopico);
		templateAvaliacaoTopicoPergunta.setTemplatePergunta(templatePergunta);
		templateAvaliacaoTopicoPergunta.setAtivo(Boolean.TRUE);
		return templateAvaliacaoTopicoPergunta;
	}

}
