package cine.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Usuario {
    private Conversor conversor = new Conversor();
    private String usuarioNombre;
    private String tituloPelicula;
    private String fechaPelicula; 
    private String horario;
    private String sala;
    private char fila;
    private char columna;
    private HashMap<String, Integer> productos = new HashMap<>();
    private int precioCandy;
    private int precioPelicula;

    public void agregarProducto(String productoNombre, int precio) {
        this.productos.put(productoNombre, this.productos.get(productoNombre) + 1);
        this.precioCandy += precio;
    }

    public void sacarProducto(String productoNombre, int precio) {
        this.productos.put(productoNombre, this.productos.get(productoNombre) - 1);
        this.precioCandy -= precio;
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

    public List<String> getInformacionPeli() {
        return Arrays.asList(new String[]{this.usuarioNombre, this.tituloPelicula, this.fechaPelicula, this.sala ,this.horario, String.valueOf(this.fila), String.valueOf(this.columna)});
    }

    public int getPrecioPelicula() {
        return this.precioPelicula;
    }

    public int getPrecioCandy() {
        return this.precioCandy;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public void setTituloPelicula(String tituloPelicula, String precio) {
        this.tituloPelicula = tituloPelicula;
        this.precioPelicula = Integer.parseInt(precio);
    }

    public void setFechaPelicula(String fechaPelicula) {
        this.fechaPelicula = fechaPelicula;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setSala(String horario, HashMap<String, String> salas) {
        this.sala = salas.get(horario);
    }

    public void setFila(int fila) {
        this.fila = this.conversor.pasarNumeroLetra(fila);
    }

    public void setColumna(int columna) {
        char letra = String.valueOf(columna).charAt(0);
        this.columna = Character.valueOf(letra);
    }

    public HashMap<String, Integer> getProductos() {
        HashMap<String, Integer> productos = new HashMap<>();
        this.productos.forEach((producto, cantidad) -> {
            if (cantidad == 0) return;
            productos.put(producto, cantidad);
        });
        return productos;
    }

    public void setProductos(HashMap<String, Integer> productos) {
        this.productos = productos;
    }

    public void reiniciarValores() {
        this.tituloPelicula = null;
        this.fechaPelicula = null;
        this.horario = null;
        this.sala = null;
        this.productos = new HashMap<>();
        this.precioCandy = 0;
    }

    public void cerrarSesion() {
        this.reiniciarValores();
        this.usuarioNombre = null;
    }
}
