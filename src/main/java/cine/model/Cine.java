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
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Cine {
    private MySQL database = new MySQL();
    private Conversor conversor = new Conversor();
    private Verificador verificador = new Verificador(database);
    private HashMap<String, String> salas = new HashMap<>();
    private ArrayList<String> horarios  = new ArrayList<>();
    private HashMap<String, Integer> precios;
    private Usuario usuario;
    private String codigoCompra;

    public Cine(Usuario usuario) {
        this.usuario = usuario;
    }

    private void cerrarConeccion(Connection conn) throws SQLException {
        conn.close();
    }

    private void setSalasHorarios(ArrayList<ArrayList<String>> salasHorarios) {
        for(ArrayList<String> salaHorario : salasHorarios)  {
            this.salas.put(salaHorario.get(1), salaHorario.get(0));
            this.horarios.add(salaHorario.get(1));
        }
    }

    private ArrayList<ArrayList<Integer>> convertirIntFilasColumnas(ArrayList<ArrayList<String>> filasColumnas) {
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

    private void setProductosPrecios(ArrayList<ArrayList<String>> filas) {
        HashMap<String, Integer> productos = new HashMap<>();
        HashMap<String, Integer> precios = new HashMap<>();
        for (ArrayList<String> fila : filas) {
            productos.put(fila.get(0), 0);
            precios.put(fila.get(0), Integer.valueOf(fila.get(1)));
        }
        this.usuario.setProductos(productos);
        this.precios = precios;
    }

    public boolean register(String nombre, String dni , String email, String contrasenia, String confirmarContrasenia) throws SQLException {
        Connection conn = this.database.conectarMySQL();
        if (verificador.registerError(conn, nombre, dni, email, contrasenia, confirmarContrasenia)) return false;
        List<String> columnas = Arrays.asList(new String[]{"nombre","contrasenia", "dni", "email"});
        List<String> valores = Arrays.asList(new String[]{nombre, contrasenia, dni, email});
        List<Integer> encryptar = Arrays.asList(new Integer[]{1});
        boolean resultado = this.database.agregar(conn, "usuario", columnas, valores, encryptar);
        this.cerrarConeccion(conn);
        return resultado;
    }

    public boolean login(String nombre, String contrasenia) throws SQLException {
        if (verificador.loginParteUnoError(nombre, contrasenia))
            return false;
        Connection conn = this.database.conectarMySQL();
        if (verificador.loginParteDosError(conn, nombre)) 
            return false;
        String contraseniaObtenida = this.database.getValor(conn, "usuario", "contrasenia" , "nombre" , nombre).iterator().next();
        this.cerrarConeccion(conn);
        return !this.verificador.loginParteTresError(contrasenia, contraseniaObtenida);
    }

    public ArrayList<String> getFechas() throws SQLException { 
        Connection conn = this.database.conectarMySQL();
        ArrayList<String> fechasObtenidas = this.database.getValor(conn, "sala", "fecha", "titulo", this.usuario.getTituloPelicula());
        Set<String> unicasFechas = new LinkedHashSet<>(fechasObtenidas);
        ArrayList<String> fechas = new ArrayList<>(unicasFechas);
        Collections.sort(fechas);
        this.cerrarConeccion(conn);
        return fechas;
    }

    public ArrayList<String> getHorarios() throws SQLException { 
        Connection conn = this.database.conectarMySQL();
        List<String> columnas =  Arrays.asList(new String[]{"sala", "horario"});
        List<String> condiciones =  Arrays.asList(new String[]{"fecha", "titulo"});
        List<String> valores =  Arrays.asList(new String[]{this.usuario.getFechaPelicula(),  this.usuario.getTituloPelicula()});
        ArrayList<ArrayList<String>> resultado = this.database.getValorVariasCondiciones(conn, "sala", columnas, condiciones, valores);
        this.cerrarConeccion(conn);
        setSalasHorarios(resultado);
        Collections.sort(this.horarios);
        return this.horarios;
    }

    public ArrayList<ArrayList<ArrayList<String>>> getPeliculas() throws SQLException {
        Connection conn = this.database.conectarMySQL();
        ArrayList<ArrayList<ArrayList<String>>> peliculas = new ArrayList<>();
        List<String> columnas = Arrays.asList(new String[]{"titulo", "fecha" , "audio", "subtitulo", "duracion", "imagen" , "precio" }); 
        ArrayList<ArrayList<String>> filas = this.database.getValor(conn, "pelicula", columnas, null, null);
        peliculas.add(filas);
        columnas = Arrays.asList(new String[]{"titulo", "imagen"});
        filas = this.database.getValor(conn, "proximamente", columnas, null, null);
        peliculas.add(filas);
        this.cerrarConeccion(conn);
        return peliculas;
    }

    public ArrayList<ArrayList<Integer>> getReservas() throws SQLException {
        Connection conn = this.database.conectarMySQL();
        List<String> columnas =  Arrays.asList(new String[]{"fila", "Columna"});
        List<String> condiciones =  Arrays.asList(new String[]{"fecha", "sala", "horario"});
        List<String> valores =  Arrays.asList(new String[]{this.usuario.getFechaPelicula(), this.usuario.getSala(), this.usuario.getHorario()});
        ArrayList<ArrayList<String>> resultado = this.database.getValorVariasCondiciones(conn, "reserva_peli", columnas, condiciones, valores);
        this.cerrarConeccion(conn);
        return convertirIntFilasColumnas( resultado);
    }

    public ArrayList<ArrayList<String>> getCandy() throws SQLException { 
        Connection conn = this.database.conectarMySQL();
        List<String> columnas =  Arrays.asList(new String[]{"nombre", "precio", "imagen"});
        ArrayList<ArrayList<String>> resultado = this.database.getValor(conn, "candy", columnas, null , null);
        this.cerrarConeccion(conn);
        this.setProductosPrecios(resultado);
        return resultado;
    }

    private void guardarPeli(Connection conn) {
        List<String> peli_columnas =  Arrays.asList(new String[]{"codigo", "reservado_por", "titulo", "fecha" ,"sala", "horario", "fila" , "columna"});
        List<String> codigo = Arrays.asList(this.codigoCompra);
        List<Integer> encryptar = Arrays.asList(new Integer[]{0});
        List<String> peli_valores = Stream.concat(codigo.stream(), this.usuario.getInformacionPeli().stream()).collect(Collectors.toList());
        this.database.agregar(conn, "reserva_peli", peli_columnas, peli_valores, encryptar);
    }

    private void guardarCandy(Connection conn) {
        List<String> codigo = Arrays.asList(this.codigoCompra);
        List<String> candy_columnas =  Arrays.asList(new String[]{"codigo", "reservado_por", "producto", "cantidad"});
        List<Integer> encryptar = Arrays.asList(new Integer[]{});
        this.usuario.getProductos().forEach((producto, cantidad) -> {
            List<String> candy_productos = Stream.concat(codigo.stream(), Arrays.asList(new String[] { this.usuario.getUsuarioNombre(), producto , String.valueOf(cantidad)}).stream()).collect(Collectors.toList());
            this.database.agregar(conn, "reserva_candy", candy_columnas, candy_productos, encryptar);
        });
    }

    public boolean guardarEleccion() throws SQLException {
        Connection conn = this.database.conectarMySQL();
        this.codigoCompra = UUID.randomUUID().toString().substring(0, 5);
        if (this.usuario.getTituloPelicula() != null) 
            guardarPeli(conn);
        if (!this.usuario.getProductos().isEmpty())
            guardarCandy(conn);
        this.cerrarConeccion(conn);
        return true;
    }

    public void agregarProducto(String productoNombre) {
        this.usuario.agregarProducto(productoNombre, this.precios.get(productoNombre));
    }

    public void sacarProducto(String productoNombre) {
        this.usuario.sacarProducto(productoNombre, this.precios.get(productoNombre));
    }

    public HashMap<String, String> getSalas() {
        return this.salas;
    }

    public String getCodigoCompra() {
        return this.codigoCompra;
    }

    public void reiniciarValores() {
        this.salas = new HashMap<>();
        this.horarios = new ArrayList<>();
        this.precios = new HashMap<>();
    }

    public String getMensaje() {
        return this.verificador.getMensajeError();
    }

}
