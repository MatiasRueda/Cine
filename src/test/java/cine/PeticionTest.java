package cine;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import cine.model.Peticion;

public class PeticionTest {
    private Peticion peticion = new Peticion();

    @Test
    public void insertTest() {
        String tabla = "usuario";
        List<String> columnas = Arrays.asList(new String[]{"nombre" , "DNI" , "contrasenia"}); 
        String peticionEsperada = "INSERT INTO usuario (nombre, DNI, contrasenia) VALUES (?, ?, ?)";
        assertEquals(peticionEsperada, this.peticion.insert(tabla, columnas));
    }

    @Test
    public void selectTest() {
        String tabla = "usuario";
        String columna = "contrasenia";
        String columnCondicion = "nombre";
        String peticionEsperada = "SELECT contrasenia FROM usuario WHERE nombre = ?";
        assertEquals(peticionEsperada, this.peticion.select(tabla, columna, columnCondicion));
    }
}
