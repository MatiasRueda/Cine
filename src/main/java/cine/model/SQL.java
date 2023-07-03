package cine.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import io.github.cdimascio.dotenv.Dotenv;


public class SQL {

    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url;
    private Encryptor encryptor = new Encryptor();
    private Dotenv dotenv = Dotenv.load();

    public  SQL() {
        this.url = "jdbc:mysql://" + this.dotenv.get("DB_HOSTNAME") + ":" + this.dotenv.get("DB_PORT") + "/" + this.dotenv.get("DB") + "?useSSL=false";
    }

    public Connection conectarMySQL() {
        Connection conn = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, this.dotenv.get("DB_USERNAME"), this.dotenv.get("DB_CONTRASENIA"));
            System.out.println("Conectada a la base de datos");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public ArrayList<String> obtenerNombresUsuarios() {
        ArrayList<String> nombres = new ArrayList<String>();
        try {
            Connection conn = this.conectarMySQL();
            Statement stmt = conn.createStatement();
            String query = "SELECT nombre FROM usuarios";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                nombres.add(nombre);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return nombres;
    }

    public String obtenerContrasenia(String nombre, String contrasenia) {
        String contraseniaEncryptada = null;
        try {
            Connection conn = this.conectarMySQL();
            String query = "SELECT contrasenia FROM usuarios WHERE nombre = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) return null;
            contraseniaEncryptada = rs.getString("contrasenia");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return contraseniaEncryptada;
    }

    public boolean compararContrasenias(String contrasenia, String contraseniaEncryptada) {
        return encryptor.contraseniasIguales(contrasenia, contraseniaEncryptada);
    }

    public boolean estaAgregado(String nombre) {
        String nombreEncontrado = null;
        try{
            Connection conn = this.conectarMySQL();
            String query = "SELECT nombre FROM usuarios WHERE nombre = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) return false;
            nombreEncontrado = rs.getString("nombre");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return nombreEncontrado.equals(nombre);
    }

    public boolean agregarUsuario(String nombre, String contrasenia) {
        try{
            Connection conn = this.conectarMySQL();
            String query = "INSERT INTO usuarios (nombre, contrasenia) VALUES (?, ?)";
            String contraseniaEncryptada = this.encryptor.encrytarContrasenia(contrasenia);
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, nombre);
            stmt.setString(2, contraseniaEncryptada);
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean eliminarUsuario(String nombre) {
        try {
            Connection conn = this.conectarMySQL();
            String query = "DELETE FROM usuarios WHERE nombre = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, nombre);
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void imprimirTabla() {
        try {
            Connection conn = this.conectarMySQL();
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM usuarios";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getString(2));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

}