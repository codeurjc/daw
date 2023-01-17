var saludo = "Hola";

var obj = {
    a: "a",
    b: "b",
    metodo: function () {
        return this.a;
    }
};

console.log(obj.a);
console.log(obj["a"]);

console.log(obj.metodo());
console.log(obj["metodo"]());

var config = {}
config["nombre"] = "Pepe Pérez";
config["correo"] = "pepe.perez@ blabla.com";
config["idioma"] = "ES";

console.log("Nombre: " + config["nombre"]);

for (key in config) {
    console.log(key + ": " + config[key]);
}

var obj1 = null;
var obj2;

//obj1.metodo();
if (!obj1) {
    console.log("obj1 is null or undefined");
}

//obj2.metodo();
if (!obj2) {
    console.log("obj2 is null or undefined");
}

function Empleado() {
    this.nombre = "Pepe";
    this.sueldo = 800;
}

var empleado = new Empleado();

console.log(empleado.prototype);