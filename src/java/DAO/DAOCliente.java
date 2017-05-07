package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Cliente;
import model.ConexaoBD;

public class DAOCliente {

    private static Connection conexao = ConexaoBD.getInstance().getConexao();

    //método que insere um novo cliente no BD
    public static void criaCliente(Cliente cliente) throws SQLException {
        try (PreparedStatement sql = conexao.prepareStatement("insert into cliente values(?, ?, ?, ?, ?)")) {
            sql.setString(1, cliente.getCpf());
            sql.setString(2, cliente.getNome());
            sql.setString(3, cliente.getEmail());
            sql.setString(4, cliente.getEndereco());
            sql.setString(5, cliente.getTelefone());
            sql.executeUpdate();
        }
    }

    //método que retorna um objeto cliente com dados do BD de um cliente que possui um dado cpf
    //se esse cliente não existir, retorna null
    public static Cliente getCliente(String cpf) throws SQLException {
        Cliente cliente = null;
        String nome, email, endereco, telefone;
        try (PreparedStatement sql = conexao.prepareStatement("select * from cliente where cpf = ?")) {
            sql.setString(1, cpf);
            ResultSet resultado = sql.executeQuery();
            if (resultado.next()) {
                nome = resultado.getString("nome");
                email = resultado.getString("email");
                endereco = resultado.getString("endereco");
                telefone = resultado.getString("telefone");
                cliente = new Cliente(cpf, nome, email, endereco, telefone);
            }
        }
        return cliente;
    }
}
