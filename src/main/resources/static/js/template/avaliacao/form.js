function selecionarTopico(idTopico){
    var checkeds = new Array();
    addConteudoTopicoDaLista(checkeds);
    removeConteudoTopicoDaLista(checkeds, idTopico);
}
function addConteudoTopicoDaLista(checkeds) {
    $("input[name='checks[]']:checked").each(function (){
        checkeds.push($(this).val());
        $('#idsTemplateTopicoSelecionados').val(checkeds);
    });
}
function removeConteudoTopicoDaLista(checkeds, idTopico){
    if(checkeds.length === 0){
        $('#idsTemplateTopicoSelecionados').val(checkeds);
    }
    $(".btnPergunta"+idTopico).attr("style", "display: none");
}