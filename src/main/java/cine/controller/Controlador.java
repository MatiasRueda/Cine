package cine.controller;

import java.io.IOException;

import cine.model.Cine;
import cine.model.Usuario;
import cine.view.Nav;
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
    public final Nav nav = new Nav();

    @FXML
    private StackPane stackControlador;
    
    @FXML
    private Button inicioBtn;

    @FXML
    private HBox controlador;

    @FXML
    private VBox navControlador;

    @FXML
    private VBox opciones;

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

    public void setStage(Stage stage) {
        escenas.setPrimaryStage(stage);
    }

    @FXML 
    void initialize() throws IOException {
        nav.setStyle(this.inicioBtn, true);
        escenas.setStackPane(this.stackControlador);
        escenas.setEscenaPrincipal(this.controlador);
        Pane escenaCargada = (Pane) escenas.loadFXML(ESCENA.INICIO);
        this.controlador.getChildren().add(escenaCargada);
    }

    @FXML
    void inicio(ActionEvent event) throws IOException {
        this.elegirOpcion(this.inicioBtn);
        escenas.cargarEscena(ESCENA.INICIO);
    }

    @FXML
    void ingresar(ActionEvent event) throws IOException {
        this.elegirOpcion((Button) event.getSource());
        escenas.cargarEscena(ESCENA.INGRESAR);
    }

    @FXML
    void comprar(ActionEvent event) throws IOException {
        this.elegirOpcion((Button) event.getSource());
        if (usuario.getUsuarioNombre() == null) {
            escenas.mensajeError("Es necesario ingresar");
            escenas.cargarEscena(ESCENA.INICIO);
            this.elegirOpcion(this.inicioBtn);
            return;
        }
        escenas.cargarEscena(ESCENA.COMPRAR);
    }

    @FXML
    void salir(ActionEvent event) {
        Button boton = ((Button)event.getSource());
        Stage sta = (Stage) boton.getScene().getWindow();
        sta.close();
    }

}