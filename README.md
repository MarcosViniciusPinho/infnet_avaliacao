# infnet_avaliacao
Info do projeto: 
[![Build Status](https://travis-ci.org/MarcosViniciusPinho/infnet_avaliacao.svg?branch=master)](https://travis-ci.org/MarcosViniciusPinho/infnet_avaliacao)
<br />
Projeto de TCC que representa o sistema de avaliação da infnet
O projeto ainda não tem sua versão em produção, portando para poder executar e utilizar o sistema deverá ser feita localmente no pc e seguindo os seguintes passos:<br />
**1º passo)** Fazer o checkout do projeto: `https://github.com/MarcosViniciusPinho/infnet_avaliacao.git`<br />
**2º passo)** Assim que terminar o checkout deverá ser configurado/executado os seguintes comandos via maven:<br />&nbsp;&nbsp;&nbsp;
    - clean flyway:migrate(Executa a migração dos dados para o banco definido no application.properties)<br />
    &nbsp;&nbsp;&nbsp;&nbsp;- clean spring-boot:run(Executa de fato a aplicação)
<br />Ambos podem ser executados via cmd(prompt comandos) desde que você tenha configurado o maven em sua máquina.

Para poder fazer a chamada da aplicação na url basta digitar: _http://localhost:8081/sai/_