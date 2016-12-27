function selecionarTopico(){
    var checkeds = new Array();
    $("input[name='checks[]']:checked").each(function (){
        checkeds.push( $(this).val());
        $('#idsTemplateTopicoSelecionados').val(checkeds);
    });
}