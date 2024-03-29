package cine.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Mensaje {

    @FXML
    private Pane contenedorActual;

    @FXML
    private VBox mensaje;

    @FXML 
    private Pane stackPane;

    @FXML
    private Text texto;

    public void setMensaje(String mensaje) {
        this.texto.setText(mensaje);
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