#Vaatimusmäärittely

##Sovelluksen tarkoitus

Sovelluksen tarkoitus on pitää yllä korkeakouluopintojen tilannetta ja suunnitelmaa. Sovelluksen avulla voi tarkistaa, montako opintopistettä on kasassa, mitä kursseja on käytynä, montako opintopistettä täytyy vielä saada ennen valmistumista ja mitä kursseja on tarkoitus käydä.

##Käyttöliittymä

Sovellus koostuu neljästä näkymästä:

1. Kirjautuminen

2. Käyttäjän opintojen tilanne (avaussivu)

3. Kurssin lisääminen tai suunnitellun kurssin poistaminen

4. Merkitse kurssi suoritetuksi. 

##Toiminnallisuus

Käyttäjä voi luoda käyttäjätunnuksen.

    - uniikki tunnus ja sille salasana

Käyttäjä voi kirjautua sisään.

    - onnistuu jos ja vain jos käyttäjätunnus on olemassa ja syötetty salasana vastaa käyttäjäkohtaista salasanaa
    
    - sovellus ilmoittaa jos virheellinen tunnus tai väärä salasana

Käyttäjä voi tarkastella käymiään kursseja 

    - kurssit listataan heti kirjautumisen jälkeen näkymälle

Käyttäjä voi tarkastella, mitä kursseja on suunnitellut käytäväksi

    - suunnitellut kurssit listataan samalle sivulle jo käytyjen kurssien kanssa, mutta ne ovat eri listalla kuin käydyt

Käyttäjä voi lisätä uuden kurssin

    - kurssi voi olla jo suoritettu tai vasta suunnitteilla

    - kurssille voi lisätä ajankohdan, jolloin aikoo suorittaa (vuosi, syksy/kevät)

    - kurssille voi merkitä, kuuluuko kandi- vai maisteriopintoihin

Käyttäjä voi poistaa kurssin, jonka on aiemmin merkinnyt suunnitelmaansa

    - jo suoritettua kurssia ei voi poistaa

    - kurssilistauksessa voisi suunniteltujen kurssien kohdalla olla "merkitse suoritetuksi" ja "poista kurssi" -napit

Käyttäjä voi merkitä suunnitellun kurssin käydyksi

    - kurssilistauksella on suunniteltujen kurssien kohdalla "merkitse suoritetuksi"

Käyttäjä voi kirjautua ulos.