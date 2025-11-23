**SPECIFIKACIJA PROJEKTA**

**Naziv projekta:** ServeLogic -- Sistem za restoranski menadžment  
**Predmet:** Metodologija razvoja softvera  
**Tim:**

-   Aleksandar Čolović - 2023270030

-   Gojko Dikić -- 2023270048

-   Boris Radosavljević -- 2023270568

-   Jovan Stoiljković -- 2024271443

-   Dušan Krstić -- 2023270886

**Verzija dokumenta:** 1.0  
**Datum:** 07.11.2025

**1. Uvod**

**1.1. Svrha dokumenta**

Ovaj dokument definiše specifikaciju projekta **ServeLogic**, sistema za
upravljanje restoran­skim jelovnicima, procesom poručivanja i pregledom
statistike porudžbina. Dokument služi kao osnov za razvoj i evaluaciju
projekta u okviru predmeta _Metodologije razvoja softvera_.

**1.2. Obuhvat sistema**

ServeLogic je web zasnovani sistem koji se sastoji iz:

-   CMS App -- Frontend administratorske Web aplikacije za upravljanje  
    restoranskim menijem i praćenje statistike porudžbina

-   Ordering App -- Frontend Web Aplikacije za pravljenje porudžbina

-   ServeLogic API -- Centralne Backend API aplikacije

-   ServeLogic DB -- Baze podataka

**2. Opis problema i ciljevi**

**2.1. Problem**

Mali restorani često koriste nepovezane alate (papir, Excel, jednostavni
POS) za:

-   upravljanje jelovnikom

-   podešavanje modifikatora (npr. dodatni prilog, nivo pečenja, veličina
    porcije)

-   kreiranje „combo" ponuda

-   praćenje narudžbina i statistike prodaje

To dovodi do grešaka u cenama, zastarelih informacija, otežanog praćenja
prodaje i slabe analitike.

**2.2. Ciljevi**

Ciljevi ServeLogic sistema su:

-   omogućiti centralizovano upravljanje jelovnikom (kategorije, artikli,
    modifikatori, kombo ponude),

-   obezbediti aplikaciju za poručivanje koja koristi pouzdan i jasan API,

-   omogućiti pregled ključnih metrika (broj porudžbina, promet,
    najprodavaniji artikli)

**2.3. Obuhvaćeno projektom**

-   Jedan restoran (jedna instanca sistema)

-   Upravljanje kategorijama, artiklima, modifikatorima i "combo" ponudama

-   Kreiranje porudžbina preko posebne FE aplikacije

-   Osnovni statistički izveštaji zasnovani na porudžbinama

-   Autentifikacija i autorizacija osnovnih uloga

**2.4. Ne obuhvaćeno projektom**

-   Aplikacija za praćenje aktivnih porudžbina

-   Integracija sa pravim platnim sistemima

-   Integracija sa fiskalnim uređajima i štampačima računa

-   Više restorana / franšizna mreža

-   Napredna analitika

**3. Akteri i korisnici**

**3.1. Stejkholderi**

-   Predmetni nastavnici i asistenti (evaluacija metodologije i
    implementacije)

-   Članovi projektnog tima

-   Potencijalni korisnici (menadžment restorana) -- hipotetički

**3.2. Korisničke uloge**

-   **Administrator**

    -   Upravljanje kategorijama, artiklima, modifikatorima i „combo"
        ponudama

    -   Pregled statistike i izveštaja

    -   Upravljanje korisničkim nalozima

-   **Kupac**

    -   Kreiranje porudžbine preko aplikacije za poručivanje

-   **Sistem**

    -   API servis i baza podataka koji validiraju, obrađuju i čuvaju
        podatke

**4. Pregled sistema i arhitekture**

**4.1. Komponente sistema**

1.  **Administratorska web aplikacija -- CMS App**

    -   Omogućava konfiguraciju jelovnika

    -   Omogućava pregled porudžbina i statistike

    -   Komunicira sa centralnim API-jem

2.  **Aplikacija za poručivanje -- Ordering App**

    -   Prikazuje jelovnik kupcu

    -   Omogućava izbor artikala, modifikatora i "combo" ponuda

    -   Komunicira sa centralnim API-jem

3.  **Backend API servis -- ServeLogic API**

    -   Implementira pravila validacije, obračuna i bezbednosne mehanizme.

    -   Isporučuje REST endpoint-e

4.  **Baza podataka -- ServeLogic DB**

    -   Predlog: ArangoDB (graf model)

    -   Sadrži kolekcije za korisnike, artikle, kategorije, modifikatore,
        kombo ponude, porudžbine i relacije među njima

**4.2. Arhitektura (opis)**

Na konceptualnom nivou:

-   CMS App → ServeLogic API → ServeLogic DB

-   Ordering App → ServeLogic API → ServeLogic DB

Ovaj model će biti prikazan **UML dijagramom**.

**5. Funkcionalni zahtevi**

Svi zahtevi su formulisani tako da budu proverljivi.

**5.1. Upravljanje kategorijama i artiklima**

