package cine.controller;

import java.io.IOException;

import cine.model.Cine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

public class MenuController {

    public final static Cine cine = new Cine();
    
    @FXML
    private HBox menu;

    @FXML
    void comprar(ActionEvent event) throws IOException {
        Escenas.mostrarEscenaSig(menu, "comprar");
    }

    @FXML
    void promos(ActionEvent event) throws IOException {
        Escenas.mostrarEscenaSig(menu, "promos");
    }

    @FXML
    void unirse(ActionEvent event) throws IOException {
        Escenas.mostrarEscenaSig(menu, "login");
    }


}