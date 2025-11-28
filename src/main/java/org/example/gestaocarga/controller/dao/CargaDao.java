package org.example.gestaocarga.controller.dao;

import org.example.gestaocarga.controller.ConexaoBanco;
import org.example.gestaocarga.controller.modelos.Carga;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CargaDao {

    private final Connection connection;

    public CargaDao() {
        this.connection = ConexaoBanco.getConexao();
        criarTabela();
    }

    private void criarTabela() {
        String sql = """
            CREATE TABLE IF NOT EXISTS cargas (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                origem TEXT NOT NULL,
                destino TEXT NOT NULL,
                dataEntrega TEXT NOT NULL,
                status TEXT NOT NULL,
                gestorId INTEGER NOT NULL
            )
        """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void salvar(Carga carga) {
        String sql = "INSERT INTO cargas (origem, destino, dataEntrega, status, gestorId) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, carga.getOrigem());
            stmt.setString(2, carga.getDestino());
            stmt.setString(3, carga.getDataEntrega());
            stmt.setString(4, carga.getStatus());
            stmt.setInt(5, carga.getGestorId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Carga> listarPendentes(int gestorId) {

        List<Carga> lista = new ArrayList<>();

        String sql = "SELECT * FROM cargas WHERE status = 'pendente' AND gestorId = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, gestorId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Carga carga = new Carga(
                        rs.getInt("id"),
                        rs.getString("origem"),
                        rs.getString("destino"),
                        rs.getString("dataEntrega"),
                        rs.getString("status"),
                        rs.getInt("gestorId")
                );

                lista.add(carga);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void aceitar(int id) {
        String sql = "UPDATE cargas SET status = 'aceita' WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}