$(document).ready(function () {
    var id = $("#id").val();
    campoSenhaDinamica(id);
    tornarVisivelTitulo(id);
});

function campoSenhaDinamica(id){
    if(id !== ""){
        $(".blocoEditar").attr("style", "display: none");
    }
}

function tornarVisivelTitulo(id) {
    if(id !== ""){
        $(".cadastrar").hide();
        $(".alterar").show();
    } else{
        $(".cadastrar").show();
        $(".alterar").hide();
    }
}