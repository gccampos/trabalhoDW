package DAO;

import br.uff.ic.ConexaoBD;
import br.uff.ic.Conta;
import br.uff.ic.Investimento;
import br.uff.ic.Transacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOConta {

    private static Connection conexao = ConexaoBD.getInstance().getConexao();

    //método que insere uma nova conta no BD
    public static int criaConta(Conta conta) throws SQLException {     
        try (PreparedStatement sql = conexao.prepareStatement("insert into conta values(default, ?)")) {
            sql.setDouble(1, conta.getSaldo());
            sql.executeUpdate();
        }
        return DAOConta.getUltimoCodigo();
    }  
    
    //método que retorna o código da última conta criada
    public static int getUltimoCodigo() throws SQLException {
        try(PreparedStatement sql = conexao.prepareStatement("select * from conta order by codigoConta desc")){
            sql.setMaxRows(1);
            ResultSet resultado = sql.executeQuery();
            if(resultado.next()){  
                return resultado.getInt("codigoConta");
            }
        }
        return 0;
    }    
    
    //método que retorna um objeto conta com dados do BD de uma conta que possui um dado código de conta
    //se essa conta não existir, retorna null
    public static Conta getConta(int codigoConta) throws SQLException{
        Investimento investimento = null;
        Conta conta = null;
        List<Transacao> transacoes = null;
        try(PreparedStatement sql = conexao.prepareStatement("select * from conta where codigoConta = ?")){
            sql.setInt(1, codigoConta);
            ResultSet resultado = sql.executeQuery();
            if(resultado.next()){
                transacoes = DAOTransacao.getTransacoes(codigoConta);
                investimento = DAOInvestimento.getInvestimento(codigoConta);
                conta = new Conta(resultado.getInt("codigoConta"), resultado.getDouble("saldo"), transacoes, investimento);
            }
        }
        return conta;
    }

    //método que atualiza o saldo de uma conta após uma aplicação
    static void updateSaldoAplicacao(Conta conta, double valor) throws SQLException {
        try(PreparedStatement sql = conexao.prepareStatement("update conta set saldo = ? where codigoConta = ?")){
            sql.setDouble(1, conta.getSaldo() - valor);
            sql.setInt(2, conta.getCodigo());
            sql.executeUpdate();
        }
    }
    
    //método que atualiza o saldo de uma conta após um investimento
    static void updateSaldoResgate(Conta conta, double valor) throws SQLException {
        try(PreparedStatement sql = conexao.prepareStatement("update conta set saldo = ? where codigoConta = ?")){
            sql.setDouble(1, conta.getSaldo() + valor);
            sql.setInt(2, conta.getCodigo());
            sql.executeUpdate();
        }
    }
}


