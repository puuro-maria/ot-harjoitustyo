# Ohjelman arkkitehtuuri

## Pakkauskaavio

Ohjelmassa on kolmitasoinen kerrosarkkitehtuuri.

![alt text](https://github.com/puuro-maria/ot-harjoitustyo/blob/master/dokumentointi/OSS_Pakkauskaavio.PNG "Pakkauskaavio")

UI-pakkauksessa on JavaFX:llä tehty graafinen käyttöliittymä, DAO-pakkauksessa ohjelman Data Access Object -luokat, jotka lukevat ja kirjoittavat tiedostoja.

## Käyttöliittymä

Käyttöliittymässä on kolme erilaista näkymää: aloitusnäkymä, rekisteröityminen ja opiskelijan näkymä. Opiskelijan näkymää pystyy muuttamaan niin, että listalla näkyy joko kaikki kurssit, suorittamattomat kurssit tai suoritetut kurssit. 

Näkymät on luotu JavaFX:n Scene-olioina. Käyttöliittymä on eriytetty sovelluslogiikasta.

## Sovelluslogiikka

Sovelluslogiikka on kirjoitettu logic-pakkauksen Logic-luokkaan. Sovelluslogiikka-olio ottaa konstruktorin parametreikseen dao-pakkauksen Dao-oliot, jotka kirjoittavat ja lukevat tiedostoja. Sovelluslogiikan tietomallit koostuvat Student- ja Course-olioista. 

## Tiedostot

Sovellus tarvitsee tiedostot courses.txt ja students.txt, nämä vaatimukset löytyvät [config.properties](https://github.com/puuro-maria/ot-harjoitustyo/blob/master/OpintojenSeurantasovellus/config.properties) -tiedostosta. 

Tiedostoihin attribuutit on kirjoitettu erottaen ne toisistaan puolipisteellä. Esimerkiksi courses.txt:n sisältö noudattaa seuraavaa mallia:

```
111;12;Kurssin nimi;5;opettaja;BACHELOR;false
```

