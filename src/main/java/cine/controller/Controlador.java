package cine.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import cine.model.Cine;
import cine.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Controlador {
    public static Usuario usuario = new Usuario();
    public final static Cine cine = new Cine(usuario);
    public final static Escenas escenas = new Escenas();
    public static ArrayList<ArrayList<String>> peliculas;

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

    @FXML 
    void initialize() throws IOException, SQLException {
        peliculas = cine.getCartelera();
        escenas.setStackPane(this.stackControlador);
        escenas.setEscenaPrincipal(this.controlador);
        StackPane stackEscena = new StackPane();
        stackEscena.prefHeight(900);
        stackEscena.prefWidth(1000);
        escenas.setStackEscena(stackEscena);
        Pane navSinUsuario = (Pane) escenas.loadFXML(ESCENA.NAVSINUSUARIO);
        Pane inicio = (Pane) escenas.loadFXML(ESCENA.INICIO);
        stackEscena.getChildren().add(inicio);
        this.controlador.getChildren().add(navSinUsuario);
        this.controlador.getChildren().add(stackEscena);
    }


}