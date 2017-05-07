package DAO;

import br.uff.ic.ConexaoBD;
import br.uff.ic.Investimento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class DAOInvestimento {

    private static Connection conexao = ConexaoBD.getInstance().getConexao();

    //método que insere um novo investimento no BD associado a uma dada conta
    public static void addInvestimento(Investimento investimento) throws SQLException {
        try (PreparedStatement sql = conexao.prepareStatement("insert into investimento values(?, ?, ?)")) {
            sql.setInt(1, investimento.getCodigoConta());
            sql.setDouble(2, investimento.getValorInvestido());
            sql.setDate(3, new java.sql.Date(investimento.getDataInicial().getTime()));
            sql.executeUpdate();
        }
    }

    //método que retorna um objeto investimento com dados do BD de um depósito que possui um dado código de conta
    //se esse investimento não existir, retorna null
    public static Investimento getInvestimento(int codigoConta) throws SQLException {
        Investimento investimento = null;
        try (PreparedStatement sql = conexao.prepareStatement("select * from investimento where codigoConta = ?")) {
            sql.setInt(1, codigoConta);
            ResultSet resultado = sql.executeQuery();

            if (resultado.next()) {
                investimento = new Investimento(resultado.getDouble("valorInvestido"),
                        resultado.getInt("codigoConta"),
                        resultado.getDate("dataInicial"));
            }
        }
        return investimento;
    }
}
