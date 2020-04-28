# Opintojen suunnittelusovellus

Sovelluksessa voi pitää kirjaa omien opintojen nykytilanteesta ja suunnitelmista. Sovellukseen lisätään jo käytyjä tai suunnitteilla olevia kursseja, jotka voidaan merkitä jo suoritetuksi tai ei vielä suoritetuksi. Sovellus näyttää, mitkä kurssit kuuluvat kandidaatin- ja maisterintutkintoon ja kuinka paljon opintopisteitä vielä puuttuu.

Sovellus on osa Helsingin Yliopiston Ohjelmistotekniikka-kurssin suoritusta.

## Viimeisin release

[Viikon 5 release](https://github.com/puuro-maria/ot-harjoitustyo/releases/tag/viikko5)

Sovellus käynnistyy konsolissa komennolla:

```
java -jar opSeurantaSovellus.jar
```

## Komentorivitoiminnot

Lataa paketti: 
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

**JavaDoc:**
```
mvn javadoc:javadoc
```

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/puuro-maria/ot-harjoitustyo/blob/master/dokumentointi/vaatimusmaarittely.md)

[Arkkitehtuuri](https://github.com/puuro-maria/ot-harjoitustyo/blob/master/dokumentointi/arkkitehtuuri.md)

[Työaikakirjanpito](https://github.com/puuro-maria/ot-harjoitustyo/blob/master/dokumentointi/tyoaikakirjanpito.md)

## Muuta

- Java versio 11

**Huom. tällä hetkellä DAO-pakkaus ei ole vielä käytössä sovelluksessa, sovellus ei vielä tallenna tietoja tiedostoon/tietokantaan.**

