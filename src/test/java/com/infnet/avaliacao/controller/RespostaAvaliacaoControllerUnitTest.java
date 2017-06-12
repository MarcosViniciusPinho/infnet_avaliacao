package com.infnet.avaliacao.controller;

import com.infnet.avaliacao.business.facade.AvaliacaoFacade;
import com.infnet.avaliacao.controller.util.ApplicationConstant;
import com.infnet.avaliacao.dto.impl.*;
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
		AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();
		avaliacaoDTO.setId(1L);

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
		avaliacaoDTO.setTemplateAvaliacaoDTO(templateAvaliacaoDTO);

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

		Mockito.when(this.avaliacaoFacade.popularAlunoAndTurmaParaAvaliacao(dto.getAlunoDTO().getCpf(), dto.getTurmaDTO().getId())).thenReturn(avaliacaoDTO);

		Assert.assertNotNull(this.respostaAvaliacaoController.save(dto, model));
		Assert.assertEquals("/resposta/avaliacao/form", this.respostaAvaliacaoController.save(dto, model));
		Assert.assertEquals(dto.getIndiceTopico(), avaliacaoDTO.getIndiceTopico());
		Assert.assertEquals(avaliacaoDTO.getTemplateAvaliacaoDTO().getTemplateTopicoDTOList().size(), avaliacaoDTO.getTotalTemplateTopicos());
		Assert.assertEquals(avaliacaoDTO.getRespostasSelecionadasComPerguntas(), dto.getRespostasSelecionadasComPerguntas());
		Assert.assertEquals(Boolean.FALSE, model.asMap().get(ApplicationConstant.EXIBIR_BOTAO_PROXIMO));
		Assert.assertEquals(Boolean.TRUE, model.asMap().get(ApplicationConstant.EXIBIR_BOTAO_SALVAR));
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
