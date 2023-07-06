package cine;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cine.model.MySQL;

public class MySQLTest {
    
    private MySQL database;
    final private String NOMBRE = "Matias";
    final private String DNI = "12345678";
    final private String CONTRASENIA = "123";
    final private String TABLA_USUARIO = "usuario";
    final private List<String> COLUMNAS = Arrays.asList(new String[]{"nombre", "DNI" ,"contrasenia"});
    final private List<String> VALORES = Arrays.asList(new String[]{NOMBRE, DNI, CONTRASENIA});
    final private List<Integer> ENCRYPTAR = Arrays.asList(new Integer[]{2});

    private void eliminarUsuario(String nombre) {
        try {
            Connection conn = this.database.conectarMySQL();
            String query = "DELETE FROM " + this.TABLA_USUARIO + " WHERE nombre = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, nombre);
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void preparativos() {
        this.database = new MySQL();
    }

    @After
    public void limpiarTabla() {
        this.eliminarUsuario(this.NOMBRE);
    }

    @Test
    public void agregarTrueTest() {
        assertTrue(this.database.agregar(this.TABLA_USUARIO, this.COLUMNAS, this.VALORES, this.ENCRYPTAR));
    }

    @Test
    public void perteneceUnUsuarioNoAgregadoFalseTest() {
        assertFalse(this.database.pertenece(this.TABLA_USUARIO, "nombre", "nombre", this.NOMBRE));
    }

    @Test
    public void perteneceUnUsuarioSiAgregadoTrueTest() {
        this.database.agregar(this.TABLA_USUARIO, this.COLUMNAS, this.VALORES, this.ENCRYPTAR);
        assertTrue(this.database.pertenece(this.TABLA_USUARIO, "nombre", "nombre", this.NOMBRE));
    }

    @Test
    public void devuelveListaVaciaSiNoEncuentraNadaTest() {
        assertTrue(this.database.getValor(this.TABLA_USUARIO, "nombre", "nombre", this.NOMBRE).isEmpty());
    }

    @Test
    public void devuelveUnaListaNoVaciaSiEncuentraAlgoTest() {
        this.database.agregar(this.TABLA_USUARIO, this.COLUMNAS, this.VALORES, this.ENCRYPTAR);
        assertFalse(this.database.getValor(this.TABLA_USUARIO, "nombre", "nombre", this.NOMBRE).isEmpty());
    }

    @Test
    public void elValorEncontradoEsElPedidoTest() {
        this.database.agregar(this.TABLA_USUARIO, this.COLUMNAS, this.VALORES, this.ENCRYPTAR);
        String nombreEncontrado = this.database.getValor(this.TABLA_USUARIO, "nombre", "nombre", this.NOMBRE).iterator().next();
        assertEquals(this.NOMBRE, nombreEncontrado);
    }

    @Test
    public void compararMismasContraseniasTrueTest() {
        this.database.agregar(this.TABLA_USUARIO, this.COLUMNAS, this.VALORES, this.ENCRYPTAR);
        String contraseniaEncryptada = this.database.getValor(this.TABLA_USUARIO, "contrasenia", "nombre", this.NOMBRE).iterator().next();
        assertTrue(this.database.compararContrasenias(this.CONTRASENIA, contraseniaEncryptada));
    }

    @Test
    public void seGuardaUnaContraseniaDistintaTest() {
        this.database.agregar(this.TABLA_USUARIO, this.COLUMNAS, this.VALORES, this.ENCRYPTAR);
        String contraseniaGuardada = this.database.getValor(this.TABLA_USUARIO, "contrasenia", "nombre", this.NOMBRE).iterator().next();
        assertNotEquals(contraseniaGuardada, this.CONTRASENIA);
    }

    @Test
    public void eliminarDevuelveTrueTest() {
        this.database.agregar(this.TABLA_USUARIO, this.COLUMNAS, this.VALORES, this.ENCRYPTAR);
        assertTrue(this.database.eliminar("nombre", this.NOMBRE));
    }

    @Test
    public void seEliminaElUsuarioTest() {
        this.database.agregar(this.TABLA_USUARIO, this.COLUMNAS, this.VALORES, this.ENCRYPTAR);
        boolean estaVacia = this.database.getValor(this.TABLA_USUARIO, "nombre", "nombre", this.NOMBRE).isEmpty();
        this.database.eliminar("nombre", this.NOMBRE);
        boolean estaVaciaPostEliminar = this.database.getValor(this.TABLA_USUARIO, "nombre", "nombre", this.NOMBRE).isEmpty();
        assertNotEquals(estaVacia, estaVaciaPostEliminar);
    }
}
