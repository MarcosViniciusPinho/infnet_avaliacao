package com.infnet.avaliacao.dto.impl;

import com.infnet.avaliacao.dto.domain.MultiplaEscolhaEnum;
import com.infnet.avaliacao.entity.TemplatePergunta;
import com.infnet.avaliacao.exception.NullParameterException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TemplatePerguntaDTOUnitTest {

    @Mock
    private Pageable pageable;

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

    @Test
    public void testConvertListEntityToListDto(){
        List<TemplatePerguntaDTO> templatePerguntaDTOList = new ArrayList<>();
        templatePerguntaDTOList.add(this.createTemplatePerguntaDTO(2L));
        templatePerguntaDTOList.add(this.createTemplatePerguntaDTO(6L));
        templatePerguntaDTOList.add(this.createTemplatePerguntaDTO(4L));

        List<TemplatePergunta> templatePerguntaList = new ArrayList<>();
        templatePerguntaList.add(this.createTemplatePergunta(2L));
        templatePerguntaList.add(this.createTemplatePergunta(6L));
        templatePerguntaList.add(this.createTemplatePergunta(4L));

        Assert.assertNotNull(TemplatePerguntaDTO.convertListEntityToListDto(templatePerguntaList));
        Assert.assertEquals(templatePerguntaDTOList, TemplatePerguntaDTO.convertListEntityToListDto(templatePerguntaList));
    }

    @Test
    public void testConvertListEntityToListDtoEmpty(){
        Assert.assertNotNull(TemplatePerguntaDTO.convertListEntityToListDto(new ArrayList<>()));
        Assert.assertEquals(new ArrayList<TemplatePerguntaDTO>(), TemplatePerguntaDTO.convertListEntityToListDto(new ArrayList<>()));
    }

    @Test
    public void testConvertPageEntityToPageDto(){
        List<TemplatePerguntaDTO> templatePerguntaDTOList = new ArrayList<>();
        templatePerguntaDTOList.add(this.createTemplatePerguntaDTO(7L));
        templatePerguntaDTOList.add(this.createTemplatePerguntaDTO(2L));
        templatePerguntaDTOList.add(this.createTemplatePerguntaDTO(5L));

        Page<TemplatePerguntaDTO> pageDtoList = new PageImpl<>(templatePerguntaDTOList, pageable, templatePerguntaDTOList.size());

        List<TemplatePergunta> templatePerguntaList = new ArrayList<>();
        templatePerguntaList.add(this.createTemplatePergunta(7L));
        templatePerguntaList.add(this.createTemplatePergunta(2L));
        templatePerguntaList.add(this.createTemplatePergunta(5L));

        Page<TemplatePergunta> pageList = new PageImpl<>(templatePerguntaList, pageable, templatePerguntaList.size());

        Assert.assertNotNull(TemplatePerguntaDTO.convertPageEntityToPageDto(pageList, this.pageable));
        Assert.assertEquals(pageDtoList, TemplatePerguntaDTO.convertPageEntityToPageDto(pageList, this.pageable));
    }

    @Test
    public void testConvertPageEntityToPageDtoEmpty(){
        Page<TemplatePerguntaDTO> pageDtoList = new PageImpl<>(new ArrayList<>(), pageable, 0);
        Page<TemplatePergunta> pageList = new PageImpl<>(new ArrayList<>(), pageable, 0);
        Assert.assertNotNull(TemplatePerguntaDTO.convertPageEntityToPageDto(pageList, this.pageable));
        Assert.assertEquals(pageDtoList, TemplatePerguntaDTO.convertPageEntityToPageDto(pageList, this.pageable));
    }

    @Test
    public void testGetMultiplaEscolhaEnumList(){
        TemplatePerguntaDTO templatePerguntaDTO = this.createTemplatePerguntaDTO(3L);
        List<MultiplaEscolhaEnum> multiplaEscolhaEnumList = Arrays.asList(MultiplaEscolhaEnum.values());
        Assert.assertNotNull(templatePerguntaDTO.getMultiplaEscolhaEnumList());
        Assert.assertEquals(multiplaEscolhaEnumList, templatePerguntaDTO.getMultiplaEscolhaEnumList());
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
