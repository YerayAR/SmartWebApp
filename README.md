# SmartWebApp

Aplicacion web de ejemplo con Spring Boot, JSF, Hibernate y PostgreSQL.

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
La aplicacion quedara disponible en `http://localhost:8080`.

## Integracion JSF
La aplicacion usa JavaServer Faces con MyFaces y CDI. El bean `JsfInitializer` registra programaticamente el `FacesServlet` y los listeners necesarios para que los enlaces y formularios funcionen correctamente. Si la navegacion parece estatica, asegúrate de ejecutar la aplicacion a traves de Spring Boot para que este inicializador se ejecute.
