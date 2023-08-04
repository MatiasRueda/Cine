package cine.controller;

import java.io.IOException;

import cine.model.Cine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class UsuarioMenuController {
    private Cine cine = MenuController.cine;

    @FXML
    private Label usuario;

    @FXML
    private HBox usuarioMenu;


    @FXML 
    void initialize() {
        this.usuario.setText(this.cine.getUsuarioNombre());
    }

    @FXML
    void comprar(ActionEvent event) throws IOException {
        Escenas.mostrarEscenaSig(usuarioMenu, "comprar");
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