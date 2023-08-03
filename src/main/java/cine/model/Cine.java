package cine.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


public class Cine {
    private MySQL database = new MySQL();
    private Conversor conversor = new Conversor();
    private Verificador verificador = new Verificador(database);
    private String usuarioNombre;
    private String tituloPelicula;
    private String fechaPelicula; 
    private char fila;
    private char columna;

    private void cerrarConeccion(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean register(String nombre, String dni , String email, String contrasenia, String confirmarContrasenia) {
        Connection conn = this.database.conectarMySQL();
        if (verificador.registerError(nombre, dni, email, contrasenia, confirmarContrasenia)) return false;
        List<String> columnas = Arrays.asList(new String[]{"nombre","contrasenia", "dni", "email"});
        List<String> valores = Arrays.asList(new String[]{nombre, contrasenia, dni, email});
        List<Integer> encryptar = Arrays.asList(new Integer[]{1});
        boolean resultado = this.database.agregar(conn, "usuario", columnas, valores, encryptar);
        this.cerrarConeccion(conn);
        return resultado;
    }

    public boolean login(String nombre, String contrasenia) {
        Connection conn = this.database.conectarMySQL();
        if (verificador.loginParteUnoError(nombre, contrasenia)) return false;
        String contraseniaObtenida = this.database.getValor(conn, "usuario", "contrasenia" , "nombre" , nombre).iterator().next();
        this.cerrarConeccion(conn);
        return !this.verificador.loginParteDosError(contrasenia, contraseniaObtenida);
    }

    public ArrayList<String> getFechas() { 
        Connection conn = this.database.conectarMySQL();
        ArrayList<String> fechasObtenidas = this.database.getValor(conn, "sala", "fecha", "titulo", this.tituloPelicula);
        Set<String> unicasFechas = new LinkedHashSet<>(fechasObtenidas);
        ArrayList<String> fechas = new ArrayList<>(unicasFechas);
        Collections.sort(fechas);
        this.cerrarConeccion(conn);
        return fechas;
    }

    public ArrayList<String> getHorarios() { 
        Connection conn = this.database.conectarMySQL();
        List<String> condiciones =  Arrays.asList(new String[]{"fecha", "titulo"});
        List<String> valores =  Arrays.asList(new String[]{this.fechaPelicula, this.tituloPelicula});
        ArrayList<String> resultado = this.database.getValorVariasCondiciones(conn, "sala", "horario", condiciones, valores);
        this.cerrarConeccion(conn);
        return resultado;
    }

    public ArrayList<ArrayList<String>> getCartelera(String offset) {
        Connection conn = this.database.conectarMySQL();
        List<String> columnas = Arrays.asList(new String[]{"titulo", "fecha" , "audio", "subtitulo", "duracion", "imagen" }); 
        ArrayList<ArrayList<String>> filas = this.database.getValorLimitOffset(conn, "pelicula", columnas, "2", offset);
        this.cerrarConeccion(conn);
        return filas;
    }

    public String getMensaje() {
        return this.verificador.getMensajeError();
    }

    public List<String> obtenerUsuarios() {
        Connection conn = this.database.conectarMySQL();
        ArrayList<String> resultado =  this.database.getValor(conn, "usuario", "nombre", null , null);
        this.cerrarConeccion(conn);
        return resultado;
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

    public void setFila(int fila) {
        this.fila = this.conversor.pasarNumeroLetra(fila);
    }

    public void setColumna(int columna) {
        char letra = String.valueOf(columna).charAt(0);
        this.columna = Character.valueOf(letra);
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

    public char getColumna() {
        return this.columna;
    }

    public char getFila() {
        return this.fila;
    }

    public void reiniciarValores() {
        this.tituloPelicula = null;
        this.fechaPelicula = null;
        this.columna = Character.MIN_VALUE;
        this.fila = Character.MIN_VALUE;
    }
}
