package model;


import DAO.DAOResgate;
import java.sql.SQLException;
import java.util.Date;

/**
 * Classe que representa um tipo de transação
 */
public class Resgate extends Transacao {

    //método construtor
    public Resgate(int codigoTransacao, String cpfCliente, double valor, double saldoInicialOrigem, int codigoContaOrigem, Date createdAt) {
        super(codigoTransacao, cpfCliente, valor, saldoInicialOrigem, codigoContaOrigem, createdAt);
    }

    //método que efetua um resgate
    public void doResgate() throws SQLException {
        DAOResgate.doResgate(this);
    }
    
    //método que retorna a transação que possui um dado código
    public static Transacao getResgate(int codigoTransacao) throws SQLException {
        return DAOResgate.getResgate(codigoTransacao);
    }
    
    //método que verifica se o saldo final da origem após o resgate será válido
    public boolean verificaValorFinalInvestimento(Investimento investimento){
        return (investimento.getValorInvestido() - this.valor >= 0);
    }    
}
