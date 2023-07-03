package cine.controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import cine.App;

public class Escenas {

    public static FXMLLoader getFXML(String fxml) {
        return new FXMLLoader(App.class.getResource("mensaje" + ".fxml"));
    }

    public static Parent loadFXML(String fxml) throws IOException {
            FXMLLoader fxmlLoader = getFXML(fxml);
            return fxmlLoader.load();
        }

    public static Stage getStage(Parent root, Modality modal, StageStyle style) {
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(modal);
            stage.initStyle(style);
            stage.setScene(scene);
            return stage;
    }
    

}
