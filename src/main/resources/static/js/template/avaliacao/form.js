function selecionarTopico(){
    var checkeds = new Array();
    addConteudoTopicoDaLista(checkeds);
    removeConteudoTopicoDaLista(checkeds);
}
function addConteudoTopicoDaLista(checkeds) {
    $("input[name='checks[]']:checked").each(function (){
        checkeds.push($(this).val());
        $('#idsTemplateTopicoSelecionados').val(checkeds);
    });
}
function removeConteudoTopicoDaLista(checkeds){
    if(checkeds.length === 0){
        $('#idsTemplateTopicoSelecionados').val(checkeds);
    }
}