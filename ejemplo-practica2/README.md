# Ejemplo Práctica 2

Ejemplo que contiene algunas de las funcionalidades solicitadas en la Prática 2 del proyecto.

Es una evolución sobre la Práctica 1 que tiene: 

* Aplicación web implementada con Spring MVC y templates Mustache.
* Base de datos MySQL
* Persistencia de imágenes en la base de datos 
* Carga de imágenes en los datos de ejemplo
* Posibilidad de editar las imágenes asociadas a los libros
* Roles de usuarios con diferentes funcionalidades
* Seguridad con https

A la que se ha añadido una API REST y la dockerización de la aplicación.

Por defecto el proyecto require una base de datos MySQL disponible en localhost con la siguiente configuración:
* Esquema: `books`
* Usuario: `root`
* Contraseña: `password`

Se puede arrancar usando docker con el comando:

```
$ docker run --rm -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=books -p 3306:3306 -d mysql:9.2
```

La aplicación se ejecuta con el comando:

```
mvn spring-boot:run
```

Los usuarios son:

* Usuario: `user`, contraseña: `pass`
* Usuario: `admin`, contraseña: `adminpass`

Se puede acceder a través de la ruta: [https://localhost:8443](https://localhost:8443)

Nota: Para generar el certificado autofirmado se ha generado usando el comando  
```sh
keytool -genkey -keyalg RSA -alias selfsigned -keystore keystore.jks -storepass password -validity 360 -keysize 2048
```
A la primera pregunta (tu nombre), hay que responder `localhost`. Si no, OpenAPI no podrá generar la documentación de la API REST.
Para generar la documentación con OpenAPI usa el comando:
```sh
mvn verify -Djavax.net.ssl.trustStore=src/main/resources/keystore.jks -Djavax.net.ssl.trustStorePassword=password
```
