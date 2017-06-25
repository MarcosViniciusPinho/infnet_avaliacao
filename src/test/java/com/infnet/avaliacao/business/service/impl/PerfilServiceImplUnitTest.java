package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.entity.Perfil;
import com.infnet.avaliacao.repository.PerfilRepository;
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
public class PerfilServiceImplUnitTest {

	@InjectMocks
	private PerfilServiceImpl perfilServiceImpl;

	@Mock
	private PerfilRepository perfilRepository;

	@Test
	public void test(){
		List<Perfil> perfilList = new ArrayList<>();
		perfilList.add(this.createPerfil(1L));
		perfilList.add(this.createPerfil(7L));
		Mockito.when(this.perfilRepository.findAll()).thenReturn(perfilList);
		Assert.assertNotNull(this.perfilServiceImpl.findAll());
		Assert.assertEquals(perfilList, this.perfilServiceImpl.findAll());
	}

	/**
	 * Métodos foram criados para auxiliar nos testes; ou seja; diminuir a codificação dos mesmos.
	 */

	private Perfil createPerfil(Long id){
		Perfil perfil = new Perfil();
		perfil.setId(4L);
		return perfil;
	}

}
