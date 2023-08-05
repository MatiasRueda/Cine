package cine.controller;

import java.io.IOException;

import cine.model.Cine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UsuarioMenuController {
    private Cine cine = MenuController.cine;

    @FXML
    private HBox usuarioMenu;

    @FXML
    private VBox opciones;

    @FXML
    private Label usuario;


    @FXML 
    void initialize() {
        this.usuario.setText(this.cine.getUsuarioNombre());
    }

    @FXML
    void comprar(ActionEvent event) throws IOException {
        Escenas.mostrarEscenaSig(this.usuarioMenu, "comprar");
    }

    @FXML
    void perfil(ActionEvent event) {

    }

    @FXML
    void promos(ActionEvent event) {

    }

    @FXML
    void salir(ActionEvent event) throws IOException {
        this.cine.setUsuarioNombre(null);
        Escenas.mostrarEscenaSig(usuarioMenu, "menu");
    }

}