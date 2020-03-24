
package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {
    
    Kassapaate paate;
    Maksukortti kortti;
    
    @Before
    public void setUp(){
        paate = new Kassapaate();
    }
    
    @Test
    public void luotuKassapaateOk(){
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kateisostoKasvattaaKassaa(){
        paate.syoMaukkaasti(400);
        assertEquals(100400, paate.kassassaRahaa());
        paate.syoEdullisesti(300);
        assertEquals(100640, paate.kassassaRahaa());
    }
    
    @Test 
    public void kateisostoLisaaLounaita(){
        paate.syoMaukkaasti(500);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
        paate.syoEdullisesti(300);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void liianPieniKateinenPalauttaaVaihtorahan(){
        assertEquals(200, paate.syoMaukkaasti(200));
        assertEquals(200, paate.syoEdullisesti(200));
    }
    
    @Test
    public void liianPieniKateinenEiKasvataLounaita(){
        paate.syoMaukkaasti(10);
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
        paate.syoEdullisesti(5);
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void liianPieniKateinenEiKasvataKassaa(){
        paate.syoMaukkaasti(10);
        assertEquals(100000, paate.kassassaRahaa());
        paate.syoEdullisesti(10);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void korttiostoPalauttaaTrue(){
        kortti = new Maksukortti(850);
        assertTrue(paate.syoMaukkaasti(kortti));
        assertTrue(paate.syoEdullisesti(kortti));
    }

    @Test
    public void korttiostoLisaaLounaita(){
        kortti = new Maksukortti(850);
        paate.syoMaukkaasti(kortti);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());  
        paate.syoEdullisesti(kortti);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void korttiostoEiMeneLapiJosMaksuEiRiita(){
        kortti = new Maksukortti(200);
        assertFalse(paate.syoMaukkaasti(kortti));
        assertFalse(paate.syoEdullisesti(kortti));
    }
    
    @Test
    public void epaonnistunutKorttiostoEiVahennaKortinSaldoa(){
        kortti = new Maksukortti(200);
        paate.syoMaukkaasti(kortti);
        assertEquals(200, kortti.saldo());
        paate.syoEdullisesti(kortti);
        assertEquals(200, kortti.saldo());
    }
    
    @Test
    public void kortilleLatausToimii(){
        kortti = new Maksukortti(0);
        paate.lataaRahaaKortille(kortti, 10);
        assertEquals(10, kortti.saldo());
        assertEquals(100010, paate.kassassaRahaa());
    }
    
}
