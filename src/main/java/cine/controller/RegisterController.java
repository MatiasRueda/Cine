package cine.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import cine.model.Cine;


public class RegisterController {
    private Cine libreria = new Cine();

    @FXML
    private TextField usuario;

    @FXML
    private TextField contrasenia;

    @FXML
    private Button register;

    @FXML
    void enviarInfo(ActionEvent event) {
        this.libreria.register(this.usuario.getText(), this.contrasenia.getText());
    }

}
