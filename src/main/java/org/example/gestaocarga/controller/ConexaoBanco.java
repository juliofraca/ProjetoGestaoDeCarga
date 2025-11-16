package org.example.gestaocarga.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {

    private static Connection conexao;
    private static final String URL = "jdbc:sqlite:gestaocarga.db";

    public static Connection getConexao() {
        if (conexao == null) {
            try {
                conexao = DriverManager.getConnection(URL);
                System.out.println("Conectado com sucesso!");
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Erro ao conectar ao banco de dados!", e);
            }
        }
        return conexao;
    }
}

