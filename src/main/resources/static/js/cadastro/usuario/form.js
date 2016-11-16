$(document).ready(function () {
    var id = $("#id").val();
    if(id !== null && id !== ""){
        $(".blocoEditar").attr("style", "display: none");
    }
});