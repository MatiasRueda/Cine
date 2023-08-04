package cine.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class Cine {
    private MySQL database = new MySQL();
    private Conversor conversor = new Conversor();
    private Verificador verificador = new Verificador(database);
    private HashMap<String, String> salas = new HashMap<>();
    private ArrayList<String> horarios  = new ArrayList<>();
    private String usuarioNombre;
    private String tituloPelicula;
    private String fechaPelicula; 
    private String horario;
    private String sala;
    private char fila;
    private char columna;

    private void cerrarConeccion(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setSalasHorarios(ArrayList<ArrayList<String>> salasHorarios) {
        for(ArrayList<String> salaHorario : salasHorarios)  {
            salas.put(salaHorario.get(1), salaHorario.get(0));
            horarios.add(salaHorario.get(1));
        }
    }

    private ArrayList<ArrayList<Integer>> covertirIntFilasColumnas(ArrayList<ArrayList<String>> filasColumnas) {
        ArrayList<ArrayList<Integer>> reservas = new ArrayList<>();
        for(ArrayList<String> filaColumna : filasColumnas)  {
            ArrayList<Integer> reserva = new ArrayList<>();
            char fila = filaColumna.get(0).charAt(0);
            char columna = filaColumna.get(1).charAt(0);
            reserva.add(this.conversor.pasarLetraNumero(fila));
            reserva.add(Character.getNumericValue(columna));
            reservas.add(reserva);
        }
        return reservas;
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
        List<String> columnas =  Arrays.asList(new String[]{"sala", "horario"});
        List<String> condiciones =  Arrays.asList(new String[]{"fecha", "titulo"});
        List<String> valores =  Arrays.asList(new String[]{this.fechaPelicula, this.tituloPelicula});
        ArrayList<ArrayList<String>> resultado = this.database.getValorVariasCondiciones(conn, "sala", columnas, condiciones, valores);
        this.cerrarConeccion(conn);
        setSalasHorarios(resultado);
        Collections.sort(this.horarios);
        return this.horarios;
    }

    public ArrayList<ArrayList<String>> getCartelera(String offset) {
        Connection conn = this.database.conectarMySQL();
        List<String> columnas = Arrays.asList(new String[]{"titulo", "fecha" , "audio", "subtitulo", "duracion", "imagen" }); 
        ArrayList<ArrayList<String>> filas = this.database.getValorLimitOffset(conn, "pelicula", columnas, "2", offset);
        this.cerrarConeccion(conn);
        return filas;
    }

    public ArrayList<ArrayList<Integer>> getFilaColumnaReservadas() {
        Connection conn = this.database.conectarMySQL();
        List<String> columnas =  Arrays.asList(new String[]{"fila", "Columna"});
        List<String> condiciones =  Arrays.asList(new String[]{"fecha", "sala", "horario"});
        List<String> valores =  Arrays.asList(new String[]{this.fechaPelicula, this.sala, this.horario});
        ArrayList<ArrayList<String>> resultado = this.database.getValorVariasCondiciones(conn, "reserva", columnas, condiciones, valores);
        this.cerrarConeccion(conn);
        return covertirIntFilasColumnas( resultado);
    }

    public String getMensaje() {
        return this.verificador.getMensajeError();
    }

    
    public boolean guardarEleccion() {
        Connection conn = this.database.conectarMySQL();
        List<String> columnas =  Arrays.asList(new String[]{"reservado_por", "titulo", "fecha" ,"sala", "horario", "fila" , "columna"});
        List<String> valores =  Arrays.asList(new String[]{this.usuarioNombre, this.tituloPelicula, this.fechaPelicula, this.salas.get(this.horario) ,this.horario, String.valueOf(this.fila), String.valueOf(this.columna)});
        this.database.agregar(conn, "reserva", columnas, valores, new ArrayList<>());
        this.cerrarConeccion(conn);
        return true;
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

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setSala(String horario) {
        this.sala = this.salas.get(horario);
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

    public String getHorario() {
        return this.horario;
    }

    public String getSala() {
        return this.sala;
    }

    public Character getColumna() {
        return this.columna;
    }

    public Character getFila() {
        return this.fila;
    }

    public void reiniciarValores() {
        this.tituloPelicula = null;
        this.horario = null;
        this.fechaPelicula = null;
        this.horarios = new ArrayList<>();
        this.salas = new HashMap<>();
        this.columna = Character.MIN_VALUE;
        this.fila = Character.MIN_VALUE;
    }
}
