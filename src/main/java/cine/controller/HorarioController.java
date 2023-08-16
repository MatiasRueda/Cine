package cine.controller;

import java.io.IOException;

import cine.model.Cine;
import cine.view.Opciones;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class HorarioController {
    private Cine cine = MenuController.cine;
    private Escenas escenas = MenuController.escenas;
    private Opciones armadorHorarios = new Opciones("white", "green");

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
        this.armadorHorarios.armar(horarios, this.cine.getHorarios());
    }


    @FXML
    void cancelar(ActionEvent event) throws IOException {
        this.cine.reiniciarValores();
        this.escenas.mostrarEscenaSig("usuarioMenu");
    }

    @FXML
    void comprar(ActionEvent event) throws IOException {
        this.cine.setHorario(this.armadorHorarios.getEleccion());
        this.cine.setSala(this.armadorHorarios.getEleccion());
        this.escenas.mostrarEscenaSig("sala");
    }
}