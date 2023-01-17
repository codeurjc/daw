# heroku-webapp2-db

Aplicación web springboot que se conecta a una PostgreSQL. 

Se puede desplegar con docker-compose o en Heroku.

Características:
* Aplicación web implementada con Spring MVC y templates Mustache.
* Base de datos PostgreSQL
* Persistencia de imágenes en la base de datos 
* Carga de imágenes en los datos de ejemplo
* Posibilidad de editar las imágenes
* Seguridad con https

## Ejecución en local

Se require una base de datos PostgreSQL disponible en localhost con la siguiente configuración:
* Esquema: `books`
* Usuario: `postgres`
* Contraseña: `password`

Se puede arrancar usando docker con el comando:

```
$ docker run --rm -e POSTGRES_PASSWORD=password -e POSTGRES_DB=books -p 5432:5432 -d postgres:14.2
```

La aplicación se ejecuta con el comando:

```
mvn spring-boot:run
```

La aplicación estará disponible en https://localhost:8443/

## Ejecución con docker-compose

Creación de imagen:

```
$ mvn spring-boot:build-image -Dspring-boot.build-image.imageName=codeurjc/heroku-webapp2-db
```

Arranque del docker-compose:

```
$ docker-compose up 
```

La aplicación estará disponible en https://localhost:8443/

## Despliegue en Heroku

Para desplegar la app en Heroku se necesita crear una cuenta en https://www.heroku.com.

Se puede crear una cuenta gratuita sin tarjeta de crédito. Las limitaciones son:
* Sólo se puede tener la aplicación en ejecución durante 550 horas al més.
* Se apaga si no se usa en 30 minutos.
* Sólo puede usar 512MB de RAM.

Una vez creada la cuenta es necesario configurar el entorno para poder interactuar con Heroku (sólo se hace una vez):

* Instalar el cliente Heroku CLI de https://devcenter.heroku.com/articles/heroku-cli
* Hacer login:

```
$ heroku login
```
* Configuramos Docker para que pueda publicar la imagen en el registro de imágenes de Heroku:

```
$ heroku container:login
```

Luego se crea la aplicación y se configura antes de desplegar el código:

* Crear la aplicación en Heroku con un identificador único en todo Heroku:

```
$ heroku create <app-id>
```

* Se añade una base de datos a la aplicación:

```
$ heroku addons:create heroku-postgresql --app <app-id>
```

* Se configuran las variables de entorno necesarias para esta app en concreto:

```
$ heroku config:set SERVER_SSL_ENABLED=false --app <app-id>
$ heroku config:set SPRING_JPA_HIBERNATE_DDL-AUTO=update --app <app-id>
```

Cada vez que queramos desplegar la aplicación se siguen estos pasos:

* Crear la imagen Docker con un nombre especial para Heroku:

```
$ mvn spring-boot:build-image -Dspring-boot.build-image.imageName=registry.heroku.com/<app-id>/web
```

* Publicar la imagen

```
$ docker push registry.heroku.com/<app-id>/web
```

* Desplegar la imagen en la aplicación:

```
$ heroku container:release web --app <app-id>
```

* Acceder a sus logs en la terminal:

```
$ heroku logs --tail --app <app-id>
```

La aplicación estará disponible en https://<app-id>.herokuapp.com/.

### Scripts de creación y despliegue

Se han implementado dos scripts bash para facilitar el proceso de creación de la app y de despliegue:

```
$ ./create_heroku_app.sh <app-id>
$ ./deploy_heroku_app.sh <app-id>
```

### Eliminar la aplicación Heroku

Para eliminar la aplicación se usa el comando:

```
$ heroku apps:destroy --app <app-id>
```
