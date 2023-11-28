package cine.controller;

import java.io.IOException;

import cine.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class Confirmar {
    private Escenas escenas = Controlador.escenas;
    private Usuario usuario = Controlador.usuario;

    @FXML
    private HBox productos;

    @FXML
    void initialize() throws IOException {
        if (!this.usuario.getProductos().isEmpty()) {
            FXMLLoader fxml = escenas.getFXML(ESCENA.CANDYS);
            Pane pane = (Pane) fxml.load();
            this.productos.getChildren().add(pane);
        }
    }

    @FXML
    void confirmar(ActionEvent event) {

    }
}