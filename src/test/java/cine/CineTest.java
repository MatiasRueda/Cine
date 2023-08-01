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
import cine.model.Errores.ErrorUsuario;

public class CineTest {

    private Cine cine;
    private MySQL database = new MySQL();
    private Errores errores = new Errores();
    final private String NOMBRE = "Matias";
    final private String DNI = "12345678";
    final private String CONTRASENIA = "123";
    List<String> columnas = Arrays.asList(new String[]{"nombre", "DNI" ,"contrasenia"});
    List<String> valores = Arrays.asList(new String[]{NOMBRE, DNI, CONTRASENIA});
    List<Integer> encryptar = Arrays.asList(new Integer[]{2});

    
    private void reiniciarID() {
        try {
            Connection conn = this.database.conectarMySQL();
            String query = "ALTER TABLE usuario AUTO_INCREMENT = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, 0);
            stmt.executeUpdate(query);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void preparativos() {
        this.cine = new Cine();
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
        assertFalse(this.cine.register("", this.DNI , this.CONTRASENIA, this.CONTRASENIA));
    }

    @Test
    public void noCompletarNombreDejaMensajeRegisterTest() {
        this.cine.register("", this.DNI, this.CONTRASENIA,  this.CONTRASENIA);
        assertEquals(errores.getMensaje(ErrorUsuario.NOMBRE_CAMPO), this.cine.getMensaje());
    }

    @Test
    public void noCompletarDNIEsFalseRegisterTest() {
        assertFalse(this.cine.register(this.NOMBRE, "" , this.CONTRASENIA,  this.CONTRASENIA));
    }

    @Test
    public void noCompletarDNIDejaMensajeRegisterTest() {
        this.cine.register(this.NOMBRE, "", this.CONTRASENIA,  this.CONTRASENIA);
        assertEquals(errores.getMensaje(ErrorUsuario.DNI_CAMPO), this.cine.getMensaje());
    }

    @Test
    public void noCompletarContraseniaEsFalseRegisterTest() {
        assertFalse(this.cine.register(this.NOMBRE, this.DNI , "",  this.CONTRASENIA));
    }

    @Test
    public void noCompletarContraseniaDejaMensajeRegisterTest() {
        this.cine.register(this.NOMBRE, this.DNI, "",  this.CONTRASENIA);
        assertEquals(errores.getMensaje(ErrorUsuario.CONTRASENIA_CAMPO), this.cine.getMensaje());
    }

    @Test
    public void noCompletarConfirmarContraseniaDejaMensajeRegisterTest() {
        this.cine.register(this.NOMBRE, this.DNI, this.CONTRASENIA,  "");
        assertEquals(errores.getMensaje(ErrorUsuario.CONTRASENIA_CONFIRMAR_CAMPO), this.cine.getMensaje());
    }

    @Test
    public void lasContraseniasNoCoincidenDejaMensajeRegisterTest() {
        this.cine.register(this.NOMBRE, this.DNI, this.CONTRASENIA,  this.CONTRASENIA + "agaig");
        assertEquals(errores.getMensaje(ErrorUsuario.CONTRASENIAS_DISTINTAS), this.cine.getMensaje());
    }

    @Test
    public void registrarseCorrectamenteEsTrueTest() {
        assertTrue(this.cine.register(this.NOMBRE, this.DNI, this.CONTRASENIA,  this.CONTRASENIA));
    }

    // ************* LOGIN TEST ****************** //

    @Test
    public void noCompletarNombreEsFalseLoginTest() {
        assertFalse(this.cine.login("", this.CONTRASENIA));
    }

    @Test
    public void noCompletarNombreDejaMensajeTest() {
        this.cine.login("", this.CONTRASENIA);
        assertEquals(errores.getMensaje(ErrorUsuario.NOMBRE_CAMPO), this.cine.getMensaje());
    }

    @Test
    public void noCompletarContraseniaEsFalseLoginTest() {
        assertFalse(this.cine.login(this.NOMBRE, ""));
    }

    @Test
    public void noCompletarContraseniaDejaMensajeTest() {
        this.cine.login(this.NOMBRE, "");
        assertEquals(errores.getMensaje(ErrorUsuario.CONTRASENIA_CAMPO), this.cine.getMensaje());
    }

    @Test
    public void noCompletarNombreContraseniaEsFalseLoginTest() {
        assertFalse(this.cine.login("", ""));
    }

    @Test
    public void noCompletarNombreContraseniaDejaMensajeTest() {
        this.cine.login("", "");
        assertEquals(errores.getMensaje(ErrorUsuario.NOMBRE_CAMPO), this.cine.getMensaje());
    }

    @Test
    public void loguearseSinRegistrarseEsFalseTest() {
        assertFalse(this.cine.login(this.NOMBRE, this.CONTRASENIA));
    }

    @Test
    public void contraseniaIncorrectaDejaMensajeRegisterTest() {
        this.cine.register(this.NOMBRE, this.DNI, this.CONTRASENIA + "agaig",  this.CONTRASENIA + "agaig");
        this.cine.login(this.NOMBRE, this.CONTRASENIA);
        assertEquals(errores.getMensaje(ErrorUsuario.CONTRASENIA_INCORRECTA), this.cine.getMensaje());
    }    

    @Test
    public void sePuedeLoguearDespuesDeRegistraseDaTrueTest() {
        this.cine.register(this.NOMBRE, this.DNI, this.CONTRASENIA,  this.CONTRASENIA);
        assertTrue(this.cine.login(this.NOMBRE, this.CONTRASENIA));
    }

    // -------------------- REINICIAR ID --------------------------------- //
    @Test
    public void reiniciarValores() {
        this.reiniciarID();
        assertTrue(true);
    }

}