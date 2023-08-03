package cine.model;

import java.sql.Connection;
import java.sql.SQLException;

import cine.model.Errores.ErrorUsuario;

public class Verificador {
    private MySQL database;
    private Mensajeria mensajes = new Mensajeria();

    public Verificador(MySQL database) {
        this.database = database;
    }

    private void cerrarConeccion(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean nombreEnBlanco(String nombre) {
        return this.mensajes.setMensaje(nombre.isBlank(), ErrorUsuario.NOMBRE_CAMPO);
    }

    private boolean contraseniaEnBlanco(String contrasenia) {
        return this.mensajes.setMensaje(contrasenia.isBlank(), ErrorUsuario.CONTRASENIA_CAMPO);
    }

    private boolean confirmarContraseniaEnBlanco(String confirmarContrasenia) {
        return this.mensajes.setMensaje(confirmarContrasenia.isBlank(), ErrorUsuario.CONTRASENIA_CONFIRMAR_CAMPO);
    }

    private boolean DNIEnBlanco(String DNI) {
        return this.mensajes.setMensaje(DNI.isBlank(), ErrorUsuario.DNI_CAMPO);
    }

    private boolean emailEnBlanco(String email) {
        return this.mensajes.setMensaje(email.isBlank(), ErrorUsuario.EMAIL_CAMPO);
    }

    private boolean NoCoincidenContrasenias(String contrasenia, String confirmarContrasenia) {
        boolean resultado = !contrasenia.equals(confirmarContrasenia);
        return this.mensajes.setMensaje(resultado, ErrorUsuario.CONTRASENIAS_DISTINTAS);
    }

    private boolean estaEnDB(String nombre) {
        Connection conn = this.database.conectarMySQL();
        boolean resultado = this.database.pertenece(conn, "usuario", "nombre", "nombre", nombre);
        this.cerrarConeccion(conn);
        return this.mensajes.setMensaje(resultado, ErrorUsuario.USUARIO_REGISTRADO, ErrorUsuario.USUARIO_NO_REGISTRADO);
    }

    public boolean loginParteUnoError(String nombre, String contrasenia) {
        return  (this.nombreEnBlanco(nombre) || this.contraseniaEnBlanco(contrasenia) || !this.estaEnDB(nombre));
    }

    public boolean loginParteDosError(String contrasenia, String contraseniaObtenida) {
        boolean resultado = !this.database.compararContrasenias(contrasenia, contraseniaObtenida);
        this.mensajes.setMensaje(resultado, ErrorUsuario.CONTRASENIA_INCORRECTA);
        return resultado;
    }
    
    public boolean registerError(String nombre, String dni, String email, String contrasenia, String confirmarContrasenia) {
        if (this.nombreEnBlanco(nombre) || this.DNIEnBlanco(dni) || this.emailEnBlanco(email)) return true;
        if (this.contraseniaEnBlanco(contrasenia) || this.confirmarContraseniaEnBlanco(confirmarContrasenia)) return true;
        if (this.NoCoincidenContrasenias(contrasenia, confirmarContrasenia)) return true;
        if (this.estaEnDB(nombre)) return true;
        return false;
    }

    public String getMensajeError() {
        return this.mensajes.getMensaje();
    }
}
