@echo off
setlocal

:: === CONFIGURATION ===
set ZIP_URL=https://github.com/siyabongawonderboymdletshe/gic-book-management/archive/refs/heads/main.zip
set TARGET_DIR=%USERPROFILE%\dev\gic-book-management-docker
set ZIP_FILE=%TEMP%\gic-book-management.zip

echo --------------------------------------------------
echo 📦 GIC Book Management - Setup and Docker Startup
echo --------------------------------------------------

:: STEP 1: Ensure curl is available
where curl >nul 2>&1
if errorlevel 1 (
    echo ❌ curl not found. Please install or use Git Bash.
    pause
    exit /b
)

:: STEP 2: Download ZIP
echo 🔽 Downloading project ZIP...
mkdir "%TARGET_DIR%" >nul 2>&1
curl -L -o "%ZIP_FILE%" %ZIP_URL%
if errorlevel 1 (
    echo ❌ Failed to download project ZIP.
    pause
    exit /b
)

:: STEP 3: Unzip to target dir
echo 📂 Extracting files...
powershell -Command "Expand-Archive -Path '%ZIP_FILE%' -DestinationPath '%TARGET_DIR%' -Force"
if errorlevel 1 (
    echo ❌ Failed to extract project ZIP.
    pause
    exit /b
)


:: ✅ Clean up ZIP file
echo 🧹 Cleaning up temporary files...
del "%ZIP_FILE%" >nul 2>&1

:: STEP 4: Go into extracted folder
cd /d "%TARGET_DIR%\gic-book-management-main"

:: STEP 5: Build JAR with Maven Wrapper
echo ⚙️ Packaging JAR...
call mvnw clean package
if errorlevel 1 (
    echo ❌ Maven build failed.
    pause
    exit /b
)

:: STEP 6: Check for Docker
echo 🐳️ Checking for Docker...
docker --version >nul 2>&1
if errorlevel 1 (
    echo ❌ Docker is not installed or not in PATH.
    echo Please install Docker Desktop first: https://www.docker.com/products/docker-desktop
    pause
    exit /b
)

:: STEP 7: Start Docker containers
echo 🐳 Starting Docker containers...
docker-compose up --build

endlocal
pause