function selecionarTopico(idTopico){
    selecionarCheckbox('#idsTemplateTopicoSelecionados');
    $(".btnPergunta"+idTopico).attr("style", "display: none");
}