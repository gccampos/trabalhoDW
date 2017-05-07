package model;

import DAO.DAOTransferencia;
import java.sql.SQLException;
import java.util.Date;

/**
 * Classe que representa um tipo de transação
 */
public class Transferencia extends Transacao {
    
    private int codigoContaDestino;
    private double saldoInicialDestino;

    //método construtor
    public Transferencia(int codigoTransacao, String cpfCliente, double valor, double saldoInicialOrigem, int codigoContaOrigem, Date createdAt, int codigoContaDestino, double saldoInicialDestino) {
        super(codigoTransacao, cpfCliente, valor, saldoInicialOrigem, codigoContaOrigem, createdAt);
        this.codigoContaDestino = codigoContaDestino;
        this.saldoInicialDestino = saldoInicialDestino;
    }

    //método que efetua uma transferencia
    public void doTransferencia() throws SQLException {
        DAOTransferencia.doTransferenciaOrigem(this);
        DAOTransferencia.doTransferenciaDestino(this);
    }
    
    //método que retorna a transação que possui um dado código
    public static Transacao getTransferencia(int codigoTransacao) throws SQLException {
        return DAOTransferencia.getTransferencia(codigoTransacao);
    }
    
    //método que retorna o código da conta destino da transferencia
    public int getCodigoContaDestino(){
        return codigoContaDestino;
    }
    
    //método que retorna o saldo inicial da conta destino da transferencia
    public double getSaldoInicialDestino(){
        return saldoInicialDestino;
    }
    
    //método que verifica se o saldo final da origem após a transação será válido
    public boolean verificaSaldoFinalOrigem(){
        return (this.getSaldoInicialOrigem() - this.getValor() >= 0.0);
    }
    
    //método que verifica se a conta destino da transferencia existe
    public boolean verificaContaDestino() throws SQLException{
        return Conta.getConta(this.codigoContaDestino) != null;
    }
    
}
