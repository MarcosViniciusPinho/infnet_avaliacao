package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.dto.impl.RespostaDTO;
import com.infnet.avaliacao.entity.Avaliacao;
import com.infnet.avaliacao.entity.Resposta;
import com.infnet.avaliacao.entity.TemplatePergunta;
import com.infnet.avaliacao.exception.NullParameterException;
import com.infnet.avaliacao.repository.RespostaRepository;
import com.infnet.avaliacao.repository.TemplatePerguntaRepository;
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
		templatePergunta.setId(3L);

		Resposta resposta = new Resposta();
		resposta.setId(id);
		resposta.setAvaliacao(avaliacao);
		resposta.setTemplatePergunta(templatePergunta);
		return resposta;
	}


}