-   **FZ-1**: Sistem mora omogućiti admin korisniku kreiranje, izmenu i
    brisanje kategorija.

-   **FZ-2**: Sistem mora omogućiti admin korisniku kreiranje, izmenu i
    brisanje artikala.

-   **FZ-3**: Svaki artikal mora biti povezan sa tačno jednom kategorijom.

-   **FZ-4**: Sistem mora omogućiti označavanje artikla kao
    aktivnog/neaktivnog.

**5.2. Modifikatori i „Combo" ponude**

-   **FZ-5**: Sistem mora omogućiti definisanje modifikatora (npr.
    dodatak) sa efektom na cenu (pozitivan, negativan ili nula).

-   **FZ-6**: Sistem mora omogućiti vezu između artikla i skupa
    dozvoljenih modifikatora.

-   **FZ-7**: Sistem mora omogućiti definisanje „combo" ponuda kao grupe
    artikala sa ukupnom cenom.

-   **FZ-8**: Sistem mora omogućiti aktiviranje/deaktiviranje modifikatora
    i kombo ponuda.

**5.3. Poručivanje**

-   **FZ-9**: Aplikacija za poručivanje mora prikazati listu aktivnih
    kategorija, artikala, dozvoljenih modifikatora i kombo ponuda.

-   **FZ-10**: Korisnik mora moći da doda više artikala i kombo ponuda u
    jednu porudžbinu.

-   **FZ-11**: Backend mora validirati da su izabrani modifikatori
    dozvoljeni za dati artikal.

-   **FZ-12**: Backend mora izračunati međuzbir, porez i ukupan iznos na
    osnovu trenutnih cena.

-   **FZ-13**: Sistem mora sačuvati porudžbinu sa vremenom kreiranja,
    stavkama, modifikatorima, ukupnim iznosom i statusom (npr. "kreirana",
    "u pripremi", "završena").

**5.4. Statistika i izveštaji**

-   **FZ-14**: Sistem mora omogućiti pregled ukupnog broja porudžbina u
    izabranom vremenskom periodu.

-   **FZ-15**: Sistem mora omogućiti pregled ukupnog prometa u izabranom
    vremenskom periodu.

-   **FZ-16**: Sistem mora prikazati listu najprodavanijih artikala (Top
    N).

-   **FZ-17**: Sistem mora omogućiti pregled učestalosti korišćenja
    pojedinačnih modifikatora i „combo" ponuda.

**5.5. Korisnici i bezbednost**

-   **FZ-18**: Sistem mora podržati najmanje dve uloge: Administrator,
    Kupac.

-   **FZ-19**: Pristup CMS funkcijama mora biti ograničen na ulogu Admin.

-   **FZ-20**: Pristup statistici mora biti ograničen na Admin.

-   **FZ-21**: Registrovani korisnici moraju se prijaviti (login) da bi
    koristili zaštićene funkcionalnosti.

**6. Nefunkcionalni zahtevi**

-   **NFZ-1 (Performanse)**: Sistem treba da obradi standardnu porudžbinu
    (učitavanje jelovnika + slanje porudžbine) u vremenu kraćem od 2
    sekunde u povoljnim uslovima.

-   **NFZ-2 (Pouzdanost)**: Potvrđene porudžbine ne smeju biti izgubljene
    nakon restarta servera.

-   **NFZ-3 (Bezbednost)**:

    -   Lozinke moraju biti sačuvane u heširanom obliku.

    -   Administratorske funkcionalnosti moraju biti zaštićene
        autentifikacijom i autorizacijom.

-   **NFZ-4 (Održavanje)**: Kod mora biti podeljen po slojevima (frontend,
    API, pristup bazi) i dokumentovan, tako da novi član tima može da se
    uključi u roku od nekoliko dana.

-   **NFZ-5 (Prenosivost)**: Sistem treba da se pokrene na standardnim
    studentskim računarima

-   **NFZ-6 (Testabilnost)**: Ključne funkcionalnosti (kreiranje artikla,
    kreiranje porudžbine, generisanje osnovne statistike) moraju imati
    automatske testove.

**7. Use case tokovi**

U ovom poglavlju su opisani ključni slučajevi korišćenja. Na osnovu
ovoga biće izrađen UML dijagram slučajeva korišćenja.

**7.1. UC-01 -- Kreiranje porudžbine (Kupac)**

**Akter:** Kupac  
**Preduslovi:** Postoji važeći jelovnik; sistem je dostupan.  
**Glavni tok:**

1.  Kupac otvara aplikaciju za poručivanje.

2.  Sistem prikazuje kategorije i artikle.

3.  Kupac bira artikle i modifikatore.

4.  Kupac pregleda korpu i potvrđuje porudžbinu.

5.  Sistem šalje podatke API-ju.

6.  API validira artikle i modifikatore, računa iznose i snima
    porudžbinu.

7.  Sistem prikazuje potvrdu porudžbine.

**Krajnji Rezultat:** Porudžbina je trajno sačuvana; dostupna je za
pregled osoblju i u statistici.

