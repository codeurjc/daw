"use strict";

function quitaCeros(arrayCeros) {
    var arrayPares = [];
    var arrayImpares = [];
    for (var i = 0; i < arrayCeros.length; i++) {
        var v = arrayCeros[i];
        if (v % 2 === 0) {
            arrayPares.push(v);
        } else {
            arrayImpares.push(v);
        }
    }
    return [arrayPares, arrayImpares];
}

console.log(quitaCeros([1, 2, 3, 4, 5, 6, 7, 8]));