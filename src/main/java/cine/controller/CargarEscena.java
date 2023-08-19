package cine.controller;

import java.io.IOException;

import cine.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class CargarEscena implements Runnable {
    private StackPane stackPane;
    private Stage primaryStage;
    private String siguienteEscena;

    public CargarEscena(StackPane stackPane, Stage primaryStage, String siguienteEscena) {
        this.stackPane = stackPane;
        this.primaryStage = primaryStage;
        this.siguienteEscena = siguienteEscena;
    }

    private FXMLLoader getFXML(String fxml) {
        return new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    }

    private Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = this.getFXML(fxml);
        return fxmlLoader.load();
    }


    @Override
    public void run() {

        try{
            synchronized(stackPane) {
                if (stackPane.getChildren().size() == 1) {
                    System.out.println("El total de hijos es 1 en CargarEscena");
                    wait();
                }
                System.out.println("El total de hijos es: " + stackPane.getChildren().size() + " en CargarEscena");
                Parent cartelera = this.loadFXML(siguienteEscena);
                Scene sceneCartelera = new Scene(cartelera);
                primaryStage.setScene(sceneCartelera);
                primaryStage.show();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
