package cine.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import io.github.cdimascio.dotenv.Dotenv;


public class MySQL {

    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url;
    private Encryptor encryptor = new Encryptor();
    private Peticion peticion = new Peticion();
    private Dotenv dotenv = Dotenv.load();

    public  MySQL() {
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
    public List<String> getValor(String tabla, String columna , String columCondicion, String valor) {
        ArrayList<String> valoresObtenidos = new ArrayList<>();
        try {
            Connection conn = this.conectarMySQL();
            String query = columCondicion.isEmpty()? peticion.select(tabla, columna) : peticion.select(tabla, columna, columCondicion);
            PreparedStatement stmt = conn.prepareStatement(query);
            if (!columCondicion.isEmpty()) stmt.setString(1, valor);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                valoresObtenidos.add(rs.getString(columna));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return valoresObtenidos;
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
                stmt.setString(indice + 1, valores.get(indice));
            }
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean eliminar(String columCondicion , String valor) {
        try {
            Connection conn = this.conectarMySQL();
            String query = peticion.delete("usuario", columCondicion);
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, valor);
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean pertenece(String tabla, String columna, String columCondicion, String valor) {
        return !this.getValor(tabla, columna, columCondicion , valor).isEmpty();
    }

    public boolean compararContrasenias(String contrasenia, String contraseniaEncryptada) {
        return encryptor.iguales(contrasenia, contraseniaEncryptada);
    }

}