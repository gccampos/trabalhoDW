/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author Salle
 */
import conexao.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Usuario;

public class UsuarioDAO {

    private static Connection conexao = ConexaoBD.getInstance().getConexao();

    //método que insere um novo cliente no BD
    public static void criaUsuario(Usuario user) throws SQLException {
        try (PreparedStatement sql = conexao.prepareStatement("INSERT INTO USUARIO VALUES(? ,? ,? ,? ,? )")) {
            sql.setInt(1, user.getId());
            sql.setString(3, user.getEndereco());
            sql.setString(4, user.getTelefone());
            sql.setString(5, user.getEmail());
            sql.setString(2, user.getNome());
            sql.executeUpdate();
        }
    }

    //método que retorna um objeto cliente com dados do BD de um cliente que possui um dado cpf
    //se esse cliente não existir, retorna null
    public static Usuario getUsuario(int id) throws SQLException {
        Usuario user = null;
        String nome, email, endereco, telefone;
        try (PreparedStatement sql = conexao.prepareStatement("SELECT * FROM USUARIO WHERE userId = ?")) {
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado.next()) {
                nome = resultado.getString("nome");
                email = resultado.getString("email");
                endereco = resultado.getString("endereco");
                telefone = resultado.getString("telefone");
                user = new Usuario(nome, endereco, telefone, email);
            }
        }
        return user;
    }
}
