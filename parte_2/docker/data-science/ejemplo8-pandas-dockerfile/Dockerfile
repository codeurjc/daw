# Si se usa Spark, cambiar la imagen base a la siguiente
# FROM jupyter/pyspark-notebook

# Selecciona la imagen base
FROM jupyter/minimal-notebook

# Definimos el directorio de trabajo en /home/jovyan/work
WORKDIR /home/jovyan/work

# Copiar el fichero de dependencias
COPY requirements.txt requirements.txt

# Instalar las dependencias python que necesita el cuaderno
RUN pip install --no-cache-dir -r requirements.txt

# Copiamos los ficheros de la aplicación
COPY Football_Soccer_Analytics_Pandas.ipynb Football_Soccer_Analytics_Pandas.ipynb
COPY goalscorers.csv goalscorers.csv

# Indicar el puerto que expone el contenedor
EXPOSE 8888