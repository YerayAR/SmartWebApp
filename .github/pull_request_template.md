# 🐳 SmartWebApp Containerization

## 📝 Descripción
Esta PR containeriza completamente SmartWebApp con Docker, eliminando la necesidad de configuración manual de PostgreSQL y simplificando el proceso de setup para nuevos desarrolladores.

## ✨ Características Principales

### 🔧 Containerización
- **Dockerfile** optimizado para Spring Boot con Java 21
- **docker-compose.yml** con orquestación de PostgreSQL y aplicación
- **Healthchecks** para asegurar que PostgreSQL esté listo antes de iniciar la app
- **Volúmenes persistentes** para datos de la base de datos

### ⚙️ Configuración por Variables de Entorno
- **`.env.example`** como template para nuevos usuarios
- **Variables de entorno** para toda la configuración sensible
- **application.properties** actualizado para usar variables
- **Configuración flexible** para desarrollo y producción

### 🛡️ Manejo de Secretos
- **`.env` excluido de Git** para proteger credenciales
- **`.env.example`** incluido para documentar variables necesarias
- **`.gitignore` actualizado** con mejores prácticas
- **Contraseña por defecto**: `8888` (estándar del proyecto)

## 🚀 Instrucciones de Uso

### Para nuevos usuarios:
```bash
git clone <repository-url>
cd SmartWebApp
cp .env.example .env
docker-compose up --build
```

### Para desarrollo:
```bash
# Ejecutar en segundo plano
docker-compose up -d --build

# Ver logs
docker-compose logs -f app

# Acceder a PostgreSQL
docker-compose exec postgres psql -U postgres -d smartwebapp
```

## 📋 Cambios Realizados

### ➕ Archivos Añadidos
- `Dockerfile` - Configuración de contenedor para la aplicación
- `docker-compose.yml` - Orquestación de servicios
- `.env.example` - Template de variables de entorno
- `.dockerignore` - Archivos excluidos del build
- `.github/pull_request_template.md` - Template para PRs

### 🔄 Archivos Modificados
- `README.md` - Reescrito con foco en Docker
- `application.properties` - Ahora usa variables de entorno
- `.gitignore` - Mejorado para proyectos con Docker

### ❌ Archivos Eliminados
- `scripts/setup.ps1` - Ya no necesario con Docker

## 🧪 Testing

- ✅ **Construcción exitosa** de contenedores
- ✅ **Conectividad** PostgreSQL ↔ Spring Boot
- ✅ **Creación automática** de tablas
- ✅ **Variables de entorno** funcionando
- ✅ **Persistencia de datos** con volúmenes
- ✅ **Logs SQL** visibles en desarrollo

## 💡 Beneficios

1. **Setup simplificado**: Un solo comando para levantar todo
2. **Sin dependencias locales**: No necesita PostgreSQL instalado
3. **Reproducible**: Mismo entorno para todos los desarrolladores  
4. **Cross-platform**: Funciona en Windows, macOS y Linux
5. **Production-ready**: Fácil deployment en cualquier entorno Docker

## 🔍 Checklist

- [x] Dockerfile optimizado y funcional
- [x] docker-compose.yml con todos los servicios
- [x] Variables de entorno configuradas
- [x] README actualizado con instrucciones Docker
- [x] .gitignore actualizado para secretos
- [x] Aplicación funciona correctamente
- [x] Base de datos se conecta y crea tablas
- [x] Testing completo realizado

## 🏷️ Etiquetas
`enhancement` `docker` `infrastructure` `configuration`
