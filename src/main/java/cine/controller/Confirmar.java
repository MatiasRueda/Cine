package cine.controller;

import java.io.IOException;

import cine.model.Cine;
import cine.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class Confirmar {
    private Escenas escenas = Controlador.escenas;
    private Usuario usuario = Controlador.usuario;
    private Cine cine = Controlador.cine;

    @FXML
    private HBox productos;

    @FXML
    void initialize() throws IOException {
        if(this.usuario.getTituloPelicula() != null) {
            FXMLLoader fxml = escenas.getFXML(ESCENA.PELICULAS);
            Pane peliculas = (Pane) fxml.load();
            this.productos.getChildren().add(peliculas);
        }

        if (!this.usuario.getProductos().isEmpty()) {
            FXMLLoader fxml = escenas.getFXML(ESCENA.CANDYS);
            Pane candys = (Pane) fxml.load();
            this.productos.getChildren().add(candys);
        }
    }

    @FXML
    void confirmar(ActionEvent event) throws IOException {
        this.cine.guardarEleccion();
        this.escenas.cargarEscena(ESCENA.COMPRADO);
    }
}