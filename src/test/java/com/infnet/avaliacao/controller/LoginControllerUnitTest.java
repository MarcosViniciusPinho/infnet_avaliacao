package com.infnet.avaliacao.controller;

import com.infnet.avaliacao.controller.util.ViewConstant;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginControllerUnitTest {

	@InjectMocks
	private LoginController loginController;

	@Mock
	private User user;

	@Test
	public void testHome(){
		Assert.assertNotNull(this.loginController.home());
		Assert.assertEquals(ViewConstant.VIEW_HOME, this.loginController.home());
	}

	@Test
	public void testLoginComUsuarioExistente(){
		Assert.assertNotNull(this.loginController.login(user));
		Assert.assertEquals("redirect:/", this.loginController.login(user));
	}

	@Test
	public void testLoginComUsuarioInexistente(){
		Assert.assertNotNull(this.loginController.login(null));
		Assert.assertEquals(ViewConstant.VIEW_LOGIN, this.loginController.login(null));
	}

	@Test
	public void testAcessoNegado(){
		Assert.assertNotNull(this.loginController.acessoNegado());
		Assert.assertEquals(ViewConstant.VIEW_ACESSO_NEGADO, this.loginController.acessoNegado());
	}

}
