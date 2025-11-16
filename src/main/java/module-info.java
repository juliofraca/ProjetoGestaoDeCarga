module org.example.gestaocarga {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;

    opens org.example.gestaocarga to javafx.fxml;
    opens org.example.gestaocarga.controller to javafx.fxml;
    exports org.example.gestaocarga.controller;
}