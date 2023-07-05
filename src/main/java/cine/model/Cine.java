package cine.model;

import java.util.Arrays;
import java.util.List;

public class Cine {
    private SQL database = new SQL();
    private Mensajes mensajes = new Mensajes();

    private boolean nombreEnBlanco(String nombre) {
        return this.mensajes.setMensaje(nombre.isBlank(), "nombre-campo");
    }

    private boolean contraseniaEnBlanco(String contrasenia) {
        return this.mensajes.setMensaje(contrasenia.isBlank(), "contrasenia-campo");
    }

    private boolean estaEnDB(String nombre) {
        return this.mensajes.setMensaje(this.database.pertenece("usuario", "nombre", nombre), "usuario-registrado", "usuario-no-registrado");
    }

    private boolean contraseniaCorrecta(String contrasenia, String contraseniaObtenida) {
        return this.mensajes.setMensaje(this.database.compararContrasenias(contrasenia, contraseniaObtenida), null , "contrasenia-comparacion");
    }

    public boolean login(String nombre, String contrasenia) {
        if (this.nombreEnBlanco(nombre) || this.contraseniaEnBlanco(contrasenia) || !this.estaEnDB(nombre)) return false;
        String contraseniaObtenida = this.database.getValor("usuario", "contrasenia" , "nombre" , nombre);
        return contraseniaCorrecta(contrasenia, contraseniaObtenida);
    }

    public boolean register(String nombre, String DNI ,String contrasenia) {
        if (this.nombreEnBlanco(nombre) || this.contraseniaEnBlanco(contrasenia) || this.estaEnDB(nombre)) return false;
        List<String> columnas = Arrays.asList(new String[]{"nombre", "DNI" ,"contrasenia"});
        List<String> valores = Arrays.asList(new String[]{nombre, DNI, contrasenia});
        List<Integer> encryptar = Arrays.asList(new Integer[]{2});
        return this.database.agregar("usuario", columnas, valores, encryptar);
    }

    public String getMensaje() {
        return this.mensajes.getMensaje();
    }

    public List<String> obtenerUsuarios() {
        return this.database.obtenerNombresUsuarios();
    }
    
}
