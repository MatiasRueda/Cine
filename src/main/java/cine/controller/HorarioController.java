package cine.controller;

import java.io.IOException;

import cine.model.Cine;
import cine.view.Armador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class HorarioController {
    private Cine cine = MenuController.cine;
    private Armador armadorHorarios = new Armador("white", "green");

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
        if (this.cine.getUsuarioNombre() == null) {
            Escenas.mostrarEscenaSig(this.horario, "menu");
            return;
        }
        Escenas.mostrarEscenaSig(this.horario, "usuarioMenu");
    }

    @FXML
    void comprar(ActionEvent event) {
        if (this.cine.getUsuarioNombre() == null) {
            System.out.println("Porfavor ingrese antes de comprar");
            return;
        }
        System.out.println("Usted esta logueado felicidades!!!!");
    }
}