$(document).ready(function () {
    var salvar  ='#salvar';
    var proximo = '#proximo';
    clicarEmProximoOuSalvar(proximo);
    clicarEmProximoOuSalvar(salvar);
    validarCamposObrigatorios(salvar, proximo);
});

function validarCamposObrigatorios(salvar, proximo) {
    $(proximo).prop("disabled", true);
    $(salvar).prop("disabled", true);
    var inputTypeRadio = $("input[type='radio']");
    var totalInputRadio = inputTypeRadio.length;
    if(totalInputRadio === 0){
        $(proximo).prop("disabled", false);
        $(salvar).prop("disabled", false);
    } else{
        validarCamposInputTypeRadio(inputTypeRadio, salvar, proximo);
    }
}

function validarCamposInputTypeRadio(inputTypeRadio, salvar, proximo){
    $(inputTypeRadio).click(function () {
        var campoPreenchido = validarCampoObrigatorioPreenchido();
        if (campoPreenchido) {
            $(proximo).prop("disabled", false);
            $(salvar).prop("disabled", false);
        } else {
            $(proximo).prop("disabled", true);
            $(salvar).prop("disabled", true);
        }
    });
}

function clicarEmProximoOuSalvar(elemento) {
    var proximoIndiceTopico = $('#indiceTopico').val();
    $(elemento).click(function () {
        proximoIndiceTopico++;
        $('#indiceTopico').val(proximoIndiceTopico);
        addConteudoDaLista();
    });
}

function addConteudoDaLista() {
    var totalPerguntas = $('#totalPerguntas').val();
    var checkeds = incializarOuRetornarMarcacao();
    for(var i = 1;i <= totalPerguntas; i++){
        tratarTypeRadio(i, checkeds);
        tratarTypeTextArea(i, checkeds);
    }
}

function validarCampoObrigatorioPreenchido(){
    var campoPreenchido = true;
    var totalPerguntas = $('#totalPerguntas').val();
    for(var i = 1;i <= totalPerguntas; i++) {
        var textArea = $('#' + i +'textarea').val();
        if(textArea === undefined){
            var respostasMarcadas = $("input[name='" + i + "radio']:checked").val();
            if (respostasMarcadas === undefined) {
                campoPreenchido = false;
            }
        }
    }
    return campoPreenchido;
}

function tratarTypeRadio(i, checkeds){
    var inputName = "input[name='"+ i +"radio']:checked";
    $(inputName).each(function (){
        checkeds.push($(this).val());
        $('#respostasSelecionadasComPerguntas').val(checkeds);
    });
}

function tratarTypeTextArea(i, checkeds){
    var valorResposta = $("#" + i + "textarea").val();
    var idTemplatePergunta = $('#idTemplatePergunta').val();
    if(valorResposta != undefined && idTemplatePergunta != undefined){
        var valorRespostaOther = valorResposta.replace(/\,/g, '00101100').replace(/\-/g, '00101101');
        var respostaComPergunta = valorRespostaOther + '-' + idTemplatePergunta;
        checkeds.push(respostaComPergunta);
        $('#respostasSelecionadasComPerguntas').val(checkeds);
    }
}

function incializarOuRetornarMarcacao(){
    var respostas = $('#respostasSelecionadasComPerguntas').val();
    return respostas === "" ? new Array() : new Array(respostas);
}