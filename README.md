# infnet_avaliacao
[![Build Status](https://travis-ci.org/MarcosViniciusPinho/infnet_avaliacao.svg?branch=master)](https://travis-ci.org/MarcosViniciusPinho/infnet_avaliacao)
[![Coverage Status](https://coveralls.io/repos/github/MarcosViniciusPinho/infnet_avaliacao/badge.svg)](https://coveralls.io/github/MarcosViniciusPinho/infnet_avaliacao)<br />
Projeto de TCC que representa o sistema de avaliação da infnet
O projeto ainda não tem sua versão em produção, portando para poder executar e utilizar o sistema deverá ser feita localmente no pc e seguindo os seguintes passos:<br />
**1º passo)** Fazer o checkout do projeto: `https://github.com/MarcosViniciusPinho/infnet_avaliacao.git`<br />

**2º passo)** Assim que terminar o checkout deverá ser configurado/executado os seguintes comandos via maven:<br />&nbsp;&nbsp;&nbsp;
    - clean flyway:migrate(Executa a migração dos dados para o banco definido no application.properties)<br />
    &nbsp;&nbsp;&nbsp;&nbsp;- clean spring-boot:run(Executa de fato a aplicação)<br />
    
**3º passo)** Opcional: Caso deseje verificar a qualidade de código do projeto, basta pegar o conteúdo definido no arquivo run-sonarlint e colar 
em seu terminal(cmd), feito isto o sonar irá fornecer um link com o dashboard da qualidade do código.<br /> 
Exemplo: /mnt/dados/Desenvolvimento/Instituicao_INFNET/infnet_avaliacao/.sonarlint/sonarlint-report.html<br /> 

**Ps: Antes de executar este passo 3 é necessário baixar o seguinte ZIP em http://www.sonarlint.org/commandline/ 
Logo em seguida descompactar e configurar a variavel de ambiente com este zip** <br />

Todos estes passos podem ser executados via terminal(cmd) desde que você tenha configurado o maven em sua máquina.
Para poder fazer a chamada da aplicação na url basta digitar: _http://localhost:8081/sai/login_