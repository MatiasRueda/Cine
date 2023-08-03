package cine;

import cine.model.Conversor;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ConversorTest {
    Conversor conversor = new Conversor();

    @Test
    public void devuelve0() {
        assertEquals(0, conversor.pasarLetraNumero("A".charAt(0)));
    }

    @Test
    public void devuelve1() {
        assertEquals(1, conversor.pasarLetraNumero("B".charAt(0)));
    }
    
    @Test
    public void devuelve2() {
        assertEquals(2, conversor.pasarLetraNumero("C".charAt(0)));
    }

    @Test
    public void devuelve3() {
        assertEquals(3, conversor.pasarLetraNumero("D".charAt(0)));
    }
    
    @Test
    public void devuelve4() {
        assertEquals(4, conversor.pasarLetraNumero("E".charAt(0)));
    }

     @Test
    public void devuelve5() {
        assertEquals(5, conversor.pasarLetraNumero("F".charAt(0)));
    }

    @Test
    public void devuelve6() {
        assertEquals(6, conversor.pasarLetraNumero("G".charAt(0)));
    }
        
    @Test
    public void devuelve7() {
        assertEquals(7, conversor.pasarLetraNumero("H".charAt(0)));
    }

    @Test
    public void devuelve8() {
        assertEquals(8, conversor.pasarLetraNumero("I".charAt(0)));
    }

    @Test
    public void devuelve9() {
        assertEquals(9, conversor.pasarLetraNumero("J".charAt(0)));
    }

    @Test
    public void devuelve10() {
        assertEquals(10, conversor.pasarLetraNumero("K".charAt(0)));
    }

    @Test
    public void devuelve11() {
        assertEquals(11, conversor.pasarLetraNumero("L".charAt(0)));
    }

    @Test
    public void devuelve12() {
        assertEquals(12, conversor.pasarLetraNumero("M".charAt(0)));
    }

    @Test
    public void devuelve13() {
        assertEquals(13, conversor.pasarLetraNumero("N".charAt(0)));
    }

    @Test
    public void devuelve14() {
        assertEquals(14, conversor.pasarLetraNumero("O".charAt(0)));
    }

    @Test
    public void devuelve15() {
        assertEquals(15, conversor.pasarLetraNumero("P".charAt(0)));
    }

     @Test
    public void devuelve16() {
        assertEquals(16, conversor.pasarLetraNumero("Q".charAt(0)));
    }

    @Test
    public void devuelveA() {
        char letra = "A".charAt(0);
        assertEquals(Character.valueOf(letra), conversor.pasarNumeroLetra(0));
    }
    @Test
    public void devuelveB() {
        char letra = "B".charAt(0);
        assertEquals(Character.valueOf(letra), conversor.pasarNumeroLetra(1));
    }

    @Test
    public void devuelveC() {
        char letra = "C".charAt(0);
        assertEquals(Character.valueOf(letra), conversor.pasarNumeroLetra(2));
    }

    @Test
    public void devuelveD() {
        char letra = "D".charAt(0);
        assertEquals(Character.valueOf(letra), conversor.pasarNumeroLetra(3));
    }

    @Test
    public void devuelveE() {
        char letra = "E".charAt(0);
        assertEquals(Character.valueOf(letra), conversor.pasarNumeroLetra(4));
    }

    @Test
    public void devuelveF() {
        char letra = "F".charAt(0);
        assertEquals(Character.valueOf(letra), conversor.pasarNumeroLetra(5));
    }

    @Test
    public void devuelveG() {
        char letra = "G".charAt(0);
        assertEquals(Character.valueOf(letra), conversor.pasarNumeroLetra(6));
    }

    @Test
    public void devuelveH() {
        char letra = "H".charAt(0);
        assertEquals(Character.valueOf(letra), conversor.pasarNumeroLetra(7));
    }

    @Test
    public void devuelveI() {
        char letra = "I".charAt(0);
        assertEquals(Character.valueOf(letra), conversor.pasarNumeroLetra(8));
    }

    @Test
    public void devuelveJ() {
        char letra = "J".charAt(0);
        assertEquals(Character.valueOf(letra), conversor.pasarNumeroLetra(9));
    }

    @Test
    public void devuelveK() {
        char letra = "K".charAt(0);
        assertEquals(Character.valueOf(letra), conversor.pasarNumeroLetra(10));
    }

    @Test
    public void devuelveL() {
        char letra = "L".charAt(0);
        assertEquals(Character.valueOf(letra), conversor.pasarNumeroLetra(11));
    }

    @Test
    public void devuelveM() {
        char letra = "M".charAt(0);
        assertEquals(Character.valueOf(letra), conversor.pasarNumeroLetra(12));
    }

}
