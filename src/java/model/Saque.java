package model;

import DAO.DAOSaque;
import java.sql.SQLException;
import java.util.Date;

/**
 * Classe que representa um tipo de transação
 */
public class Saque extends Transacao { 

    //método construtor
    public Saque(int codigoTransacao, String cpfCliente, double valor, double saldoInicialOrigem, int codigoContaOrigem, Date createdAt){
        super(codigoTransacao, cpfCliente, valor, saldoInicialOrigem, codigoContaOrigem, createdAt);
    }    

    //método que efetua um saque
    public void doSaque() throws SQLException {
        DAOSaque.doSaque(this);
    }

    //método que retorna a transação que possui um dado código
    public static Transacao getSaque(int codigoTransacao) throws SQLException {
        return DAOSaque.getSaque(codigoTransacao);
    }
    
    //método que verifica se o saldo final da origem após a transação será válido
    public boolean verificaSaldoFinalOrigem(){
        return (this.getSaldoInicialOrigem() - this.getValor() >= 0.0);
    }
    
}
