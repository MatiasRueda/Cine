package cine.controller;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
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
        Scene sceneCarga = new Scene(pantallaCarga);
        Stage secundaryStage = new Stage();
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