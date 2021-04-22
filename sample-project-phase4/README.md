# SAMPLE-PROJECT-PHASE-4

Esta carpeta contiene todo el código necesario para ejecutar la aplicación web Angular que se comunica con el backend.

## Lanzar el backend (Spring)

> Nos situamos en la carpeta `sample-project-phase3`

El backend de esta aplicación web será el proyecto Spring situado en la carpeta `sample-project-phase3`

El backend necesita de una base de datos lanzada previamente (MySQL), la cual podemos lanzar con Docker

```
docker run --rm -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=books -p 3306:3306 -d mysql:8.0.22
```

Compilamos la aplicación y la lanzamos (también puede ejecutarse desde el IDE):

```
mvn clean package
java -jar target/sample-project-phase2-0.0.1-SNAPSHOT.jar
```

## Lanzar el frontend (Angular)

> Nos situamos en la carpeta `sample-project-phase4`

Instalamos las dependencias:

```
npm install
```

Lanzamos la aplicación en modo desarrollo

```
ng serve --proxy-config proxy.conf.json
```

La aplicación Angular usa un proxy para evitar problemas de CORS

