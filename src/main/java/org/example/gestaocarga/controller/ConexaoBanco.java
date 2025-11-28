package org.example.gestaocarga.controller;

import java.sql.*;

public class ConexaoBanco {

    private static Connection conexao;
    private static final String URL = "jdbc:sqlite:gestaocarga.db";

    public static Connection getConexao() {

        if (conexao == null) {
            try {
                conexao = DriverManager.getConnection(URL);
                criarTabelaCargas();

            } catch (SQLException e) {
                throw new RuntimeException("Erro ao conectar ao banco!", e);
            }
        }
        return conexao;
    }

    private static void criarTabelaCargas() {
        String sql = """
        CREATE TABLE IF NOT EXISTS cargas (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            origem TEXT NOT NULL,
            destino TEXT NOT NULL,
            dataEntrega TEXT NOT NULL,
            status TEXT NOT NULL,
            gestorId INTEGER NOT NULL
        );
    """;

        try (Statement stmt = conexao.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela 'cargas' criada/verificada com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela cargas: " + e.getMessage());
        }
    }
}