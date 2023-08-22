package cine.controller;

import cine.model.Cine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CompraExitoController {
    private Cine cine = MenuController.cine;

    @FXML
    private Label codigo;


    @FXML
    void initialize() {
        this.codigo.setText(this.cine.generarCodigo()); 
    }

    @FXML
    void menu(ActionEvent event) {

    }

}