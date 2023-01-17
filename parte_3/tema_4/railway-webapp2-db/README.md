# railway-webapp2-db

Aplicación web springboot que se conecta a una MySQL. 

Se puede desplegar con docker-compose o en Railway.

Características:
* Aplicación web implementada con Spring MVC y templates Mustache.
* Base de datos MySQL
* Persistencia de imágenes en la base de datos 
* Carga de imágenes en los datos de ejemplo
* Posibilidad de editar las imágenes
* Seguridad con https

## Ejecución en local

Se require una base de datos MySQL disponible en localhost con la siguiente configuración:
* Esquema: `books`
* Usuario: `root`
* Contraseña: `password`

Se puede arrancar usando docker con el comando:

```
$ docker run --rm -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=books -p 5432:5432 -d mysql:8.0
```

La aplicación se ejecuta con el comando:

```
mvn spring-boot:run
```

La aplicación estará disponible en https://localhost:8443/

## Ejecución con docker-compose

Creación de imagen:

```
$ docker build -t "codeurjc/railway-webapp2-db" -f "docker/Dockerfile" .
```

Arranque del docker-compose:

```
$ docker-compose up 
```

La aplicación estará disponible en https://localhost:8443/

## Despliegue en Railway

Para desplegar la app en Railway se necesita crear una cuenta en https://railway.app/.

Se puede crear una cuenta gratuita sin tarjeta de crédito. Las limitaciones son:
* La aplicación debe estar asociada con una cuenta de Github
* Se obtienen 5$ créditos para consumir.
* Sólo se puede tener la aplicación en ejecución durante 500 horas al mes.
* Sólo puede usar 512MB de RAM.

Una vez creada la cuenta es necesario configurar el entorno para poder interactuar con Railway (sólo se hace una vez):

* Instalar el cliente Railway CLI de https://docs.railway.app/develop/cli
* Hacer login:

```
$ railway login
```

Luego se crea el proyecto y se configura antes de desplegar el código:

* Crear el proyecto en Railway:

```
$ railway init
```

Cada vez que queramos desplegar la aplicación se siguen estos pasos:

* Configurar el Docker file de la aplicación:

```bash
FROM maven:3.8.4-openjdk-17 as builder
WORKDIR /project
COPY /src /project/src
COPY pom.xml /project/
RUN mvn -B package

FROM openjdk:17-jdk-slim
ENV JAVA_TOOL_OPTIONS="-Xss256K -XX:ReservedCodeCacheSize=64M -XX:MaxMetaspaceSize=100000K -Xmx64M"
WORKDIR /usr/src/app/
COPY --from=builder /project/target/*.jar /usr/src/app/
EXPOSE 8080
CMD [ "java", "-jar", "railway-webapp1.jar" ]
```

* Definir el lugar en el que se encuentra el Dockerfile. Para ello creamos en la raíz el fichero `railway.toml`:

```bash
[build]
builder = "DOCKERFILE"
dockerfilePath = "docker/Dockerfile"

[deploy]
restartPolicyType = "ON_FAILURE"
restartPolicyMaxRetries = 3
```

* Desplegar la aplicación:

```
$ railway up
```

* Definir variables y reiniciar la app:

```
railway variables set SERVER_SSL_ENABLED=false
railway variables set SPRING_JPA_HIBERNATE_DDL-AUTO=update
railway up
```

* Acceder a sus logs en la terminal:

```
$ railway logs
```

* Se debe exponer la aplicación desde el dashboard de Railway en https://railway.app/dashboard

Una vez expuesta la aplicación estará disponible en https://<project-name>.up.railway.app/


### Scripts de creación y despliegue

Se han implementado dos scripts bash para facilitar el proceso de creación de la app y de despliegue:

```
$ ./create_railway_app.sh <app-id>
$ ./deploy_railway_app.sh <app-id>
```

### Eliminar la aplicación Railway

Para eliminar la aplicación se debe hacer desde el dashboard web https://<project-name>.up.railway.app/
