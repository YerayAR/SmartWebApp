# Documentación de SmartWebApp

## Resumen ejecutivo
SmartWebApp es un ejemplo de aplicación web basada en **Spring Boot** que
utiliza **JavaServer Faces (JSF)** como motor de vistas. El acceso a datos se
realiza mediante **Hibernate/JPA** sobre **PostgreSQL**. Su objetivo principal
es ilustrar una arquitectura en capas sencilla (Controlador, Servicio y
Repositorio) para la gestión de usuarios y tareas.

## Introducción
SmartWebApp es una aplicación web desarrollada con **Spring Boot** y
**JavaServer Faces (JSF)** que utiliza **Hibernate/JPA** para el acceso
persistente sobre **PostgreSQL**. El propósito del proyecto es mostrar un
pequeño gestor de usuarios y tareas, sirviendo como base para ejemplos de
arquitectura en capas y pruebas con Spring.

## Arquitectura general
La estructura sigue un modelo clásico dividido en controladores, servicios y
repositorios. A continuación se resume el flujo principal:

```text
[Controladores] --> [Servicios] --> [Repositorios] --> [Base de datos]
        ^                                             \
        |                                              \-> [Entidades JPA]
```

### Diagrama de capas
```text
+---------+     +---------+     +-------------+     +-----------------+
| Control | --> | Service | --> | Repository  | --> |     Database     |
+---------+     +---------+     +-------------+     +-----------------+
```

Cada capa tiene una responsabilidad concreta:
- **Controladores**: reciben las peticiones web y preparan las vistas JSF.
- **Servicios**: contienen la lógica de dominio y validaciones.
- **Repositorios**: interfaces JPA para operaciones de persistencia.

## Estructura de carpetas
```text
src/main
├── java
│   └── com.example.smartwebapp
│       ├── controller   # Controladores MVC
│       ├── service      # Servicios de dominio
│       ├── repository   # Interfaces JPA
│       ├── model        # Entidades
│       └── exception    # Clases de error
├── resources
│   └── application.properties
└── webapp           # Vistas JSF
```

## Paquetes y clases principales
- `SmartWebAppApplication`: arranque de la aplicación Spring.
- `controller.UserController`: páginas JSF para listar y crear usuarios.
- `controller.TaskController`: páginas JSF para tareas.
- `service.UserService` / `service.TaskService`: servicios con operaciones
  CRUD básicas.
- `repository.UserRepository` / `repository.TaskRepository`: acceso a datos.
- `model.User` y `model.Task`: entidades JPA persistidas en la base de datos.
- `exception.*`: manejo centralizado de errores.

### Diagrama de entidades
```text
User 1 --- * Task
```

## API y rutas
Actualmente la aplicación expone vistas MVC con las siguientes rutas:

| Método | Ruta    | Descripción                                   |
|--------|---------|-----------------------------------------------|
| GET    | `/users`| Muestra listado de usuarios y formulario de alta |
| POST   | `/users`| Registra un nuevo usuario                      |
| GET    | `/tasks`| Lista tareas y permite registrar una nueva     |
| POST   | `/tasks`| Persiste la tarea enviada                      |

Ejemplo de petición usando `curl` para crear un usuario:
```bash
curl -X POST -d "name=John" http://localhost:8080/users
```

## Requisitos técnicos
- **Java 17**
- **Maven**
- **PostgreSQL** configurado y accesible según
  `src/main/resources/application.properties`.

En `application.properties` se define la URL de conexión a la base de datos,
usuario y contraseña. Hibernate está configurado con `ddl-auto=update` para
crear o actualizar las tablas automáticamente.

## Ejecución local
1. Crear la base `smartwebapp` y ajustar credenciales en el fichero
   `application.properties`.
2. (Opcional) ejecutar `db-init.sql` para crear las tablas manualmente o usar
   el script `import.sql` para datos de ejemplo.
3. Compilar y ejecutar:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
4. Abrir `http://localhost:8080` en el navegador.

## Despliegue en producción
1. Empaquetar con `mvn clean package`.
2. Copiar el JAR generado al servidor destino con Java 17 instalado.
3. Configurar las variables de entorno de conexión a la base de datos o
   modificar `application.properties`.
4. Ejecutar:
   ```bash
   java -jar smartwebapp-0.0.1-SNAPSHOT.jar
   ```

Para ejecutar las pruebas unitarias:
```bash
mvn test
```

## Posibles mejoras
- Incluir capa de seguridad (Spring Security) para autenticar usuarios.
- Añadir controladores REST y pruebas automáticas adicionales.
- Revisar las pruebas actuales: `UserServiceTests` usa `@DataJpaTest` pero
  podría migrarse a `@SpringBootTest` para disponer del contexto completo.
- Extraer la lógica de recarga de listas en los controladores a métodos
  privados o servicios auxiliares para reducir duplicidad de código.

