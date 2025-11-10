# NBA-playerlist sovellus
Sovellukseen voi listata NBA koripallo pelaajia. Sovelluksessa onnistuu seuraavat asiat:

- Kirjautuminen: user ja admin roolit
- Uuden pelaajan lisääminen admin roolilla
- Muokkaaminen ja poistaminen admin roolilla
- User roolilla voi vain katsoa dataa
- Uloskirjautuminen
- Sorttaamaan tietyn kategorian mukaan pelaajat järjestykseen

## Tietokanta
- H2 (ajonaikainen)
- Postgre-Sql (ulkoinen)

## Pilvipalvelu
- Rahti

## Toiminta
Sovellus aukeaa login näkymästä, tähän on mahdollista syöttää käyttäjän Username ja password. Näillä kirjaudutaan sisään. Alta pystyy luomaan uuden User roolin käyttäjän.
Username ei voi olla sama kuin toisella käyttäjällä. Luodessa uutta käyttäjää pitää syöttää username, email ja password. Email on lisätty jatkokehitystä ajatellen. 

Kun kirjautuu sisään näkee listan pelaajia. User roolin käyttöoikeudet ovat vain näkeminen. User roolissa pystyy myös tekemään GET pyynnön. Admin roolilla on mahdollista luoda 
uusia pelaajia sekä poistaa ja muokata.

Sort by on lisätty eli pelaajat saa lajiteltua eri kategorian mukaisesti. Toimii molemmilla rooleilla.

Sovellus toimii pilvessä, alla olevasta linkistä sovellukseen.

### URL: [NBA-Playerlist](https://back-end-ohjelmointi-projekti-nba-playerlist-postgres.2.rahtiapp.fi/login)

<img width="1920" height="1200" alt="image" src="https://github.com/user-attachments/assets/5bd67d60-95e7-40e2-8219-e2e509e2a2e9" />

