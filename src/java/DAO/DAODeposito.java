package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.ConexaoBD;
import model.Conta;
import model.Deposito;

public class DAODeposito {

    private static Connection conexao = ConexaoBD.getInstance().getConexao();

    //método que atualiza o saldo de uma conta após um depósito
    public static void doDeposito(Deposito deposito) throws SQLException {
        Conta contaDestino = Conta.getConta(deposito.getCodigoContaDestino());
        try (PreparedStatement sql = conexao.prepareStatement("update conta set saldo = ? where codigoConta = ?")) {
            sql.setDouble(1, contaDestino.getSaldo() + deposito.getValor());
            sql.setInt(2, deposito.getCodigoContaDestino());
            sql.executeUpdate();
        }
    }

    //método que retorna um objeto depósito com dados do BD de um depósito que possui um dado código de transação
    //se esse depósito não existir, retorna null
    public static Deposito getDeposito(int codigoTransacao) throws SQLException {
        Deposito deposito = null;
        try (PreparedStatement sql = conexao.prepareStatement("select * from transacao where codigoTransacao = ?")) {
            sql.setInt(1, codigoTransacao);
            ResultSet resultado = sql.executeQuery();

            if (resultado.next()) {
                deposito = new Deposito(resultado.getInt("codigoTransacao"),
                        resultado.getString("cpfCliente"),
                        resultado.getDouble("valor"),
                        resultado.getDouble("saldoInicialOrigem"),
                        resultado.getInt("codigoContaOrigem"),
                        resultado.getDate("createdAt"),
                        resultado.getInt("codigoContaDestino"),
                        resultado.getDouble("saldoInicialDestino"));
            }
        }
        return deposito;

    }

}
