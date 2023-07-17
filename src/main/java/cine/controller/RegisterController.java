package cine.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class RegisterController {

    @FXML
    private VBox register;

    @FXML
    private TextField usuarioNombre;

    @FXML
    private TextField DNI;

    @FXML
    private PasswordField contrasenia;

    @FXML
    private PasswordField contraseniaConfirmar;

    @FXML
    void registrar(ActionEvent event) throws IOException {
        if (!MenuController.cine.register(usuarioNombre.getText(), DNI.getText(), contrasenia.getText(), contraseniaConfirmar.getText())) {
            Escenas.mostrarMsjError(MenuController.cine.getMensaje());
            return;
        }
        Escenas.mostrarEscenaSig(register, "menu");
    }


    @FXML
    void menu(ActionEvent event) throws IOException {
        Escenas.mostrarEscenaSig(register, "menu");
    }

}