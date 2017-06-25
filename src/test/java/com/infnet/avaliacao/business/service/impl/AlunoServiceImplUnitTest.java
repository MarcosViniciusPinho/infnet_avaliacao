package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.entity.Aluno;
import com.infnet.avaliacao.exception.NotFoundException;
import com.infnet.avaliacao.exception.NullParameterException;
import com.infnet.avaliacao.repository.AlunoRepository;
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
public class AlunoServiceImplUnitTest {

	@InjectMocks
	private AlunoServiceImpl alunoServiceImpl;

	@Mock
	private AlunoRepository alunoRepository;

	@Test
	public void testVerificarSeExisteCpf(){
		Aluno aluno = this.createAluno(7L);
		Mockito.when(this.alunoRepository.findByCpf(5465456L)).thenReturn(aluno);
		this.alunoServiceImpl.verificarSeExisteCpf(5465456L);
	}

	@Test(expected = NotFoundException.class)
	public void testVerificarSeExisteCpfFailedCpfNaoEncontrado(){
		this.alunoServiceImpl.verificarSeExisteCpf(5465456L);
	}

	@Test(expected = NullParameterException.class)
	public void testVerificarSeExisteCpfFailedCpfNull(){
		this.alunoServiceImpl.verificarSeExisteCpf(null);
	}

	@Test
	public void testFindByCpf(){
		Aluno aluno = this.createAluno(7L);
		Mockito.when(this.alunoRepository.findByCpf(5465456L)).thenReturn(aluno);
		Assert.assertNotNull(this.alunoServiceImpl.findByCpf(5465456L));
		Assert.assertEquals(aluno, this.alunoServiceImpl.findByCpf(5465456L));
	}

	@Test(expected = NullParameterException.class)
	public void testFindByCpfFailedCpfNull(){
		this.alunoServiceImpl.findByCpf(null);
	}

	/**
	 * Métodos foram criados para auxiliar nos testes; ou seja; diminuir a codificação dos mesmos.
	 */

	private Aluno createAluno(Long id){
		Aluno aluno = new Aluno();
		aluno.setId(id);
		return aluno;
	}
}
