$(document).ready(function () {
    $('#modalExcluir').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var id = button.data('id');
        var nome = button.data('nome');
        var modal = $(this);
        var action = $('#formExcluir').data('url-base');
        $('#formExcluir').attr('action', action + id);
        modal.find('.modal-body span').html($('#mensagemExcluir').val() + ' <strong>' + nome + '</strong>?');
    })
});