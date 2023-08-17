package cine.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MensajeController {
    private Escenas escenas = MenuController.escenas;

    @FXML
    private VBox mensaje;

    @FXML
    private Button aceptarBtn;

    @FXML 
    private Pane contenedorActual;

    @FXML
    private Label mensajeText;

    public void setMensaje(String mensaje) {
        this.mensajeText.setText(mensaje);
    }

    public void setContenedorActual(Pane contenedorActual) {
        this.contenedorActual = contenedorActual;
    }

    @FXML
    void aceptar(ActionEvent event) throws IOException {
        this.contenedorActual.getChildren().remove(mensaje);
    }

}