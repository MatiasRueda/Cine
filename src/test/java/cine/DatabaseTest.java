package cine;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cine.model.SQL;

public class DatabaseTest {
    
    private SQL database;
    private String nombre = "Matias";
    private String contrasenia = "123";

    private void eliminarUsuario(String nombre) {
        try {
            Connection conn = this.database.conectarMySQL();
            String query = "DELETE FROM usuarios WHERE nombre = ?";
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
            String query = "SELECT contrasenia FROM usuarios WHERE nombre = ?";
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
        this.database = new SQL();
    }

    @After
    public void limpiarTabla() {
        this.eliminarUsuario(nombre);
    }

    @Test
    public void obtenerElNombreDeLosUsuariosNoEsNullTest() {
        List<String> nombres = this.database.obtenerNombresUsuarios();
        assertNotNull(nombres);
    }

    @Test
    public void registrarTrueTest() {
        assertTrue(this.database.agregarUsuario(this.nombre, this.contrasenia));
    }

    @Test
    public void registrarAumentaEnUnoElLargoDeObtenerUsuariosTest() {
        List<String> nombresAntes = this.database.obtenerNombresUsuarios();
        this.database.agregarUsuario(this.nombre, this.contrasenia);
        List<String> nombresDespues = this.database.obtenerNombresUsuarios();
        assertEquals(nombresAntes.size() + 1,nombresDespues.size());
    }
 
    @Test 
    public void obtenerUsuariosTieneUsuarioRegistradoTest() {
        this.database.agregarUsuario(this.nombre, this.contrasenia);
        List<String> nombres = this.database.obtenerNombresUsuarios();
        assertTrue(nombres.contains(this.nombre));
    }

    @Test
    public void eliminarUsuarioDevuelveTrueTest() {
        this.database.agregarUsuario(this.nombre, this.contrasenia);
        assertTrue(this.database.eliminarUsuario(this.nombre));
    }
    
    @Test
    public void despuesDeEliminarElLargoDeObtenerUsuariosEsElMismoTest() {
        List<String> nombres = this.database.obtenerNombresUsuarios();
        int largoInicial = nombres.size();
        this.database.agregarUsuario(this.nombre, this.contrasenia);
        this.database.eliminarUsuario(this.nombre);
        nombres = this.database.obtenerNombresUsuarios();
        assertEquals(largoInicial, nombres.size());
    }

    @Test
    public void seGuardaUnaPasswordDistintaTest() {
        this.database.agregarUsuario(this.nombre, this.contrasenia);
        String contraseniaGuardada = this.obtenerContrasenia(this.nombre); 
        assertNotEquals(contraseniaGuardada, this.contrasenia);
    }
}
