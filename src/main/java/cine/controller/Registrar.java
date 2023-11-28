package cine.controller;

import java.io.IOException;

import cine.model.Cine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Registrar {
    private Cine cine = Controlador.cine;
    private Escenas escenas = Controlador.escenas;

    @FXML
    private StackPane stackRegister;

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
            this.escenas.mensajeError(this.cine.getMensaje());
            return;
        }
        //this.escenas.mostrarEscenaSig(ESCENA.INICIO);
    }


    @FXML
    void menu(ActionEvent event) throws IOException {
        //this.escenas.mostrarEscenaSig(ESCENA.INICIO);
    }

}