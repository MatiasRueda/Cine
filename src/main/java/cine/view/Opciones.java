package cine.view;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public class Opciones {
    private String eleccion;
    private Button anteriorBtn;
    private int HEIGHT = 40;
    private int WIDTH = 80;
    private String DEFAULT_STYLE = "-fx-border-color: black; -fx-text-fill: black;";
    private String SELECT_STYLE = "-fx-border-color: black; -fx-text-fill: white;";
    private int espacioOcupadoFila = 0;

    public Opciones(String defaultColor, String selectColor) {
        this.DEFAULT_STYLE += "-fx-background-color: "+ defaultColor + ";";
        this.SELECT_STYLE += "-fx-background-color: "+ selectColor + ";";
    }

    public Opciones(String defaultColor, String selectColor, int height, int width) {
        this.DEFAULT_STYLE += "-fx-background-color: "+ defaultColor + ";";
        this.SELECT_STYLE += "-fx-background-color: "+ selectColor + ";";
        this.HEIGHT = height;
        this.WIDTH = width;
    }

    public void armar(Pane contenedor, ArrayList<String> elementos) {
        for (String elemento : elementos) {
            Button button = new Button(elemento);
            button.setPrefHeight(this.HEIGHT);
            button.setPrefWidth(this.WIDTH);
            button.setStyle(DEFAULT_STYLE);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (anteriorBtn != null) anteriorBtn.setStyle(DEFAULT_STYLE);
                    button.setStyle(SELECT_STYLE);
                    eleccion = button.getText();
                    anteriorBtn = button;
                }
            });
            Region region = new Region();
            region.setPrefHeight(40);
            region.setPrefWidth(10);
            contenedor.getChildren().add(button);
            this.espacioOcupadoFila += this.WIDTH;
            if ((this.espacioOcupadoFila + this.WIDTH) < contenedor.getPrefWidth()) {
                this.espacioOcupadoFila += region.getPrefWidth();
                contenedor.getChildren().add(region);
                continue;
            };
            this.espacioOcupadoFila = 0;
            this.espacioOcupadoFila += region.getPrefWidth();
            Region espacioVertical = new Region();
            espacioVertical.setPrefWidth(contenedor.getPrefWidth());
            espacioVertical.setPrefHeight(10);
            contenedor.getChildren().addAll(espacioVertical, region);
        }
    }

    public String getEleccion(){
        return this.eleccion;
    }

}
