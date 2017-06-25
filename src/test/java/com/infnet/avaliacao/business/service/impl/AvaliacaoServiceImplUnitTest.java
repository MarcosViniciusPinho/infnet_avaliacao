package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.dto.impl.AvaliacaoDTO;
import com.infnet.avaliacao.entity.*;
import com.infnet.avaliacao.exception.NullParameterException;
import com.infnet.avaliacao.exception.UniqueException;
import com.infnet.avaliacao.repository.AvaliacaoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AvaliacaoServiceImplUnitTest {

	@InjectMocks
	private AvaliacaoServiceImpl avaliacaoServiceImpl;

	@Mock
	private AvaliacaoRepository avaliacaoRepository;

	@Test
	public void testSave(){
		Avaliacao avaliacao = this.createAvaliacao(4L);
		AvaliacaoDTO avaliacaoDTO = this.createAvaliacaoDTO(4L);
		Mockito.when(this.avaliacaoRepository.save(avaliacao)).thenReturn(avaliacao);
		Assert.assertNotNull(this.avaliacaoServiceImpl.save(avaliacaoDTO));
		Assert.assertEquals(avaliacao, this.avaliacaoServiceImpl.save(avaliacaoDTO));
	}

	@Test(expected = NullParameterException.class)
	public void testSaveFailedAvaliacaoDTONull(){
		this.avaliacaoServiceImpl.save(null);
	}

	@Test(expected = UniqueException.class)
	public void testVerificarSeAlunoJaRespondeuAvaliacao(){
		Avaliacao avaliacao = this.createAvaliacao(4L);
		Mockito.when(this.avaliacaoRepository.findByTurmaAndAluno(avaliacao.getTurma(), avaliacao.getAluno())).thenReturn(avaliacao);
		this.avaliacaoServiceImpl.verificarSeAlunoJaRespondeuAvaliacao(avaliacao.getTurma(), avaliacao.getAluno());
	}

	@Test
	public void testVerificarSeAlunoJaRespondeuAvaliacaoAlunoNaoRespondeuAvaliacao(){
		Avaliacao avaliacao = this.createAvaliacao(4L);
		this.avaliacaoServiceImpl.verificarSeAlunoJaRespondeuAvaliacao(avaliacao.getTurma(), avaliacao.getAluno());
	}

	@Test(expected = NullParameterException.class)
	public void testVerificarSeAlunoJaRespondeuAvaliacaoFailedTurmaNull(){
		Avaliacao avaliacao = this.createAvaliacao(4L);
		this.avaliacaoServiceImpl.verificarSeAlunoJaRespondeuAvaliacao(null, avaliacao.getAluno());
	}

	@Test(expected = NullParameterException.class)
	public void testVerificarSeAlunoJaRespondeuAvaliacaoFailedAlunoNull(){
		Avaliacao avaliacao = this.createAvaliacao(4L);
		this.avaliacaoServiceImpl.verificarSeAlunoJaRespondeuAvaliacao(avaliacao.getTurma(), null);
	}

	/**
	 * Métodos foram criados para auxiliar nos testes; ou seja; diminuir a codificação dos mesmos.
	 */

	private Avaliacao createAvaliacao(Long id){
		TemplateAvaliacao templateAvaliacao = new TemplateAvaliacao();
		templateAvaliacao.setId(7L);

		Modulo modulo = new Modulo();
		modulo.setId(1L);

		Turma turma = new Turma();
		turma.setId(2L);
		turma.setModulo(modulo);

		Aluno aluno = new Aluno();
		aluno.setId(3L);
		aluno.setCpf(5464894654L);

		Avaliacao avaliacao = new Avaliacao();
		avaliacao.setId(id);
		avaliacao.setTemplateAvaliacao(templateAvaliacao);
		avaliacao.setTurma(turma);
		avaliacao.setAluno(aluno);
		return avaliacao;
	}

	private AvaliacaoDTO createAvaliacaoDTO(Long id){
		return AvaliacaoDTO.toDto(this.createAvaliacao(id));
	}

}
