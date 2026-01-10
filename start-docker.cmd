@echo off
echo ========================================
echo Starting ServeLogic Application Stack
echo ========================================
echo.
echo This will start all services:
echo - ArangoDB Database (port 8529)
echo - Fuseki Database (port 3030)
echo - ServeLogic API (port 7999)
echo - CMS Application (port 7998)
echo - Ordering Application (port 8080)
echo.
echo Building and starting containers...
echo.

docker-compose up --build

echo.
echo ========================================
echo Application stack stopped
echo ========================================
