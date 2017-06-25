package com.infnet.avaliacao.business.facade.impl;

import com.infnet.avaliacao.business.service.*;
import com.infnet.avaliacao.dto.impl.AvaliacaoDTO;
import com.infnet.avaliacao.dto.impl.RespostaDTO;
import com.infnet.avaliacao.entity.*;
import com.infnet.avaliacao.exception.NullParameterException;
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
public class AvaliacaoFacadeImplUnitTest {

	@InjectMocks
	private AvaliacaoFacadeImpl avaliacaoFacadeImpl;

	@Mock
	private AvaliacaoService avaliacaoService;

	@Mock
	private AlunoService alunoService;

	@Mock
	private TurmaService turmaService;

	@Mock
	private TemplateAvaliacaoService templateAvaliacaoService;

	@Mock
	private RespostaService respostaService;

	@Test
	public void testSave(){
		Avaliacao avaliacao = this.createAvaliacao(3L);
		AvaliacaoDTO avaliacaoDTO = this.createAvaliacaoDTO(3L);

		Mockito.when(this.avaliacaoService.save(avaliacaoDTO)).thenReturn(avaliacao);
		Mockito.when(this.respostaService.popularResposta("Opção 1", 4L, avaliacao)).thenReturn(this.createRespostaDTO(3L));
		Mockito.when(this.respostaService.popularResposta("Opção 2", 3L, avaliacao)).thenReturn(this.createRespostaDTO(5L));
		this.avaliacaoFacadeImpl.save(avaliacaoDTO);
	}

	@Test(expected = NullParameterException.class)
	public void testSaveFailedDtoNull(){
		this.avaliacaoFacadeImpl.save(null);
	}

	@Test
	public void testPopularAlunoAndTurmaParaAvaliacao(){
		Avaliacao avaliacao = this.createAvaliacao(null);
		AvaliacaoDTO avaliacaoDTO = AvaliacaoDTO.toDto(avaliacao);

		Mockito.when(this.turmaService.findTemplateAvaliacaoTurmaById(4L)).thenReturn(3L);
		Mockito.when(this.alunoService.findByCpf(5464894654L)).thenReturn(avaliacao.getAluno());
		Mockito.when(this.turmaService.findById(4L)).thenReturn(avaliacao.getTurma());
		Mockito.when(this.templateAvaliacaoService.findById(3L)).thenReturn(avaliacao.getTemplateAvaliacao());
		Assert.assertNotNull(this.avaliacaoFacadeImpl.popularAlunoAndTurmaParaAvaliacao(5464894654L, 4L));
		Assert.assertEquals(avaliacaoDTO, this.avaliacaoFacadeImpl.popularAlunoAndTurmaParaAvaliacao(5464894654L, 4L));
	}

	@Test(expected = NullParameterException.class)
	public void testPopularAlunoAndTurmaParaAvaliacaoFailedCpfNull(){
		this.avaliacaoFacadeImpl.popularAlunoAndTurmaParaAvaliacao(null, 4L);
	}

	@Test(expected = NullParameterException.class)
	public void testPopularAlunoAndTurmaParaAvaliacaoFailedIdTurmaNull(){
		this.avaliacaoFacadeImpl.popularAlunoAndTurmaParaAvaliacao(5464894654L, null);
	}

	@Test
	public void testVerificarParametrosEnviadosAoCarregarPagina(){
		this.avaliacaoFacadeImpl.verificarParametrosEnviadosAoCarregarPagina(8798798L, 6L);
	}

	@Test(expected = NullParameterException.class)
	public void testVerificarParametrosEnviadosAoCarregarPaginaFailedCpfNull(){
		this.avaliacaoFacadeImpl.verificarParametrosEnviadosAoCarregarPagina(null, 6L);
	}

	@Test(expected = NullParameterException.class)
	public void testVerificarParametrosEnviadosAoCarregarPaginaFailedIdNull(){
		this.avaliacaoFacadeImpl.verificarParametrosEnviadosAoCarregarPagina(8798798L, null);
	}

	@Test
	public void testVerificarSeAlunoJaRespondeuAvaliacao(){
		AvaliacaoDTO avaliacaoDTO = this.createAvaliacaoDTO(1L);
		this.avaliacaoFacadeImpl.verificarSeAlunoJaRespondeuAvaliacao(avaliacaoDTO.getTurmaDTO(), avaliacaoDTO.getAlunoDTO());
	}

	@Test(expected = NullParameterException.class)
	public void testVerificarSeAlunoJaRespondeuAvaliacaoFailedTurmaDTONull(){
		AvaliacaoDTO avaliacaoDTO = this.createAvaliacaoDTO(1L);
		this.avaliacaoFacadeImpl.verificarSeAlunoJaRespondeuAvaliacao(null, avaliacaoDTO.getAlunoDTO());
	}

	@Test(expected = NullParameterException.class)
	public void testVerificarSeAlunoJaRespondeuAvaliacaoFailedAlunoDTONull(){
		AvaliacaoDTO avaliacaoDTO = this.createAvaliacaoDTO(1L);
		this.avaliacaoFacadeImpl.verificarSeAlunoJaRespondeuAvaliacao(avaliacaoDTO.getTurmaDTO(), null);
	}

	/**
	 * Métodos foram criados para auxiliar nos testes; ou seja; diminuir a codificação dos mesmos.
	 */

	private RespostaDTO createRespostaDTO(Long id){
		RespostaDTO respostaDTO = new RespostaDTO();
		respostaDTO.setId(id);
		return respostaDTO;
	}

	private AvaliacaoDTO createAvaliacaoDTO(Long id){
		List<String> respostasSelecionadasComPerguntas = new ArrayList<>();
		respostasSelecionadasComPerguntas.add("Opção 1-4");
		respostasSelecionadasComPerguntas.add("Opção 2-3");
		AvaliacaoDTO avaliacaoDTO = AvaliacaoDTO.toDto(this.createAvaliacao(id));
		avaliacaoDTO.setRespostasSelecionadasComPerguntas(respostasSelecionadasComPerguntas);
		return avaliacaoDTO;
	}

	private Avaliacao createAvaliacao(Long id){
		TemplateAvaliacao templateAvaliacao = new TemplateAvaliacao();
		templateAvaliacao.setId(3L);

		Modulo modulo = new Modulo();
		modulo.setId(1L);

		Turma turma = new Turma();
		turma.setId(4L);
		turma.setModulo(modulo);

		Aluno aluno = new Aluno();
		aluno.setId(7L);
		aluno.setCpf(5464894654L);

		Avaliacao avaliacao = new Avaliacao();
		avaliacao.setId(id);
		avaliacao.setTemplateAvaliacao(templateAvaliacao);
		avaliacao.setTurma(turma);
		avaliacao.setAluno(aluno);
		return avaliacao;
	}

}
