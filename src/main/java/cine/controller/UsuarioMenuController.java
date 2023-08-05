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
    private Escenas escenas = MenuController.escenas;

    @FXML
    private HBox usuarioMenu;

    @FXML
    private VBox opciones;

    @FXML
    private Label usuarioNombre;

    @FXML
    void initialize() {
        this.usuarioNombre.setText(this.cine.getUsuarioNombre());
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
        this.cine.setUsuarioNombre(null);
        this.escenas.mostrarEscenaSig("menu");
    }

}