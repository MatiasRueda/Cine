package cine.controller;

import java.io.IOException;

import cine.model.Cine;
import cine.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Controlador {

    public static Usuario usuario = new Usuario();
    public final static Cine cine = new Cine(usuario);
    public final static Escenas escenas = new Escenas();

    @FXML
    private HBox controlador;

    @FXML
    private VBox navControlador;

    @FXML
    private VBox opciones;

    @FXML
    private StackPane stackOrigen;

    public void setStage(Stage stage) {
        escenas.setPrimaryStage(stage);
    }

    @FXML
    void comprar(ActionEvent event) throws IOException {
        if (usuario.getUsuarioNombre() == null) {
            escenas.mensajeError("Es necesario ingresar", this.stackOrigen, this.controlador);
            return;
        }
        escenas.mostrarEscenaSig("comprar");
    }

    @FXML
    void promos(ActionEvent event) throws IOException {
        escenas.mostrarEscenaSig("promos");
    }

    @FXML
    void unirse(ActionEvent event) throws IOException {
        escenas.mostrarEscenaSig("ingresar");
    }

    @FXML
    void salir(ActionEvent event) {
        Button boton = ((Button)event.getSource());
        Stage sta = (Stage) boton.getScene().getWindow();
        sta.close();
    }

}