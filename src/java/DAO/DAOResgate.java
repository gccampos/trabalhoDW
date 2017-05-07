package DAO;

import br.uff.ic.ConexaoBD;
import br.uff.ic.Conta;
import br.uff.ic.Investimento;
import br.uff.ic.Resgate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class DAOResgate {
    
    private static Connection conexao = ConexaoBD.getInstance().getConexao();

    //método que atualiza o valor investido de um investimento após um resgate
    static void doResgate(Resgate resgate) throws SQLException {
        Investimento investimento = Conta.getConta(resgate.getCodigoContaOrigem()).getInvestimento();
        try (PreparedStatement sql = conexao.prepareStatement("update investimento set valorInvestido = ? where codigoConta = ?")) {
            sql.setDouble(1, investimento.getValorInvestido() - resgate.getValor());
            sql.setInt(2, resgate.getCodigoContaOrigem());
            sql.executeUpdate();
        }
    }
    
    //método que retorna um objeto resgate com dados do BD de um resgate que possui um dado código de transação
    //se esse resgate não existir, retorna null
    public static Resgate getResgate(int codigoTransacao) throws SQLException {
        Resgate resgate = null;
        try(PreparedStatement sql = conexao.prepareStatement("select * from transacao where codigoTransacao = ?")){
            sql.setInt(1, codigoTransacao);
            ResultSet resultado = sql.executeQuery();
            
            if(resultado.next()){
                resgate = new Resgate(resultado.getInt("codigoTransacao"),
                                    resultado.getString("cpfCliente"), 
                                    resultado.getDouble("valor"),
                                    resultado.getDouble("saldoInicialOrigem"),
                                    resultado.getInt("codigoContaOrigem"),
                                    resultado.getDate("createdAt"));
            }
        }        
        return resgate;
    }
    
}
