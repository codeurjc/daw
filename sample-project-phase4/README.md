# sample-project-phase4

Este proyecto implementa la interfaz de usuario en Angular del `sample-project-phase3` de este repositorio. 

Se puede usar como referencia para implementar la Fase 4 del proyecto de la asignatura "Desarrollo de Aplicaciones Web" de la ETSII URJC. 

## Ejecución del backend 

Para que la aplicación Angular funcione correctamente es necesario ejecutar previamente el backend (una API REST implementada con Spring Boot).

El código del backend está situado en la carpeta `sample-project-phase3`.

Podemos ejecutar la aplicación desde un IDE (Eclipse, Visual Studio Code, IntelliJ...) o desde la línea de comandos usando Maven:

```
$ cd sample-project-phase3
$ mvn spring-boot:run
```

## Ejecución del frontend (en modo desarrollo)

Nos situamos en la carpeta `sample-project-phase4`

```
$ cd sample-project-phase4
```

Instalamos las dependencias:

```
$ npm install
```

Lanzamos la aplicación en modo desarrollo

```
$ npm start
```

Comando que ejecutará `ng serve` con el proxy configurado (para evitar problemas de CORS en desarrollo y para poder usar rutas relativas a la API REST, lo que facilita el despliegue en producción).

Cuando aparezca en consola `Compiled successfully.` se podrá abrir la aplicación Angular en `http://localhost:4200/`

## Distribución con el backend

Para un correcto despliegue de la fase 4 del proyecto es necesario construir la aplicación Angular y copiar los artefactos en la carpeta de ficheros estáticos del proyecto `sample-project-phase3`.

