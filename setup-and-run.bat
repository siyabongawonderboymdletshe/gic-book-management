@echo off
setlocal

:: === CONFIGURATION ===
set ZIP_URL=https://github.com/siyabongawonderboymdletshe/gic-book-management/archive/refs/heads/main.zip
set TARGET_DIR=%USERPROFILE%\dev\gic-book-management-local
set ZIP_FILE=%TEMP%\gic-book-management.zip

echo --------------------------------------------------
echo 📦 GIC Book Management - Setup


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


java -version
if errorlevel 1 (
    echo ❌ Java is NOT installed or not added to PATH..
    pause
    exit /b
)

:: STEP 5: Build JAR with Maven Wrapper
echo ⚙️ Packaging JAR...
call mvnw clean package
if errorlevel 1 (
    echo ❌ Maven build failed.
    pause
    exit /b
)


echo.
echo ✅ Setup complete! Project is in: %TARGET_DIR%\gic-book-management-main

:: Step 5: Run the app
echo Starting app...
java -jar target\gic-book-management-0.0.1-SNAPSHOT.jar

endlocal
pause