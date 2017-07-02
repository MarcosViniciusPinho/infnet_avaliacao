package com.infnet.avaliacao.exception;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BusinessExceptionUnitTest {

    @Test
    public void testGetMultipleMessages(){
        Set<BusinessException> businessExceptionSet = new HashSet<>();
        businessExceptionSet.add(new BusinessException("Mensagem de erro 1"));
        businessExceptionSet.add(new BusinessException("Mensagem de erro 2"));
        businessExceptionSet.add(new BusinessException("Mensagem de erro 3"));

        BusinessException businessException = new BusinessException(businessExceptionSet);

        Assert.assertNotNull(businessException.getMultipleMessages());
        Assert.assertEquals(businessExceptionSet, businessException.getMultipleMessages());
    }

    @Test
    public void testGetMensagem(){
        BusinessException businessException = new BusinessException("Mensagem de erro 1");
        Assert.assertNotNull(businessException.getMensagem());
        Assert.assertEquals("Mensagem de erro 1", businessException.getMensagem());
    }

}
