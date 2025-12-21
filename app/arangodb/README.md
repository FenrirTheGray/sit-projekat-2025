# ArangoDB (Development Setup)

This project uses **ArangoDB 3.11** running in Docker for local development.

## How ArangoDB is run

- Image: `arangodb:3.11`
- Container: `servelogic-app_arangodb`
- Port: `8529`
- Root user enabled (`root` / `root`)

> Only ArangoDB runs here — the backend is in a separate container.

## Data persistence

Database data is persisted in a **Docker named volume**: 

- The volume is managed by Docker
- It is **not stored in Git**
- It survives container restarts
- named volume: `servelogic-app_arangodb_data`

Make sure Docker is installed and running.

Container can be safely stopped via Ctrl+C (or forced via repeated Ctrl+C)

### Container Started/Removed via:
PowerShell
- started
```powershell
docker-compose -f .\docker-compose.env-dev.yml up
```
- removed
```powershell
docker-compose -f .\docker-compose.env-dev.yml down
```
Command Prompt
- started
```cmd
docker-compose -f docker-compose.env-dev.yml up
```
- removed
```cmd
docker-compose -f docker-compose.env-dev.yml down
```
Bash
- started
```bash
docker-compose -f docker-compose.env-dev.yml up
```
- removed
```bash
docker-compose -f docker-compose.env-dev.yml down
```


## To fully reset the database:
PowerShell
```powershell
docker-compose -f .\docker-compose.env-dev.yml down -v
```
Command Prompt
```cmd
docker-compose -f docker-compose.env-dev.yml down -v
```
Bash
```bash
docker-compose -f docker-compose.env-dev.yml down -v
```
