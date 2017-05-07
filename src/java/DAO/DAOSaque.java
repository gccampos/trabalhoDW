package DAO;

import br.uff.ic.ConexaoBD;
import br.uff.ic.Saque;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOSaque {

    private static Connection conexao = ConexaoBD.getInstance().getConexao();

    //método que atualiza o saldo de uma conta após um saque
    public static void doSaque(Saque saque) throws SQLException {
        try (PreparedStatement sql = conexao.prepareStatement("update conta set saldo = ? where codigoConta = ?")) {
            sql.setDouble(1, saque.getSaldoInicialOrigem() - saque.getValor());
            sql.setInt(2, saque.getCodigoContaOrigem());
            sql.executeUpdate();
        }
    }    

    //método que retorna um objeto saque com dados do BD de um saque que possui um dado código de transação
    //se esse saque não existir, retorna null
    public static Saque getSaque(int codigoTransacao) throws SQLException {
        Saque saque = null;
        try (PreparedStatement sql = conexao.prepareStatement("select * from transacao where codigoTransacao = ?")) {
            sql.setInt(1, codigoTransacao);
            ResultSet resultado = sql.executeQuery();

            if (resultado.next()) {
                saque = new Saque(resultado.getInt("codigoTransacao"),
                        resultado.getString("cpfCliente"),
                        resultado.getDouble("valor"),
                        resultado.getDouble("saldoInicialOrigem"),
                        resultado.getInt("codigoContaOrigem"),
                        resultado.getDate("createdAt"));
            }
        }
        return saque;
    }

}
