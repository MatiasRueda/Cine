package cine.controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import cine.App;

public class Escenas {

    public static Parent loadFXML(String fxml) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
            return fxmlLoader.load();
    }

}
