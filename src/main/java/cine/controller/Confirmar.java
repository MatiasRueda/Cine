package cine.controller;

import java.io.IOException;
import java.sql.SQLException;

import cine.model.Cine;
import cine.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
            Pane peliculas = (Pane) this.escenas.loadFXML(ESCENA.PELICULAS);
            this.productos.getChildren().add(peliculas);
        }

        if (!this.usuario.getProductos().isEmpty()) {
            Pane candys = (Pane) this.escenas.loadFXML(ESCENA.CANDYS);
            this.productos.getChildren().add(candys);
        }
    }

    @FXML
    void confirmar(ActionEvent event) throws IOException, InterruptedException, SQLException {
        this.cine.guardarEleccion();
        this.escenas.cargarEscena(ESCENA.COMPRADO);
    }
}