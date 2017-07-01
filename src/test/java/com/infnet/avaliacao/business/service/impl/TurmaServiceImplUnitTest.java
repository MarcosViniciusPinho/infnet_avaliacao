package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.entity.Turma;
import com.infnet.avaliacao.exception.NullParameterException;
import com.infnet.avaliacao.repository.TurmaRepository;
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
public class TurmaServiceImplUnitTest {

	@InjectMocks
	private TurmaServiceImpl turmaServiceImpl;

	@Mock
	private TurmaRepository turmaRepository;

	@Test
	public void testFindById(){
		Long id = 3L;
		Turma turma = this.createTurma(id);

		Mockito.when(this.turmaRepository.findById(id)).thenReturn(turma);

		Assert.assertNotNull(this.turmaServiceImpl.findById(id));
		Assert.assertEquals(turma, this.turmaServiceImpl.findById(id));
	}

	@Test(expected = NullParameterException.class)
	public void testFindByIdFailedIdNull(){
		this.turmaServiceImpl.findById(null);
	}

	/**
	 * Métodos foram criados para auxiliar nos testes; ou seja; diminuir a codificação dos mesmos.
	 */

	private Turma createTurma(Long id){
		Turma turma = new Turma();
		turma.setId(id);
		return turma;
	}

}
