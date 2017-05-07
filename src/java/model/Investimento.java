package model;

import DAO.DAOInvestimento;
import java.sql.SQLException;
import java.util.Date;

/**
 * Classe que representa um investimento de uma conta
 */
public class Investimento {

    private int codigoConta;
    private double valorInvestido; //guarda o valor inicial investido.
    private Date dataInicial; //guarda a data do investimento realizado.
    private static final double rentabilidadeDiaria = 0.00026; //0.026% de rentabilidade diária  

    //método construtor para um objeto inicial
    public Investimento(double valorInvestido, int codigoConta) {
        this.valorInvestido = valorInvestido;
        this.dataInicial = new Date();
        this.codigoConta = codigoConta;
    }
    
    //método construtor usado pelo DAO para criar objetos com dados provenientes do BD
    public Investimento(double valorInvestido, int codigoConta, Date dataInicial) {
        this.valorInvestido = valorInvestido;
        this.dataInicial = dataInicial;
        this.codigoConta = codigoConta;
    }

    //método que retorna a taxa de rentabilidade dos investimentos
    public double getRentabilidadeDiaria() {
        return rentabilidadeDiaria;
    }   
    
    //método que retorna a rentabilidade de um investimento (em dias)
    public double getRentabilidade() {
        int days = getDaysBetween(dataInicial, new Date());

        if (days > 0) {
            double rentabilidade = valorInvestido;
            for (int i = 0; i < days; i++) {
                rentabilidade *= (1.0 + rentabilidadeDiaria);
            }
            return rentabilidade;
        } else {
            return valorInvestido;
        }
    }

    //método que retorna a rentabilidade de um investimento (em minutos)
    public double getRentabilidadeMinutos() {
        int iMinutos = getMinutesBetweenDates(dataInicial, new Date());

        if (iMinutos > 0) {
            double rentabilidade = valorInvestido;
            for (int i = 0; i < iMinutos; i++) {
                rentabilidade *= (1.0 + rentabilidadeDiaria);
            }
            return rentabilidade;
        } else {
            return valorInvestido;
        }
    }

    //método que retorna a quantidade de dias entre duas datas
    private int getDaysBetween(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

    //método que retorna a quantidade de minutos entre duas datas
    private int getMinutesBetweenDates(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return (int) (diff / (60 * 1000) % 60);
    }

    //método que retorna o valor investido em um investimento
    public double getValorInvestido() {
        return valorInvestido;
    }

    //método que retorna a data inicial de um investimento
    public Date getDataInicial() {
        return dataInicial;
    }
    
    //método que retorna o código da conta a qual pertence um investimento
    public int getCodigoConta(){
        return codigoConta;
    }
    
    //método que inclui um novo investimento no BD
    public void addInvestimento() throws SQLException{
        DAOInvestimento.addInvestimento(this);
    }
}
