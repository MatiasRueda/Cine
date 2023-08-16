package cine.controller;

import java.io.IOException;

import cine.model.Cine;
import cine.view.Opciones;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class HorarioController {
    private Cine cine = MenuController.cine;
    private Escenas escenas = MenuController.escenas;
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
    void initialize() {
        this.labelHorarios.setText("Horarios para: " + this.cine.getTituloPelicula());
        this.labelFecha.setText("Fecha elegida: " + this.cine.getFechaPelicula());
        for (String horario: this.cine.getHorarios()) {
            Button boton = this.opciones.armar(horario);
            this.horarios.getChildren().add(boton);        
        }
    }


    @FXML
    void cancelar(ActionEvent event) throws IOException {
        this.cine.reiniciarValores();
        this.escenas.mostrarEscenaSig("usuarioMenu");
    }

    @FXML
    void comprar(ActionEvent event) throws IOException {
        this.cine.setHorario(this.opciones.getEleccion());
        this.cine.setSala(this.opciones.getEleccion());
        this.escenas.mostrarEscenaSig("sala");
    }
}