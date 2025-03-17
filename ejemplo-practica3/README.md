# **Ejemplo Práctica 3**  

Este ejemplo incluye algunas de las funcionalidades requeridas en la **Práctica 3** del proyecto. Se trata de una página web desarrollada con **Angular 17**, que consume una **API REST** implementada en la **Práctica 2**.  

---

## **Ejecución del backend**  

Para que la aplicación Angular funcione correctamente, primero es necesario ejecutar el backend (**una API REST implementada con Spring Boot**).  

El código del backend se encuentra en la carpeta **`ejemplo-practica2`**.  

Se puede ejecutar desde un IDE (Eclipse, Visual Studio Code, IntelliJ...) o desde la línea de comandos con Maven:  

```sh
cd ejemplo-practica2
mvn spring-boot:run
```

## Ejecución del frontend (en modo desarrollo)

Nos ubicamos en la carpeta del frontend:

```
$ cd ejemplo-practica3
```

Instalamos las dependencias:

```
$ npm install
```

Ejecutamos la aplicación en modo desarrollo:

```
$ npm start
```


Este comando ejecutará `ng serve` con el proxy configurado, lo que:

* **Evita problemas de CORS** en desarrollo.
* **Permite usar rutas relativas a la API REST**, facilitando el despliegue en producción.

Una vez que en la consola aparezca:

```sh
Compiled successfully.
```

Podemos acceder a la aplicación Angular en:

* 🔗 `http://localhost:4200/`


## Distribución con el backend

Para desplegar correctamente la **Práctica 3**, es necesario **compilar** la aplicación Angular y copiar los archivos generados en la carpeta de archivos estáticos del backend (ejemplo-practica2).

