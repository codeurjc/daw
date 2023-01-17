$(document).ready(function () {

    $("#add-button").click(function (event) {
        event.preventDefault();
        var value = $('#value-input').val();
        $('#info').append('<p>' + value + '</p>')
    })
})