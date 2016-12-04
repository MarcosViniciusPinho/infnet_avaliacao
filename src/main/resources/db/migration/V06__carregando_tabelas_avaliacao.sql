

insert into template_avaliacao (id, titulo, situacao)
select 1 , 'PÓS JAVA' , 'xxx' union all 
select 2 , 'PÓS .NET' , 'xxx' union all
select 3 , 'GRADUAÇÃO' , 'xxx';



insert into template_avaliacao_turma (id_turma , id_template_avaliacao) 
select 1 , 1 union all
select 2 , 2 union all
select 3 , 3 ;

 

insert into template_topico ( id, enunciado)
select 1 , 'Avaliação geral da Pós-Graduação' union all
select 2 , 'Avaliação do professor no módulo' union all
select 3 , 'Avaliação de conteúdo e infra-estrutura no módulo' union all
select 4 , 'Outros' ;


insert into template_avaliacao_topico(id_template_topico , id_template_avaliacao)
select 1, 1 union all
select 2, 1 union all
select 3, 1 union all
select 4, 1 union all
select 1, 2 union all
select 2, 2 union all
select 3, 2 union all
select 4, 2 union all
select 1, 3 union all
select 2, 3 union all
select 3, 3 union all
select 4, 3 ;


insert into template_pergunta(id, questao, id_template_topico)
select 1 , 'Até agora, o curso está atingindo as minhas expectativas.' , 1 union all 
select 2 , 'Até agora, eu indicaria o curso para um amigo.' , 1 union all 
select 3 , 'Até agora, o curso me parece voltado para as necessidades do mercado.' , 1 union all 
select 4 , 'Até agora, a coordenação pedagógica parece comprometida com aqualidade do curso.' , 1 union all 
select 5 , 'Até agora, minha turma parece proporcionar um networking relevante para a minha carreira.' , 1 union all 
select 6 , 'Até agora, o atendimento de Secretaria que recebi está atingindo as minhas expectativas.' , 1 union all 

select 7 , 'O professor contribuiu para o meu aprendizado.' , 2 union all 
select 8 , 'O professor é atencioso e esteve disponível para tirar dúvidas.' , 2 union all 
select 9 , 'O professor aproveitou bem o tempo em sala de aula.' , 2 union all 
select 10 , 'O professor utilizou o Moodle e outros recursos didáticos para ajudar no meu aprendizado.' , 2 union all 
select 11 , 'O professor aproveitou bem os recursos da sala de aula.' , 2 union all 
select 12 , 'Gostaria de cursar outros módulos com esse professor.' , 2 union all 

select 13 , 'Eu adquiri as competências propostas para o módulo.' , 3 union all
select 14 , 'O módulo foi útil para o meu crescimento profissional.' , 3 union all
select 15 , 'A carga horária do módulo foi apropriada.' , 3 union all
select 16 , 'O acervo da biblioteca atendeu as necessidades desse módulo.' , 3 union all
select 17 , 'A configuração do(s) computadore(s) e equipamentos da sala de aula e a qualidade do suporte foi apropriada.' , 3 union all

select 18 , 'Você tem comentários e sugestões?' , 4 ;




insert into avaliacao(id, id_template_avaliacao, id_turma, id_aluno)
select 1, 1 , 1 , 1 UNION ALL 
select 2, 1 , 1 , 2 ;




insert into resposta(id, id_avaliacao, valor, id_template_pergunta)
select 1 , 1 ,'Concordo totalmente' , 1 union all
select 2 , 1 ,'Concordo' , 2 union all
select 3 , 1 ,'Não Concordo nem discordo' , 3 union all
select 4 , 1 ,'Discordo' , 4 union all
select 5 , 1 ,'Discordo totalmente' , 5 union all
select 6 , 1 ,'Não sei Avaliar' , 6 union all
select 7 , 1 ,'Discordo' , 7 union all
select 8 , 1 ,'Discordo' , 8 union all
select 9 , 1 ,'Discordo' , 9 union all
select 10 , 1 ,'Discordo' , 10 union all
select 11 , 1 ,'Discordo' , 11 union all
select 12 , 1 ,'Discordo' , 12 union all
select 13 , 1 ,'Discordo' , 13 union all
select 14, 1 ,'Discordo' , 14 union all
select 15 , 1 ,'Discordo' , 15 union all
select 16 , 1 ,'Discordo' , 16 union all
select 17 , 1 ,'Discordo' , 17 union all
select 18 , 1 ,'Reestruturar quadro de funcionários para melhor atender os alunos. Pq tá dificil. ' , 18 union all

select 19 , 2 ,'Concordo totalmente' , 1 union all
select 20, 2 ,'Concordo' , 2 union all
select 21 , 2 ,'Não Concordo nem discordo' , 3 union all
select 22 , 2 ,'Discordo' , 4 union all
select 23 , 2 ,'Discordo totalmente' , 5 union all
select 24 , 2 ,'Não sei Avaliar' , 6 union all
select 25 , 2 ,'Discordo' , 7 union all
select 26 , 2 ,'Discordo' , 8 union all
select 27 , 2 ,'Discordo' , 9 union all
select 28 , 2 ,'Discordo' , 10 union all
select 29 , 2 ,'Discordo' , 11 union all
select 30 , 2 ,'Discordo' , 12 union all
select 31 , 2 ,'Discordo' , 13 union all
select 32, 2 ,'Discordo' , 14 union all
select 33 , 2 ,'Discordo' , 15 union all
select 34 , 2 ,'Discordo' , 16 union all
select 35 , 2 ,'Discordo' , 17 union all
select 36 , 2 ,'Reestruturar quadro de funcionários para melhor atender os alunos. Pq tá dificil. ' , 18 ;
