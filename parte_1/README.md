# Parte 1: Tecnologías Web de Servidor con Spring

Este directorio contiene ejemplos y ejercicios prácticos sobre el desarrollo de aplicaciones web del lado del servidor utilizando el framework **Spring Boot**.

## 📋 Descripción General

Esta primera parte de la asignatura se enfoca en las tecnologías fundamentales para el desarrollo backend con Spring, cubriendo desde los conceptos básicos de Spring MVC hasta el despliegue de aplicaciones en producción.

---

## 📚 Contenido del Temario

### 🌐 [Spring Web](spring_web/)

**Tema 1: Fundamentos de Spring MVC**

Introducción al desarrollo web con Spring MVC, manejo de controladores, vistas y datos.

**Contenido:**
- 8 ejemplos progresivos (`web_ejem1` - `web_ejem8b`)
- 7 ejercicios prácticos (`web_ejer1` - `web_ejer7`)

**Conceptos cubiertos:**
- Controladores y mapeo de URLs
- Templates Mustache
- Gestión de formularios
- Sesiones HTTP
- Manejo de archivos estáticos
- Upload de archivos
- Validación de datos

---

### 💾 [Spring Data](spring_data/)

**Tema 2: Persistencia de Datos con JPA**

Gestión de bases de datos relacionales utilizando Spring Data JPA e Hibernate.

**Contenido:**
- 21 ejemplos (`bd_ejem1` - `bd_ejem21`)
- Script para MySQL en Docker: `runMySQLDocker.sh`

**Conceptos cubiertos:**
- Configuración de bases de datos
- Entidades JPA y relaciones (OneToMany, ManyToOne, ManyToMany)
- Repositorios Spring Data
- Consultas personalizadas (JPQL, Query Methods)
- Transacciones
- Carga lazy vs eager
- Caché de segundo nivel
- Migraciones con Flyway

**Base de datos:** MySQL

---

### 🔐 [Spring Security](spring_security/)

**Tema 3: Autenticación y Autorización**

Implementación de mecanismos de seguridad en aplicaciones web.

**Contenido:**
- 9 ejemplos (`security_ejem1` - `security_ejem7`)

**Conceptos cubiertos:**
- Configuración básica de Spring Security
- Autenticación en memoria
- Autenticación con base de datos
- Roles y permisos
- Login personalizado
- HTTPS y certificados SSL
- Password encoding (BCrypt)
- CSRF protection
- Session management

---

### 🔌 [Spring REST](spring_rest/)

**Tema 4: API REST y Servicios Web**

Desarrollo de APIs RESTful y consumo de servicios web.

**Contenido:**
- 17 ejemplos de API REST (`rest_ejem1` - `rest_ejem17`)
- 2 ejercicios (`rest_ejer1`, `rest_ejer2`)
- 2 ejemplos de frontend consumidor (`rest_front_ejem1_console`, `rest_front_ejem2_dom`)
- 1 ejemplo adicional con Feign Client

**Conceptos cubiertos:**
- Controladores REST (`@RestController`)
- Métodos HTTP (GET, POST, PUT, DELETE, PATCH)
- Códigos de estado HTTP
- DTOs y mappers
- Manejo de errores y excepciones
- Validación de datos (Bean Validation)
- Paginación y filtrado
- Upload de imágenes en APIs
- Documentación con OpenAPI/Swagger
- Seguridad en APIs (Basic Auth, JWT)
- RestClient y consumo de APIs
- Jackson para JSON

---

### 🚀 [Spring Deploy](spring_deploy/)

**Tema 5: Despliegue de Aplicaciones**

Empaquetar y desplegar aplicaciones Spring Boot en diferentes entornos.

**Contenido:**
- 2 ejemplos de despliegue (`despliegue_ejem1`, `despliegue_ejem2`)

**Conceptos cubiertos:**
- Empaquetado JAR y WAR
- Configuración por perfiles (dev, prod)
- Variables de entorno
- Despliegue en servidores de aplicaciones
- Configuración de producción

---

## 🛠️ Tecnologías Utilizadas

- **Java 21+**
- **Spring Boot 4.x**
  - Spring MVC
  - Spring Data JPA
  - Spring Security
  - Spring REST
- **Maven** (gestión de dependencias)
- **MySQL 9+** (base de datos)
- **Mustache** (motor de templates)
- **OpenAPI/Swagger** (documentación API)

---

## ⚙️ Requisitos Previos

Para trabajar con estos ejemplos necesitas:

- **Java JDK 21** o superior
- **Maven 3.6+**
- **MySQL 9+** (o Docker para ejecutarlo en contenedor)
- **IDE recomendado:** Visual Studio Code