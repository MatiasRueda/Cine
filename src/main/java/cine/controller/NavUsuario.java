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
    private Escenas escenas = Controlador.escenas;
    private Usuario usuario = Controlador.usuario;
    private Cine cine = Controlador.cine;
    private final Nav nav = new Nav();
 
    @FXML
    private Button inicioBtn;

    @FXML
    private VBox opciones;

    @FXML
    private StackPane titulo;

    private void elegirOpcion(Button opcion) {
        this.opciones.getChildren().forEach(o -> {
            if (!(o instanceof Button))
                return;
            if (opcion != o) {
                nav.setStyle((Button) o, false);
                return;
            } 
            nav.setStyle(opcion, true);
        });
    }

    @FXML
    void initialize() {
        nav.setStyle(this.inicioBtn, true);
    }

    @FXML
    void inicio(ActionEvent event) throws IOException {
        this.usuario.reiniciarValores();
        this.cine.reiniciarValores();
        this.elegirOpcion((Button) event.getSource());
        escenas.cargarEscena(ESCENA.INICIO);
    }

    @FXML
    void comprar(ActionEvent event) throws IOException {
        this.elegirOpcion((Button) event.getSource());
        escenas.cargarEscena(ESCENA.COMPRAR);
    }

    @FXML
    void salir(ActionEvent event) {

    }
}
