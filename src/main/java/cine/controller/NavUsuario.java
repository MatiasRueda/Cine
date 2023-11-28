package cine.controller;

import java.io.IOException;

import cine.view.Nav;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class NavUsuario {
    private Escenas escenas = Controlador.escenas;
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
    void inicio(ActionEvent event) {

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
