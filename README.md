# SmartWebApp

## 📌 Resumen del Proyecto
Aplicación web de ejemplo construida con **Spring Boot** que permite administrar usuarios y tareas. Sirve como base de pruebas para demostrar una arquitectura en capas sencilla y el uso de Thymeleaf como motor de plantillas.

## 🔧 Requisitos Técnicos
- **Java 17**
- **Maven**
- **PostgreSQL**
- Spring Boot 3.2.3

## 🚀 Instrucciones de Instalación
1. Clonar el repositorio.
2. Crear la base de datos `smartwebapp` y ajustar credenciales en `src/main/resources/application.properties`.
3. Instalar dependencias y ejecutar la aplicación:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
   La aplicación quedará disponible en `http://localhost:8082`.
4. En Windows puede usarse el script `scripts/setup.ps1` para automatizar la configuración.

## 🧪 Ejemplos de Uso
- Llamada a la API REST para listar tareas:
  ```bash
  curl http://localhost:8082/tasks/api
  ```

## 🗂️ Estructura del Proyecto
```text
src/
├── main
│   ├── java/com/example/smartwebapp
│   │   ├── controllers/    -> Controladores HTTP y REST
│   │   ├── services/       -> Lógica de negocio
│   │   ├── repositories/   -> Interfaces JPA
│   │   ├── models/         -> Entidades de dominio
│   │   ├── exceptions/     -> Manejo de errores
│   │   └── SmartWebAppApplication.java
│   └── resources
│       ├── application.properties
│       └── templates/      -> Vistas Thymeleaf
└── test
    └── java/com/example/smartwebapp
        └── UserServiceTests.java
```
Además existen carpetas `scripts/` con utilidades y `docs/` para documentación adicional.

## 👨‍💻 Contribución
Las aportaciones son bienvenidas. Abre un *issue* o envía un *pull request* describiendo tu propuesta.

## 📄 Licencia
Este proyecto se distribuye bajo la licencia MIT.

## 🧠 Consideraciones Finales
La lógica de negocio actual se mantiene simple para fines demostrativos. Se pueden implementar mejoras como seguridad con Spring Security o más pruebas automatizadas.
