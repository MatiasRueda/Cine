package cine.controller;

import java.io.IOException;

import cine.App;
import cine.model.Cine;
import cine.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MenuController {

    public static Usuario usuario = new Usuario();
    public final static Cine cine = new Cine(usuario);
    public final static Escenas escenas = new Escenas();

    @FXML
    private HBox menu;

    @FXML
    private Button salirBtn;

    @FXML
    private StackPane menuVerdad;

    public void setStage(Stage stage) {
        escenas.setPrimaryStage(stage);
    }

    @FXML
    void comprar(ActionEvent event) throws IOException {
        if (usuario.getUsuarioNombre() == null) {
            escenas.mensajeError("Es necesario ingresar", menuVerdad);
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