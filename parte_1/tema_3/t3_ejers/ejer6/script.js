// Figura ----------------------------

function Figura(color) {
    this.color = color;
    this.area = this.calculaArea();
    this.perimetro = this.calculaPerimetro();
    this.ratioAreaPerimetro = this.calculaRatioAreaPerimetro();
}

Figura.prototype.calculaRatioAreaPerimetro = function () {
    return this.area / this.perimetro;
}

Figura.prototype.toString = function () {
    return "Figura: color=" + this.color + " area=" + this.area + " perimetro=" + this.perimetro + " ratio=" + this.ratioAreaPerimetro;
}

// Circulo -----------------------------

Circulo.prototype = Object.create(Figura.prototype);

function Circulo(color, radio) {
    this.radio = radio;
    Figura.call(this, color);
}

Circulo.prototype.calculaArea = function () {
    return Math.PI * this.radio * this.radio;
}

Circulo.prototype.calculaPerimetro = function () {
    return 2 * Math.PI * this.radio;
}

Circulo.prototype.toString = function () {
    return Figura.prototype.toString.call(this) + " Circulo: radio=" + this.radio;
}

// Rectangulo ---------------------------------

Rectangulo.prototype = Object.create(Figura.prototype);

function Rectangulo(color, ancho, alto) {
    this.ancho = ancho;
    this.alto = alto;
    Figura.call(this, color);
}

Rectangulo.prototype.calculaArea = function () {
    return this.ancho * this.alto;
}

Rectangulo.prototype.calculaPerimetro = function () {
    return 2 * this.ancho + 2 * this.alto;
}

Rectangulo.prototype.toString = function () {
    return Figura.prototype.toString.call(this) + " Rectangulo: alto=" + this.alto + " ancho=" + this.ancho;
}

// AnalizadorFiguras -----------------------------

function AnalizadorFiguras(figuras) {
    this.figuras = figuras;
}

AnalizadorFiguras.prototype.sumaAreas = function () {
    var suma = 0;
    for (var i = 0; i < this.figuras.length; i++) {
        suma += this.figuras[i].area;
    }
    return suma;
}

AnalizadorFiguras.prototype.sumaPerimetros = function () {
    var suma = 0;
    for (var i = 0; i < this.figuras.length; i++) {
        suma += this.figuras[i].perimetro;
    }
    return suma;
}

AnalizadorFiguras.prototype.areaMedia = function () {
    return this.sumaAreas() / this.figuras.length;
}

AnalizadorFiguras.prototype.perimetroMedio = function () {
    return this.sumaAreas() / this.figuras.length;
}

AnalizadorFiguras.prototype.sumaAreas = function () {
    var suma = 0;
    for (var i = 0; i < this.figuras.length; i++) {
        suma += this.figuras[i].area;
    }
    return suma;
}

AnalizadorFiguras.prototype.sumaPerimetros = function () {
    var suma = 0;
    for (var i = 0; i < this.figuras.length; i++) {
        suma += this.figuras[i].perimetro;
    }
    return suma;
}

AnalizadorFiguras.prototype.ratioAreaPerimetroMedio = function () {
    var suma = 0;
    for (var i = 0; i < this.figuras.length; i++) {
        suma += this.figuras[i].ratioAreaPerimetro;
    }
    return suma / this.figuras.length;
}

// Pruebas ---------------------------------

var circulo = new Circulo("rojo", 3);
var rectangulo = new Rectangulo("verde", 4, 6);

console.log("Circulo: " + circulo);
console.log("Rectangulo: " + rectangulo);

var analizador = new AnalizadorFiguras([circulo, rectangulo]);

console.log("Suma areas: " + analizador.sumaAreas());
console.log("Suma perimetros: " + analizador.sumaPerimetros());
console.log("Area media: " + analizador.areaMedia());
console.log("Perimetro medio: " + analizador.perimetroMedio());
console.log("Ratio area perimetro medio: " + analizador.ratioAreaPerimetroMedio());