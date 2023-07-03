module cine {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires io.github.cdimascio.dotenv.java;

    opens cine.controller to javafx.fxml;
    exports cine;
}
