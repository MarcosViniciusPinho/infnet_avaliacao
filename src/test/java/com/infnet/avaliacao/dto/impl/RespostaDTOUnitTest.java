package com.infnet.avaliacao.dto.impl;

import com.infnet.avaliacao.entity.Avaliacao;
import com.infnet.avaliacao.entity.Resposta;
import com.infnet.avaliacao.entity.TemplatePergunta;
import com.infnet.avaliacao.exception.NullParameterException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RespostaDTOUnitTest {

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

    @Test
    public void testConvertListDtoToListEntity(){
        List<Resposta> respostaList = new ArrayList<>();
        respostaList.add(this.createResposta(2L));
        respostaList.add(this.createResposta(6L));
        respostaList.add(this.createResposta(4L));

        List<RespostaDTO> respostaDTOList = new ArrayList<>();
        respostaDTOList.add(this.createRespostaDTO(2L));
        respostaDTOList.add(this.createRespostaDTO(6L));
        respostaDTOList.add(this.createRespostaDTO(4L));

        Assert.assertNotNull(RespostaDTO.convertListDtoToListEntity(respostaDTOList));
        Assert.assertEquals(respostaList, RespostaDTO.convertListDtoToListEntity(respostaDTOList));
    }

    @Test
    public void testConvertListDtoToListEntityEmpty(){
        Assert.assertNotNull(RespostaDTO.convertListDtoToListEntity(new ArrayList<>()));
        Assert.assertEquals(new ArrayList<Resposta>(), RespostaDTO.convertListDtoToListEntity(new ArrayList<>()));
    }

    @Test
    public void testConvertListEntityToListDto(){
        List<RespostaDTO> respostaDTOList = new ArrayList<>();
        respostaDTOList.add(this.createRespostaDTO(2L));
        respostaDTOList.add(this.createRespostaDTO(6L));
        respostaDTOList.add(this.createRespostaDTO(4L));

        List<Resposta> respostaList = new ArrayList<>();
        respostaList.add(this.createResposta(2L));
        respostaList.add(this.createResposta(6L));
        respostaList.add(this.createResposta(4L));

        Assert.assertNotNull(RespostaDTO.convertListEntityToListDto(respostaList));
        Assert.assertEquals(respostaDTOList, RespostaDTO.convertListEntityToListDto(respostaList));
    }

    @Test
    public void testConvertListEntityToListDtoEmpty(){
        Assert.assertNotNull(RespostaDTO.convertListEntityToListDto(new ArrayList<>()));
        Assert.assertEquals(new ArrayList<RespostaDTO>(), RespostaDTO.convertListEntityToListDto(new ArrayList<>()));
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
