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
    public final static Nav nav = new Nav();

    @FXML
    private Button inicioBtn;

    @FXML
    private Button ingresarBtn;

    @FXML
    private VBox opciones;

    @FXML
    private StackPane titulo;

    @FXML
    void initialize() {
        nav.setStyle(this.inicioBtn, true);
        nav.setBtnInicio(this.inicioBtn);
        nav.setBtnIngresar(this.ingresarBtn);
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