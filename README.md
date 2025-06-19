# SmartWebApp

Aplicación web de ejemplo con **Spring Boot 3.2.3**, JSF, Hibernate y PostgreSQL.

## Requisitos
- Java 21
- Maven
- PostgreSQL

## Configuraci\u00f3n
Crea una base de datos llamada `smartwebapp` y ajusta las credenciales en `src/main/resources/application.properties`.

Para poblar datos iniciales se usa `import.sql`.

## Compilaci\u00f3n
```bash
mvn clean package -DskipTests
```

## Ejecuci\u00f3n
```bash
mvn spring-boot:run
```
La aplicaci\u00f3n quedar\u00e1 disponible en `http://localhost:8080`.
