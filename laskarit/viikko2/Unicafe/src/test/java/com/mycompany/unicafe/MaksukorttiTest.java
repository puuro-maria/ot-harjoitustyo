package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoAlussaOikein(){
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void lataaminenKasvattaaSaldoa(){
        kortti.lataaRahaa(1000);
        assertEquals("saldo: 10.10", kortti.toString());
    }
    
    /*@Test
    public void saldoVahenee(){
        kortti.otaRahaa(5);
        assertEquals("saldo: 0.05", kortti.toString());
    }*/
    
    @Test
    public void saldoEiVaheneJosOttaaLiikaa(){
        kortti.otaRahaa(200);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void otaRahaaPalauttaaOikein(){
        assertTrue(kortti.otaRahaa(5));
        assertFalse(kortti.otaRahaa(200));
    }
}
