package cine.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MensajeController {

    @FXML
    private Button aceptarBtn;

    @FXML
    private Label mensajeText;

    public void setMensaje(String mensaje) {
        this.mensajeText.setText(mensaje);
    }

    @FXML
    void aceptar(ActionEvent event) {
        Stage stage = (Stage) this.aceptarBtn.getScene().getWindow();
        stage.close();
    }

}