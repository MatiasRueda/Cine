package cine.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cine.model.MySQL;
import cine.visual.Pelicula;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;

public class CarteleraController {

    private MySQL database = new MySQL();

    @FXML
    private TabPane peliculas;
    
    @FXML
    void initialize() {
        assert peliculas != null : "fx:id=\"peliculas\" was not injected: check your FXML file 'cartelera.fxml'.";
        this.cargarCartelera();
    }

    private void cargarCartelera() {
        List<String> columnas = Arrays.asList(new String[]{"titulo", "fecha" , "audio", "subtitulo", "duracion", "imagen" }); 
        ArrayList<ArrayList<String>> filas = this.database.getValor("pelicula", columnas, null, null);
        for(ArrayList<String> fila : filas) {
            Pelicula pelicula = new Pelicula(fila);
            this.peliculas.getTabs().add(pelicula.getPelicula());
        }
    }

    @FXML
    void cancelar(ActionEvent event) {

    }

    @FXML
    void continuar(ActionEvent event) {

    }

}
