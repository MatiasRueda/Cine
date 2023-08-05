package cine.controller;

import java.io.IOException;
import java.util.ArrayList;

import cine.model.Cine;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CarteleraController {

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

    private Cine cine = MenuController.cine;
    
    private Peliculas pelicula;
    
    @FXML
    void initialize() {
        this.pelicula = new Peliculas(titulo, fecha, audio, subtitulos, duracion, imagen);
        this.btnAnterior.setDisable(true);
        ArrayList<ArrayList<String>> filas = this.cargarCartelera();
        if (filas.size() == 1) btnSiguiente.setDisable(true);
    }

    private ArrayList<ArrayList<String>> cargarCartelera() {
        ArrayList<ArrayList<String>> filas = this.cine.getCartelera(String.valueOf(this.pelicula.getOffset()));
        ArrayList<String> fila = filas.iterator().next();
        this.pelicula.setInformacion(fila);
        this.cine.setTituloPelicula(fila.iterator().next());
        return filas;
    }

    @FXML
    void cancelar(ActionEvent event) throws IOException {
        this.cine.reiniciarValores();
        Escenas.mostrarEscenaSig(this.cartelera, "usuarioMenu");
    }

    @FXML
    void continuar(ActionEvent event) throws IOException {
        Escenas.mostrarEscenaSig(cartelera, "fecha");
    }


    @FXML
    void anterior(ActionEvent event) throws IOException{
        Stage primaryStage = (Stage)cartelera.getScene().getWindow();
        Stage secundaryStage = Escenas.armarPantallaCarga(primaryStage);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                btnSiguiente.setDisable(false);
                pelicula.bajarOffset();
                cargarCartelera();
                if (pelicula.getOffset() == 0) btnAnterior.setDisable(true);
                secundaryStage.close();
            }
            
        });
    }

    @FXML
    void siguiente(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage)cartelera.getScene().getWindow();
        Stage secundaryStage = Escenas.armarPantallaCarga(primaryStage);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                btnAnterior.setDisable(false);
                pelicula.subirOffset();
                ArrayList<ArrayList<String>> filas = cargarCartelera();
                if (filas.size() == 1) btnSiguiente.setDisable(true);
                secundaryStage.close();
            }
            
        });
    }

}
