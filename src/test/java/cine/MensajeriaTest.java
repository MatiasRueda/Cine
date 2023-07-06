package cine;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cine.model.Mensajeria;
import cine.model.Errores.ErrorUsuario;


public class MensajeriaTest {
    private Mensajeria mensajeria;
    
    @Before
    public void preparativos() {
        this.mensajeria = new Mensajeria();
    }

    @Test
    public void devuelveElMismoResultadoTrue() {
        boolean resultado = true;
        assertTrue(this.mensajeria.setMensaje(resultado, ErrorUsuario.NOMBRE_CAMPO));
    }

    @Test
    public void devuelveElMismoResultadoFalse() {
        boolean resultado = false;
        assertFalse(this.mensajeria.setMensaje(resultado, ErrorUsuario.NOMBRE_CAMPO));
    }

    @Test
    public void DosTipoErrorDevuelveElMismoResultadoTrue() {
        boolean resultado = true;
        assertTrue(this.mensajeria.setMensaje(resultado, ErrorUsuario.NOMBRE_CAMPO, ErrorUsuario.CONTRASENIA_CAMPO));
    }

    @Test
    public void DosTipoErrorDevuelveElMismoResultadoFalse() {
        boolean resultado = false;
        assertFalse(this.mensajeria.setMensaje(resultado, ErrorUsuario.NOMBRE_CAMPO, ErrorUsuario.CONTRASENIA_CAMPO));
    }


}
