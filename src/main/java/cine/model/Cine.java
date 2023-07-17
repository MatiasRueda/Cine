package cine.model;

import java.util.Arrays;
import java.util.List;

import cine.model.Errores.ErrorUsuario;

public class Cine {
    private MySQL database = new MySQL();
    private Mensajeria mensajes = new Mensajeria();

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

    private boolean estaEnDB(String nombre) {
        boolean resultado = this.database.pertenece("usuario", "nombre", "nombre", nombre);
        return this.mensajes.setMensaje(resultado, ErrorUsuario.USUARIO_REGISTRADO, ErrorUsuario.USUARIO_NO_REGISTRADO);
    }

    private boolean contraseniaCorrecta(String contrasenia, String contraseniaObtenida) {
        boolean resultado = this.database.compararContrasenias(contrasenia, contraseniaObtenida);
        return this.mensajes.setMensaje(resultado, null , ErrorUsuario.CONTRASENIA_INCORRECTA);
    }

    private boolean NoCoincidenContrasenias(String contrasenia, String confirmarContrasenia) {
        boolean resultado = !contrasenia.equals(confirmarContrasenia);
        return this.mensajes.setMensaje(resultado, ErrorUsuario.CONTRASENIAS_DISTINTAS);
    }

    public boolean login(String nombre, String contrasenia) {
        if (this.nombreEnBlanco(nombre) || this.contraseniaEnBlanco(contrasenia) || !this.estaEnDB(nombre)) return false;
        String contraseniaObtenida = this.database.getValor("usuario", "contrasenia" , "nombre" , nombre).iterator().next();
        return this.contraseniaCorrecta(contrasenia, contraseniaObtenida);
    }

    public boolean register(String nombre, String DNI ,String contrasenia, String confirmarContrasenia) {
        if (this.nombreEnBlanco(nombre) || this.DNIEnBlanco(DNI) || this.contraseniaEnBlanco(contrasenia) || this.confirmarContraseniaEnBlanco(confirmarContrasenia)) return false;
        if (this.NoCoincidenContrasenias(contrasenia, confirmarContrasenia)) return false;
        if (this.estaEnDB(nombre)) return false;
        List<String> columnas = Arrays.asList(new String[]{"nombre", "DNI" ,"contrasenia"});
        List<String> valores = Arrays.asList(new String[]{nombre, DNI, contrasenia});
        List<Integer> encryptar = Arrays.asList(new Integer[]{2});
        return this.database.agregar("usuario", columnas, valores, encryptar);
    }

    public String getMensaje() {
        return this.mensajes.getMensaje();
    }

    public List<String> obtenerUsuarios() {
        return this.database.getValor("usuario", "nombre", null , null);
    }
    
}
