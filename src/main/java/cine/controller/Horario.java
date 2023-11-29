package cine.controller;

import java.io.IOException;
import java.sql.SQLException;

import cine.model.Cine;
import cine.model.Usuario;
import cine.view.Opciones;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class Horario {

    private Cine cine = Controlador.cine;
    private Escenas escenas = Controlador.escenas;
    private Usuario usuario = Controlador.usuario;
    private Opciones opciones = new Opciones("white", "green");

    @FXML
    private VBox horario;

    @FXML
    private FlowPane horarios;

    @FXML
    private Label labelHorarios;

    @FXML
    private Label labelFecha;

    @FXML
    void initialize() throws SQLException {
        this.labelHorarios.setText("Horarios para: " + this.usuario.getTituloPelicula());
        this.labelFecha.setText("Fecha elegida: " + this.usuario.getFechaPelicula());
        for (String horario: this.cine.getHorarios()) {
            Button boton = this.opciones.armar(horario);
            this.horarios.getChildren().add(boton);        
        }
    }

    @FXML
    void continuar(ActionEvent event) throws IOException, InterruptedException {
        String opcion = this.opciones.getEleccion();
        if (opcion == null) {
            this.escenas.mensajeError(opcion);
            return;
        }
        this.usuario.setHorario(opcion);
        this.usuario.setSala(opcion, this.cine.getSalas());
        this.escenas.cargarSiguienteEscena(ESCENA.SALA);
    }
}