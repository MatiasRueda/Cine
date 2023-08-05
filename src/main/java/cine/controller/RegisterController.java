package cine.controller;

import java.io.IOException;

import cine.model.Cine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class RegisterController {
    private Cine cine = MenuController.cine;
    private Escenas escenas = MenuController.escenas;

    @FXML
    private VBox register;

    @FXML
    private TextField usuarioNombre;

    @FXML
    private TextField DNI;
    
    @FXML
    private TextField email;

    @FXML
    private PasswordField contrasenia;

    @FXML
    private PasswordField contraseniaConfirmar;

    @FXML
    void registrar(ActionEvent event) throws IOException {
        if (!this.cine.register(usuarioNombre.getText(), DNI.getText(), email.getText(), contrasenia.getText(), contraseniaConfirmar.getText())) {
            this.escenas.mostrarMsjError(this.cine.getMensaje());
            return;
        }
        this.escenas.mostrarEscenaSig("menu");
    }


    @FXML
    void menu(ActionEvent event) throws IOException {
        this.escenas.mostrarEscenaSig("menu");
    }

}