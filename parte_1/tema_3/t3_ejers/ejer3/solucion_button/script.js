$(document).ready(function () {

    $("#add-button").click(function () {
        var value = $('#value-input').val();
        $('#info').append('<p>' + value + '</p>')
    })
})