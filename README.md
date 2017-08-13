# infnet_avaliacao
[![Build Status](https://travis-ci.org/MarcosViniciusPinho/infnet_avaliacao.svg?branch=master)](https://travis-ci.org/MarcosViniciusPinho/infnet_avaliacao)
[![Coverage Status](https://coveralls.io/repos/github/MarcosViniciusPinho/infnet_avaliacao/badge.svg)](https://coveralls.io/github/MarcosViniciusPinho/infnet_avaliacao)<br />
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/b18981c1dd854c0cb1f7be6edbf9d35f)](https://www.codacy.com/app/MarcosViniciusPinho/infnet_avaliacao?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=MarcosViniciusPinho/infnet_avaliacao&amp;utm_campaign=Badge_Grade)
Projeto de TCC que representa o sistema de avaliação da infnet
O projeto ainda não tem sua versão em produção, portando para poder executar e utilizar o sistema deverá ser feita localmente no pc e seguindo os seguintes passos:<br />
**1º passo)** Fazer o checkout do projeto: `https://github.com/MarcosViniciusPinho/infnet_avaliacao.git`<br />

**2º passo)** Assim que terminar o checkout deverá ser configurado/executado os seguintes comandos via maven, caso queira executar a aplicação:<br />&nbsp;&nbsp;&nbsp;
    - clean flyway:migrate(Executa a migração dos dados para o banco definido no application.properties)<br />
    &nbsp;&nbsp;&nbsp;&nbsp;- clean spring-boot:run(Executa de fato a aplicação)<br />
    
**3º passo)** Analisar métricas da qualidade do código produzido acessar: <br />
    - Codacy: https://www.codacy.com/app/MarcosViniciusPinho/infnet_avaliacao/dashboard?bid=5100299<br />
    - SonarQube: https://sonarcloud.io/dashboard?id=com.infnet%3Asai

Todos estes passos podem ser executados via terminal(cmd) desde que você tenha configurado o maven em sua máquina.
Para poder fazer a chamada da aplicação na url basta digitar: _http://localhost:8081/sai/login_