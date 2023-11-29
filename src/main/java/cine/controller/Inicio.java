package cine.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import cine.model.Cine;
import cine.model.Usuario;
import cine.view.Pelicula;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Inicio {
    private ArrayList<ArrayList<String>> estrenoPeliculas = Controlador.estreno;
    private ArrayList<ArrayList<String>> proximamentePeliculas = Controlador.proximamente;
    private Escenas escenas = Controlador.escenas;
    private Usuario usuario = Controlador.usuario;
    private Cine cine = Controlador.cine;
    private final Pelicula pelicula = new Pelicula();

    @FXML
    private HBox peliculas;

    @FXML
    private HBox proximamente;

    @FXML
    void initialize() {
        this.estrenoPeliculas.forEach(pelicula -> irAPeli(pelicula));
        this.proximamentePeliculas.forEach((proximamente) -> {
            this.proximamente.getChildren().add(this.pelicula.crearProximamente(proximamente.get(0), proximamente.get(1)));
        });
    }

    private void comprarPeli(MouseEvent evento, ArrayList<String> pelicula) {
        try {
            if (this.usuario.getUsuarioNombre() == null) {
                this.escenas.cargarEscena(ESCENA.INGRESAR);
                return;
            } 
            this.usuario.setTituloPelicula(pelicula.get(0), pelicula.get(6));
            this.cine.setPelicula();
            this.escenas.cargarSiguienteEscena(ESCENA.FECHA);
        }   catch (IOException | InterruptedException | SQLException e ) {
            e.printStackTrace();
        }
    }

    private void irAPeli(ArrayList<String> pelicula) {
        VBox contenedor = this.pelicula.crearEstreno(pelicula.get(0), pelicula.get(5));
        contenedor.setOnMouseClicked(e -> comprarPeli(e, pelicula));
        this.peliculas.getChildren().add(contenedor);
    }

}