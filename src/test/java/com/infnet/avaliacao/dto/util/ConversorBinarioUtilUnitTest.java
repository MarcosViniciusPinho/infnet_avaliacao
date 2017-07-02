package com.infnet.avaliacao.dto.util;

import com.infnet.avaliacao.exception.NullParameterException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConversorBinarioUtilUnitTest {

	@Test
	public void testConverterBinaryToCharacterInString(){
		String valor = "230010110065450010110156456";
		String valorEsperado = "23,6545-56456";
		Assert.assertNotNull(ConversorBinarioUtil.converterBinaryToCharacterInString(valor));
		Assert.assertEquals(valorEsperado, ConversorBinarioUtil.converterBinaryToCharacterInString(valor));
	}

	@Test(expected = NullParameterException.class)
	public void testConverterBinaryToCharacterInStringFailedValorNull(){
		ConversorBinarioUtil.converterBinaryToCharacterInString(null);
	}

}
