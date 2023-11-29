package cine.controller;

import java.io.IOException;

import cine.model.Cine;
import cine.view.Nav;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Comprado {
    private Cine cine = Controlador.cine;
    private Escenas escenas = Controlador.escenas;
    private Nav nav = NavUsuario.nav;

    @FXML
    private VBox compraExito;

    @FXML
    private Label codigo;

    @FXML
    void initialize() {
        this.codigo.setText(this.cine.getCodigoCompra()); 
    }

    @FXML
    void menu(ActionEvent event) throws IOException {
        this.escenas.cargarEscena(ESCENA.INICIO);
        nav.indicarInicio();
    }

}