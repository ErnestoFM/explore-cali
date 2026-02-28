# ExploreCali - Sistema de Microservicios 🇨🇴

Este proyecto es una aplicación basada en microservicios diseñada para gestionar la oferta turística de Cali. Utiliza una arquitectura de **API Gateway** para centralizar el acceso, un servicio de datos relacionales para los tours y un servicio NoSQL para la gestión de imágenes.

## 🏗️ Arquitectura del Sistema

El sistema se divide en tres componentes principales:

1. **API Gateway (Puerto 8080):** Punto de entrada único. Gestiona el enrutamiento y la seguridad perimetral.
2. **Tour Service (Puerto 8082):** Microservicio basado en **Spring Data JPA** y SQL para gestionar la información estructurada de los tours.
3. **Image Service (Puerto 8083):** Microservicio basado en **MongoDB** para el almacenamiento y recuperación de metadatos de imágenes.

---

## 🚀 Tecnologías Utilizadas

* **Java 17+** & **Spring Boot 3.x**
* **Spring Cloud Gateway:** Enrutamiento reactivo.
* **Spring Security WebFlux:** Seguridad en el Gateway.
* **Spring Data JPA:** Persistencia relacional.
* **Spring Data MongoDB:** Almacenamiento NoSQL.
* **Maven:** Gestión de dependencias y construcción.

---

## 🛠️ Configuración y Ejecución

### Requisitos Previos

* Tener instalado el JDK 17 o superior.
* Base de datos SQL (H2/MySQL) y MongoDB en ejecución.

### Ejecución de los Servicios

Para levantar cada servicio, navega a su carpeta raíz y usa el Maven Wrapper:

```powershell
# Ejecutar con puerto por defecto
.\mvnw spring-boot:run

# Ejecutar especificando un puerto (ejemplo para Gateway)
.\mvnw spring-boot:run "-Dspring-boot.run.arguments=--server.port=8080"

```

---

## 🔐 Seguridad y Autenticación

El sistema implementa **HTTP Basic Auth** en el Gateway.

* **Lectura (GET):** Acceso público permitido para todos los endpoints.
* **Escritura (POST, PUT, DELETE):** Requiere autenticación.

### Credenciales de Acceso (Postman)

Para realizar peticiones protegidas, usa la pestaña **Authorization** > **Basic Auth**:

* **Usuario:** `user` (minúsculas)
* **Contraseña:** Revisar la consola del Gateway al iniciar (`Using generated security password: ...`) o configurar una fija en `application.yml`.

---

## 🛣️ Rutas del Gateway

| Recurso | Ruta Externa (8080) | Microservicio Destino |
| --- | --- | --- |
| Tours | `/tours/**` | `http://localhost:8082` |
| Paquetes | `/packages/**` | `http://localhost:8082` |
| Imágenes | `/images/**` | `http://localhost:8083` |

---

## 🧪 Pruebas Unitarias

El proyecto incluye pruebas de integración para los controladores. Para ejecutar los tests saltando errores conocidos de configuración:

```powershell
.\mvnw test -DskipTests=false

```

---

## 📋 Notas de Desarrollo

* **Manejo de Excepciones:** Se utiliza un `GlobalExceptionHandler` para capturar errores como `404 Not Found` y `400 Bad Request` (Constraint Violations), devolviendo objetos `ProblemDetail` estandarizados.
* **Validación:** El sistema valida que los IDs de cliente y puntajes de reseñas sean correctos antes de persistir en la base de datos.

---

**¿Te gustaría que añadiera una sección específica sobre cómo configurar la base de datos MongoDB o los modelos de datos JPA?**
