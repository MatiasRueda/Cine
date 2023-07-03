package cine.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import cine.model.Cine;

public class LoginController {
    private Cine libreria = new Cine();

    @FXML
    private TextField usuario;

    @FXML
    private TextField contrasenia;

    @FXML
    private Button login;

    @FXML
    void enviarInfo(ActionEvent event) throws IOException {
        if (!this.libreria.login(this.usuario.getText(), this.contrasenia.getText())) {
            Parent root = Escenas.loadFXML("mensaje");
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.showAndWait();
        }
    }

}
