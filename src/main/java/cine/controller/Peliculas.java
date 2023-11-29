package cine.controller;

import cine.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class Peliculas {
    private Usuario usuario = Controlador.usuario;    

    @FXML
    private Label fecha;

    @FXML
    private Label horario;

    @FXML
    private Text titulo;

    @FXML
    private Label precio;

    @FXML
    void initialize() {
        this.titulo.setText(this.usuario.getTituloPelicula());
        this.horario.setText(this.usuario.getHorario());
        this.fecha.setText(this.usuario.getFechaPelicula());
        this.precio.setText(String.valueOf(this.usuario.getPrecioPelicula()));
    }

}