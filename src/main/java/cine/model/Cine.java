package cine.model;

import java.util.List;

public class Cine {
    private SQL database = new SQL();
    private String mensaje;

    private boolean nombreEnBlanco(String nombre) {
        if (!nombre.isBlank()) return false;
        this.mensaje = "El nombre esta en blanco";
        return true;
    }

    private boolean contraseniaEnBlanco(String contrasenia) {
        if (!contrasenia.isBlank()) return false;
        this.mensaje = "La contrasenia esta en blanco";
        return true;
    }

    private boolean estaEnDB(String nombre) {
        if (!this.database.estaAgregado(nombre)) {
            this.mensaje = "El usuario no esta registrado";
            return false;
        }
        this.mensaje = "Usuario ya registrado";
        return true;
    }

    private boolean contraseniaCorrecta(String contrasenia, String contraseniaObtenida) {
        if (this.database.compararContrasenias(contrasenia, contraseniaObtenida)) return true;
        this.mensaje = "Contrasenia incorrecta";
        return false;
    }


    public boolean login(String nombre, String contrasenia) {
        if (this.nombreEnBlanco(nombre) || this.contraseniaEnBlanco(contrasenia) || !this.estaEnDB(nombre)) return false;
        String contraseniaObtenida = this.database.obtenerContrasenia(nombre, contrasenia);
        return contraseniaCorrecta(contrasenia, contraseniaObtenida);
    }

    public boolean register(String nombre, String contrasenia) {
        if (this.nombreEnBlanco(nombre) || this.contraseniaEnBlanco(contrasenia) || this.estaEnDB(nombre)) return false;
        return this.database.agregarUsuario(nombre, contrasenia);
    }

    public String getMensaje() {
        return this.mensaje;
    }

    public List<String> obtenerUsuarios() {
        return this.database.obtenerNombresUsuarios();
    }
    
}
