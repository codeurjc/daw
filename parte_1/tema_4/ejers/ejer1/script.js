$(document).ready(function () {

    var input = $('#value-input')
    var info = $('#info')

    info.change(function (event) {
        var checkbox = $(event.target);
        var text = checkbox.parent().find('span');
        var style = checkbox.prop('checked') ? 'line-through' : 'none';
        text.css('text-decoration', style);
    })

    info.click(function (event) {
        var elem = $(event.target);
        if (elem.is('button')) {
            elem.parent().remove();
        }
    })

    $("#add-button").click(function () {
        var value = input.val();
        input.val('');
        info.append(
            '<div><input type="checkbox"><span>' + value +
            '</span> <button>Delete</button></div>')
    })
})