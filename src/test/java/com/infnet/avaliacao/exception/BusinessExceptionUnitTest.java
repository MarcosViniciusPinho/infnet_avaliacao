package com.infnet.avaliacao.exception;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

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
