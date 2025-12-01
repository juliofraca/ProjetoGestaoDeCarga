package org.example.gestaocarga.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import org.example.gestaocarga.controller.dao.CargaDao;
import org.example.gestaocarga.controller.modelos.Carga;

import java.io.IOException;
import java.util.List;

public class CamionheiroController {

    @FXML
    private FlowPane flowPaneCargas;

    private final int gestorId = 1;

    @FXML
    public void initialize() {
        carregarCargasPendentes();
    }

    private void carregarCargasPendentes() {
        try {
            CargaDao dao = new CargaDao();

            List<Carga> cargas = dao.listarPendentes(gestorId);

            for (Carga carga : cargas) {

                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/org/example/gestaocarga/cardCarga-view.fxml")
                );

                Parent card = loader.load();

                CardCargaController controller = loader.getController();

                controller.setData(carga, () -> flowPaneCargas.getChildren().remove(card));

                flowPaneCargas.getChildren().add(card);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void voltaTelaInical(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/gestaocarga/login-view.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/login.css").toExternalForm());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}