package model;

import DAO.DAOTransacao;
import java.sql.SQLException;
import java.util.Date;

/**
 * Classe abstrata que representa as transações de uma conta 
 * Os tipos de transação serão representados por classes-filha desta classe
 */
public abstract class Transacao {

    protected int codigoTransacao;
    protected String cpfCliente;
    protected int codigoContaOrigem;
    protected Date createdAt;
    protected double valor;
    protected double saldoInicialOrigem;

    //método construtor
    public Transacao(int codigoTransacao, String cpfCliente, double valor, double saldoInicialOrigem, int codigoContaOrigem, Date createdAt) {
        this.codigoTransacao = codigoTransacao;
        this.cpfCliente = cpfCliente;
        this.codigoContaOrigem = codigoContaOrigem;
        this.createdAt = createdAt;
        this.valor = valor;
        this.saldoInicialOrigem = saldoInicialOrigem;
    }

    //método que inclui uma nova transação no BD
    public int addTransacao(int tipo, int codigoContaDestino, double saldoInicialDestino) throws SQLException {
        return DAOTransacao.addTransacao(this, tipo, codigoContaDestino, saldoInicialDestino);
    }

    //método que retorna o saldo inicial da conta origem da transação
    public double getSaldoInicialOrigem() {
        return saldoInicialOrigem;
    }

    //método que retorna o CPF do cliente que fez a transação
    public String getCpfCliente() {
        return cpfCliente;
    }

    //método que retorna o valor da transação
    public double getValor() {
        return valor;
    }

    //método que retorna o código da conta origem da transação
    public int getCodigoContaOrigem() {
        return codigoContaOrigem;
    }

    //método que retorna a data em que uma transação foi feita
    public Date getCreatedAt() {
        return createdAt;
    }

    //método que retorna o código de uma dada transação
    public int getCodigoTransacao() {
        return codigoTransacao;
    }

    //método que retorna o tipo de uma dada transação
    public String getTipoTransacao() throws SQLException {
        return DAOTransacao.getTipoTransacao(this);
    }

    //método que retorna o sado inicial da conta destino da transação
    public double getSaldoInicialDestino() throws SQLException {
        return DAOTransacao.getSaldoInicialDestino(this);
    }

    //método que retorna o código da conta destino da transação
    public int getCodigoContaDestino() throws SQLException {
        return DAOTransacao.getCodigoContaDestino(this);
    }

    //método que retorna o saldo final da conta corrente após uma dada transação
    public double getSaldoFinal(int codigoContaCorrente) throws SQLException {
        String tipoTransacao = this.getTipoTransacao();
        double saldoFinal = 0.0;
        switch (tipoTransacao) {
            case "Saque":
                saldoFinal = this.getSaldoInicialOrigem() - this.getValor();
                break;
            case "Depósito":
                if (this.getCodigoContaDestino() == codigoContaCorrente) {
                    //conta corrente foi o destino da transação
                    saldoFinal = this.getSaldoInicialDestino() + this.getValor();
                } else { 
                    //conta corrente foi a prigem da transação
                    saldoFinal = this.getSaldoInicialOrigem();
                }
                break;
            case "Transferência":
                if (this.getCodigoContaOrigem() != codigoContaCorrente) { 
                    //conta corrente foi o destino da transação
                    saldoFinal = this.getSaldoInicialDestino() + this.getValor();
                } else { 
                    //conta corrente foi a prigem da transação
                    saldoFinal = this.getSaldoInicialOrigem() - this.getValor();
                }
                break;
            case "Aplicação":
                saldoFinal = this.getSaldoInicialOrigem() - this.getValor();
                break;
            case "Resgate":
                saldoFinal = this.getSaldoInicialOrigem() + this.getValor();
                break;
        }

        return saldoFinal;
    }

    //método helper (ou gambiarra) para exibição de extrato:
    public String defineCor(int codigoContaCorrente) throws SQLException {
        String tipoTransacao = this.getTipoTransacao();
        String classe = null;
        switch (tipoTransacao) {
            case "Saque":
                classe = "danger";
                break;
            case "Depósito":
                if (this.getCodigoContaDestino() == codigoContaCorrente) {
                    classe = "success";
                } else { 
                    classe = "warning";
                }
                break;
            case "Transferência":
                if (this.getCodigoContaOrigem() != codigoContaCorrente) { //codigoContaCorrente == codigoContaDestino
                    classe = "success";
                } else { 
                    classe = "danger";
                }
                break;
            case "Aplicação":
                classe = "danger";
                break;
            case "Resgate":
                classe = "success";
                break;
        }
        return classe;
    }

}
