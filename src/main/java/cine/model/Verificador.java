package cine.model;

import java.sql.Connection;

public class Verificador {
    private MySQL database;
    private Mensajeria mensajes = new Mensajeria();

    public Verificador(MySQL database) {
        this.database = database;
    }

    private boolean campoEnBlanco(String campo, Errores error) {
        return this.mensajes.setMensaje(campo.isBlank(), error);
    }

    private boolean NoCoincidenContrasenias(String contrasenia, String confirmarContrasenia) {
        boolean resultado = !contrasenia.equals(confirmarContrasenia);
        return this.mensajes.setMensaje(resultado, Errores.CONTRASENIAS_DISTINTAS);
    }

    private boolean estaEnDB(Connection conn, String nombre) {
        boolean resultado = this.database.pertenece(conn, "usuario", "nombre", "nombre", nombre);
        return this.mensajes.setMensaje(resultado, Errores.USUARIO_REGISTRADO, Errores.USUARIO_NO_REGISTRADO);
    }

    public boolean loginParteUnoError(String nombre, String contrasenia) {
        return this.campoEnBlanco(nombre, Errores.NOMBRE_CAMPO) || this.campoEnBlanco(contrasenia, Errores.CONTRASENIA_CAMPO);
    }

    public boolean loginParteDosError(Connection conn, String nombre) {
        return !this.estaEnDB(conn, nombre);
    }

    public boolean loginParteTresError(String contrasenia, String contraseniaObtenida) {
        boolean resultado = !this.database.compararContrasenias(contrasenia, contraseniaObtenida);
        this.mensajes.setMensaje(resultado, Errores.CONTRASENIA_INCORRECTA);
        return resultado;
    }
    
    public boolean registerError(Connection conn, String nombre, String dni, String email, String contrasenia, String confirmarContrasenia) {
        if (this.campoEnBlanco(nombre, Errores.NOMBRE_CAMPO) || this.campoEnBlanco(dni, Errores.DNI_CAMPO) || this.campoEnBlanco(email, Errores.EMAIL_CAMPO)) return true;
        if (this.campoEnBlanco(contrasenia, Errores.CONTRASENIA_CAMPO) || this.campoEnBlanco(confirmarContrasenia,  Errores.CONTRASENIA_CONFIRMAR_CAMPO)) return true;
        if (this.NoCoincidenContrasenias(contrasenia, confirmarContrasenia)) return true;
        if (this.estaEnDB(conn, nombre)) return true;
        return false;
    }

    public String getMensajeError() {
        return this.mensajes.getMensaje();
    }
}
