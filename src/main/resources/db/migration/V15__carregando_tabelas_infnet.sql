--BLOCO
insert into bloco(id, descricao)
select 1 , 'A' union all
select 2 , 'B' union all
select 3 , 'C' union all
select 4 , 'D';

--MODULO
insert into modulo(id,id_bloco,descricao)
select 1, 1, 'Engenharia de Software Aplicada' union all
select 2, 1, 'Métrica de Software' union all
select 3, 1, 'Projeto de Bloco' union all
select 4, 2, 'Processos Ágeis de Desenvolvimento de Software' union all
select 5, 2, 'Modelagem de Software' union all
select 6, 2, 'Projeto de Bloco' union all
select 7, 3, 'Tecnologia Java' union all
select 8, 3, 'Persistência em Java' union all
select 9, 3, 'Projeto de Bloco' union all
select 10, 4, 'Aplicações Web com Tecnologia Java' union all
select 11, 4, 'Serviços com Tecnologia Java' union all
select 12, 4, 'Projeto de Bloco' union all
select 13, 4, 'TCC';

--PROFESSOR
insert into professor(id,nome)
select 1, 'Wilder' union all
select 2, 'Marcos' union all
select 3, 'Jessica';

--ALUNO
insert into aluno(id, email, cpf, nome)
select 1 , 'w@gmail.com', 00364021250, 'wilder' union all
select 2 , 'm@gmail.com', 96832388543, 'marcos' union all
select 3 , 'j@gmail.com', 00456787323, 'jessica' union all
select 4 , 'h@gmail.com', 87698534523, 'hugo' union all
select 5 , 's@gmail.com', 98754623454, 'sergio' union all
select 6 , 'b@gmail.com', 54698345676, 'bia' union all
select 7 , 'l@gmail.com', 98723743787, 'lia' union all
select 8 , 'ml@gmail.com',34583765873, 'manoel' union all
select 9 , 'br@gmail.com', 54673892098, 'bruno'; 

--TURMA
insert into turma(id, id_modulo, id_professor, numero, data_termino)
select 1, 1, 1, 1001, '2016-04-11 00:00:00' union all
select 2, 2, 2, 2002, '2016-11-20 00:00:00' union all
select 3, 3, 3, 3003, '2017-03-25 00:00:00' union all
select 4, 4, 1, 4004, '2017-09-15 00:00:00';

--TURMA_ALUNO
insert into turma_aluno(id_turma, id_aluno)
select 1, 1 union all
select 1, 2 union all
select 1, 3 union all
select 2, 4 union all
select 2, 5 union all
select 2, 6 union all
select 3, 7 union all
select 3, 8 union all
select 4, 9;