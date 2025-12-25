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

## 💻 Pokretanje projekta (Development)

### Preduslovi

- Docker i Docker Compose
- Java 21+
- Maven

### 1. Pokretanje baze podataka

Baza podataka se pokreće pomoću Docker-a:

```powershell
cd app
docker-compose -f docker-compose.env-dev.yml up -d
```

### 2. Pokretanje API-ja

```powershell
cd app/servelogic-api
./mvnw spring-boot:run
```

### 3. Pokretanje aplikacije za poručivanje (Ordering App)

```powershell
cd app/ordering
mvn vaadin:run
```

## 👥 Tim

- **Aleksandar Čolović** - 2023270030
- **Gojko Dikić** - 2023270048
- **Boris Radosavljević** - 2023270568
- **Jovan Stoiljković** - 2024271443
- **Dušan Krstić** - 2023270886

---
*Projekat razvijen za Univerzitet Singidunum - 2025*
