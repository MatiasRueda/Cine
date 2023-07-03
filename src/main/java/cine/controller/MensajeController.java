package cine.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MensajeController {

    @FXML
    private Button mensajeBtn;

    @FXML
    void aceptar(ActionEvent event) {
        Stage stage = (Stage) this.mensajeBtn.getScene().getWindow();
        stage.close();
    }

}