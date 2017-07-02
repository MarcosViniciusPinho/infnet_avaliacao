package com.infnet.avaliacao.exception.util;

import com.infnet.avaliacao.exception.NullParameterException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParameterExceptionUtilUnitTest {

    @Test
    public void testValidateObjectNull(){
        ParameterExceptionUtil.validateObjectNull("Teste");
    }

    @Test(expected = NullParameterException.class)
    public void testValidateObjectNullValorNull(){
        ParameterExceptionUtil.validateObjectNull(null);
    }

}
