package cine.controller;

import java.io.IOException;

import cine.model.Cine;
import cine.model.Usuario;
import cine.view.Nav;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class NavUsuario {
    private final Escenas escenas = Controlador.escenas;
    private final Usuario usuario = Controlador.usuario;
    private final Cine cine = Controlador.cine;
    public final static Nav nav = new Nav();
 
    @FXML
    private Button inicioBtn;

    @FXML
    private Button comprarBtn;

    @FXML
    private VBox opciones;

    @FXML
    private StackPane titulo;

    @FXML
    void initialize() {
        nav.setBtnInicio(this.inicioBtn);
        nav.setBtnComprar(this.comprarBtn);
        nav.setOpciones(this.opciones);
        nav.setStyle(this.inicioBtn, true);
    }

    @FXML
    void inicio(ActionEvent event) throws IOException {
        this.usuario.reiniciarValores();
        this.cine.reiniciarValores();
        nav.elegirOpcion((Button) event.getSource());
        escenas.cargarEscena(ESCENA.INICIO);
    }

    @FXML
    void comprar(ActionEvent event) throws IOException {
        nav.elegirOpcion((Button) event.getSource());
        escenas.cargarEscena(ESCENA.COMPRAR);
    }

    @FXML
    void cerrarSesion(ActionEvent event) throws IOException {
        this.usuario.cerrarSesion();
        this.escenas.cerrarSesion();
    }
}
