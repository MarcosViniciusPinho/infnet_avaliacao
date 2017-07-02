package com.infnet.avaliacao.mailer.impl;

import com.infnet.avaliacao.exception.NullParameterException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmtpMailSenderImplUnitTest {

	@InjectMocks
	private SmtpMailSenderImpl smtpMailSenderImpl;

	@Mock
	private JavaMailSender javaMailSender;

	@Mock
	private MimeMessage mimeMessage;

	@Test
	public void testEnviarEmail(){
		this.enviarEmail("Teste envio de email");
	}

	@Test(expected = NullParameterException.class)
	public void testEnviarEmailFailedEmailNull(){
		this.enviarEmail(null);
	}

	/**
	 * Métodos foram criados para auxiliar nos testes; ou seja; diminuir a codificação dos mesmos.
	 */

	private void enviarEmail(String email){
		try {
			Mockito.when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
			this.smtpMailSenderImpl.enviarEmail(email);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
