package cine.model;

import java.sql.Connection;

import cine.model.Errores.ErrorUsuario;

public class Verificador {
    private MySQL database;
    private Mensajeria mensajes = new Mensajeria();

    public Verificador(MySQL database) {
        this.database = database;
    }

    private boolean campoEnBlanco(String campo, ErrorUsuario error) {
        return this.mensajes.setMensaje(campo.isBlank(), error);
    }

    private boolean NoCoincidenContrasenias(String contrasenia, String confirmarContrasenia) {
        boolean resultado = !contrasenia.equals(confirmarContrasenia);
        return this.mensajes.setMensaje(resultado, ErrorUsuario.CONTRASENIAS_DISTINTAS);
    }

    private boolean estaEnDB(Connection conn, String nombre) {
        boolean resultado = this.database.pertenece(conn, "usuario", "nombre", "nombre", nombre);
        return this.mensajes.setMensaje(resultado, ErrorUsuario.USUARIO_REGISTRADO, ErrorUsuario.USUARIO_NO_REGISTRADO);
    }

    public boolean loginParteUnoError(Connection conn, String nombre, String contrasenia) {
        return (this.campoEnBlanco(nombre, ErrorUsuario.NOMBRE_CAMPO) || this.campoEnBlanco(contrasenia, ErrorUsuario.CONTRASENIA_CAMPO) || !this.estaEnDB(conn, nombre));
    }

    public boolean loginParteDosError(String contrasenia, String contraseniaObtenida) {
        boolean resultado = !this.database.compararContrasenias(contrasenia, contraseniaObtenida);
        this.mensajes.setMensaje(resultado, ErrorUsuario.CONTRASENIA_INCORRECTA);
        return resultado;
    }
    
    public boolean registerError(Connection conn, String nombre, String dni, String email, String contrasenia, String confirmarContrasenia) {
        if (this.campoEnBlanco(nombre, ErrorUsuario.NOMBRE_CAMPO) || this.campoEnBlanco(dni, ErrorUsuario.DNI_CAMPO) || this.campoEnBlanco(email, ErrorUsuario.EMAIL_CAMPO)) return true;
        if (this.campoEnBlanco(contrasenia, ErrorUsuario.CONTRASENIA_CAMPO) || this.campoEnBlanco(confirmarContrasenia,  ErrorUsuario.CONTRASENIA_CONFIRMAR_CAMPO)) return true;
        if (this.NoCoincidenContrasenias(contrasenia, confirmarContrasenia)) return true;
        if (this.estaEnDB(conn, nombre)) return true;
        return false;
    }

    public String getMensajeError() {
        return this.mensajes.getMensaje();
    }
}
