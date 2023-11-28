package cine.controller;

import cine.model.Usuario;
import cine.view.Item;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Candys {
    private Usuario usuario = Controlador.usuario;
    private Item fabrica = new Item();

    @FXML
    private VBox contenedor;

    @FXML
    private Label precioTotal;

    @FXML
    void initialize() {
        this.usuario.getProductos().forEach((producto, cantidad) -> {
            HBox item = this.fabrica.armar(producto, String.valueOf(cantidad));
            this.contenedor.getChildren().add(item);
        });

    }

}