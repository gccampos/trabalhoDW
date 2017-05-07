package model;

import DAO.DAOConta;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa uma conta existente no banco
 */
public class Conta {

    private int codigo;
    private double saldo;
    private List<Transacao> transacoes;
    private Investimento investimento;

    //método construtor para um objeto inicial
    public Conta() {
        this.saldo = 0.0;
        this.transacoes = new ArrayList<>();
        this.investimento = null;
    }    
    
    //método construtor usado pelo DAO para criar objetos com dados provenientes do BD
    public Conta(int codigo, double saldo, List<Transacao> transacoes, Investimento investimento) {
        this.codigo = codigo;
        this.saldo = saldo;
        this.transacoes = transacoes;
        this.investimento = investimento;
    }  
    
    //método que retorna o objeto investimento de uma conta
    public Investimento getInvestimento() {
        return investimento;
    }
    
    //método que retorna o saldo atual de uma conta
    public double getSaldo() {
        return saldo;
    }    
    
    //método que retorna o código de uma conta
    public int getCodigo() {
        return codigo;
    }    
    
    //método que retorna a lista de transações de uma conta
    public List<Transacao> getTransacoes(){
        return transacoes;
    }

    //método que inclui uma nova conta no banco e retorna seu código
    public int addConta() throws SQLException {
        return DAOConta.criaConta(this);
    }
    
    //método que retorna a conta que possui um dado código
    public static Conta getConta(int codigoConta) throws SQLException{
        return DAOConta.getConta(codigoConta);
    }

    //método que atualiza o saldo da conta após uma aplicação
    public void updateSaldoAplicacao(double valor) throws SQLException {
        DAOConta.updateSaldoAplicacao(this, valor);
    }
    
    //método que atualiza o saldo da conta após um resgate
    public void updateSaldoResgate(double valor) throws SQLException {
        DAOConta.updateSaldoResgate(this, valor);
    }

}
