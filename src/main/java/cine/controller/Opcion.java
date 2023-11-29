package cine.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Opcion {
    private Escenas escenas = Controlador.escenas;

    @FXML
    void candy(ActionEvent event) throws IOException, InterruptedException {
        this.escenas.cargarSiguienteEscena(ESCENA.CANDY);
    }

    @FXML
    void confirmarCompra(ActionEvent event) throws IOException {
        this.escenas.cargarEscena(ESCENA.CONFIRMAR);
    }

}
