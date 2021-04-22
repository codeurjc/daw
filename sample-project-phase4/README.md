# SAMPLE-PROJECT-PHASE-4

Este proyecto implementa la interfaz de usuario en Angular del `sample-project-phase3` de este repositorio. 

Se puede usar como referencia para implementar la Fase 4 del proyecto de la asignatura "Desarrollo de Aplicaciones Web" de la ETSII URJC. 

## Ejecución del backend 

Para que la aplicación Angular funcione correctamente es necesario ejecutar previamente el backend que es una API REST implementada con Spring Boot.

El backend necesita de una base de datos lanzada previamente (MySQL), la cual podemos lanzar con Docker

```
docker run --rm -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=books -p 3306:3306 -d mysql:8.0.22
```

El código del backend está situado en la carpeta `sample-project-phase3`. 

Podemos ejecutar la aplicación desde un IDE (Eclipse, Visual Studio Code, IntelliJ...) o desde la línea de comandos usando Maven:

```
cd sample-project-phase3
mvn spring-boot:run
```

## Ejecución del frontend (en modo desarrollo)

Nos situamos en la carpeta `sample-project-phase4`

```
cd sample-project-phase4
```

Instalamos las dependencias:

```
npm install
```

Lanzamos la aplicación en modo desarrollo

```
npm start
```

Lo que ejecutará `ng serve` con el proxy configurado (para evitar problemas de CORS en desarrollo y para poder usar rutas relativas a la API REST, lo que facilita el despliegue en producción).

Cuando aparezca en consola `Compiled successfully.` se podrá abrir la aplicación en `http://localhost:4200/`

