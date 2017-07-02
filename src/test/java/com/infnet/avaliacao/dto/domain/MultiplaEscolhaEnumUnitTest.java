package com.infnet.avaliacao.dto.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultiplaEscolhaEnumUnitTest {

	@Test
	public void testMultiplaEscolhaConcordoTotalmente(){
		Assert.assertNotNull(MultiplaEscolhaEnum.CONCORDO_TOTALMENTE);
		Assert.assertEquals("template.avaliacao.concordo.totalmente", MultiplaEscolhaEnum.CONCORDO_TOTALMENTE.getId());
	}

	@Test
	public void testMultiplaEscolhaConcordo(){
		Assert.assertNotNull(MultiplaEscolhaEnum.CONCORDO);
		Assert.assertEquals("template.avaliacao.concordo", MultiplaEscolhaEnum.CONCORDO.getId());
	}

	@Test
	public void testMultiplaEscolhaNaoConcordoNemDiscordo(){
		Assert.assertNotNull(MultiplaEscolhaEnum.NAO_CONCORDO_NEM_DISCORDO);
		Assert.assertEquals("template.avaliacao.nao.concordo", MultiplaEscolhaEnum.NAO_CONCORDO_NEM_DISCORDO.getId());
	}

	@Test
	public void testMultiplaEscolhaDiscordo(){
		Assert.assertNotNull(MultiplaEscolhaEnum.DISCORDO);
		Assert.assertEquals("template.avaliacao.discordo", MultiplaEscolhaEnum.DISCORDO.getId());
	}

	@Test
	public void testMultiplaEscolhaDiscordoTotalmente(){
		Assert.assertNotNull(MultiplaEscolhaEnum.DISCORDO_TOTALMENTE);
		Assert.assertEquals("template.avaliacao.discordo.totalmente", MultiplaEscolhaEnum.DISCORDO_TOTALMENTE.getId());
	}

	@Test
	public void testMultiplaEscolhaNaoSeiAvaliar(){
		Assert.assertNotNull(MultiplaEscolhaEnum.NAO_SEI_AVALIAR);
		Assert.assertEquals("template.avaliacao.nao.sei.avaliar", MultiplaEscolhaEnum.NAO_SEI_AVALIAR.getId());
	}
}
