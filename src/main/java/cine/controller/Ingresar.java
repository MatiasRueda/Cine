package cine.controller;

import java.io.IOException;

import cine.model.Cine;
import cine.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class Ingresar {

    private Cine cine = Controlador.cine;
    private Escenas escenas = Controlador.escenas;
    private Usuario usuario = Controlador.usuario;

    @FXML
    private VBox ingresar;

    @FXML
    private TextField nombre;

    @FXML
    private PasswordField contrasenia;

    @FXML
    void ingresar(ActionEvent event) throws IOException {    
        if (!this.cine.login(nombre.getText() , contrasenia.getText())) {
            this.escenas.mensajeError(this.cine.getMensaje());
            return;
        }
        this.usuario.setUsuarioNombre(this.nombre.getText());
        this.escenas.mostrarEscenaSig(ESCENA.MENU);
    }

    @FXML
    void registrar(ActionEvent event) throws IOException {
        this.escenas.mostrarEscenaSig(ESCENA.REGISTRAR);
    }

}