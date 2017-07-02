package com.infnet.avaliacao.dto.impl;

import com.infnet.avaliacao.entity.TemplatePergunta;
import com.infnet.avaliacao.exception.NullParameterException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TemplatePerguntaDTOUnitTest {

    @Test
    public void testToEntity(){
        TemplatePergunta templatePergunta = this.createTemplatePergunta(3L);
        TemplatePerguntaDTO templatePerguntaDTO = this.createTemplatePerguntaDTO(3L);
        Assert.assertNotNull(templatePerguntaDTO.toEntity());
        Assert.assertEquals(templatePergunta, templatePerguntaDTO.toEntity());
    }

    @Test
    public void testToDto(){
        TemplatePergunta templatePergunta = this.createTemplatePergunta(3L);
        TemplatePerguntaDTO templatePerguntaDTO = this.createTemplatePerguntaDTO(3L);
        Assert.assertNotNull(TemplatePerguntaDTO.toDto(templatePergunta));
        Assert.assertEquals(templatePerguntaDTO, TemplatePerguntaDTO.toDto(templatePergunta));
    }

    @Test(expected = NullParameterException.class)
    public void testToDtoFailedTemplatePerguntaNull(){
        TemplatePerguntaDTO.toDto(null);
    }

    /**
     * Métodos foram criados para auxiliar nos testes; ou seja; diminuir a codificação dos mesmos.
     */

    private TemplatePerguntaDTO createTemplatePerguntaDTO(Long id){
        TemplatePerguntaDTO templatePerguntaDTO = new TemplatePerguntaDTO();
        templatePerguntaDTO.setId(id);
        templatePerguntaDTO.setQuestao("Questao XPTO");
        return templatePerguntaDTO;
    }

    private TemplatePergunta createTemplatePergunta(Long id){
        TemplatePergunta templatePergunta = new TemplatePergunta();
        templatePergunta.setId(id);
        templatePergunta.setQuestao("Questao XPTO");
        return templatePergunta;
    }

}
