package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.ConexaoBD;
import model.Conta;
import model.Transferencia;

public class DAOTransferencia {
    
    private static Connection conexao = ConexaoBD.getInstance().getConexao(); 

    //método que atualiza o saldo da conta origem de uma transferencia
    public static void doTransferenciaOrigem(Transferencia transferencia) throws SQLException {        
        Conta contaOrigem = Conta.getConta(transferencia.getCodigoContaOrigem());        
        try(PreparedStatement sql = conexao.prepareStatement("update conta set saldo = ? where codigoConta = ?")){
            sql.setDouble(1, contaOrigem.getSaldo() - transferencia.getValor());
            sql.setInt(2, transferencia.getCodigoContaOrigem());
            sql.executeUpdate();
        }
    }
    
    //método que atualiza o saldo da conta destino de uma transferencia
    public static void doTransferenciaDestino(Transferencia transferencia) throws SQLException {
        Conta contaDestino = Conta.getConta(transferencia.getCodigoContaDestino());
        try(PreparedStatement sql = conexao.prepareStatement("update conta set saldo = ? where codigoConta = ?")){
            sql.setDouble(1, contaDestino.getSaldo() + transferencia.getValor());
            sql.setInt(2, transferencia.getCodigoContaDestino());
            sql.executeUpdate();
        }
    }
    
    //método que retorna um objeto transferencia com dados do BD de uma transferencia que possui um dado código de transação
    //se essa transferencia não existir, retorna null
    public static Transferencia getTransferencia(int codigoTransacao) throws SQLException {
        Transferencia transferencia = null;
        try (PreparedStatement sql = conexao.prepareStatement("select * from transacao where codigoTransacao = ?")) {
            sql.setInt(1, codigoTransacao);
            ResultSet resultado = sql.executeQuery();

            if (resultado.next()) {
                transferencia = new Transferencia(resultado.getInt("codigoTransacao"),
                        resultado.getString("cpfCliente"),
                        resultado.getDouble("valor"),
                        resultado.getDouble("saldoInicialOrigem"),
                        resultado.getInt("codigoContaOrigem"),
                        resultado.getDate("createdAt"),
                        resultado.getInt("codigoContaDestino"),
                        resultado.getDouble("saldoInicialDestino"));
            }
        }
        return transferencia;

    }
    
}
