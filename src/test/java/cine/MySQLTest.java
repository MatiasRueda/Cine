package cine;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    List<String> columnas = Arrays.asList(new String[]{"nombre", "DNI" ,"contrasenia"});
    List<String> valores = Arrays.asList(new String[]{NOMBRE, DNI, CONTRASENIA});
    List<Integer> encryptar = Arrays.asList(new Integer[]{2});

    private void eliminarUsuario(String nombre) {
        try {
            Connection conn = this.database.conectarMySQL();
            String query = "DELETE FROM usuario WHERE nombre = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, nombre);
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String obtenerContrasenia(String nombre) {
        try{
            Connection conn = this.database.conectarMySQL();
            String query = "SELECT contrasenia FROM usuario WHERE nombre = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getString("contrasenia");
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
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
    public void obtenerElNombreDeLosUsuariosNoEsNullTest() {
        List<String> nombres = this.database.obtenerNombresUsuarios();
        assertNotNull(nombres);
    }

    @Test
    public void registrarTrueTest() {
        assertTrue(this.database.agregar("usuario", columnas, valores, encryptar));
    }

    @Test
    public void registrarAumentaEnUnoElLargoDeObtenerUsuariosTest() {
        List<String> nombresAntes = this.database.obtenerNombresUsuarios();
        this.database.agregar("usuario", columnas, valores, encryptar);
        List<String> nombresDespues = this.database.obtenerNombresUsuarios();
        assertEquals(nombresAntes.size() + 1,nombresDespues.size());
    }
 
    @Test 
    public void obtenerUsuariosTieneUsuarioRegistradoTest() {
        this.database.agregar("usuario", columnas, valores, encryptar);
        List<String> nombres = this.database.obtenerNombresUsuarios();
        assertTrue(nombres.contains(this.NOMBRE));
    }

    @Test
    public void eliminarUsuarioDevuelveTrueTest() {
        this.database.agregar("usuario", columnas, valores, encryptar);
        assertTrue(this.database.eliminarUsuario(this.NOMBRE));
    }
    
    @Test
    public void despuesDeEliminarElLargoDeObtenerUsuariosEsElMismoTest() {
        List<String> nombres = this.database.obtenerNombresUsuarios();
        int largoInicial = nombres.size();
        this.database.agregar("usuario", columnas, valores, encryptar);
        this.database.eliminarUsuario(this.NOMBRE);
        nombres = this.database.obtenerNombresUsuarios();
        assertEquals(largoInicial, nombres.size());
    }

    @Test
    public void seGuardaUnaPasswordDistintaTest() {
        this.database.agregar("usuario", columnas, valores, encryptar);
        String contraseniaGuardada = this.obtenerContrasenia(this.NOMBRE); 
        assertNotEquals(contraseniaGuardada, this.CONTRASENIA);
    }
}
