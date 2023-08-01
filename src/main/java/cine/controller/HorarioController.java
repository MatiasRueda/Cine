package cine.controller;

import java.io.IOException;

import cine.model.Cine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HorarioController {
    private Cine cine = MenuController.cine;

    @FXML
    private VBox horario;

    @FXML
    private HBox horarios;

    @FXML
    private Label labelHorarios;

    private String titulo;

    @FXML
    void cancelar(ActionEvent event) throws IOException {
        if (this.cine.getUsuarioNombre() == null) {
            Escenas.mostrarEscenaSig(this.horario, "usuarioMenu");
            return;
        }
        Escenas.mostrarEscenaSig(this.horario, "menu");
    }

    @FXML
    void comprar(ActionEvent event) {
        if (this.cine.getUsuarioNombre() == null) {
            System.out.println("Porfavor ingrese antes de comprar");
            return;
        }
        System.out.println("Usted esta logueado felicidades!!!!");
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
        this.labelHorarios.setText("Horarios para " + this.titulo + ":");
    }
}