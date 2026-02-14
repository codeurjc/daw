# Selecciona la imagen base
FROM jupyter/minimal-notebook

# Definimos el directorio de trabajo en /home/jovyan/work
WORKDIR /home/jovyan/work

# Copiar el fichero de dependencias
COPY requirements.txt requirements.txt

# Instalar las dependencias python que necesita el cuaderno
RUN pip install --no-cache-dir -r requirements.txt

# Copiamos los ficheros de la aplicación
COPY Football_Telemetry_Analytics.ipynb Football_Telemetry_Analytics.ipynb
COPY football_telemetry.csv football_telemetry.csv

# Indicar el puerto que expone el contenedor
EXPOSE 8888