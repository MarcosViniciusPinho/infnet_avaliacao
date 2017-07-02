package com.infnet.avaliacao.dto.impl;

import com.infnet.avaliacao.entity.Modulo;
import com.infnet.avaliacao.entity.Turma;
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
public class TurmaDTOUnitTest {

    @Test
    public void testToEntity(){
        Turma turma = this.createTurma(3L);
        TurmaDTO turmaDTO = this.createTurmaDTO(3L);
        Assert.assertNotNull(turmaDTO.toEntity());
        Assert.assertEquals(turma, turmaDTO.toEntity());
    }

    @Test
    public void testToDto(){
        Turma turma = this.createTurma(3L);
        TurmaDTO turmaDTO = this.createTurmaDTO(3L);
        Assert.assertNotNull(TurmaDTO.toDto(turma));
        Assert.assertEquals(turmaDTO, TurmaDTO.toDto(turma));
    }

    @Test(expected = NullParameterException.class)
    public void testToDtoFailedAlunoNull(){
        TurmaDTO.toDto(null);
    }

    /**
     * Métodos foram criados para auxiliar nos testes; ou seja; diminuir a codificação dos mesmos.
     */

    private TurmaDTO createTurmaDTO(Long id){
        List<AvaliacaoDTO> avaliacaoDTOList = new ArrayList<>();
        avaliacaoDTOList.add(this.createAvaliacaoDTO(4L));
        avaliacaoDTOList.add(this.createAvaliacaoDTO(1L));
        avaliacaoDTOList.add(this.createAvaliacaoDTO(7L));

        TurmaDTO turmaDTO = new TurmaDTO();
        turmaDTO.setId(id);
        turmaDTO.setNumero("765");
        turmaDTO.setModuloDTO(
                ModuloDTO.toDto(this.createModulo(2L)));
        turmaDTO.setAvaliacaoDTOList(avaliacaoDTOList);

        return turmaDTO;
    }

    private Turma createTurma(Long id){
        List<AvaliacaoDTO> avaliacaoDTOList = new ArrayList<>();
        avaliacaoDTOList.add(this.createAvaliacaoDTO(3L));
        avaliacaoDTOList.add(this.createAvaliacaoDTO(7L));
        avaliacaoDTOList.add(this.createAvaliacaoDTO(5L));

        Turma turma = new Turma();
        turma.setId(id);
        turma.setNumero("765");
        turma.setAvaliacaoList(
                AvaliacaoDTO.convertListDtoToListEntity(
                        avaliacaoDTOList));
        turma.setModulo(this.createModulo(2L));
        return turma;
    }

    private ModuloDTO createModuloDTO(Long id){
        return ModuloDTO.toDto(this.createModulo(id));

    }

    private Modulo createModulo(Long id){
        Modulo modulo = new Modulo();
        modulo.setId(id);
        return modulo;
    }

    private AvaliacaoDTO createAvaliacaoDTO(Long id){
        TemplateAvaliacaoDTO templateAvaliacaoDTO = new TemplateAvaliacaoDTO();
        templateAvaliacaoDTO.setId(3L);

        TurmaDTO turmaDTO = new TurmaDTO();
        turmaDTO.setId(1L);
        turmaDTO.setModuloDTO(this.createModuloDTO(2L));

        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(4L);

        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();
        avaliacaoDTO.setId(id);
        avaliacaoDTO.setTemplateAvaliacaoDTO(templateAvaliacaoDTO);
        avaliacaoDTO.setTurmaDTO(turmaDTO);
        avaliacaoDTO.setAlunoDTO(alunoDTO);
        return avaliacaoDTO;
    }

}
