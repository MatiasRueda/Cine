package cine.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MensajeController {

    @FXML
    private Pane contenedorActual;

    @FXML
    private VBox mensaje;

    @FXML
    private Button aceptarBtn;

    @FXML 
    private Pane stackPane;

    @FXML
    private Label mensajeText;


    public void setMensaje(String mensaje) {
        this.mensajeText.setText(mensaje);
    }

    public void setStackPane(Pane stackPane) {
        this.stackPane = stackPane;
    }

    public void setContenedorActual(Pane contenedorActual) {
        this.contenedorActual = contenedorActual;
    }

    @FXML
    void aceptar(ActionEvent event) throws IOException {
        this.stackPane.getChildren().remove(mensaje);
        this.contenedorActual.setDisable(false);
        this.contenedorActual.setEffect(null);
    }

}