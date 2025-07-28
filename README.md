# SmartWebApp

## 📌 Resumen del Proyecto
Aplicación web de ejemplo construida con **Spring Boot** que permite administrar usuarios y tareas. Sirve como base de pruebas para demostrar una arquitectura en capas sencilla y el uso de Thymeleaf como motor de plantillas.

## 🔧 Requisitos Técnicos
- **Docker** y **Docker Compose**
- Spring Boot 3.2.3
- PostgreSQL 15
- Java 21

## 🚀 Instrucciones de Instalación

### Opción 1: Con Docker (Recomendado)
1. Clonar el repositorio:
   ```bash
   git clone <repository-url>
   cd SmartWebApp
   ```

2. Crear archivo de variables de entorno:
   ```bash
   cp .env.example .env
   ```

3. Ejecutar con Docker Compose:
   ```bash
   docker-compose up --build
   ```

4. La aplicación estará disponible en `http://localhost:8080`

5. Para detener la aplicación:
   ```bash
   docker-compose down
   ```

### Opción 2: Desarrollo Local
1. Clonar el repositorio
2. Tener PostgreSQL ejecutándose localmente
3. Crear la base de datos `smartwebapp`
4. Ejecutar:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

## 🧪 Ejemplos de Uso
- Acceder a la aplicación web: `http://localhost:8080`
- Llamada a la API REST para listar tareas:
  ```bash
  curl http://localhost:8080/tasks/api
  ```
- Llamada a la API REST para listar usuarios:
  ```bash
  curl http://localhost:8080/users/api
  ```

## ⚙️ Configuración

### Variables de Entorno
El archivo `.env` contiene la configuración principal:

```bash
# Base de datos
POSTGRES_DB=smartwebapp          # Nombre de la base de datos
POSTGRES_USER=postgres           # Usuario de PostgreSQL
POSTGRES_PASSWORD=8888          # Contraseña (cambiar en producción)

# Aplicación
SERVER_PORT=8080                # Puerto de la aplicación

# Spring Boot
SPRING_PROFILES_ACTIVE=docker   # Perfil activo
SPRING_JPA_HIBERNATE_DDL_AUTO=update  # Gestión de esquema BD
SPRING_JPA_SHOW_SQL=true        # Mostrar consultas SQL
```

### Personalización
- **Cambiar puerto**: Modifica `SERVER_PORT` en `.env`
- **Cambiar credenciales**: Modifica `POSTGRES_*` en `.env`
- **Modo producción**: Cambia `SPRING_PROFILES_ACTIVE=prod`
- **Deshabilitar logs SQL**: `SPRING_JPA_SHOW_SQL=false`

## 🐳 Comandos Docker Útiles
```bash
# Construir y ejecutar en segundo plano
docker-compose up -d --build

# Ver logs de la aplicación
docker-compose logs -f app

# Ver logs de PostgreSQL
docker-compose logs -f postgres

# Acceder al contenedor de la aplicación
docker-compose exec app bash

# Acceder a PostgreSQL
docker-compose exec postgres psql -U postgres -d smartwebapp

# Parar y eliminar contenedores
docker-compose down

# Parar y eliminar contenedores + volúmenes
docker-compose down -v

# Reconstruir solo la aplicación
docker-compose build app
```

## 🗂️ Estructura del Proyecto
```text
├── src/
│   ├── main/
│   │   ├── java/com/example/smartwebapp/
│   │   │   ├── controllers/    → Controladores HTTP y REST
│   │   │   ├── services/       → Lógica de negocio
│   │   │   ├── repositories/   → Interfaces JPA
│   │   │   ├── models/         → Entidades de dominio
│   │   │   ├── exceptions/     → Manejo de errores
│   │   │   └── SmartWebAppApplication.java
│   │   └── resources/
│   │       ├── application.properties  → Configuración con variables
│   │       └── templates/      → Vistas Thymeleaf
│   └── test/
│       └── java/com/example/smartwebapp/
├── Dockerfile              → Configuración Docker para la app
├── docker-compose.yml      → Orquestación de servicios
├── .dockerignore           → Archivos excluidos del build
├── .env.example            → Template de variables de entorno
├── .env                    → Variables de entorno (no en Git)
├── .gitignore              → Archivos excluidos de Git
└── docs/                   → Documentación adicional
```

## 👨‍💻 Contribución
Las aportaciones son bienvenidas. Abre un *issue* o envía un *pull request* describiendo tu propuesta.

## 📄 Licencia
Este proyecto se distribuye bajo la licencia MIT.

## 🧠 Consideraciones Finales
La lógica de negocio actual se mantiene simple para fines demostrativos. Se pueden implementar mejoras como seguridad con Spring Security o más pruebas automatizadas.
