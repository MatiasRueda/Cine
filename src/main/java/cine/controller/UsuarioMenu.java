package cine.controller;

import java.io.IOException;

import cine.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UsuarioMenu {
    private Escenas escenas = Menu.escenas;
    private Usuario usuario = Menu.usuario;

    @FXML
    private HBox usuarioMenu;

    @FXML
    private VBox opciones;

    @FXML
    private Label usuarioNombre;

    @FXML
    void initialize() {
        this.usuarioNombre.setText(this.usuario.getUsuarioNombre());
    }

    @FXML
    void comprar(ActionEvent event) throws IOException {
        this.escenas.mostrarEscenaSig("comprar");
    }

    @FXML
    void perfil(ActionEvent event) {

    }

    @FXML
    void promos(ActionEvent event) {

    }

    @FXML
    void salir(ActionEvent event) throws IOException {
        this.usuario.setUsuarioNombre(null);
        this.escenas.mostrarEscenaSig("menu");
    }

}