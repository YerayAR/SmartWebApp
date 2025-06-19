# SmartWebApp

Aplicacion web de ejemplo con Spring Boot, JSF, Hibernate y PostgreSQL.

## Requisitos
- Java 17
- Maven
- PostgreSQL

## Configuracion
Crea una base de datos llamada `smartwebapp` y ajusta las credenciales en `src/main/resources/application.properties`.

Para poblar datos iniciales se usa `import.sql`.

## Compilacion
```bash
mvn clean package
```

## Ejecucion
```bash
mvn spring-boot:run
```
La aplicacion quedara disponible en `http://localhost:8080`.
