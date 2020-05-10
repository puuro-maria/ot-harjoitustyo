![Alt_text](https://media.giphy.com/media/HPF6ivflFs7U4/giphy.gif)
# Opintojen suunnittelusovellus :green_book:

Sovelluksessa voi pitää kirjaa omien opintojen nykytilanteesta ja suunnitelmista. Sovellukseen lisätään jo käytyjä tai suunnitteilla olevia kursseja, jotka voidaan merkitä jo suoritetuksi tai ei vielä suoritetuksi. Sovellus näyttää, mitkä kurssit kuuluvat kandidaatin- ja maisterintutkintoon ja kuinka paljon opintopisteitä vielä puuttuu.

Sovellus on osa Helsingin Yliopiston Ohjelmistotekniikka-kurssin suoritusta.

## Viimeisin release :loudspeaker:

[Viikon 6 release](https://github.com/puuro-maria/ot-harjoitustyo/releases/tag/viikko6)

[Viikon 5 release](https://github.com/puuro-maria/ot-harjoitustyo/releases/tag/viikko5)

Sovellus (esim. viikon 6 versio) käynnistyy konsolissa komennolla:

```
java -jar viikko6.jar
```

## Komentorivitoiminnot :robot:

Lataa paketti omalle koneelle: 
```
git clone https://github.com/puuro-maria/ot-harjoitustyo

cd ot-harjoitustyo/OpintojenSeurantasovellus
```

**Suoritettavan jar-tiedoston luominen:**
```
mvn package
```
Tämä generoi tiedoston *OpintojenSuunnittelusovellus-1.0-SNAPSHOT.jar* kansioon target.

**Testien ajaminen:**
```
mvn test
```

**Jacoco-raportti eli testikattavuusraportti:**
```
mvn jacoco:report
```
Jacoco-raportti ilmestyy kansioon *target/site/jacoco* nimellä *index.html*.

**Checkstyle-raportin ajaminen:**
```
mvn jxr:jxr checkstyle:checkstyle
```
Raportti ilmestyy kansioon *target/site* ja tiedoston nimi on *checkstyle.html*

**JavaDoc:**
```
mvn javadoc:javadoc
```
Javadoc löytyy *target/site/apidocs/index.html* -tiedostosta.

## Dokumentaatio :page_with_curl:

[Vaatimusmäärittely](https://github.com/puuro-maria/ot-harjoitustyo/blob/master/dokumentointi/vaatimusmaarittely.md) :pencil:

[Arkkitehtuuri](https://github.com/puuro-maria/ot-harjoitustyo/blob/master/dokumentointi/arkkitehtuuri.md) :triangular_ruler:

[Työaikakirjanpito](https://github.com/puuro-maria/ot-harjoitustyo/blob/master/dokumentointi/tyoaikakirjanpito.md) :clock10:

[Käyttäjän ohjeet](https://github.com/puuro-maria/ot-harjoitustyo/blob/master/dokumentointi/kayttoohjeet.md) :woman:

[Ohjelman testaus](https://github.com/puuro-maria/ot-harjoitustyo/blob/master/dokumentointi/testaus.md) :mag:

## Muuta

- Ohjelma toimii Javan versiolla 11

## Kehitysideoita :bulb:

- Käyttöliittymässä olisi paranneltavaa.

- Dao-oliot voisi kytkeä tietokantaan sen sijaan, että ne kirjottaisivat tekstitiedostoihin. Tämä on toteutettavissa.

- Opinnoista voisi tarjota tarkempaa analyysia ja aikataulutusta, esim. *vuodelle 2021 suunnitellut opinnot yhteensä 65 op.*

