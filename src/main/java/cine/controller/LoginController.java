package cine.controller;

import java.io.IOException;

import cine.model.Cine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController {

    Cine cine = new Cine();

    @FXML
    private TextField nombre;

    @FXML
    private TextField contrasenia;

    @FXML
    void loguear(ActionEvent event) throws IOException {
        if (!this.cine.login(this.nombre.getText() , this.contrasenia.getText())) {
            FXMLLoader fxmlLoader = Escenas.getFXML("mensaje");
            Parent root = fxmlLoader.load();
            MensajeController mensajeCtrller = fxmlLoader.getController();
            mensajeCtrller.setMensaje(cine.getMensaje());
            Stage stage = Escenas.getStage(root, Modality.APPLICATION_MODAL, StageStyle.UNDECORATED);
            stage.showAndWait();
        }
    }

}