# daw

Código de ejemplo de la asignatura "Desarrollo de Aplicaciones Web" del Grado en Ingeniería del Software de la ETSII URJC

# sample-project-phase1

Ejemplo que contiene algunas de las funcionalidades solicitadas en la Fase 1 del proyecto, en concreto:

* Base de datos MySQL
* Persistencia de imágenes en la base de datos 
* Carga de imágenes en los datos de ejemplo
* Posibilidad de editar las imágenes asociadas a los libros
* Roles de usuarios con diferentes funcionalidades
* Seguridad con https
* Podéis usar este código como base para poder implementar la Fase 1 de vuestro proyecto. 

Por defecto el proyecto require una base de datos MySQL disponible en localhost con la siguiente configuración:
* esquema=books
* username=root
* password=password

Se puede arrancar usando docker en un sistema linux con el comando `$./start_db.sh`

La configuración se puede cambiar en el fichero `src/main/resources/application.properties`

