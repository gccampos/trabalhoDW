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
import model.User;

public class UserDAO {
    
    private static Connection conexao = ConexaoBD.getInstance().getConexao();

    //método que insere um novo cliente no BD
    public static void criaUser(User user) throws SQLException {
        try (PreparedStatement sql = conexao.prepareStatement("insert into usuario values(?, ?, ?, ?, 'Surf')")) {
            sql.setString(1, user.getNome());
            sql.setString(2, user.getEndereco());
            sql.setString(3, user.getTelefone());
            sql.setString(4, user.getEmail());
            sql.executeUpdate();
        }
    }

    //método que retorna um objeto cliente com dados do BD de um cliente que possui um dado cpf
    //se esse cliente não existir, retorna null
    public static User getUser(int id) throws SQLException {
        User user = null;
        String nome, email, endereco, telefone;
        try (PreparedStatement sql = conexao.prepareStatement("select * from cliente where userId = ?")) {
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado.next()) {
                nome = resultado.getString("nome");
                email = resultado.getString("email");
                endereco = resultado.getString("endereco");
                telefone = resultado.getString("telefone");
                user = new User(nome, endereco, telefone, email);
            }
        }
        return user;
    }
}
