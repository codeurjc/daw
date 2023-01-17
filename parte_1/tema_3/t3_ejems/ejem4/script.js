function Empleado(nombre, salario) {
    this.nombre = nombre;
    this.salario = salario;
}

Empleado.prototype.getNombre = function () {
    return nombre;
}

Empleado.prototype.toString = function () {
    return "Nombre:" + this.nombre + ",Salario: " + this.salario;
}

Jefe.prototype = Object.create(Empleado.prototype);

function Jefe(nombre, salario, despacho) {
    Empleado.call(this, nombre, salario);
    this.despacho = despacho;
}

Jefe.prototype.toString = function () {
    return Empleado.prototype.toString.call(this) + "    Despacho: " + this.despacho;
}

var pedro = new Jefe("Pedro ", 900, "B12");
var juan = new Empleado("Juan", 800);

console.log(pedro);
console.log(juan);