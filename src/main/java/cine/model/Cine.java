package cine.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cine.model.Errores.ErrorUsuario;

public class Cine {
    private MySQL database = new MySQL();
    private Mensajeria mensajes = new Mensajeria();
    private String usuarioNombre;
    private String tituloPelicula;
    private String fechaPelicula; 

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

    public boolean register(String nombre, String dni , String email, String contrasenia, String confirmarContrasenia) {
        if (this.nombreEnBlanco(nombre) || this.DNIEnBlanco(dni) || this.emailEnBlanco(email)) return false;
        if (this.contraseniaEnBlanco(contrasenia) || this.confirmarContraseniaEnBlanco(confirmarContrasenia)) return false;
        if (this.NoCoincidenContrasenias(contrasenia, confirmarContrasenia)) return false;
        if (this.estaEnDB(nombre)) return false;
        List<String> columnas = Arrays.asList(new String[]{"nombre","contrasenia", "dni", "email"});
        List<String> valores = Arrays.asList(new String[]{nombre, contrasenia, dni, email});
        List<Integer> encryptar = Arrays.asList(new Integer[]{1});
        return this.database.agregar("usuario", columnas, valores, encryptar);
    }

    public ArrayList<String> getFechas() { 
        return this.database.getValor("sala", "fecha", "titulo", this.tituloPelicula);
    }

    public ArrayList<String> getHorarios() { 
        List<String> condiciones =  Arrays.asList(new String[]{"fecha", "titulo"});
        List<String> valores =  Arrays.asList(new String[]{this.fechaPelicula, this.tituloPelicula});
        return this.database.getValorVariasCondiciones("sala", "horario", condiciones, valores);
    }

    public String getMensaje() {
        return this.mensajes.getMensaje();
    }

    public List<String> obtenerUsuarios() {
        return this.database.getValor("usuario", "nombre", null , null);
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public void setTituloPelicula(String tituloPelicula) {
        this.tituloPelicula = tituloPelicula;
    }

    public void setFechaPelicula(String fechaPelicula) {
        this.fechaPelicula = fechaPelicula;
    }

    public String getUsuarioNombre() {
        return this.usuarioNombre;
    }
    
    public String getTituloPelicula() {
        return this.tituloPelicula;
    }

    public String getFechaPelicula() {
        return this.fechaPelicula;
    }


    public void reiniciarValores() {
        this.tituloPelicula = null;
        this.fechaPelicula = null;
    }
}
