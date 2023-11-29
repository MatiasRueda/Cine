package cine.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import cine.model.Usuario;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Cartelera {
    private Escenas escenas = Controlador.escenas;
    private Usuario usuario = Controlador.usuario;
    private ArrayList<ArrayList<String>> peliculas = Controlador.peliculas;

    private int indice = 0;

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
    void initialize() throws SQLException {
        this.cargarCartelera();
        if (peliculas.size() == 1) 
            btnSiguiente.setDisable(true);
    }

    private ArrayList<ArrayList<String>> cargarCartelera() {
        ArrayList<String> pelicula = this.peliculas.get(this.indice);
        this.titulo.setText(pelicula.get(0));
        this.fecha.setText(pelicula.get(1));
        this.audio.setText(pelicula.get(2));
        this.subtitulos.setText(pelicula.get(3));
        this.duracion.setText(pelicula.get(4));
        Image imagenPeli = new Image(pelicula.get(5), 175, 275, false, false);
        this.imagen.setImage(imagenPeli);
        this.usuario.setTituloPelicula(pelicula.get(0), pelicula.get(6));
        return peliculas;
    }

    @FXML
    void continuar(ActionEvent event) throws IOException, InterruptedException {
        this.escenas.cargarSiguienteEscena(ESCENA.FECHA);
    }

    @FXML
    void anterior(ActionEvent event) throws IOException, InterruptedException{
        this.escenas.cargando();
        this.btnSiguiente.setDisable(false);
        indice--;
        Thread.sleep(30);
        Platform.runLater(() -> {
            this.cargarCartelera();
            if (indice == 0) 
                btnAnterior.setDisable(true);
            this.escenas.sacarCargando();
        });
    }

    @FXML
    void siguiente(ActionEvent event) throws IOException, InterruptedException {
        this.escenas.cargando();
        this.btnAnterior.setDisable(false);
        indice++;
        Thread.sleep(30);
        Platform.runLater(() -> {
            this.cargarCartelera();
            if (this.peliculas.size() == (indice + 1))
                btnSiguiente.setDisable(true);
            this.escenas.sacarCargando();
        });
    }


}
