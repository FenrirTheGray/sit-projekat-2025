# ServeLogic

Web sistem za upravljanje restoranom - menadžment menija, poručivanje i statistika porudžbina.

## Pregled

ServeLogic je full-stack aplikacija koja omogućava administratorima restorana da upravljaju menijima i prate porudžbine, dok korisnicima pruža intuitivan interfejs za poručivanje.

## Arhitektura sistema

```
┌─────────────────┐     ┌─────────────────┐
│   CMS App       │     │  Ordering App   │
│  (Admin UI)     │     │  (Korisnički UI)│
│   Port 7998     │     │   Port 8080     │
└────────┬────────┘     └────────┬────────┘
         │                       │
         └───────────┬───────────┘
                     │
                     ▼
         ┌───────────────────────┐
         │    ServeLogic API     │
         │      Port 7999        │
         └───────────┬───────────┘
                     │
         ┌───────────┴───────────┐
         │                       │
         ▼                       ▼
┌─────────────────┐     ┌─────────────────┐
│    ArangoDB     │     │  Apache Fuseki  │
│   Port 8529     │     │   Port 3030     │
└─────────────────┘     └─────────────────┘
```

### Komponente

| Komponenta | Opis | Port |
|------------|------|------|
| **CMS App** | Admin interfejs za upravljanje menijem i statistiku porudžbina | 7998 |
| **Ordering App** | Korisnička aplikacija za pregled menija i poručivanje | 8080 |
| **ServeLogic API** | Backend REST API sa poslovnom logikom | 7999 |
| **ArangoDB** | Document baza podataka za perzistenciju | 8529 |
| **Apache Fuseki** | RDF/SPARQL server za semantičke podatke | 3030 |

## Tehnologije

| Sloj | Tehnologija |
|------|-------------|
| Backend | Java 23, Spring Boot 3.4.1 |
| Frontend | Vaadin 24.6.3 |
| Baza podataka | ArangoDB 3.11 |
| Semantički store | Apache Jena Fuseki |
| Build alat | Maven 3.9 |
| Kontejnerizacija | Docker, Docker Compose |

## Funkcionalnosti

### CMS aplikacija (Administratori)
- Upravljanje menijem (kategorije, artikli, modifikatori, combo ponude)
- Praćenje i statistika porudžbina
- Izveštaji o prometu i analitika
- Autentifikacija korisnika

### Ordering aplikacija (Kupci)
- Pregled kategorija i artikala iz menija
- Prilagođavanje porudžbina sa modifikatorima
- Upravljanje korpom
- Istorija porudžbina

## Pokretanje projekta

### Preduslovi

**Za Docker deployment:**
- Docker Desktop ili Docker Engine sa Docker Compose

**Za lokalni razvoj:**
- Java 23
- Maven
- Node.js
- Docker

### Brzi start sa Docker-om (Preporučeno)

**Windows:**
```powershell
.\start-docker.cmd
```

**Linux/Mac:**
```bash
./start-docker.sh
```

**Ili direktno sa Docker Compose:**
```bash
docker-compose up --build
```

Ovo će pokrenuti sve servise:
- CMS App: http://localhost:7998
- Ordering App: http://localhost:8080
- API: http://localhost:7999/api
- ArangoDB UI: http://localhost:8529 (korisnik: `root`, lozinka: `root`)
- Fuseki UI: http://localhost:3030 (korisnik: `admin`, lozinka: `admin`)

**Zaustavljanje servisa:**
```bash
docker-compose down
```

**Potpuno čišćenje (briše i podatke iz baze):**
```bash
docker-compose down -v
```

### Lokalni razvoj

#### 1. Pokretanje baza podataka

```powershell
cd app
docker-compose -f docker-compose.env-dev.yml up -d
```

#### 2. Pokretanje API-ja

```powershell
cd app/servelogic-api
.\mvnw spring-boot:run
```

#### 3. Pokretanje CMS aplikacije

```powershell
cd app/cms
.\mvnw spring-boot:run
```

#### 4. Pokretanje Ordering aplikacije

```powershell
cd app/ordering
.\mvnw spring-boot:run
```

> **Napomena:** Pokrenite servise redom: baze podataka → API → frontend aplikacije. Prvi build može potrajati nekoliko minuta dok Vaadin kompajlira frontend komponente.

### Rešavanje problema

**Greška sa Java verzijom ("Unsupported class file major version 69"):**

Podesite JAVA_HOME na Java 23:
```powershell
$env:JAVA_HOME = "C:\Program Files\Java\jdk-23"
```

**Problemi sa build-om:**
```powershell
.\mvnw clean install
.\mvnw spring-boot:run
```

**Pregled logova:**
```bash
# Svi servisi
docker-compose logs -f

# Pojedinačni servis
docker-compose logs -f servelogic_api
docker-compose logs -f servelogic_cms
docker-compose logs -f servelogic_ordering
```

## Struktura projekta

```
sit-projekat-2025/
├── app/
│   ├── servelogic-api/          # Backend REST API
│   │   ├── src/main/java/       # Java izvorni kod
│   │   │   └── rs/ac/singidunum/servelogic/
│   │   │       ├── controller/  # REST endpointi
│   │   │       ├── model/       # Entity klase
│   │   │       ├── repository/  # Sloj za pristup podacima
│   │   │       ├── service/     # Poslovna logika
│   │   │       └── utility/     # Konfiguracija
│   │   └── Dockerfile
│   │
│   ├── cms/                     # CMS frontend aplikacija
│   │   ├── src/main/java/       # Vaadin prikazi i komponente
│   │   └── Dockerfile
│   │
│   ├── ordering/                # Ordering frontend aplikacija
│   │   ├── src/main/java/       # Vaadin prikazi i komponente
│   │   └── Dockerfile
│   │
│   ├── arangodb/                # Skripte za inicijalizaciju baze
│   └── docker-compose.env-dev.yml
│
├── documentation/               # Projektna dokumentacija
│   ├── astah/                   # UML dijagrami
│   ├── project-specification.md
│   └── style-guide.md
│
├── docker-compose.yml           # Produkcioni Docker setup
├── start-docker.cmd             # Windows Docker skripta
├── start-docker.sh              # Linux/Mac Docker skripta
└── start.cmd                    # Windows skripta za lokalni razvoj
```

## Pristup bazama podataka

### ArangoDB
- URL: http://localhost:8529
- Korisnik: `root`
- Lozinka: `root`
- Baza: `servelogic`

### Apache Fuseki
- URL: http://localhost:3030
- Korisnik: `admin`
- Lozinka: `admin`
- Dataset: `servelogic`

## Dokumentacija

Detaljna dokumentacija je dostupna u [documentation/](documentation/) folderu:
- [Specifikacija projekta](documentation/project-specification.md)
- [Stil kodiranja](documentation/style-guide.md)
- UML dijagrami (use case, sekvencni, klasni, deployment) u [documentation/astah/](documentation/astah/)

## Tim

| Ime i prezime | Broj indeksa |
|---------------|--------------|
| Aleksandar Čolović | 2023270030 |
| Gojko Dikić | 2023270048 |
| Boris Radosavljević | 2023270568 |
| Jovan Stoiljković | 2024271443 |
| Dušan Krstić | 2023270886 |
