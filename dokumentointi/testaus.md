![alt_text](https://media.giphy.com/media/26zzbft8IcT78Vwsw/giphy.gif)

# Ohjelman testaus

Ohjelmaan on kirjoitettu valmiiksi JUnit-testit, jotka sijaitsevat omassa testipaketissaan. Jokaiselle luokalle - käyttöliittymäluokkaa lukuun ottamatta - on oma testiluokka.

## Testikattavuusraportti

![alt_text](https://github.com/puuro-maria/ot-harjoitustyo/blob/master/dokumentointi/kuvat/testikattavuus.png)

## Ohjelman testaus

Ohjelmaa testattiin myös kokeilemalla eri toimintoja. 

  - Kirjautuminen tunnuksella, jota ei ole:
    
      - Tulostuu virheviesti "*Virheellinen käyttäjätunnus tai salasana!*"
      
  - Kirjautuminen väärällä salasanalla:
  
      - Tulostuu virheviesti "*Virheellinen käyttäjätunnus tai salasana!*"
      
  - Koitetaan rekisteröidä käyttäjä jo olemassaolevalla käyttäjätunnuksella:
  
      - Tulostuu virheviesti "*Opiskelijanumerolla on jo tunnus!*"
      
  - Koitetaan rekisteröitäessä syöttää eriävät salasanat *salasana* ja *toista salasana* -kenttiin:
  
      - Tulostuu virheviesti "*Syöttämäsi salasanat eivät täsmänneet!*"
      
  - Koitetaan lisätä kurssi jättäen tekstikentät tyhjiksi:
  
      - Virheviestiä ei tulostu, mutta myöskään tyhjää kurssia ei luoda
