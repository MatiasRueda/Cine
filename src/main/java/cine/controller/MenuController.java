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
    public final static Escenas escenas = new Escenas();

    @FXML
    private HBox menu;

    @FXML
    private Button salirBtn;

    public void setStage(Stage stage) {
        escenas.setPrimaryStage(stage);
    }

    @FXML
    void comprar(ActionEvent event) throws IOException {
        if (cine.getUsuarioNombre() == null) {
            escenas.mostrarMsjError("Es necesario ingresar");
            escenas.mostrarEscenaSig("login");
            return;
        }
        escenas.mostrarEscenaSig("comprar");
    }

    @FXML
    void promos(ActionEvent event) throws IOException {
        escenas.mostrarEscenaSig("promos");
    }

    @FXML
    void unirse(ActionEvent event) throws IOException {
        escenas.mostrarEscenaSig("login");
    }

    @FXML
    void salir(ActionEvent event) {
        Stage stage = (Stage) this.salirBtn.getScene().getWindow();
        stage.close();
    }

}