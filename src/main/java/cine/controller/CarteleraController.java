package cine.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cine.model.MySQL;
import cine.visual.Pelicula;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

public class CarteleraController {

    @FXML
    private VBox cartelera;

    @FXML
    private TabPane peliculas;

    private MySQL database = new MySQL();
    
    @FXML
    void initialize() {
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
    void cancelar(ActionEvent event) throws IOException {
        Escenas.mostrarEscenaSig(this.cartelera, "menu");
    }

    @FXML
    void continuar(ActionEvent event) {

    }

}
