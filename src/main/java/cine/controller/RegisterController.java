package cine.controller;

import java.io.IOException;

import cine.model.Cine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class RegisterController {
    private Cine cine = new Cine();

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
        if (!contrasenia.getText().equals(contraseniaConfirmar.getText())) {
            Escenas.mostrarMsjError("Las contrasenias no son iguales");
            contrasenia.setText("");
            contraseniaConfirmar.setText("");
            return;
        }

        if (!cine.register(usuarioNombre.getText(), DNI.getText(), contrasenia.getText())) {
            Escenas.mostrarMsjError(cine.getMensaje());
            return;
        }
        Escenas.mostrarEscenaSig(register, "menu");
    }

}