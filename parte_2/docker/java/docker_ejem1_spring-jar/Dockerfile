# Selecciona la imagen base
FROM eclipse-temurin:21-jre

# Define el directorio de trabajo para el comando
WORKDIR /usr/src/app/

# Copia de la aplicación compilada y la renombra a "app.jar"
COPY target/*.jar /usr/src/app/app.jar

# Indica el puerto que expone el contenedor
EXPOSE 8080

# Comando que se ejecuta al hacer docker run
CMD [ "java", "-jar", "app.jar" ]