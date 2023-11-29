package cine.controller;

import java.io.IOException;
import java.sql.SQLException;

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
    void registrar(ActionEvent event) throws IOException, SQLException {
        if (!this.cine.register(usuarioNombre.getText(), DNI.getText(), email.getText(), contrasenia.getText(), contraseniaConfirmar.getText())) {
            this.escenas.mensajeError(this.cine.getMensaje());
            return;
        }
        //this.escenas.mostrarEscenaSig(ESCENA.INICIO);
    }
}