# Parte 2: Despliegue de Aplicaciones Web con Contenedores

Este directorio contiene ejemplos y ejercicios prácticos sobre **contenedorización** y **orquestación** de aplicaciones utilizando **Docker** y **Docker Compose**.

## 📋 Descripción General

La segunda parte de la asignatura se enfoca en tecnologías de despliegue modernas, específicamente en el uso de contenedores para empaquetar, distribuir y ejecutar aplicaciones de forma consistente en cualquier entorno.

---

## 📚 Contenido del Temario

### 🐳 [Docker](docker/)

**Tema 2: Contenedores y Docker**

Fundamentos de contenedorización, creación de imágenes Docker y gestión de contenedores.

**Estructura por tecnologías:**

#### 📦 [Java/Spring](docker/java/)
- `docker_ejem1_spring-jar` - Dockerizar aplicación Spring Boot básica
- `docker_ejem2_spring-multistage` - Build multi-stage para optimizar tamaño
- `docker_ejem3_spring-multistage-cache` - Optimización con caché de dependencias Maven
- `docker_ejem4_java-cmd` - Uso de CMD y ENTRYPOINT

---

### 🎼 [Docker Compose](docker-compose/)

**Tema 3: Orquestación con Docker Compose**

Definición y gestión de aplicaciones multi-contenedor con Docker Compose.

**Estructura por tecnologías:**

#### ☕ [Java/Spring](docker-compose/java/)
- `docker-compose_ejem1_drupal` - Aplicación CMS multi-contenedor
- `docker-compose_ejem2_spring` - Spring Boot + MySQL con compose
- `docker-compose_ejem3_spring_build` - Build automático de imágenes
- `docker-compose_ejem4_spring_healthcheck` - Health checks y dependencias
- `docker-compose_ejem5_spring_oci` - Uso de imágenes OCI

---

## 🛠️ Tecnologías Utilizadas

- **Docker Engine** 20.10+
- **Docker Compose** 2.0+

---