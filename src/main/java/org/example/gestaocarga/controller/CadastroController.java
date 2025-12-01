package org.example.gestaocarga.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.gestaocarga.controller.dao.UsuarioDao;
import org.example.gestaocarga.controller.modelos.Usuario;

import java.io.IOException;


public class CadastroController {

    @FXML
    public TextField senhaDigitada;
   @FXML
    public TextField usuarioCadastrado;
    @FXML
    public ComboBox cbxTipo;

    @FXML
    public void initialize(){
        cbxTipo.getItems().addAll("Gestor", "Camionheiro");

    }

    @FXML
    protected void voltarLogin(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/gestaocarga/login-view.fxml"));
        Scene loginScene = new Scene(fxmlLoader.load());
        loginScene.getStylesheets().add(getClass().getResource("/login.css").toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(loginScene);
        stage.setTitle("Tela de Login");
        stage.show();

    }
    public void cadastrarUsuario() {
        String nome = usuarioCadastrado.getText();
        String senha = senhaDigitada.getText();
        String tipo = cbxTipo.getValue().toString();

        Usuario novo = new Usuario(0, nome, senha, tipo);

        UsuarioDao dao = new UsuarioDao();
        dao.cadastrarUsuario(novo);

        System.out.println("Usuário cadastrado: " + tipo);
    }


}
