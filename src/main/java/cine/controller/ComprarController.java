package cine.controller;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

// Imagenes sacadas de pixebay:
// Peliculas: https://pixabay.com/es/vectors/claqueta-película-recorte-video-311792/
// Candy: https://pixabay.com/es/vectors/palomitas-caja-bocadillo-película-4788367/

public class ComprarController {

    @FXML
    private HBox comprar;

    @FXML
    void pelicula(ActionEvent event) throws IOException, InterruptedException {
        Parent pantallaCarga = Escenas.loadFXML("carga");
        Stage primaryStage = (Stage)comprar.getScene().getWindow();
        Scene sceneCarga = new Scene(pantallaCarga);
        Stage secundaryStage = new Stage();
        secundaryStage.setWidth(250);
        secundaryStage.setHeight(75);
        secundaryStage.setX(primaryStage.getX() + primaryStage.getWidth() / 2 - secundaryStage.getWidth() / 2);
        secundaryStage.setY(primaryStage.getY() + primaryStage.getHeight() / 2 - secundaryStage.getHeight() / 2);
        secundaryStage.setResizable(false);
        secundaryStage.initModality(Modality.APPLICATION_MODAL);
        secundaryStage.initStyle(StageStyle.UNDECORATED);
        secundaryStage.setScene(sceneCarga);
        secundaryStage.show();
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                Parent cartelera;
                try {
                    cartelera = Escenas.loadFXML("cartelera");
                    Scene sceneCartelera = new Scene(cartelera);
                    Stage primaryStage = (Stage)comprar.getScene().getWindow();
                    primaryStage.setScene(sceneCartelera);

                    primaryStage.show();
                    secundaryStage.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            
        });
    }

    @FXML
    void candy(ActionEvent event) {

    }
}