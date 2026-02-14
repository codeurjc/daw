# Selecciona la imagen base
FROM python:3.14.0-slim

# Definimos el directorio de trabajo en /usr/src/app/
WORKDIR /usr/src/app/

# Copiamos los ficheros de la aplicación
COPY app.py /usr/src/app/

# Comando por defecto
ENTRYPOINT [ "python", "app.py" ]

# Parametro por defecto
CMD ["--server"]