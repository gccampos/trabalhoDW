package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Aplicacao;
import model.ConexaoBD;
import model.Conta;
import model.Investimento;

class DAOAplicacao {

    private static Connection conexao = ConexaoBD.getInstance().getConexao();

    //método que atualiza o valor investido de um investimento após uma aplicação
    static void doAplicacao(Aplicacao aplicacao) throws SQLException {
        Investimento investimento = Conta.getConta(aplicacao.getCodigoContaOrigem()).getInvestimento();
        try (PreparedStatement sql = conexao.prepareStatement("update investimento set valorInvestido = ? where codigoConta = ?")) {
            sql.setDouble(1, investimento.getValorInvestido() + aplicacao.getValor());
            sql.setInt(2, aplicacao.getCodigoContaOrigem());
            sql.executeUpdate();
        }
    }
    
    //método que retorna um objeto aplicação com dados do BD de uma aplicação que possui um dado código de transação
    //se essa aplicação não existir, retorna null
    public static Aplicacao getAplicacao(int codigoTransacao) throws SQLException {
        Aplicacao aplicacao = null;
        try(PreparedStatement sql = conexao.prepareStatement("select * from transacao where codigoTransacao = ?")){
            sql.setInt(1, codigoTransacao);
            ResultSet resultado = sql.executeQuery();
            
            if(resultado.next()){
                aplicacao = new Aplicacao(resultado.getInt("codigoTransacao"),
                                    resultado.getString("cpfCliente"), 
                                    resultado.getDouble("valor"),
                                    resultado.getDouble("saldoInicialOrigem"),
                                    resultado.getInt("codigoContaOrigem"),
                                    resultado.getDate("createdAt"));
            }
        }        
        return aplicacao;
    }

}
