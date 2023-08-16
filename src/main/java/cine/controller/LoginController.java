package cine.controller;

import java.io.IOException;

import cine.model.Cine;
import cine.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class LoginController {

    private Cine cine = MenuController.cine;
    private Escenas escenas = MenuController.escenas;
    private Usuario usuario = MenuController.usuario;

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
        if (!this.cine.login(nombre.getText() , contrasenia.getText())) {
            escenas.mostrarMsjError(this.cine.getMensaje());
            return;
        }
        this.usuario.setUsuarioNombre(this.nombre.getText());
        this.escenas.mostrarEscenaSig("usuarioMenu");
    }

    @FXML
    void registrarse(ActionEvent event) throws IOException {
        this.escenas.mostrarEscenaSig("register");
    }

    @FXML
    void menu(ActionEvent event) throws IOException {
        this.escenas.mostrarEscenaSig("menu");
    }   

}