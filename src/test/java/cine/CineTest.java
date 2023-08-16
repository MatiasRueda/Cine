package cine;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import cine.model.Cine;
import cine.model.Errores;
import cine.model.MySQL;
import cine.model.Usuario;

public class CineTest {

    private Cine cine;
    private MySQL database = new MySQL();
    final private String NOMBRE = "ASFAFAMFAOEF";
    final private String CONTRASENIA = "123";
    final private String DNI = "12345678";
    final private String EMAIL = "AFYIAEBFIAEF@gmail.com";
    List<String> columnas = Arrays.asList(new String[]{"nombre","contrasenia", "dni", "email"});
    List<String> valores = Arrays.asList(new String[]{NOMBRE, CONTRASENIA, DNI, EMAIL});
    List<Integer> encryptar = Arrays.asList(new Integer[]{1});

    @Before
    public void preparativos() {
        this.cine = new Cine(new Usuario());
    }

    @After
    public void limpiarUsuarios() {
        try {
            Connection conn = this.database.conectarMySQL();
            String query = "DELETE FROM usuario WHERE nombre = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, this.NOMBRE);
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // ************* REGISTER TEST ****************** //

    @Test
    public void noCompletarNombreEsFalseRegisterTest() {
        assertFalse(this.cine.register("", this.DNI , this.EMAIL, this.CONTRASENIA, this.CONTRASENIA));
    }

    @Test
    public void noCompletarNombreDejaMensajeRegisterTest() {
        this.cine.register("", this.DNI,  this.EMAIL, this.CONTRASENIA,  this.CONTRASENIA);
        assertEquals(Errores.NOMBRE_CAMPO.mensaje, this.cine.getMensaje());
    }

    @Test
    public void noCompletarDNIEsFalseRegisterTest() {
        assertFalse(this.cine.register(this.NOMBRE, "" , this.EMAIL, this.CONTRASENIA,  this.CONTRASENIA));
    }

    @Test
    public void noCompletarDNIDejaMensajeRegisterTest() {
        this.cine.register(this.NOMBRE, "", this.EMAIL, this.CONTRASENIA,  this.CONTRASENIA);
        assertEquals(Errores.DNI_CAMPO.mensaje, this.cine.getMensaje());
    }

    @Test
    public void noCompletarContraseniaEsFalseRegisterTest() {
        assertFalse(this.cine.register(this.NOMBRE, this.DNI , this.EMAIL, "",  this.CONTRASENIA));
    }

    @Test
    public void noCompletarContraseniaDejaMensajeRegisterTest() {
        this.cine.register(this.NOMBRE, this.DNI, this.EMAIL, "",  this.CONTRASENIA);
        assertEquals(Errores.CONTRASENIA_CAMPO.mensaje, this.cine.getMensaje());
    }

    @Test
    public void noCompletarConfirmarContraseniaDejaMensajeRegisterTest() {
        this.cine.register(this.NOMBRE, this.DNI, this.EMAIL, this.CONTRASENIA,  "");
        assertEquals(Errores.CONTRASENIA_CONFIRMAR_CAMPO.mensaje, this.cine.getMensaje());
    }

    @Test
    public void lasContraseniasNoCoincidenDejaMensajeRegisterTest() {
        this.cine.register(this.NOMBRE, this.DNI, this.EMAIL, this.CONTRASENIA,  this.CONTRASENIA + "agaig");
        assertEquals(Errores.CONTRASENIAS_DISTINTAS.mensaje, this.cine.getMensaje());
    }

    @Test
    public void registrarseCorrectamenteEsTrueTest() {
        assertTrue(this.cine.register(this.NOMBRE, this.DNI, this.EMAIL, this.CONTRASENIA,  this.CONTRASENIA));
    }

    // ************* LOGIN TEST ****************** //

    @Test
    public void noCompletarNombreEsFalseLoginTest() {
        assertFalse(this.cine.login("", this.CONTRASENIA));
    }

    @Test
    public void noCompletarNombreDejaMensajeTest() {
        this.cine.login("", this.CONTRASENIA);
        assertEquals(Errores.NOMBRE_CAMPO.mensaje, this.cine.getMensaje());
    }

    @Test
    public void noCompletarContraseniaEsFalseLoginTest() {
        assertFalse(this.cine.login(this.NOMBRE, ""));
    }

    @Test
    public void noCompletarContraseniaDejaMensajeTest() {
        this.cine.login(this.NOMBRE, "");
        assertEquals(Errores.CONTRASENIA_CAMPO.mensaje, this.cine.getMensaje());
    }

    @Test
    public void noCompletarNombreContraseniaEsFalseLoginTest() {
        assertFalse(this.cine.login("", ""));
    }

    @Test
    public void noCompletarNombreContraseniaDejaMensajeTest() {
        this.cine.login("", "");
        assertEquals(Errores.NOMBRE_CAMPO.mensaje, this.cine.getMensaje());
    }

    @Test
    public void loguearseSinRegistrarseEsFalseTest() {
        assertFalse(this.cine.login(this.NOMBRE, this.CONTRASENIA));
    }

    @Test
    public void contraseniaIncorrectaDejaMensajeRegisterTest() {
        this.cine.register(this.NOMBRE, this.DNI, this.EMAIL, this.CONTRASENIA + "agaig",  this.CONTRASENIA + "agaig");
        this.cine.login(this.NOMBRE, this.CONTRASENIA);
        assertEquals(Errores.CONTRASENIA_INCORRECTA.mensaje, this.cine.getMensaje());
    }    

    @Test
    public void sePuedeLoguearDespuesDeRegistraseDaTrueTest() {
        this.cine.register(this.NOMBRE, this.DNI, this.EMAIL, this.CONTRASENIA,  this.CONTRASENIA);
        assertTrue(this.cine.login(this.NOMBRE, this.CONTRASENIA));
    }
}
