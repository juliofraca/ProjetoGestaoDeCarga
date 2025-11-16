package org.example.gestaocarga.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.gestaocarga.controller.dao.UsuarioDao;
import org.example.gestaocarga.controller.modelos.Usuario;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class HelloApplication extends Application {

    private Stage stage;

    private Scene cadastroScene;
    private Scene loginScene;
    private Scene gestorScene;
    private Scene camioneiroScene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/gestaocarga/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        stage.setTitle("Gestão de Carga");
        stage.setScene(scene);
        stage.show();
    }

}
