package cine.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField contrasenia;

    @FXML
    private TextField nombre;

    @FXML
    void loguear(ActionEvent event) {

    }

}

/*

        if (!this.libreria.login(this.usuario.getText(), this.contrasenia.getText())) {
            Parent root = Escenas.loadFXML("mensaje");
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.showAndWait();
        }

        */