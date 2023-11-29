package cine.controller;

import java.util.ArrayList;

import cine.view.Pelicula;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

public class Inicio {
    private ArrayList<ArrayList<String>> estrenoPeliculas = Controlador.peliculas;
    private final Pelicula pelicula = new Pelicula();

    @FXML
    private HBox ofertas;

    @FXML
    private HBox peliculas;

    @FXML
    void initialize() {
        this.estrenoPeliculas.forEach((pelicula) -> {
            this.peliculas.getChildren().add(this.pelicula.crear(pelicula.get(0), pelicula.get(5)));
        });
    }

}