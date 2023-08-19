package cine.controller;

import java.io.IOException;
import java.util.ArrayList;

import cine.model.Cine;
import cine.model.Usuario;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class CarteleraController {
    private Cine cine = MenuController.cine;
    private Escenas escenas = MenuController.escenas;
    private Usuario usuario = MenuController.usuario;

    private int offset = 0;

    @FXML
    private StackPane stackCartelera;

    @FXML
    private VBox cartelera;

    @FXML
    private Label titulo;

    @FXML
    private Label fecha;

    @FXML
    private Label audio;

    @FXML
    private Label subtitulos;

    @FXML
    private Label duracion;

    @FXML
    private ImageView imagen;

    @FXML
    private Button btnAnterior;

    @FXML
    private Button btnSiguiente;
    
    @FXML
    void initialize() {
        ArrayList<ArrayList<String>> filas = this.cargarCartelera();
        if (filas.size() == 1) btnSiguiente.setDisable(true);
    }

    private ArrayList<ArrayList<String>> cargarCartelera() {
        ArrayList<ArrayList<String>> filas = this.cine.getCartelera(String.valueOf(offset));
        ArrayList<String> fila = filas.iterator().next();
        this.titulo.setText(fila.get(0));
        this.fecha.setText(fila.get(1));
        this.audio.setText(fila.get(2));
        this.subtitulos.setText(fila.get(3));
        this.duracion.setText(fila.get(4));
        Image imagenPeli = new Image(fila.get(5), 125, 175, false, false);
        this.imagen.setImage(imagenPeli);
        this.usuario.setTituloPelicula(fila.iterator().next());
        return filas;
    }

    @FXML
    void cancelar(ActionEvent event) throws IOException {
        this.usuario = new Usuario();
        this.escenas.mostrarEscenaSig("usuarioMenu");
    }

    @FXML
    void continuar(ActionEvent event) throws IOException {
        this.escenas.mostrarEscenaSig("fecha");
    }

    @FXML
    void anterior(ActionEvent event) throws IOException{
        Pane pantallaCarga = this.escenas.armarPantallaCarga();
        this.stackCartelera.getChildren().add(pantallaCarga);
        this.btnSiguiente.setDisable(false);
        offset--;
        Platform.runLater(() -> {
            cargarCartelera();
            if (offset == 0) btnAnterior.setDisable(true);
            stackCartelera.getChildren().remove(pantallaCarga);
        });
    }

    @FXML
    void siguiente(ActionEvent event) throws IOException {
        Pane pantallaCarga = this.escenas.armarPantallaCarga();
        this.stackCartelera.getChildren().add(pantallaCarga);
        this.btnAnterior.setDisable(false);
        offset++;
        Platform.runLater(() -> {
            if (cargarCartelera().size() == 1) btnSiguiente.setDisable(true);
            stackCartelera.getChildren().remove(pantallaCarga);
        });
    }

}
