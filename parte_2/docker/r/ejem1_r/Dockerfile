# Selecciona la imagen base
FROM r-base:4.5.1

# Definimos el directorio de trabajo
WORKDIR /usr/src/app/

# Copiamos fichero de dependencias
COPY requirements.R /usr/src/app/

# Instalación de las librerías necesarias
RUN Rscript requirements.R

# Copiamos los scripts
COPY predict.R /usr/src/app/

# Comando que se ejecuta cuando se arranque el contenedor
ENTRYPOINT [ "Rscript", "predict.R" ]
CMD [ "5.1", "3.5", "1.4", "0.2" ]