# ServeLogic - Sistem za restoranski menadžment

ServeLogic je web zasnovani sistem za upravljanje restoranskim jelovnicima, procesom poručivanja i pregledom statistike porudžbina. Projekat je razvijen u okviru predmeta *Metodologija razvoja softvera*.

## 🚀 Komponente sistema

Sistem se sastoji iz četiri glavne komponente:

1.  **CMS App** - Frontend administratorska Web aplikacija za upravljanje restoranskim menijem i praćenje statistike porudžbina.
2.  **Ordering App** - Frontend Web Aplikacija za pravljenje porudžbina od strane kupaca.
3.  **ServeLogic API** - Centralna Backend API aplikacija koja implementira poslovnu logiku i pravila validacije.
4.  **ServeLogic DB** - ArangoDB baza podataka za perzistenciju podataka.

## 🛠️ Tehnologije

- **Backend:** Java, Spring Boot
- **Frontend:** Java, Vaadin
- **Baza podataka:** ArangoDB
- **Kontejnerizacija:** Docker

## 📋 Funkcionalnosti

- **Upravljanje jelovnikom:** Kreiranje i izmena kategorija, artikala, modifikatora i "combo" ponuda.
- **Poručivanje:** Interfejs za kupce za pregled jelovnika i kreiranje porudžbina.
- **Statistika:** Pregled prometa, broja porudžbina i najprodavanijih artikala.
- **Autentifikacija:** Siguran pristup administratorskim funkcijama.

## 💻 Pokretanje projekta

### Opcija 1: Docker (Preporučeno)

Najbrži način da pokrenete ceo sistem je korišćenjem Docker-a. Potreban je samo Docker i Docker Compose.

#### Preduslovi

- Docker Desktop ili Docker Engine sa Docker Compose

#### Pokretanje jednom komandom

**Windows:**
```powershell
.\start-docker.cmd
```

**Linux/Mac:**
```bash
./start-docker.sh
```

Ili direktno sa Docker Compose:
```bash
docker-compose up --build
```

Skripta će automatski:
1. Izgraditi Docker Image za sve aplikacije
2. Pokrenuti ArangoDB bazu podataka (port 8529)
3. Pokrenuti ServeLogic API (port 7999)
4. Pokrenuti CMS aplikaciju (port 7998)
5. Pokrenuti Ordering aplikaciju (port 8080)

**Pristup aplikacijama:**
- **CMS Aplikacija:** http://localhost:7998
- **Ordering Aplikacija:** http://localhost:8080
- **API Endpointi:** http://localhost:7999/api
- **ArangoDB Web UI:** http://localhost:8529 (korisnik: `root`, lozinka: `root`)

**Zaustavljanje:**
Pritisnite `Ctrl+C` u terminalu gde je pokrenut Docker Compose, ili pokrenite:
```bash
docker-compose down
```

**Čišćenje (uklanja i podatke iz baze):**
```bash
docker-compose down -v
```

**Praćenje logova:**
```bash
# Svi servisi
docker-compose logs -f

# Pojedinačni servis
docker-compose logs -f servelogic_api
docker-compose logs -f servelogic_cms
docker-compose logs -f servelogic_ordering
```

### Opcija 2: Development (Lokalno)

Za lokalni development bez Docker-a.

#### Preduslovi

- Docker i Docker Compose (samo za bazu podataka)
- Java 21+ (za API)
- Java 17+ (za frontend aplikacije)
- Maven

#### 1. Pokretanje baze podataka

```powershell
cd app
docker-compose -f docker-compose.env-dev.yml up -d
```

#### 2. Pokretanje API-ja

```powershell
cd app/servelogic-api
./mvnw spring-boot:run
```

#### 3. Pokretanje CMS aplikacije

```powershell
cd app/cms
mvnw spring-boot:run
```

#### 4. Pokretanje aplikacije za poručivanje (Ordering App)

```powershell
cd app/ordering
mvnw spring-boot:run
```

## 👥 Tim

- **Aleksandar Čolović** - 2023270030
- **Gojko Dikić** - 2023270048
- **Boris Radosavljević** - 2023270568
- **Jovan Stoiljković** - 2024271443
- **Dušan Krstić** - 2023270886

---
*Projekat razvijen za Univerzitet Singidunum - 2025*
