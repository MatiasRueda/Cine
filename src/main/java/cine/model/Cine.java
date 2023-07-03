package cine.model;

import java.util.List;

public class Cine {
    private SQL database = new SQL();

    public boolean login(String nombre, String contrasenia) {
        if (nombre.isBlank() || contrasenia.isBlank() || !this.database.estaAgregado(nombre)) return false;
        String contraseniaObtenida = this.database.obtenerContrasenia(nombre, contrasenia);
        return this.database.compararContrasenias(contrasenia, contraseniaObtenida);
    }

    public boolean register(String nombre, String contrasenia) {
        if (nombre.isBlank() || contrasenia.isBlank() ||  this.database.estaAgregado(nombre)) return false;
        return this.database.agregarUsuario(nombre, contrasenia);
    }

    public List<String> obtenerUsuarios() {
        return this.database.obtenerNombresUsuarios();
    }
    
}
