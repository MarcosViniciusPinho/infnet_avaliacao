package com.infnet.avaliacao.controller;

import com.infnet.avaliacao.business.facade.AvaliacaoFacade;
import com.infnet.avaliacao.controller.util.ApplicationConstant;
import com.infnet.avaliacao.controller.util.MessageConstant;
import com.infnet.avaliacao.dto.impl.*;
import com.infnet.avaliacao.exception.NullParameterException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RespostaAvaliacaoControllerUnitTest {

	@InjectMocks
	private RespostaAvaliacaoController respostaAvaliacaoController;

	@Mock
	private AvaliacaoFacade avaliacaoFacade;

	@Test
	public void testSaveExibirBotaoSalvar(){
		Model model = new ExtendedModelMap();

		TemplateAvaliacaoDTO templateAvaliacaoDTO = new TemplateAvaliacaoDTO();
		templateAvaliacaoDTO.setId(7L);

		List<TemplateTopicoDTO> templateTopicoDTOList = new ArrayList<>();
		templateTopicoDTOList.add(this.createTemplateTopicoComTemplateAvaliacaoTopicoPergunta(22L, 5L, Boolean.TRUE, templateAvaliacaoDTO));
		templateTopicoDTOList.add(this.createTemplateTopicoComTemplateAvaliacaoTopicoPergunta(10L, 2L, Boolean.TRUE, templateAvaliacaoDTO));
		templateTopicoDTOList.add(this.createTemplateTopicoComTemplateAvaliacaoTopicoPergunta(1L, 15L, Boolean.TRUE, templateAvaliacaoDTO));
		templateTopicoDTOList.add(this.createTemplateTopicoComTemplateAvaliacaoTopicoPergunta(13L, 20L, Boolean.TRUE, templateAvaliacaoDTO));
		templateTopicoDTOList.add(this.createTemplateTopicoComTemplateAvaliacaoTopicoPergunta(52L, 3L, Boolean.TRUE, templateAvaliacaoDTO));
		templateTopicoDTOList.add(this.createTemplateTopicoComTemplateAvaliacaoTopicoPergunta(23L, 17L, Boolean.TRUE, templateAvaliacaoDTO));

		templateAvaliacaoDTO.setTemplateTopicoDTOList(templateTopicoDTOList);
		AvaliacaoDTO dto = this.getDto();
		AvaliacaoDTO avaliacaoDTO = this.getAvaliacaoDTO(templateAvaliacaoDTO);
		Mockito.when(this.avaliacaoFacade.popularAlunoAndTurmaParaAvaliacao(dto.getAlunoDTO().getCpf(), dto.getTurmaDTO().getId())).thenReturn(avaliacaoDTO);
		Assert.assertNotNull(this.respostaAvaliacaoController.save(dto, model));
		Assert.assertEquals("/resposta/avaliacao/form", this.respostaAvaliacaoController.save(dto, model));
		Assert.assertEquals(dto.getIndiceTopico(), avaliacaoDTO.getIndiceTopico());
		Assert.assertEquals(avaliacaoDTO.getTemplateAvaliacaoDTO().getTemplateTopicoDTOList().size(), avaliacaoDTO.getTotalTemplateTopicos());
		Assert.assertEquals(avaliacaoDTO.getRespostasSelecionadasComPerguntas(), dto.getRespostasSelecionadasComPerguntas());
		Assert.assertEquals(Boolean.FALSE, model.asMap().get(ApplicationConstant.EXIBIR_BOTAO_PROXIMO));
		Assert.assertEquals(Boolean.TRUE, model.asMap().get(ApplicationConstant.EXIBIR_BOTAO_SALVAR));
	}

	@Test
	public void testSaveExibirBotaoProximo(){
		Model model = new ExtendedModelMap();

		TemplateAvaliacaoDTO templateAvaliacaoDTO = new TemplateAvaliacaoDTO();
		templateAvaliacaoDTO.setId(7L);

		List<TemplateTopicoDTO> templateTopicoDTOList = new ArrayList<>();
		templateTopicoDTOList.add(this.createTemplateTopicoComTemplateAvaliacaoTopicoPergunta(22L, 5L, Boolean.TRUE, templateAvaliacaoDTO));
		templateTopicoDTOList.add(this.createTemplateTopicoComTemplateAvaliacaoTopicoPergunta(10L, 2L, Boolean.TRUE, templateAvaliacaoDTO));
		templateTopicoDTOList.add(this.createTemplateTopicoComTemplateAvaliacaoTopicoPergunta(1L, 15L, Boolean.TRUE, templateAvaliacaoDTO));
		templateTopicoDTOList.add(this.createTemplateTopicoComTemplateAvaliacaoTopicoPergunta(13L, 20L, Boolean.TRUE, templateAvaliacaoDTO));
		templateTopicoDTOList.add(this.createTemplateTopicoComTemplateAvaliacaoTopicoPergunta(52L, 3L, Boolean.TRUE, templateAvaliacaoDTO));
		templateTopicoDTOList.add(this.createTemplateTopicoComTemplateAvaliacaoTopicoPergunta(23L, 17L, Boolean.TRUE, templateAvaliacaoDTO));
		templateTopicoDTOList.add(this.createTemplateTopicoComTemplateAvaliacaoTopicoPergunta(50L, 24L, Boolean.TRUE, templateAvaliacaoDTO));

		templateAvaliacaoDTO.setTemplateTopicoDTOList(templateTopicoDTOList);
		AvaliacaoDTO dto = this.getDto();
		AvaliacaoDTO avaliacaoDTO = this.getAvaliacaoDTO(templateAvaliacaoDTO);
		Mockito.when(this.avaliacaoFacade.popularAlunoAndTurmaParaAvaliacao(dto.getAlunoDTO().getCpf(), dto.getTurmaDTO().getId())).thenReturn(avaliacaoDTO);
		Assert.assertNotNull(this.respostaAvaliacaoController.save(dto, model));
		Assert.assertEquals("/resposta/avaliacao/form", this.respostaAvaliacaoController.save(dto, model));
		Assert.assertEquals(dto.getIndiceTopico(), avaliacaoDTO.getIndiceTopico());
		Assert.assertEquals(avaliacaoDTO.getTemplateAvaliacaoDTO().getTemplateTopicoDTOList().size(), avaliacaoDTO.getTotalTemplateTopicos());
		Assert.assertEquals(avaliacaoDTO.getRespostasSelecionadasComPerguntas(), dto.getRespostasSelecionadasComPerguntas());
		Assert.assertEquals(Boolean.TRUE, model.asMap().get(ApplicationConstant.EXIBIR_BOTAO_PROXIMO));
		Assert.assertEquals(Boolean.FALSE, model.asMap().get(ApplicationConstant.EXIBIR_BOTAO_SALVAR));
	}

	@Test
	public void testSave(){
		Model model = new ExtendedModelMap();

		TemplateAvaliacaoDTO templateAvaliacaoDTO = new TemplateAvaliacaoDTO();
		templateAvaliacaoDTO.setId(7L);

		List<TemplateTopicoDTO> templateTopicoDTOList = new ArrayList<>();
		templateTopicoDTOList.add(this.createTemplateTopicoComTemplateAvaliacaoTopicoPergunta(22L, 5L, Boolean.TRUE, templateAvaliacaoDTO));
		templateTopicoDTOList.add(this.createTemplateTopicoComTemplateAvaliacaoTopicoPergunta(10L, 2L, Boolean.TRUE, templateAvaliacaoDTO));
		templateTopicoDTOList.add(this.createTemplateTopicoComTemplateAvaliacaoTopicoPergunta(1L, 15L, Boolean.TRUE, templateAvaliacaoDTO));
		templateTopicoDTOList.add(this.createTemplateTopicoComTemplateAvaliacaoTopicoPergunta(13L, 20L, Boolean.TRUE, templateAvaliacaoDTO));
		templateTopicoDTOList.add(this.createTemplateTopicoComTemplateAvaliacaoTopicoPergunta(50L, 24L, Boolean.TRUE, templateAvaliacaoDTO));

		templateAvaliacaoDTO.setTemplateTopicoDTOList(templateTopicoDTOList);
		AvaliacaoDTO dto = this.getDto();
		AvaliacaoDTO avaliacaoDTO = this.getAvaliacaoDTO(templateAvaliacaoDTO);
		Mockito.when(this.avaliacaoFacade.popularAlunoAndTurmaParaAvaliacao(dto.getAlunoDTO().getCpf(), dto.getTurmaDTO().getId())).thenReturn(avaliacaoDTO);
		Assert.assertNotNull(this.respostaAvaliacaoController.save(dto, model));
		Assert.assertEquals("redirect:/resposta/avaliacao/agradecimento", this.respostaAvaliacaoController.save(dto, model));
		Assert.assertEquals(dto.getIndiceTopico(), avaliacaoDTO.getIndiceTopico());
		Assert.assertEquals(avaliacaoDTO.getTemplateAvaliacaoDTO().getTemplateTopicoDTOList().size(), avaliacaoDTO.getTotalTemplateTopicos());
		Assert.assertEquals(avaliacaoDTO.getRespostasSelecionadasComPerguntas(), dto.getRespostasSelecionadasComPerguntas());
		Assert.assertEquals(Boolean.FALSE, model.asMap().get(ApplicationConstant.EXIBIR_BOTAO_PROXIMO));
		Assert.assertEquals(Boolean.FALSE, model.asMap().get(ApplicationConstant.EXIBIR_BOTAO_SALVAR));
	}

	@Test
	public void testSaveFalha(){
		Model model = new ExtendedModelMap();
		AvaliacaoDTO dto = this.getDto();
		Assert.assertNotNull(this.respostaAvaliacaoController.save(dto, model));
		Assert.assertEquals("/resposta/avaliacao/form", this.respostaAvaliacaoController.save(dto, model));
		Assert.assertNull(model.asMap().get(MessageConstant.ERROR));
	}

	@Test(expected = NullParameterException.class)
	public void testSaveFailedAvaliacaoDTONull(){
		Model model = new ExtendedModelMap();
		this.respostaAvaliacaoController.save(null, model);
	}

	@Test(expected = NullParameterException.class)
	public void testSaveFailedModelNull(){
		AvaliacaoDTO dto = this.getDto();
		this.respostaAvaliacaoController.save(dto, null);
	}

	@Test
	public void testPrepareCreate(){
		Model model = new ExtendedModelMap();
		TemplateAvaliacaoDTO templateAvaliacaoDTO = new TemplateAvaliacaoDTO();
		templateAvaliacaoDTO.setId(7L);

		List<TemplateTopicoDTO> templateTopicoDTOList = new ArrayList<>();
		templateTopicoDTOList.add(this.createTemplateTopicoComTemplateAvaliacaoTopicoPergunta(22L, 5L, Boolean.TRUE, templateAvaliacaoDTO));
		templateTopicoDTOList.add(this.createTemplateTopicoComTemplateAvaliacaoTopicoPergunta(10L, 2L, Boolean.TRUE, templateAvaliacaoDTO));
		templateTopicoDTOList.add(this.createTemplateTopicoComTemplateAvaliacaoTopicoPergunta(1L, 15L, Boolean.TRUE, templateAvaliacaoDTO));
		templateTopicoDTOList.add(this.createTemplateTopicoComTemplateAvaliacaoTopicoPergunta(13L, 20L, Boolean.TRUE, templateAvaliacaoDTO));
		templateTopicoDTOList.add(this.createTemplateTopicoComTemplateAvaliacaoTopicoPergunta(50L, 24L, Boolean.TRUE, templateAvaliacaoDTO));

		templateAvaliacaoDTO.setTemplateTopicoDTOList(templateTopicoDTOList);

		AvaliacaoDTO dto = this.getDto();
		AvaliacaoDTO avaliacaoDTO = this.getAvaliacaoDTO(templateAvaliacaoDTO);
		Mockito.when(this.avaliacaoFacade.popularAlunoAndTurmaParaAvaliacao(dto.getAlunoDTO().getCpf(), dto.getTurmaDTO().getId())).thenReturn(avaliacaoDTO);
		Assert.assertNotNull(this.respostaAvaliacaoController.prepareCreate(dto.getAlunoDTO().getCpf(), dto.getTurmaDTO().getId(), model));
		Assert.assertEquals("/resposta/avaliacao/form", this.respostaAvaliacaoController.prepareCreate(dto.getAlunoDTO().getCpf(), dto.getTurmaDTO().getId(), model));
		Assert.assertEquals(avaliacaoDTO, model.asMap().get("avaliacaoDTO"));
		Assert.assertEquals(Boolean.TRUE, model.asMap().get(ApplicationConstant.EXIBIR_BOTAO_PROXIMO));
		Assert.assertEquals(Boolean.FALSE, model.asMap().get(ApplicationConstant.EXIBIR_BOTAO_SALVAR));
	}

	@Test
	public void testPrepareCreateFalha(){
		Model model = new ExtendedModelMap();
		this.respostaAvaliacaoController.prepareCreate(4564564L, 79L, model);
		Assert.assertNull(model.asMap().get(MessageConstant.ERROR));
	}

	@Test(expected = NullParameterException.class)
	public void testPrepareCreateFailedCpfNull(){
		Model model = new ExtendedModelMap();
		this.respostaAvaliacaoController.prepareCreate(null, 79L, model);
	}

	@Test(expected = NullParameterException.class)
	public void testPrepareCreateFailedIdNull(){
		Model model = new ExtendedModelMap();
		this.respostaAvaliacaoController.prepareCreate(4564564L, null, model);
	}

	@Test(expected = NullParameterException.class)
	public void testPrepareCreateFailedModelNull(){
		this.respostaAvaliacaoController.prepareCreate(4564564L, 564L, null);
	}

	@Test
	public void testViewAgradecimento(){
		Assert.assertNotNull(this.respostaAvaliacaoController.viewAgradecimento());
		Assert.assertEquals("/resposta/avaliacao/agradecimento", this.respostaAvaliacaoController.viewAgradecimento());
	}

	@Test
	public void testGetFacade(){
		Assert.assertNotNull(this.avaliacaoFacade);
		Assert.assertEquals(this.avaliacaoFacade, this.respostaAvaliacaoController.getFacade());
	}

	@Test
	public void testGetPathView(){
		Assert.assertNotNull(this.respostaAvaliacaoController.getPathView());
		Assert.assertEquals("/resposta/avaliacao", this.respostaAvaliacaoController.getPathView());
	}

	/**
	 * Métodos foram criados para auxiliar nos testes; ou seja; diminuir a codificação dos mesmos.
	 */

	private AvaliacaoDTO getAvaliacaoDTO(TemplateAvaliacaoDTO templateAvaliacaoDTO){
		AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();
		avaliacaoDTO.setId(1L);
		avaliacaoDTO.setTemplateAvaliacaoDTO(templateAvaliacaoDTO);
		return avaliacaoDTO;
	}

	private AvaliacaoDTO getDto(){
		AvaliacaoDTO dto = new AvaliacaoDTO();
		dto.setIndiceTopico(5);

		AlunoDTO alunoDTO = new AlunoDTO();
		alunoDTO.setCpf(2343423L);
		dto.setAlunoDTO(alunoDTO);

		TurmaDTO turmaDTO = new TurmaDTO();
		turmaDTO.setId(43L);
		dto.setTurmaDTO(turmaDTO);

		List<String> listaRespostaComPerguntasSelecionadas = new ArrayList<>();
		listaRespostaComPerguntasSelecionadas.add("pergunta 1");
		listaRespostaComPerguntasSelecionadas.add("pergunta 2");
		listaRespostaComPerguntasSelecionadas.add("pergunta 3");

		dto.setRespostasSelecionadasComPerguntas(listaRespostaComPerguntasSelecionadas);
		return dto;
	}

	private TemplateTopicoDTO createTemplateTopicoComTemplateAvaliacaoTopicoPergunta(Long idTopico, Long idAvaliacaoTopicoPergunta, boolean status, TemplateAvaliacaoDTO templateAvaliacaoDTO){
		TemplateTopicoDTO templateTopicoDTO = new TemplateTopicoDTO();
		templateTopicoDTO.setId(idTopico);

		TemplatePerguntaDTO templatePerguntaDTO = new TemplatePerguntaDTO();
		templatePerguntaDTO.setId(4L);

		TemplateAvaliacaoTopicoPerguntaDTO templateAvaliacaoTopicoPerguntaDTO = new TemplateAvaliacaoTopicoPerguntaDTO();
		templateAvaliacaoTopicoPerguntaDTO.setId(idAvaliacaoTopicoPergunta);
		templateAvaliacaoTopicoPerguntaDTO.setAtivo(status);
		templateAvaliacaoTopicoPerguntaDTO.setTemplateTopico(templateTopicoDTO.toEntity());
		templateAvaliacaoTopicoPerguntaDTO.setTemplateAvaliacao(templateAvaliacaoDTO.toEntity());
		templateAvaliacaoTopicoPerguntaDTO.setTemplatePergunta(templatePerguntaDTO.toEntity());

		List<TemplateAvaliacaoTopicoPerguntaDTO> templateAvaliacaoTopicoPerguntaDTOList = new ArrayList<>();
		templateAvaliacaoTopicoPerguntaDTOList.add(templateAvaliacaoTopicoPerguntaDTO);

		templateTopicoDTO.setTemplateAvaliacaoTopicoPerguntaDTOList(templateAvaliacaoTopicoPerguntaDTOList);
		return templateTopicoDTO;
	}

}
