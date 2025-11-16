package org.example.gestaocarga.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import org.example.gestaocarga.controller.dao.UsuarioDao;
import org.example.gestaocarga.controller.modelos.Usuario;

import java.io.IOException;



public class LoginController {

    @FXML
    private TextField usuarioDigitado;

    @FXML
    private TextField senhaDigitada;

    @FXML
    protected void cadastrarUsuario(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/gestaocarga/cadastro-view.fxml"));
         Scene cadastroScene = new Scene(fxmlLoader.load());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(cadastroScene);
        stage.setTitle("Cadastro de Usuário");
        stage.show();
    }

    public void bntEntrar(ActionEvent actionEvent) throws IOException {


        String nome = usuarioDigitado.getText();
        String senha = senhaDigitada.getText();

        UsuarioDao dao = new UsuarioDao();
        Usuario usuario = dao.login(nome, senha);

        if (usuario == null) {
            System.out.println("Usuário ou senha incorretos!");
            return;
        }

        String telaDestino;

        if (usuario.getTipo().equals("Gestor")) {
            telaDestino = "/org/example/gestaocarga/gestor-view.fxml";
        } else {
            telaDestino = "/org/example/gestaocarga/camionheiro-view.fxml";
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(telaDestino));
        Scene novaCena = new Scene(fxmlLoader.load());

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(novaCena);
        stage.show();
    }

    }

