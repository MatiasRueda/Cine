package cine.controller;

import java.io.IOException;

import cine.view.Nav;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NavSinUsuario {
    public final static Escenas escenas = Controlador.escenas;
    private final Nav nav = new Nav();

    @FXML
    private Button inicioBtn;

    @FXML
    private VBox opciones;

    @FXML
    private StackPane titulo;

    @FXML
    void initialize() {
        nav.setStyle(this.inicioBtn, true);
        nav.setOpciones(this.opciones);
    }

    @FXML
    void inicio(ActionEvent event) throws IOException {
        nav.elegirOpcion((Button) event.getSource());
        escenas.cargarEscena(ESCENA.INICIO);
    }

    @FXML
    void ingresar(ActionEvent event) throws IOException {
        nav.elegirOpcion((Button) event.getSource());
        escenas.cargarEscena(ESCENA.INGRESAR);
    }


     @FXML
    void salir(ActionEvent event) {
        Button boton = ((Button)event.getSource());
        Stage sta = (Stage) boton.getScene().getWindow();
        sta.close();
    }

}