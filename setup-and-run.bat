@echo off
setlocal

:: Set your desired target directory
:: set TARGET_DIR=C:\dev\gic-book-management-local
set TARGET_DIR=%USERPROFILE%\dev\gic-book-management-local

:: Create the directory if it doesn't exist
if not exist "%TARGET_DIR%" (
    mkdir "%TARGET_DIR%"
)

:: Move into the target directory
cd /d "%TARGET_DIR%"

:: Step 1: Download project zip
echo Downloading project zip...
curl -L -o gic-book-management.zip https://github.com/siyabongawonderboymdletshe/gic-book-management/archive/refs/heads/main.zip
if errorlevel 1 (
    echo ❌ Failed to download project ZIP.
    pause
    exit /b
)

:: Step 2: Extract the zip
echo Extracting zip...
powershell -Command "Expand-Archive -Path 'gic-book-management.zip' -DestinationPath '.' -Force"

if errorlevel 1 (
    echo ❌ Failed to extract project ZIP.
    pause
    exit /b
)

:: Step 3: Go into the extracted folder
cd gic-book-management-main

:: Step 4: Build the project with Maven Wrapper
echo Building project...
call mvnw clean package
if errorlevel 1 (
    echo ❌ Maven build failed.
    pause
    exit /b
)

:: Step 5: Run the app
echo Starting app...
java -jar target\gic-book-management-0.0.1-SNAPSHOT.jar

echo.
echo ✅ Setup complete! Project is in: %TARGET_DIR%\gic-book-management-main
pause