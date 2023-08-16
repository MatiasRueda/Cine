package cine;

import static org.junit.Assert.*;

import org.junit.Test;

import cine.model.Errores;

public class ErroresTest {


    @Test
    public void MensajeCorrectoNOMBRE_CAMPO() {
        String mensaje =  "El nombre esta en blanco";
        assertEquals(mensaje, Errores.NOMBRE_CAMPO.mensaje);
    }

    @Test
    public void MensajeCorrectoDNI_CAMPO() {
        String mensaje =  "El DNI esta en blanco";
        assertEquals(mensaje, Errores.DNI_CAMPO.mensaje);
    }

    @Test
    public void MensajeCorrectoCONTRASENIA_CAMPO() {
        String mensaje =  "La contrasenia esta en blanco";
        assertEquals(mensaje, Errores.CONTRASENIA_CAMPO.mensaje);
    }

    @Test
    public void MensajeCorrectoUSUARIO_REGISTRADO() {
        String mensaje =  "El usuario ya esta registrado";
        assertEquals(mensaje, Errores.USUARIO_REGISTRADO.mensaje);
    }

    @Test
    public void MensajeCorrectoUSUARIO_NO_REGISTRADO() {
        String mensaje =  "El usuario no esta registrado";
        assertEquals(mensaje, Errores.USUARIO_NO_REGISTRADO.mensaje);
    }

    @Test
    public void MensajeCorrectoCONTRASENIA_COMPARACION() {
        String mensaje =  "Contrasenia incorrecta";
        assertEquals(mensaje, Errores.CONTRASENIA_INCORRECTA.mensaje);
    }
}
