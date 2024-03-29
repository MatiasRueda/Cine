package cine;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * JavaFX App
 */

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        String escenaInicial = "controlador";
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(escenaInicial + ".fxml"));
        scene = new Scene(fxmlLoader.load(), 1300, 900);
        stage.setTitle("Cine");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}