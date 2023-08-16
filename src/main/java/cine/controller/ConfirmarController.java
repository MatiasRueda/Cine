package cine.controller;

import cine.view.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ConfirmarController {

    private Item fabrica = new Item();

    @FXML
    private Label fecha;

    @FXML
    private Label horario;

    @FXML
    private Label precioTotal;

    @FXML
    private Label titulo;

    @FXML
    private VBox contenedor;

    @FXML
    void initialize() {
        for (int cantidad = 0; cantidad < 10; cantidad++) {
            HBox item = this.fabrica.armar(this.contenedor, "Producto" + String.valueOf(cantidad), false);
            this.contenedor.getChildren().add(item);
        }
    }

    @FXML
    void confirmar(ActionEvent event) {

    }

    @FXML
    void menu(ActionEvent event) {

    }

}