**7.2. UC-02 -- Upravljanje artiklom (Administrator)**

**Akter:** Administrator  
**Preduslovi:** Postoji važeći jelovnik; sistem je dostupan.  
**Glavni tok:**

1.  Administrator se prijavljuje u CMS.

2.  Odabira opciju "Artikli".

3.  Kreira novi artikal ili menja postojeći (naziv, opis, cena,
    kategorija).

4.  Sistem snima izmene.

**Krajnji Rezultat:** Ažurirani podaci vidljivi su u aplikaciji za
poručivanje.

**7.3. UC-03 -- Konfigurisanje modifikatora (Administrator)**

**Akter:** **Administrator**  
**Preduslovi:** Postoji važeći jelovnik; sistem je dostupan.  
**Glavni tok:**

1.  Administrator u CMS-u bira sekciju "Modifikatori".

2.  Definiše novi modifikator (npr. "extra sir", +50 RSD).

3.  Povezuje modifikator sa jednim ili više artikala.

4.  Sistem snima vezu.

**Krajnji Rezultat:** Ažurirani podaci vidljivi su u aplikaciji za
poručivanje.

**7.4. UC-04 -- Pregled dnevne statistike (Administrator)**

**Akter:** Administrator  
**Glavni tok:**

1.  Administrator otvara modul "Statistika".

2.  Bira vremenski period (npr. današnji dan).

3.  Sistem prikazuje:

    -   broj porudžbina,

    -   ukupan promet,

    -   top N artikala,

    -   pregled modifikatora.

**8. Model podataka (visok nivo)**

Sledeće strukture predstavljaju logički model; precizna šema biće
definisana tokom izrade projekta

Primeri entiteta:

-   **Category**(id, name, description, isActive)

-   **Item**(id, categoryId, name, description, basePrice, isActive)

-   **Modifier**(id, name, description, priceDelta, isActive, type)

-   **ItemModifier**(itemId, modifierId)

-   **Combo**(id, name, description, totalPrice, isActive)

-   **ComboItem**(comboId, itemId)

-   **Order**(id, createdAt, createdByUserId/null, total, tax, status)

-   **OrderItem**(id, orderId, itemId, quantity, \[selectedModifiers\])

U ArangoDB implementaciji koristiće se kolekcije (document) i edge
kolekcije za povezivanje (npr. Item-Category, Item-Modifier, Combo-Item,
Order-OrderItem).

**9. Organizacija projekta i metodologija rada**

**9.1. Veličina i struktura tima**

Tim broji 5 članova. Predlaže se podela:

-   **R1 -- Vođa tima / Arhitekta**

    -   Koordinacija, tehničke odluke, komunikacija sa asistentima.

-   **R2 -- Backend developer**

    -   API, validacija, integracija sa bazom.

-   **R3 -- Frontend CMS developer**

    -   Administratorski interfejs.

-   **R4 -- Frontend Ordering developer**

    -   Aplikacija za poručivanje.

-   **R5 -- QA & Dokumentacija**

    -   Testiranje, automatizovani testovi, održavanje dokumentacije.

U praksi, uloge se mogu preplitati.

**9.2. Metodološki pristup**

Koristi se prilagođeni **agilni pristup**:

-   Rad u iteracijama (npr. 2--3 sprinta od po 2--3 nedelje)

-   Backlog definisan iz funkcionalnih zahteva

-   Na kraju svake iteracije:

    -   demonstracija funkcionalnosti

    -   revizija dokumentacije

    -   kratka retrospektiva

**9.3. Alati**

-   Kontrola verzija: GitHub

-   Upravljanje zadacima: Jira

-   Dokumentacija: ovaj dokument + dodatna tehnička dokumentacija

-   Testiranje: osnovni end-to-end scenariji

**9.4. Definition of Done**

Funkcionalnost se smatra završenom ako:

1.  Je implementirana prema specifikaciji

2.  Ima pokrivene osnovne testove

3.  Kod je pregledan (code review)

**10. Pretpostavke, ograničenja i rizici**

**10.1. Pretpostavke**

-   Sistem se koristi u okviru fakultetskog okruženja.

-   Postoji samo jedan restoran (nema potrebe za multi-facility rešenjem).

-   Korisnici imaju osnovno tehničko znanje za rad sa web aplikacijama.

**10.2. Ograničenja**

-   Vreme izrade ograničeno je trajanjem predmeta.

-   Infrastruktura je ograničena na raspoložive studentske/server resurse.

-   Tehnologije se biraju tako da su prikladne za program predmeta
    (Python + ArangoDB itd.).

**10.3. Rizici**

-   **R1 -- Prevelik obim funkcionalnosti**

    -   Mitigacija: prioritet imaju osnovni use case-ovi (jelovnik,
        porudžbina, osnovna statistika).

-   **R2 -- Neiskustvo sa izabranim tehnologijama**

    -   Mitigacija: jednostavnija arhitektura, podela znanja u timu.

-   **R3 -- Nedostatak vremena**

    -   Mitigacija: jasna podela zadataka, minimalan skup obaveznih
        funkcionalnosti.
