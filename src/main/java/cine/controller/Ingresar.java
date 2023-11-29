package cine.controller;

import java.io.IOException;
import java.sql.SQLException;

import cine.model.Cine;
import cine.model.Usuario;
import cine.view.Nav;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class Ingresar {

    private Cine cine = Controlador.cine;
    private Escenas escenas = Controlador.escenas;
    private Usuario usuario = Controlador.usuario;
    private Nav nav = NavSinUsuario.nav;

    @FXML
    private VBox ingresar;

    @FXML
    private TextField nombre;

    @FXML
    private PasswordField contrasenia;

    @FXML
    void initialize() {
        nav.indicarIngresar();
    }

    @FXML
    void ingresar(ActionEvent event) throws IOException, SQLException {    
        if (!this.cine.login(nombre.getText() , contrasenia.getText())) {
            this.escenas.mensajeError(this.cine.getMensaje());
            return;
        }
        this.usuario.setUsuarioNombre(this.nombre.getText());
        this.escenas.ingresarUsuario();    
    }

    @FXML
    void registrar(ActionEvent event) throws IOException {
        this.escenas.cargarEscena(ESCENA.REGISTRAR);
    }

}