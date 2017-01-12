function selecionarCheckbox(elemento){
    var checkeds = new Array();
    addConteudoDaLista(checkeds, elemento);
    removeConteudoDaLista(checkeds, elemento);
}
function addConteudoDaLista(checkeds, elemento) {
    $("input[name='checks[]']:checked").each(function (){
        checkeds.push($(this).val());
        $(elemento).val(checkeds);
    });
}
function removeConteudoDaLista(checkeds, elemento){
    if(checkeds.length === 0){
        $(elemento).val(checkeds);
    }
}