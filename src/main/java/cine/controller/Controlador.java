package cine.controller;

import java.io.IOException;

import cine.model.Cine;
import cine.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Controlador {

    public static Usuario usuario = new Usuario();
    public final static Cine cine = new Cine(usuario);
    public final static Escenas escenas = new Escenas();
    
    @FXML
    private StackPane stackControlador;

    @FXML
    private HBox controlador;

    @FXML
    private VBox navControlador;

    @FXML
    private VBox opciones;

    @FXML 
    void initialize() throws IOException {
        Pane menu = (Pane) escenas.loadFXML(ESCENA.MENU);
        controlador.getChildren().add(menu);
        escenas.setStackPane(this.stackControlador);
        escenas.setEscenaPrincipal(this.controlador);
    }

    public void setStage(Stage stage) {
        escenas.setPrimaryStage(stage);
    }

    @FXML
    void comprar(ActionEvent event) throws IOException {
        if (usuario.getUsuarioNombre() == null) {
            escenas.mensajeError("Es necesario ingresar");
            return;
        }
        escenas.cargarEscena(ESCENA.COMPRAR, this.controlador);
    }

    @FXML
    void unirse(ActionEvent event) throws IOException {
        escenas.cargarEscena(ESCENA.INGRESAR, this.controlador);
    }

    @FXML
    void salir(ActionEvent event) {
        Button boton = ((Button)event.getSource());
        Stage sta = (Stage) boton.getScene().getWindow();
        sta.close();
    }

}