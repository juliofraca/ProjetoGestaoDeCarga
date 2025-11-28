package org.example.gestaocarga.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.example.gestaocarga.controller.dao.CargaDao;
import org.example.gestaocarga.controller.modelos.Carga;

public class CardCargaController {

    @FXML private Label lblOrigem;
    @FXML private Label lblDestino;
    @FXML private Label lblData;
    @FXML private Button btnAceitar;

    private Carga carga;
    private Runnable onAcceptedCallback;
    private final CargaDao cargaDao = new CargaDao();

    public void setData(Carga carga, Runnable callback) {
        this.carga = carga;
        this.onAcceptedCallback = callback;

        lblOrigem.setText(carga.getOrigem());
        lblDestino.setText(carga.getDestino());

        String dataISO = carga.getDataEntrega(); // yyyy-MM-dd
        String dataFormatada = dataISO.substring(8, 10) + "/" +
                dataISO.substring(5, 7) + "/" +
                dataISO.substring(0, 4);

        lblData.setText(dataFormatada);
    }

    @FXML
    private void aceitarCarga() {
        if (carga != null) {
            cargaDao.aceitar(carga.getId());

            if (onAcceptedCallback != null) {
                onAcceptedCallback.run();
            }
        }
    }
}