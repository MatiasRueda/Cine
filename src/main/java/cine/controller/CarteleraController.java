package cine.controller;

import cine.visual.Pelicula;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;

public class CarteleraController {

    @FXML
    private TabPane peliculas;
    
    @FXML
    void initialize() {
        assert peliculas != null : "fx:id=\"peliculas\" was not injected: check your FXML file 'cartelera.fxml'.";
        this.cargarCartelera();
    }

    private void cargarCartelera() {
         Pelicula pelicula = new Pelicula("Dragon ball", "https://i.ibb.co/02v853W/dragonball.jpg","10/01/2023", "Japones", "Espaniol", "120");
         this.peliculas.getTabs().add(pelicula.getPelicula());
    }

    @FXML
    void cancelar(ActionEvent event) {

    }

    @FXML
    void continuar(ActionEvent event) {

    }

}
