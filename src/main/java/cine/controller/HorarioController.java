package cine.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HorarioController {

    @FXML
    private VBox horario;

    @FXML
    private HBox horarios;

    @FXML
    private Label labelHorarios;

    private String titulo;

    @FXML
    void cancelar(ActionEvent event) throws IOException {
        Escenas.mostrarEscenaSig(this.horario, "menu");
    }

    @FXML
    void comprar(ActionEvent event) {
        System.out.println(this.titulo);
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
        this.labelHorarios.setText("Horarios para " + this.titulo + ":");
    }
}