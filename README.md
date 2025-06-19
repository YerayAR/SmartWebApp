# SmartWebApp

Aplicacion web de ejemplo con Spring Boot, JSF, Hibernate y PostgreSQL.

## Requisitos
- Java 17
- Maven
- PostgreSQL

## Configuraci\u00f3n
Crea una base de datos llamada `smartwebapp` y ajusta las credenciales en `src/main/resources/application.properties`.

Para poblar datos iniciales se usa `import.sql`.

## Compilaci\u00f3n
```bash
mvn clean package
```

## Ejecuci\u00f3n
```bash
mvn spring-boot:run
```
La aplicaci\u00f3n quedar\u00e1 disponible en `http://localhost:8080`.
