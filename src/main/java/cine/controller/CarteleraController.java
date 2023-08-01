package cine.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cine.model.Cine;
import cine.model.MySQL;
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

    private MySQL database = new MySQL();

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
        List<String> columnas = Arrays.asList(new String[]{"titulo", "fecha" , "audio", "subtitulo", "duracion", "imagen" }); 
        ArrayList<ArrayList<String>> filas = this.database.getValorLimitOffset("pelicula", columnas, "2", String.valueOf(this.pelicula.getOffset()));
        ArrayList<String> fila = filas.iterator().next();
        this.pelicula.setInformacion(fila);
        this.cine.setTituloPelicula(fila.iterator().next());
        return filas;
    }

    @FXML
    void cancelar(ActionEvent event) throws IOException {
        Escenas.mostrarEscenaSig(this.cartelera, "menu");
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
