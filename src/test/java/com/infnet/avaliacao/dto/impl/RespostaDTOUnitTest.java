package com.infnet.avaliacao.dto.impl;

import com.infnet.avaliacao.entity.Avaliacao;
import com.infnet.avaliacao.entity.Resposta;
import com.infnet.avaliacao.entity.TemplatePergunta;
import com.infnet.avaliacao.exception.NullParameterException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RespostaDTOUnitTest {

    @Mock
    private Pageable pageable;

    @Test
    public void testToEntity(){
        Resposta respostaEsperada = this.createResposta(3L);
        RespostaDTO respostaDTO = this.createRespostaDTO(3L);
        Assert.assertNotNull(respostaDTO.toEntity());
        Assert.assertEquals(respostaEsperada, respostaDTO.toEntity());
    }

    @Test
    public void testToDto(){
        Resposta resposta = this.createResposta(3L);
        RespostaDTO respostaDTO = this.createRespostaDTO(3L);
        Assert.assertNotNull(RespostaDTO.toDto(resposta));
        Assert.assertEquals(respostaDTO, RespostaDTO.toDto(resposta));
    }

    @Test(expected = NullParameterException.class)
    public void testToDtoFailedUsuarioNull(){
        UsuarioDTO.toDto(null);
    }


    /**
     * Métodos foram criados para auxiliar nos testes; ou seja; diminuir a codificação dos mesmos.
     */

    private Resposta createResposta(Long id){
        Resposta resposta = new Resposta();
        resposta.setId(id);
        resposta.setTemplatePergunta(this.createTemplatePergunta(2L));
        resposta.setAvaliacao(this.createAvaliacao(1L));
        return resposta;
    }

    private RespostaDTO createRespostaDTO(Long id){
        RespostaDTO respostaDTO = new RespostaDTO();
        respostaDTO.setId(id);
        respostaDTO.setTemplatePerguntaDTO(this.createTemplatePerguntaDTO(2L));
        respostaDTO.setAvaliacao(this.createAvaliacao(1L));
        return respostaDTO;
    }

    private TemplatePerguntaDTO createTemplatePerguntaDTO(Long id){
        return TemplatePerguntaDTO.toDto(this.createTemplatePergunta(id));
    }

    private TemplatePergunta createTemplatePergunta(Long id){
        TemplatePergunta templatePergunta = new TemplatePergunta();
        templatePergunta.setId(id);
        return templatePergunta;
    }

    private Avaliacao createAvaliacao(Long id){
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setId(id);
        return avaliacao;
    }

}
