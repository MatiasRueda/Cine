package cine;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import cine.model.Peticion;

public class PeticionTest {
    private final String TABLA_USUARIO = "usuario";
    private Peticion peticion = new Peticion();

    @Test
    public void insertTest() {
        List<String> columnas = Arrays.asList(new String[]{"nombre" , "DNI" , "contrasenia"}); 
        String peticionEsperada = "INSERT INTO usuario (nombre, DNI, contrasenia) VALUES (?, ?, ?)";
        assertEquals(peticionEsperada, this.peticion.insert(TABLA_USUARIO, columnas));
    }

    @Test
    public void deleteTest() {
        String columnCondicion = "nombre"; 
        String peticionEsperada = "DELETE FROM usuario WHERE nombre = ?";
        assertEquals(peticionEsperada, this.peticion.delete(TABLA_USUARIO, columnCondicion));
    }

    @Test
    public void selectTest() {
        String columna = "contrasenia";
        String columnCondicion = "nombre";
        String peticionEsperada = "SELECT contrasenia FROM usuario WHERE nombre = ?";
        assertEquals(peticionEsperada, this.peticion.select(TABLA_USUARIO, columna, columnCondicion));
    }

    @Test
    public void selectFormatoDosTest() {
        String columna = "nombre";
        String peticionEsperada = "SELECT nombre FROM usuario";
        assertEquals(peticionEsperada, this.peticion.select(TABLA_USUARIO, columna));
    }

    @Test
    public void selectFormatoTresTest() {
        List<String> columnas = Arrays.asList(new String[]{"nombre" , "contrasenia"}); 
        String columnCondicion = "nombre";
        String peticionEsperada = "SELECT nombre, contrasenia FROM usuario WHERE nombre = ?";
        assertEquals(peticionEsperada, this.peticion.select(TABLA_USUARIO, columnas, columnCondicion));
    }

    @Test
    public void selectFormatoCuatroTest() {
        List<String> columnas = Arrays.asList(new String[]{"nombre" , "DNI" , "contrasenia"}); 
        String columnCondicion = "nombre";
        String peticionEsperada = "SELECT nombre, DNI, contrasenia FROM usuario WHERE nombre = ?";
        assertEquals(peticionEsperada, this.peticion.select(TABLA_USUARIO, columnas, columnCondicion));
    }


    @Test
    public void selectFormatoCincoTest() {
        List<String> columnas = Arrays.asList(new String[]{"nombre" , "DNI" , "contrasenia"}); 
        String columnCondicion = "contrasenia";
        String peticionEsperada = "SELECT nombre, DNI, contrasenia FROM usuario WHERE contrasenia = ?";
        assertEquals(peticionEsperada, this.peticion.select(TABLA_USUARIO, columnas, columnCondicion));
    }

    @Test
    public void selectFormatoSeisTest() {
        List<String> columnas = Arrays.asList(new String[]{"nombre" , "DNI" , "contrasenia"}); 
        String columnCondicion = "email";
        String peticionEsperada = "SELECT nombre, DNI, contrasenia FROM usuario WHERE email = ?";
        assertEquals(peticionEsperada, this.peticion.select(TABLA_USUARIO, columnas, columnCondicion));
    }
    
    @Test
    public void selectFormatoSieteTest() {
        List<String> columnas = Arrays.asList(new String[]{"nombre" , "DNI" , "contrasenia"}); 
        String peticionEsperada = "SELECT nombre, DNI, contrasenia FROM usuario";
        assertEquals(peticionEsperada, this.peticion.select(TABLA_USUARIO, columnas));
    }
}
