package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.dto.impl.RespostaDTO;
import com.infnet.avaliacao.entity.Avaliacao;
import com.infnet.avaliacao.entity.Resposta;
import com.infnet.avaliacao.entity.TemplatePergunta;
import com.infnet.avaliacao.exception.NullParameterException;
import com.infnet.avaliacao.repository.RespostaRepository;
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
public class RespostaServiceImplUnitTest {

	@InjectMocks
	private RespostaServiceImpl respostaServiceImpl;

	@Mock
	private RespostaRepository respostaRepository;//Mesmo não sendo utlizado no codigo nos testes é necessário permanecer aqui

	@Mock
	private TemplatePerguntaRepository templatePerguntaRepository;

	@Test
	public void testSave(){
		List<RespostaDTO> respostaDTOList = new ArrayList<>();
		respostaDTOList.add(this.createRespostaDTO(4L));
		respostaDTOList.add(this.createRespostaDTO(2L));
		this.respostaServiceImpl.save(respostaDTOList);
	}

	@Test(expected = NullParameterException.class)
	public void testSaveFailedRespostaDTOListNull(){
		this.respostaServiceImpl.save(null);
	}

	@Test
	public void testPopularResposta(){
		Resposta resposta = this.createResposta(null);
		RespostaDTO respostaDTO = this.createRespostaDTO(null);
		Mockito.when(this.templatePerguntaRepository.getOne(4L)).thenReturn(resposta.getTemplatePergunta());
		Assert.assertNotNull(this.respostaServiceImpl.popularResposta("A", resposta.getTemplatePergunta().getId(), resposta.getAvaliacao()));
		Assert.assertEquals(respostaDTO, this.respostaServiceImpl.popularResposta("A", resposta.getTemplatePergunta().getId(), resposta.getAvaliacao()));
	}

	@Test(expected = NullParameterException.class)
	public void testPopularRespostaFailedRespostaNull(){
		Resposta resposta = this.createResposta(8L);
		this.respostaServiceImpl.popularResposta(null, resposta.getTemplatePergunta().getId(), resposta.getAvaliacao());
	}

	@Test(expected = NullParameterException.class)
	public void testPopularRespostaFailedIdTemplatePerguntaNull(){
		Resposta resposta = this.createResposta(8L);
		this.respostaServiceImpl.popularResposta("A", null, resposta.getAvaliacao());
	}

	@Test(expected = NullParameterException.class)
	public void testPopularRespostaFailedAvaliacaoNull(){
		Resposta resposta = this.createResposta(4L);
		this.respostaServiceImpl.popularResposta("A", resposta.getTemplatePergunta().getId(), null);
	}

	/**
	 * Métodos foram criados para auxiliar nos testes; ou seja; diminuir a codificação dos mesmos.
	 */

	private RespostaDTO createRespostaDTO(Long id){
		return RespostaDTO.toDto(this.createResposta(id));
	}

	private Resposta createResposta(Long id){
		Avaliacao avaliacao = new Avaliacao();
		avaliacao.setId(5L);

		TemplatePergunta templatePergunta = new TemplatePergunta();
		templatePergunta.setId(4L);

		Resposta resposta = new Resposta();
		resposta.setId(id);
		resposta.setValor("A");
		resposta.setAvaliacao(avaliacao);
		resposta.setTemplatePergunta(templatePergunta);
		return resposta;
	}


}
