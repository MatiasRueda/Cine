package cine;

import static org.junit.Assert.*;

import org.junit.Test;

import cine.model.Errores;
import cine.model.Errores.ErrorUsuario;

public class ErroresTest {
    private Errores errores = new Errores();

    @Test
    public void MensajeCorrectoNOMBRE_CAMPO() {
        String mensaje =  "El nombre esta en blanco";
        assertEquals(mensaje, errores.getMensaje(ErrorUsuario.NOMBRE_CAMPO));
    }

    @Test
    public void MensajeCorrectoDNI_CAMPO() {
        String mensaje =  "El DNI esta en blanco";
        assertEquals(mensaje, errores.getMensaje(ErrorUsuario.DNI_CAMPO));
    }

    @Test
    public void MensajeCorrectoCONTRASENIA_CAMPO() {
        String mensaje =  "La contrasenia esta en blanco";
        assertEquals(mensaje, errores.getMensaje(ErrorUsuario.CONTRASENIA_CAMPO));
    }

    @Test
    public void MensajeCorrectoUSUARIO_REGISTRADO() {
        String mensaje =  "El usuario ya esta registrado";
        assertEquals(mensaje, errores.getMensaje(ErrorUsuario.USUARIO_REGISTRADO));
    }

    @Test
    public void MensajeCorrectoUSUARIO_NO_REGISTRADO() {
        String mensaje =  "El usuario no esta registrado";
        assertEquals(mensaje, errores.getMensaje(ErrorUsuario.USUARIO_NO_REGISTRADO));
    }

    @Test
    public void MensajeCorrectoCONTRASENIA_COMPARACION() {
        String mensaje =  "La contrasenia es incorrecta";
        assertEquals(mensaje, errores.getMensaje(ErrorUsuario.CONTRASENIA_COMPARACION));
    }
}
