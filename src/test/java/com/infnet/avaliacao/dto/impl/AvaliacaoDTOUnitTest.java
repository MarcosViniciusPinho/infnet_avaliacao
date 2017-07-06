package com.infnet.avaliacao.dto.impl;

import com.infnet.avaliacao.controller.wrapper.PerguntaAssociadaWrapper;
import com.infnet.avaliacao.entity.*;
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
public class AvaliacaoDTOUnitTest {

    @Test
    public void testToEntity(){
        Avaliacao avaliacao = this.createAvaliacao(3L);
        AvaliacaoDTO avaliacaoDTO = this.createAvaliacaoDTO(3L);
        Assert.assertNotNull(avaliacaoDTO.toEntity());
        Assert.assertEquals(avaliacao, avaliacaoDTO.toEntity());
    }

    @Test
    public void testToDto(){
        Avaliacao avaliacao = this.createAvaliacao(3L);
        AvaliacaoDTO avaliacaoDTO = this.createAvaliacaoDTO(3L);
        Assert.assertNotNull(AvaliacaoDTO.toDto(avaliacao));
        Assert.assertEquals(avaliacaoDTO, AvaliacaoDTO.toDto(avaliacao));
    }

    @Test(expected = NullParameterException.class)
    public void testToDtoFailedAvaliacaoNull(){
        AvaliacaoDTO.toDto(null);
    }

    @Test
    public void testConvertListDtoToListEntity(){
        List<Avaliacao> avaliacaoList = new ArrayList<>();
        avaliacaoList.add(this.createAvaliacao(2L));
        avaliacaoList.add(this.createAvaliacao(6L));
        avaliacaoList.add(this.createAvaliacao(4L));

        List<AvaliacaoDTO> avaliacaoDTOList = new ArrayList<>();
        avaliacaoDTOList.add(this.createAvaliacaoDTO(2L));
        avaliacaoDTOList.add(this.createAvaliacaoDTO(6L));
        avaliacaoDTOList.add(this.createAvaliacaoDTO(4L));

        Assert.assertNotNull(AvaliacaoDTO.convertListDtoToListEntity(avaliacaoDTOList));
        Assert.assertEquals(avaliacaoList, AvaliacaoDTO.convertListDtoToListEntity(avaliacaoDTOList));
    }

    @Test
    public void testConvertListDtoToListEntityEmpty(){
        Assert.assertNotNull(AvaliacaoDTO.convertListDtoToListEntity(new ArrayList<>()));
        Assert.assertEquals(new ArrayList<Avaliacao>(), AvaliacaoDTO.convertListDtoToListEntity(new ArrayList<>()));
    }

    @Test
    public void testConvertListEntityToListDto(){
        List<AvaliacaoDTO> avaliacaoDTOList = new ArrayList<>();
        avaliacaoDTOList.add(this.createAvaliacaoDTO(2L));
        avaliacaoDTOList.add(this.createAvaliacaoDTO(6L));
        avaliacaoDTOList.add(this.createAvaliacaoDTO(4L));

        List<Avaliacao> avaliacaoList = new ArrayList<>();
        avaliacaoList.add(this.createAvaliacao(2L));
        avaliacaoList.add(this.createAvaliacao(6L));
        avaliacaoList.add(this.createAvaliacao(4L));

        Assert.assertNotNull(AvaliacaoDTO.convertListEntityToListDto(avaliacaoList));
        Assert.assertEquals(avaliacaoDTOList, AvaliacaoDTO.convertListEntityToListDto(avaliacaoList));
    }

    @Test
    public void testConvertListEntityToListDtoEmpty(){
        Assert.assertNotNull(AvaliacaoDTO.convertListEntityToListDto(new ArrayList<>()));
        Assert.assertEquals(new ArrayList<AvaliacaoDTO>(), AvaliacaoDTO.convertListEntityToListDto(new ArrayList<>()));
    }

    @Test
    public void testIsExisteTemplateAvaliacaoAndTemplateTopico(){
        AvaliacaoDTO avaliacaoDTO = this.createAvaliacaoDTO(3L);
        Assert.assertNotNull(avaliacaoDTO.isExisteTemplateAvaliacaoAndTemplateTopico());
        Assert.assertEquals(Boolean.TRUE, avaliacaoDTO.isExisteTemplateAvaliacaoAndTemplateTopico());
    }

    @Test
    public void testIsExisteTemplateAvaliacaoAndTemplateTopicoTemplateAvaliacaoDTONull(){
        AvaliacaoDTO avaliacaoDTO = this.createAvaliacaoDTO(3L);
        avaliacaoDTO.setTemplateAvaliacaoDTO(null);
        Assert.assertNotNull(avaliacaoDTO.isExisteTemplateAvaliacaoAndTemplateTopico());
        Assert.assertEquals(Boolean.FALSE, avaliacaoDTO.isExisteTemplateAvaliacaoAndTemplateTopico());
    }

    @Test
    public void testShowNextTopicoComPergunta(){
        AvaliacaoDTO avaliacaoDTO = this.createAvaliacaoDTO(3L);
        List<PerguntaAssociadaWrapper> perguntaAssociadaWrapperList = new ArrayList<>();
        avaliacaoDTO.showNextTopicoComPergunta(perguntaAssociadaWrapperList, 1);
    }

    @Test
    public void testIsExisteProximoTopico(){
        AvaliacaoDTO avaliacaoDTO = this.createAvaliacaoDTO(3L);
        Assert.assertNotNull(avaliacaoDTO.isExisteProximoTopico());
        Assert.assertEquals(Boolean.TRUE, avaliacaoDTO.isExisteProximoTopico());
    }

