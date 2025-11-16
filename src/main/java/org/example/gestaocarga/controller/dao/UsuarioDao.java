package org.example.gestaocarga.controller.dao;

import org.example.gestaocarga.controller.ConexaoBanco;
import org.example.gestaocarga.controller.modelos.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDao {
    private final Connection connection;

    public UsuarioDao() {
        this.connection = ConexaoBanco.getConexao();
        criarTabelaSePreciso();
    }
    private void criarTabelaSePreciso(){
        String sql = "CREATE TABLE IF NOT EXISTS usuario ("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT NOT NULL," +
                "tipo TEXT NOT NULL," +
                "senha TEXT NOT NULL" +
                ");"
                ;

        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.execute();
        } catch (SQLException e){
            System.out.println("Erro ao criar Tabela de seu usuario");
        }
    }
    public void cadastrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome, tipo, senha) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getTipo());
            stmt.setString(3, usuario.getSenha());
            stmt.executeUpdate();
            System.out.println("Usuário cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar usuário ");
        }
    }
    public Usuario login(String nome, String senha) {
        String sql = "SELECT * FROM usuario WHERE nome = ? AND senha = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, senha);

            var rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTipo(rs.getString("tipo"));
                return usuario;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao fazer login");
        }

        return null;
    }



}


