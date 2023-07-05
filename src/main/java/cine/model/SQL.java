package cine.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import io.github.cdimascio.dotenv.Dotenv;


public class SQL {

    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url;
    private Encryptor encryptor = new Encryptor();
    private Peticion peticion = new Peticion();
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
            String query = "SELECT nombre FROM usuario";
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

    public String getValor(String tabla, String columna , String columCondicion,String valor) {
        String valorObtenido = null;
        try {
            Connection conn = this.conectarMySQL();
            String query = peticion.select(tabla, columna, columCondicion);
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, valor);
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) return null;
            valorObtenido = rs.getString(columna);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return valorObtenido;
    }

    public boolean pertenece(String tabla, String columna , String valor) {
        return this.getValor(tabla, columna, columna , valor) != null;
    }

    public boolean compararContrasenias(String contrasenia, String contraseniaEncryptada) {
        return encryptor.iguales(contrasenia, contraseniaEncryptada);
    }

    public boolean agregar(String tabla, List<String> columnas, List<String> valores, List<Integer> encryptar) {
        try{
            Connection conn = this.conectarMySQL();
            String query = this.peticion.insert(tabla, columnas);
            PreparedStatement stmt = conn.prepareStatement(query);
            for (int indice : encryptar) {
                valores.set(indice , this.encryptor.encryptar(valores.get(indice)));
            }
            for (int indice = 0; indice < valores.size(); indice++) {
                stmt.setString(indice+1 , valores.get(indice));
            }
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
            String query = "DELETE FROM usuario WHERE nombre = ?";
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

}