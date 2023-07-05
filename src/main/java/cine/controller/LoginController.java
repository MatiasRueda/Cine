package cine.controller;

import java.io.IOException;

import cine.model.Cine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class LoginController {
    
    private Cine cine = new Cine();

    @FXML
    private VBox login;

    @FXML
    private TextField nombre;

    @FXML
    private PasswordField contrasenia;

    @FXML
    private Hyperlink register;

    @FXML
    void loguear(ActionEvent event) throws IOException {    
        
        if (!cine.login(nombre.getText() , contrasenia.getText())) {
            Escenas.mostrarMsjError(cine.getMensaje());
            return;
        }

        FXMLLoader fxmlLoader = Escenas.getFXML("usuarioMenu");
        Parent root = fxmlLoader.load();
        UsuarioMenuController usuarioMenuController = fxmlLoader.getController();
        usuarioMenuController.setUsuario(nombre.getText());
        Escenas.mostrarEscenaSig(login, root);
    }

    @FXML
    void registrarse(ActionEvent event) throws IOException {
        Escenas.mostrarEscenaSig(login, "register");
    }

}