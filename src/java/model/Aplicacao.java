package model;

import DAO.DAOAplicacao;
import java.sql.SQLException;
import java.util.Date;
/**
 * Classe que representa um tipo de transação
 */
public class Aplicacao extends Transacao {

    //método construtor
    public Aplicacao(int codigoTransacao, String cpfCliente, double valor, double saldoInicialOrigem, int codigoContaOrigem, Date createdAt) {
        super(codigoTransacao, cpfCliente, valor, saldoInicialOrigem, codigoContaOrigem, createdAt);
    }

    //método que efetua uma aplicação
    public void doAplicacao() throws SQLException {
        DAOAplicacao.doAplicacao(this);
    }
    
    //método que retorna a transação que possui um dado código
    public static Transacao getAplicacao(int codigoTransacao) throws SQLException {
        return DAOAplicacao.getAplicacao(codigoTransacao);
    }

    //método que verifica se o saldo final da origem após a transação será válido
    public boolean verificaSaldoFinalOrigem(){
        return (this.getSaldoInicialOrigem() - this.getValor() >= 0.0);
    }
    
}
