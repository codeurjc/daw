library(randomForest)

# Lectura de argumentos
args <- commandArgs(T)

input <- data.frame(
  Sepal.Length = as.numeric(args[1]),
  Sepal.Width = as.numeric(args[2]),
  Petal.Length = as.numeric(args[3]),
  Petal.Width = as.numeric(args[4])
)

# Entrenamiento del modelo si no existe
if (!file.exists("/tmp/model/iris_clf_rf.rds")){
  message('Training model...')
  
  clf_rf <- randomForest(Species ~ ., data = iris)
  
  # Guardado del modelo entrenado
  if (!dir.exists("/tmp/model")) {
    dir.create("/tmp/model")
  }

  saveRDS(clf_rf, '/tmp/model/iris_clf_rf.rds')

# Carga del modelo entrenado
} else {
  message('Reading model...')
  clf_rf <- readRDS('/tmp/model/iris_clf_rf.rds')
}

# Predicción
predict(clf_rf, input)