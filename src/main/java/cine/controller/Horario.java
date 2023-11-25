package cine.controller;

import java.io.IOException;

import cine.model.Cine;
import cine.model.Usuario;
import cine.view.Opciones;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Horario {

    private Cine cine = Menu.cine;
    private Escenas escenas = Menu.escenas;
    private Usuario usuario = Menu.usuario;
    private Opciones opciones = new Opciones("white", "green");

    @FXML 
    private StackPane stackHorario;

    @FXML
    private VBox horario;

    @FXML
    private FlowPane horarios;

    @FXML
    private Label labelHorarios;

    @FXML
    private Label labelFecha;

    @FXML
    void initialize() {
        this.labelHorarios.setText("Horarios para: " + this.usuario.getTituloPelicula());
        this.labelFecha.setText("Fecha elegida: " + this.usuario.getFechaPelicula());
        for (String horario: this.cine.getHorarios()) {
            Button boton = this.opciones.armar(horario);
            this.horarios.getChildren().add(boton);        
        }
    }


    @FXML
    void cancelar(ActionEvent event) throws IOException {
        this.usuario.reiniciarValores();
        this.cine.reiniciarValores();
        this.escenas.mostrarEscenaSig("usuarioMenu");
    }

    @FXML
    void continuar(ActionEvent event) throws IOException {
        String opcion = this.opciones.getEleccion();
        if (opcion == null) {
            this.escenas.mensajeError(opcion, this.stackHorario, horario);
            return;
        }
        this.usuario.setHorario(opcion);
        this.usuario.setSala(opcion, this.cine.getSalas());
        this.escenas.mostrarEscenaSig("sala");
    }
}