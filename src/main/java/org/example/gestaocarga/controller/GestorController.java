package org.example.gestaocarga.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.gestaocarga.controller.dao.CargaDao;
import org.example.gestaocarga.controller.modelos.Carga;
import org.example.gestaocarga.controller.modelos.Usuario;
import javafx.scene.Node;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GestorController {

    @FXML private AnchorPane adicionarTelinha;

    @FXML private TextField txtOrigem;
    @FXML private TextField txtDestino;
    @FXML private DatePicker txtDataEntrega;

    @FXML private FlowPane containerCards;

    private Usuario usuarioLogado;

    private CargaDao cargaDao = new CargaDao();

    @FXML
    public void initialize() {}

    public void setUsuario(Usuario usuario) {
        this.usuarioLogado = usuario;
        carregarPendentes();
    }

    private void carregarPendentes() {
        containerCards.getChildren().clear();

        List<Carga> pendentes = cargaDao.listarPendentes(usuarioLogado.getId());
        for (Carga c : pendentes) {
            adicionarCard(c);
        }
    }

    @FXML
    private void abrirTelinha() {
        adicionarTelinha.setVisible(true);
    }

    @FXML
    private void fecharTelinha() {
        adicionarTelinha.setVisible(false);
    }

    @FXML
    private void salvarCarga(ActionEvent event) {

        String dataISO = txtDataEntrega.getValue().toString(); // yyyy-MM-dd

        Carga carga = new Carga(
                txtOrigem.getText(),
                txtDestino.getText(),
                dataISO,
                usuarioLogado.getId()
        );

        cargaDao.salvar(carga);

        adicionarCard(carga);

        txtOrigem.clear();
        txtDestino.clear();
        txtDataEntrega.setValue(null);

        adicionarTelinha.setVisible(false);
    }


    private void adicionarCard(Carga carga) {

        AnchorPane card = new AnchorPane();
        card.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10; -fx-padding: 10;");
        card.setPrefSize(250, 120);

        String dataFormatada = carga.getDataEntrega();
        if (dataFormatada.contains("-")) {
            dataFormatada = dataFormatada.substring(8,10) + "/" +
                    dataFormatada.substring(5,7) + "/" +
                    dataFormatada.substring(0,4);
        }

        Label lblOrigem = new Label("Origem: " + carga.getOrigem());
        Label lblDestino = new Label("Destino: " + carga.getDestino());
        Label lblData = new Label("Entrega: " + dataFormatada);

        VBox box = new VBox(lblOrigem, lblDestino, lblData);
        box.setSpacing(5);

        card.getChildren().add(box);

        containerCards.getChildren().add(card);
    }

    public void voltarMenu(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/gestaocarga/login-view.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/login.css").toExternalForm());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}