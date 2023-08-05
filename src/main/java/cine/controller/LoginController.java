package cine.controller;

import java.io.IOException;

import cine.model.Cine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class LoginController {

    private Cine cine = MenuController.cine;

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
            Stage primaryStage = (Stage)login.getScene().getWindow();
            Escenas.mostrarMsjError(primaryStage, this.cine.getMensaje());
            return;
        }
        this.cine.setUsuarioNombre(this.nombre.getText());
        Escenas.mostrarEscenaSig(this.login, "usuarioMenu");
    }

    @FXML
    void registrarse(ActionEvent event) throws IOException {
        Escenas.mostrarEscenaSig(this.login, "register");
    }

    @FXML
    void menu(ActionEvent event) throws IOException {
        Escenas.mostrarEscenaSig(this.login, "menu");
    }   

}