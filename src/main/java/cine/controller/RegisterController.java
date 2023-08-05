package cine.controller;

import java.io.IOException;

import cine.model.Cine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterController {
    private Cine cine = MenuController.cine;

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
            Stage primaryStage = (Stage)register.getScene().getWindow();
            Escenas.mostrarMsjError(primaryStage, this.cine.getMensaje());
            return;
        }
        Escenas.mostrarEscenaSig(register, "menu");
    }


    @FXML
    void menu(ActionEvent event) throws IOException {
        Escenas.mostrarEscenaSig(register, "menu");
    }

}