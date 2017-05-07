package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.ConexaoBD;
import model.ContaCliente;

public class DAOContaCliente {

    private static Connection conexao = ConexaoBD.getInstance().getConexao();

    //método que inclui uma nova associação entre conta e cliente no BD
    public static void addContaCliente(ContaCliente cc) throws SQLException {
        try (PreparedStatement sql = conexao.prepareStatement("insert into ContaCliente values(?, ?, ?)")) {
            sql.setString(1, cc.getCpfCliente());
            sql.setInt(2, cc.getCodigoConta());
            sql.setString(3, cc.getSenha());
            sql.executeUpdate();
        }
    }

    //método que retorna um objeto contaCliente com dados do BD de uma associação que possui um cpf de cliente, um código de conta e uma senha
    //se essa associação não existir, retorna null
    public static ContaCliente getContaCliente(String cpfCliente, int codigoConta, String senha) throws SQLException {
        ContaCliente cc = null;
        try (PreparedStatement sql = conexao.prepareStatement("select * from ContaCliente where cpfCliente = ? and codigoConta = ? and senha = ?")) {
            sql.setString(1, cpfCliente);
            sql.setInt(2, codigoConta);
            sql.setString(3, senha);
            ResultSet resultado = sql.executeQuery();
            if (resultado.next()) {
                cc = new ContaCliente(cpfCliente, codigoConta, senha);
            }
            return cc;
        }
    }

    //método que retorna uma lista de objetos cliente com dados do BD
    //esses clientes são os que possuem acesso a uma conta que possui um dado código de conta
    public static List<Cliente> getClientes(ContaCliente cc) throws SQLException {
        Cliente cliente;
        List<Cliente> clientes = new ArrayList<>();
        try (PreparedStatement sql = conexao.prepareStatement("select * from ContaCliente where codigoConta = ?")) {
            sql.setInt(1, cc.getCodigoConta());
            ResultSet resultado = sql.executeQuery();
            while (resultado.next()) {
                cliente = Cliente.getCliente(resultado.getString("cpfCliente"));
                clientes.add(cliente);
            }
        }
        return clientes;
    }
}
