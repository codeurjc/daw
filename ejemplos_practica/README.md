# Proyectos de Ejemplo - Desarrollo de Aplicaciones Web

Este directorio contiene proyectos de ejemplo completos que ilustran las diferentes fases del desarrollo de una aplicación web moderna. Cada ejemplo representa una práctica entregable de la asignatura, construyendo progresivamente sobre el anterior.

## 📋 Descripción General

Los ejemplos implementan una **aplicación de gestión de libros** que evoluciona desde una aplicación web hasta una arquitectura basada en API REST con contenedores Docker.

---

## 🎯 Proyectos Disponibles

### 📘 [Práctica 1 - Aplicación Web](ejemplo-practica1/)

**Objetivo:** Desarrollo de una aplicación web completa con servidor de aplicaciones.

**Tecnologías:**
- Spring Boot + Spring MVC
- Templates Mustache
- MySQL (base de datos relacional)
- Spring Security (autenticación y autorización)
- HTTPS

**Funcionalidades:**
- ✅ CRUD completo de libros
- ✅ Gestión de imágenes (persistencia en BD)
- ✅ Sistema de usuarios con roles (user/admin)
- ✅ Autenticación y autorización
- ✅ Interfaz web responsiva

**Inicio rápido:**
```bash
cd ejemplo-practica1
docker run --rm -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=books -p 3306:3306 -d mysql:9.2
mvn spring-boot:run
```
🌐 Acceso: https://localhost:8443

---

### 📗 [Práctica 2 - API REST y Docker](ejemplo-practica2/)

**Objetivo:** Añadir una API REST y contenedorizar la aplicación.

**Nuevo en esta práctica:**
- 🆕 API REST completa
- 🆕 Documentación OpenAPI/Swagger
- 🆕 Colección de Postman

**Hereda de Práctica 1:**
- Todo lo anterior (Spring MVC, MySQL, seguridad, etc.)

**Inicio rápido:**
```bash
cd ejemplo-practica2
docker run --rm -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=books -p 3306:3306 -d mysql:9.2
mvn spring-boot:run
```
🌐 Web: https://localhost:8443  
📡 API: https://localhost:8443/api/