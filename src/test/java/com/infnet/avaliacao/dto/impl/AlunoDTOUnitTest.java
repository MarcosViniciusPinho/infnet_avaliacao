package com.infnet.avaliacao.dto.impl;

import com.infnet.avaliacao.entity.Aluno;
import com.infnet.avaliacao.exception.NullParameterException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlunoDTOUnitTest {

    @Test
    public void testToEntity(){
        Aluno aluno = this.createAluno(3L);
        AlunoDTO alunoDTO = this.createAlunoDTO(3L);
        Assert.assertNotNull(alunoDTO.toEntity());
        Assert.assertEquals(aluno, alunoDTO.toEntity());
    }

    @Test
    public void testToDto(){
        Aluno aluno = this.createAluno(3L);
        AlunoDTO alunoDTO = this.createAlunoDTO(3L);
        Assert.assertNotNull(AlunoDTO.toDto(aluno));
        Assert.assertEquals(alunoDTO, AlunoDTO.toDto(aluno));
    }

    @Test(expected = NullParameterException.class)
    public void testToDtoFailedAlunoNull(){
        AlunoDTO.toDto(null);
    }

    /**
     * Métodos foram criados para auxiliar nos testes; ou seja; diminuir a codificação dos mesmos.
     */

    private Aluno createAluno(Long id){
        Aluno aluno = new Aluno();
        aluno.setId(id);
        aluno.setNome("Marcos");
        aluno.setCpf(54564654L);
        aluno.setEmail("marcos@gmail.com");
        return aluno;
    }

    private AlunoDTO createAlunoDTO(Long id){
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(id);
        alunoDTO.setNome("Marcos");
        alunoDTO.setCpf(54564654L);
        alunoDTO.setEmail("marcos@gmail.com");
        return alunoDTO;
    }
}
