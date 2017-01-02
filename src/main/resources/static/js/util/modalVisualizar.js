$(document).ready(function () {
    $('#modalVisualizar').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var nome = button.data('nome');
        var modal = $(this);
        modal.find('.modal-body span').html(nome);
    });
});