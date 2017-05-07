package model;

import DAO.DAODeposito;
import java.sql.SQLException;
import java.util.Date;

/**
 * Classe que representa um tipo de transação
 */
public class Deposito extends Transacao {

    private int codigoContaDestino;
    private double saldoInicialDestino;

    //método construtor
    public Deposito(int codigoTransacao, String cpfCliente, double valor, double saldoInicialOrigem, int codigoContaOrigem, Date createdAt, int codigoContaDestino, double saldoInicialDestino) {
        super(codigoTransacao, cpfCliente, valor, saldoInicialOrigem, codigoContaOrigem, createdAt);
        this.codigoContaDestino = codigoContaDestino;
        this.saldoInicialDestino = saldoInicialDestino;
    }

    //método que efetua um depósito
    public void doDeposito() throws SQLException {
        DAODeposito.doDeposito(this);
    }

    //método que retorna um depósito que possui um dado código de transação
    public static Transacao getDeposito(int codigoTransacao) throws SQLException {
        return DAODeposito.getDeposito(codigoTransacao);
    }

    //método que retorna o codigo da conta destino de um depósito
    public int getCodigoContaDestino() {
        return codigoContaDestino;
    }

    //método que retorna o saldo inicial da conta destino de um depósito
    public double getSaldoInicialDestino() {
        return saldoInicialDestino;
    }

}
