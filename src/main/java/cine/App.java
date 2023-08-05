package cine;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import cine.controller.MenuController;

/**
 * JavaFX App
 */

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        String escenaInicial = "menu";
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(escenaInicial + ".fxml"));
        scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Cine");
        stage.setScene(scene);
        stage.setResizable(false);
        MenuController.escenas.setPrimaryStage(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}