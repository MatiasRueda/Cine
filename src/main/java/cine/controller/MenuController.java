package cine.controller;

import java.io.IOException;

import cine.model.Cine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MenuController {

    public final static Cine cine = new Cine();

    @FXML
    private HBox menu;

    @FXML
    private Button salirBtn;

    
    @FXML
    void comprar(ActionEvent event) throws IOException {
        if (cine.getUsuarioNombre() == null) {
            Stage primaryStage = (Stage)menu.getScene().getWindow();
            Escenas.mostrarMsjError(primaryStage , "Es necesario ingresar");
            Escenas.mostrarEscenaSig(menu, "login");
            return;
        }
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

    @FXML
    void salir(ActionEvent event) {
        Stage stage = (Stage) this.salirBtn.getScene().getWindow();
        stage.close();
    }



}