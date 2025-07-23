<#
    Script de configuracion automatizada para SmartWebApp.
    - Verifica servicio PostgreSQL y arranca si es necesario
    - Crea la base de datos smartwebapp
    - Resuelve conflictos de puerto (8080-8090)
    - Actualiza src/main/resources/application.properties
    - Ajusta nombres de tablas JPA que sean palabras reservadas
    - Verifica declaraciones de paquete
    - Compila y ejecuta la aplicacion
#>

Write-Host "--- Inicio de configuracion de SmartWebApp ---" -ForegroundColor Cyan

# 1. Verificar servicio PostgreSQL
$pgService = Get-Service -Name "*postgres*" -ErrorAction SilentlyContinue | Select-Object -First 1
if (-not $pgService) {
    Write-Error "No se encontro un servicio de PostgreSQL. Instale PostgreSQL antes de continuar.";
    exit 1
}
if ($pgService.Status -ne 'Running') {
    Write-Host "Iniciando servicio $($pgService.Name)..."
    Start-Service $pgService.Name
}

# 2. Localizar psql.exe
$psql = Get-ChildItem -Path "C:\Program Files\PostgreSQL" -Recurse -Filter "psql.exe" -ErrorAction SilentlyContinue | Select-Object -First 1
if (-not $psql) {
    Write-Error "No se encontro psql.exe en la ruta por defecto."
    exit 1
}

# 3. Crear base de datos si no existe
$env:PGPASSWORD = '5409'
$dbExists = & "$($psql.FullName)" -U postgres -tAc "SELECT 1 FROM pg_database WHERE datname='smartwebapp';"
if (-not $dbExists) {
    Write-Host "Creando base de datos 'smartwebapp'..."
    & "$($psql.FullName)" -U postgres -c "CREATE DATABASE smartwebapp;"
} else {
    Write-Host "La base de datos 'smartwebapp' ya existe."
}

# 4. Detectar puerto disponible
$port = 8080
while ($port -le 8090) {
    if (-not (netstat -ano | Select-String ":$port\s")) { break }
    $port++
}
if ($port -gt 8090) {
    Write-Error "No hay puertos libres entre 8080 y 8090."
    exit 1
}
Write-Host "Se usara el puerto $port para la aplicacion."

# 5. Actualizar application.properties
$propPath = Join-Path $PSScriptRoot 'src/main/resources/application.properties'
$lines = Get-Content $propPath | Where-Object { $_ -notmatch '^server\.port=' }
$lines = $lines | ForEach-Object {
    if ($_ -match '^spring.datasource.username') { 'spring.datasource.username=postgres' }
    elseif ($_ -match '^spring.datasource.password') { 'spring.datasource.password=5409' }
    else { $_ }
}
$lines += "server.port=$port"
Set-Content $propPath $lines
Write-Host "Archivo application.properties actualizado."

# 6. Ajustar nombres de tablas para palabras reservadas
$reserved = @('user','order','group')
$modelDir = Join-Path $PSScriptRoot 'src/main/java/com/example/smartwebapp/models'
Get-ChildItem $modelDir -Filter '*.java' | ForEach-Object {
    $class = $_.BaseName.ToLower()
    if ($reserved -contains $class) {
        $content = Get-Content $_.FullName
        if (-not ($content | Select-String '@Table')) {
            $idx = $content.IndexOf('@Entity')
            if ($idx -ge 0) {
                $newName = "app_$class"
                $content[$idx] += "`n@Table(name = '$newName')"
                Set-Content $_.FullName $content
                Write-Host "Se aplico @Table(name='$newName') en la entidad $class"
            }
        }
    }
}

# 7. Verificar declaraciones de paquete
$srcBase = Join-Path $PSScriptRoot 'src/main/java'
Get-ChildItem $srcBase -Recurse -Filter '*.java' | ForEach-Object {
    $path = $_.FullName
    $content = Get-Content $path
    $firstNonEmpty = $content | Where-Object { $_.Trim() } | Select-Object -First 1
    if ($firstNonEmpty -notmatch '^package ') {
        $relPath = $path.Substring($srcBase.Length + 1)
        $pkg = $relPath.Substring(0,$relPath.LastIndexOf('\')) -replace '\\','.'
        $packageLine = "package $pkg;"
        $newContent = @($packageLine,'' ) + $content
        Set-Content $path $newContent
        Write-Host "Se agrego declaracion de paquete en $path"
    }
}

# 8. Compilar proyecto
Write-Host "Compilando proyecto Maven..."
& mvn clean compile
if ($LASTEXITCODE -ne 0) {
    Write-Error "La compilacion fallo."
    exit 1
}

# 9. Iniciar aplicacion
Write-Host "Iniciando aplicacion en http://localhost:$port ..."
& mvn spring-boot:run

