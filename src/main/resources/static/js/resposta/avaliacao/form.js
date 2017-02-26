$(document).ready(function () {
    clicarEmProximoOuSalvar("#proximo");
    clicarEmProximoOuSalvar("#salvar");
});

function clicarEmProximoOuSalvar(elemento){
    var proximoIndiceTopico = $('#indiceTopico').val();
    $(elemento).click(function() {
        proximoIndiceTopico++;
        $('#indiceTopico').val(proximoIndiceTopico);
        addConteudoDaLista();
    });
}

function addConteudoDaLista() {
    var totalPerguntas = $('#totalPerguntas').val();
    var checkeds = incializarOuRetornarMarcacao();
    for(var i = 1;i <= totalPerguntas; i++){
        var inputName = "input[name='"+ i +"radio']:checked";
        $(inputName).each(function (){
            checkeds.push($(this).val());
            $('#respostasSelecionadasComPerguntas').val(checkeds);
        });
    }
}

function incializarOuRetornarMarcacao(){
    var respostas = $('#respostasSelecionadasComPerguntas').val();
    return respostas === "" ? new Array() : new Array(respostas);
}