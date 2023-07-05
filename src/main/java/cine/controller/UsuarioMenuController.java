package cine.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class UsuarioMenuController {

    @FXML
    private Label usuario;

    @FXML
    void comprar(ActionEvent event) {

    }

    @FXML
    void perfil(ActionEvent event) {

    }

    @FXML
    void promos(ActionEvent event) {

    }

    @FXML
    void salir(ActionEvent event) {

    }

    public void setUsuarioLabel(String mensaje) {
        this.usuario.setText(mensaje);
    }  
}