    @Test
    public void testIsExisteProximoTopicoIndiceTopicoIgual(){
        AvaliacaoDTO avaliacaoDTO = this.createAvaliacaoDTO(3L);
        avaliacaoDTO.setIndiceTopico(3);
        Assert.assertNotNull(avaliacaoDTO.isExisteProximoTopico());
        Assert.assertEquals(Boolean.FALSE, avaliacaoDTO.isExisteProximoTopico());
    }

    @Test
    public void testIsExisteProximoTopicoIndiceTopicoMaior(){
        AvaliacaoDTO avaliacaoDTO = this.createAvaliacaoDTO(3L);
        avaliacaoDTO.setIndiceTopico(4);
        Assert.assertNotNull(avaliacaoDTO.isExisteProximoTopico());
        Assert.assertEquals(Boolean.FALSE, avaliacaoDTO.isExisteProximoTopico());
    }

    @Test
    public void testGetValorResposta(){
        AvaliacaoDTO avaliacaoDTO = this.createAvaliacaoDTO(3L);
        String valorEsperado = "fhsdjfhsdjf,fhsdjfhsdjh";
        String valorEnviado = "fhsdjfhsdjf00101100fhsdjfhsdjh";

        Assert.assertNotNull(avaliacaoDTO.getValorResposta(valorEnviado));
        Assert.assertEquals(valorEsperado, avaliacaoDTO.getValorResposta(valorEnviado));
    }

    @Test
    public void testGetIdTemplatePergunta(){
        AvaliacaoDTO avaliacaoDTO = this.createAvaliacaoDTO(3L);
        Long valorEsperado = 4L;
        String valorEnviado = "fhsdjfhsdjf00101100fhsdjfhsdjh-4";

        Assert.assertNotNull(avaliacaoDTO.getIdTemplatePergunta(valorEnviado));
        Assert.assertEquals(valorEsperado, avaliacaoDTO.getIdTemplatePergunta(valorEnviado));
    }

    /**
     * Métodos foram criados para auxiliar nos testes; ou seja; diminuir a codificação dos mesmos.
     */

    private Avaliacao createAvaliacao(Long id){
        List<Resposta> respostaList = new ArrayList<>();
        respostaList.add(this.createResposta(5L));
        respostaList.add(this.createResposta(8L));
        respostaList.add(this.createResposta(1L));

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setId(id);
        avaliacao.setTemplateAvaliacao(this.createTemplateAvaliacao(3L));
        avaliacao.setRespostaList(respostaList);
        avaliacao.setTurma(this.createTurma(4L));
        avaliacao.setAluno(this.createAluno(5L));
        return avaliacao;
    }

    private AvaliacaoDTO createAvaliacaoDTO(Long id){
        List<RespostaDTO> respostaDTOList = new ArrayList<>();
        respostaDTOList.add(this.createRespostaDTO(5L));
        respostaDTOList.add(this.createRespostaDTO(8L));
        respostaDTOList.add(this.createRespostaDTO(1L));

        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();
        avaliacaoDTO.setId(id);
        avaliacaoDTO.setTemplateAvaliacaoDTO(this.createTemplateAvaliacaoDTO(3L));
        avaliacaoDTO.setRespostaDTOList(respostaDTOList);
        avaliacaoDTO.setTurmaDTO(this.createTurmaDTO(4L));
        avaliacaoDTO.setAlunoDTO(this.createAlunoDTO(5L));
        avaliacaoDTO.setIndiceTopico(2);
        return avaliacaoDTO;
    }

    private RespostaDTO createRespostaDTO(Long id){
        return RespostaDTO.toDto(this.createResposta(id));

    }

    private Resposta createResposta(Long id){
        Resposta resposta = new Resposta();
        resposta.setId(id);
        resposta.setTemplatePergunta(this.createTemplatePergunta(id));
        return resposta;
    }

    private TemplatePergunta createTemplatePergunta(Long id){
        TemplatePergunta templatePergunta = new TemplatePergunta();
        templatePergunta.setId(id);
        return templatePergunta;
    }

    private TemplateAvaliacaoDTO createTemplateAvaliacaoDTO(Long id){
        return TemplateAvaliacaoDTO.toDto(this.createTemplateAvaliacao(id));
    }

    private TemplateAvaliacao createTemplateAvaliacao(Long id){
        List<TemplateTopico> templateTopicoList = new ArrayList<>();
        templateTopicoList.add(this.createTemplateTopico(4L));
        templateTopicoList.add(this.createTemplateTopico(1L));
        templateTopicoList.add(this.createTemplateTopico(8L));

        TemplateAvaliacao templateAvaliacao = new TemplateAvaliacao();
        templateAvaliacao.setId(id);
        templateAvaliacao.setTemplateTopicoList(templateTopicoList);
        return templateAvaliacao;
    }

    private TemplateTopico createTemplateTopico(Long id){
        TemplateTopico templateTopico = new TemplateTopico();
        templateTopico.setId(id);
        return templateTopico;
    }

    private TurmaDTO createTurmaDTO(Long id){
        return TurmaDTO.toDto(this.createTurma(id));
    }

    private Turma createTurma(Long id){
        Turma turma = new Turma();
        turma.setId(id);
        turma.setModulo(this.createModulo(8L));
        return turma;
    }

    private Modulo createModulo(Long id){
        Modulo modulo = new Modulo();
        modulo.setId(id);
        return modulo;
    }

    private AlunoDTO createAlunoDTO(Long id){
        return AlunoDTO.toDto(this.createAluno(id));
    }

    private Aluno createAluno(Long id){
        Aluno aluno = new Aluno();
        aluno.setId(id);
        return aluno;
    }



}
