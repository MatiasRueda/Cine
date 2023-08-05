package cine.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

// Imagenes sacadas de pixebay:
// Peliculas: https://pixabay.com/es/vectors/claqueta-película-recorte-video-311792/
// Candy: https://pixabay.com/es/vectors/palomitas-caja-bocadillo-película-4788367/

public class ComprarController {
    private Escenas escenas = MenuController.escenas;

    @FXML
    private HBox comprar;

    @FXML
    void pelicula(ActionEvent event) throws IOException, InterruptedException {
        this.escenas.cargarSiguienteEscena("cartelera");
    }

    @FXML
    void candy(ActionEvent event) {

    }
}