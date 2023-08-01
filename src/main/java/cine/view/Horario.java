package cine.view;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public class Horario {
    private String horarioElegido;
    private Button anteriorBtn;
    final private String DEFAULT_STYLE = "-fx-background-color: white; -fx-border-color: black; -fx-text-fill: black;";
    final private String SELECT_STYLE = "-fx-background-color: red; -fx-text-fill: white;";

    public void armarHorarios(Pane contenedor, ArrayList<String> horarios) {
        for (String horario : horarios) {
            Button button = new Button(horario);
            button.setPrefWidth(80);
            button.setPrefHeight(40);
            button.setStyle(DEFAULT_STYLE);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (anteriorBtn != null) anteriorBtn.setStyle(DEFAULT_STYLE);
                    button.setStyle(SELECT_STYLE);
                    horarioElegido = button.getText();
                    anteriorBtn = button;
                }
            });
            Region region = new Region();
            region.setPrefHeight(40);
            region.setPrefWidth(10);
            contenedor.getChildren().addAll(button, region);
        }
    }

    public String getHorarioElegido() {
        return this.horarioElegido;
    }
}
