package cine.controller;

import java.util.ArrayList;

import cine.view.Pelicula;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

public class Inicio {
    private ArrayList<ArrayList<String>> estrenoPeliculas = Controlador.estreno;
    private ArrayList<ArrayList<String>> proximamentePeliculas = Controlador.proximamente;
    private final Pelicula pelicula = new Pelicula();

    @FXML
    private HBox peliculas;

    @FXML
    private HBox proximamente;

    @FXML
    void initialize() {
        this.estrenoPeliculas.forEach((pelicula) -> {
            this.peliculas.getChildren().add(this.pelicula.crearEstreno(pelicula.get(0), pelicula.get(5)));
        });
        this.proximamentePeliculas.forEach((proximamente) -> {
            this.proximamente.getChildren().add(this.pelicula.crearProximamente(proximamente.get(0), proximamente.get(1)));
        });
    }

}