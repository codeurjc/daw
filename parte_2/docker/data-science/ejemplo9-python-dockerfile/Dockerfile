# Selecciona la imagen base
FROM python:3.14-slim

# Definimos el directorio de trabajo en /project
WORKDIR /project

# Copiar el fichero de dependencias
COPY requirements.txt requirements.txt

# Instalar las dependencias python que necesita el script
RUN pip install --no-cache-dir -r requirements.txt

# Copiamos los ficheros de la aplicación
COPY app.py app.py
COPY goalscorers.csv goalscorers.csv

# Ejecutar el script
CMD ["python", "app.py"]