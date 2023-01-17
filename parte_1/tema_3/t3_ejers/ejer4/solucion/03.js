$(document).ready(function () {

    // Efecto hover sobre la barra de herramientas
    $('#switcher').hover(function () {
        $(this).addClass('hover');
    }, function () {
        $(this).removeClass('hover');
    });

    // Cambia la barra de herramientas de minimizado a normal (y viceversa)
    var toggleSwitcher = function (event) {
        if (!$(event.target).is('button')) {
            $('#switcher button').toggleClass('hidden');
        }
    };

    $('#switcher').on('click', toggleSwitcher);

    // Simular un click para iniciar la barra minimizada
    $('#switcher').click();

    // Cambia el estilo del <body>
    // Actualiza el estado de la barra de herramientas
    var setBodyClass = function (className) {

        $('body').removeClass().addClass(className);

        $('#switcher button').removeClass('selected');

        $('#switcher-' + className).addClass('selected');

    };

    // Inicia con el estilo por defecto
    $('#switcher-default').addClass('selected');

    // Llama a setBodyClass() cuando se pulsa un botón
    $('#switcher').click(function (event) {
        if ($(event.target).is('button')) {
            var bodyClass = event.target.id.split('-')[1];
            setBodyClass(bodyClass);
        }
    });

    // Mapa para relacionar teclas a estilos
    var triggers = {
        D: 'default',
        N: 'narrow',
        L: 'large'
    };

    // Llama a setBodyClass() cuando se pulsa una tecla
    $(document).keyup(function (event) {
        var key = String.fromCharCode(event.which);
        if (key in triggers) {
            setBodyClass(triggers[key]);
        }
    });
});
