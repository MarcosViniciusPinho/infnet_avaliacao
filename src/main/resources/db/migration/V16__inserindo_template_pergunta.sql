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
