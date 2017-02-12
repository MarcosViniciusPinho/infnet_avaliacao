$(document).ready(function () {
    var proximoIndiceTopico = $('#indiceTopico').val();
    $("#salvar").click(function() {
        proximoIndiceTopico++;
        $('#indiceTopico').val(proximoIndiceTopico);
    });
});