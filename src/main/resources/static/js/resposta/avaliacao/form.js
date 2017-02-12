$(document).ready(function () {
    var proximoIndiceTopico = $('#indiceTopico').val();
    $("#proximo").click(function() {
        proximoIndiceTopico++;
        $('#indiceTopico').val(proximoIndiceTopico);
    });
});