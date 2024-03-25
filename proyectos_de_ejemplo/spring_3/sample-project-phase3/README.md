# sample-project-phase3

Ejemplo que contiene algunas de las funcionalidades solicitadas en la Fase 3 del proyecto, en concreto:

* Aplicación de la Fase 2 a la que se le ha añadido una API REST.
* La API REST gestiona los usuarios con JWT.
* Documentación de la API REST con OpenAPI ([consultar online](https://rawcdn.githack.com/codeurjc/daw/4a4f15fba127aad11d239e610e3c46da94b8f5a6/sample-project-phase3/api-docs/api-docs.html))

Se usa una base de datos H2 en memoria para simplificar la ejecución de la aplicación.

La aplicación se ejecuta con el comando:

```
mvn spring-boot:run
```

Los usuarios son:

* Usuario: `user`, contraseña: `pass`
* Usuario: `admin`, contraseña: `adminpass`

## Generación de documentación de la API REST

El `pom.xml` tiene configurados diversos plugins que permiten generar la documentación de la API REST en formato OpenAPI (`api-docs.yaml`) y en HTML (`api-docs.html`) en la carpeta `api-docs`.

Para ello se ejecuta el comando:

```
$ mvn verify
```