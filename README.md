# Desarrollo de Aplicaciones Web

Este repositorio contiene los ejemplos y ejercicios utilizados en la asignatura **Desarrollo de Aplicaciones Web**, perteneciente al **Grado en Ingeniería del Software** de la ETSII URJC.

El objetivo es ofrecer material práctico que permita familiarizarse con las diferentes tecnologías involucradas en el desarrollo de aplicaciones web modernas, desde el backend hasta el frontend, pasando por el despliegue y la contenedorización.

## 📑 Tabla de Contenidos

- [Tecnologías Utilizadas](#-tecnologías-utilizadas)
- [Estructura del Repositorio](#-estructura-del-repositorio)
- [Temario de la Asignatura](#-temario-de-la-asignatura)
- [Proyectos de Ejemplo](#-proyectos-de-ejemplo)
- [Requisitos Previos](#-requisitos-previos)
- [Recursos](#-recursos)
- [Licencia](#-licencia)

## 🛠️ Tecnologías Utilizadas

Este repositorio incluye ejemplos y proyectos que utilizan las siguientes tecnologías:

**Backend:**
- Java (Spring Boot, Spring MVC, Spring Data, Spring Security)
- API REST
- Base de datos MySQL
- Maven

**Frontend:**
- React
- JavaScript/TypeScript
- HTML/CSS
- React Router
- Bootstrap & Material UI

**Despliegue y Contenedorización:**
- Docker
- Docker Compose

## 📂 Estructura del Repositorio

```
daw/
├── parte_1/              # Tecnologías web de servidor (Spring)
│   ├── spring_web/       # Ejemplos de Spring MVC
│   ├── spring_data/      # Ejemplos de Spring Data JPA
│   ├── spring_security/  # Ejemplos de autenticación y autorización
│   ├── spring_rest/      # Ejemplos de API REST
│   └── spring_deploy/    # Ejemplos de despliegue
├── parte_2/              # Despliegue de aplicaciones web
│   ├── docker/           # Ejemplos de Docker
│   └── docker-compose/   # Ejemplos de Docker Compose
├── parte_3/              # Tecnologías web avanzadas de cliente (React)
├── ejemplos_practica/    # Proyectos de ejemplo completos
│   ├── ejemplo-practica1/  # Spring MVC + MySQL + Mustache
│   └── ejemplo-practica2/  # API REST + Docker + OpenAPI
└── README.md
```

## 📚 Temario de la Asignatura

El temario de la asignatura se divide en las siguientes partes:

### Parte 1. Tecnologías web de servidor
* **Tema 1:** Spring Web
* **Tema 2:** Spring Data
* **Tema 3:** Spring Security
* **Tema 4:** Spring Rest
* **Tema 5:** Spring Deploy

📁 Los ejemplos y ejercicios de esta parte pueden encontrarse en **[parte_1/](parte_1/)**

### Parte 2. Despliegue de aplicaciones web
* **Tema 1:** Virtualización, Cloud Computing y Contenedores
* **Tema 2:** Docker - Contenedores y gestión
* **Tema 3:** Docker Compose - Orquestación de contenedores

📁 Los ejemplos y ejercicios de esta parte pueden encontrarse en **[parte_2/](parte_2/)**

### Parte 3. Tecnologías web avanzadas de cliente (React)
* **Tema 1:** Introducción
* **Tema 2:** Componentes
* **Tema 3:** REST y Servicios
* **Tema 4:** Aplicaciones Multipagina - Router
* **Tema 5:** Librerías de Componentes
* **Tema 6:** Publicación

📁 Los ejemplos y ejercicios de esta parte pueden encontrarse en **[parte_3/](parte_3/)**

## 🚀 Proyectos de Ejemplo

El objetivo de la asignatura es que los alumnos desarrollen una aplicación web completa, trabajando en grupos como si estuvieran en un entorno profesional. La aplicación se desarrolla y entrega por fases progresivas.

Todos los proyectos de ejemplo se encuentran en la carpeta **[ejemplos_practica/](ejemplos_practica/)**

### [Ejemplo Práctica 1](ejemplos_practica/ejemplo-practica1/)

**Aplicación web monolítica con servidor de aplicaciones**

Aplicación de gestión de libros que incluye:
- ✅ Implementación con **Spring MVC** y templates **Mustache**
- ✅ Base de datos **MySQL**
- ✅ Persistencia de imágenes en la base de datos
- ✅ CRUD completo (crear, leer, actualizar, eliminar)
- ✅ Sistema de **roles de usuario** con diferentes permisos
- ✅ Seguridad con **HTTPS**
- ✅ Gestión de sesiones y autenticación

### [Ejemplo Práctica 2](ejemplos_practica/ejemplo-practica2/)

**API REST y contenedorización**

Evolución de la Práctica 1 que añade:
- ✅ **API REST** completa con todos los endpoints
- ✅ Documentación de la API con **OpenAPI/Swagger**
- ✅ **Dockerización** de la aplicación
- ✅ **Docker Compose** para orquestar aplicación y base de datos
- ✅ Colección de **Postman** para probar la API
- ✅ Separación entre capa web y capa API

## ⚙️ Requisitos Previos

Para trabajar con los proyectos de este repositorio necesitas tener instalado:

**Para la Parte 1 (Spring):**
- **Java 21** o superior
- **Maven 3.6+**
- **MySQL** (o Docker para ejecutar MySQL en contenedor)

**Para la Parte 2 (Docker):**
- **Docker**
- **Docker Compose**

**Para la Parte 3 (React):**
- **Node.js 24+** y **npm**

**Herramientas generales:**
- **Git**
- Navegador web moderno (Chrome, Firefox, Edge)
- Cliente REST (Postman)

**IDEs recomendados:**
- **Visual Studio Code**

## 📖 Recursos

💡 **Nota:** Este material está pensado como apoyo para el aprendizaje práctico de herramientas y tecnologías de desarrollo web moderno.

**Enlaces del repositorio:**
- 🔗 Repositorio GitHub: https://github.com/codeurjc/daw

## 📄 Licencia

>©2026 Micael Gallego Carrillo, Francisco Gortázar Bellas, Michel Maes Bermejo, Óscar Soto Sánchez e Iván Chicano Capelo
>Algunos derechos reservados  
>Este documento se distribuye bajo la licencia  
>“Atribución-CompartirIgual 4.0 Internacional” de Creative Commons, disponible en  
>https://creativecommons.org/licenses/by-sa/4.0/deed.es