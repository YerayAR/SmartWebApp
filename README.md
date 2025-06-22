# SmartWebApp

Aplicacion web de ejemplo con Spring Boot, Thymeleaf, Hibernate y PostgreSQL.

## Requisitos
- Java 17
- Maven
- PostgreSQL

## Configuracion
Crea una base de datos llamada `smartwebapp` y ajusta las credenciales en `src/main/resources/application.properties`.

Para poblar datos iniciales se usa `import.sql`.

## Configuracion automatica
Para simplificar la puesta en marcha en Windows se incluye el script
`setup.ps1`. Ejecutalo desde PowerShell para que se encargue de crear la
base de datos, resolver posibles conflictos de puertos y arrancar la
aplicacion.

```powershell
./setup.ps1
```

## Compilacion
```bash
mvn clean package
```

## Ejecucion
```bash
mvn spring-boot:run
```
La aplicacion quedara disponible en `http://localhost:8082`.

## Vistas con Thymeleaf
La interfaz web se construye con [Thymeleaf](https://www.thymeleaf.org/),
lo que permite usar plantillas HTML estáticas enriquecidas con expresiones de
Spring para mostrar datos dinámicos de forma sencilla. No se requiere ninguna
configuración adicional, ya que Spring Boot detecta automáticamente las
plantillas ubicadas en `src/main/resources/templates`.
