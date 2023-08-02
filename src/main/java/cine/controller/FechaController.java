package cine.controller;

import java.io.IOException;

import cine.model.Cine;
import cine.view.Armador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class FechaController {

    Cine cine = MenuController.cine;
    Armador armadoFichas = new Armador("white", "red");

    @FXML
    private VBox fecha;

    @FXML
    private FlowPane fechas;

    @FXML
    private Label labelFecha;

    @FXML
    void initialize() {
        this.labelFecha.setText("Fechas disponibles para: " + this.cine.getTituloPelicula());
        this.armadoFichas.armar(fechas, this.cine.getFechas());
    }

    @FXML
    void cancelar(ActionEvent event) throws IOException {
        this.cine.reiniciarValores();
        if (this.cine.getUsuarioNombre() == null) {
            Escenas.mostrarEscenaSig(fecha, "menu");
            return;
        }
        Escenas.mostrarEscenaSig(fecha, "usuarioMenu");
    }

    @FXML
    void continuar(ActionEvent event) throws IOException {
        this.cine.setFechaPelicula(this.armadoFichas.getEleccion());
        Escenas.mostrarEscenaSig(fecha, "horario");
    }

}