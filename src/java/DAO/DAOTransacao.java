package DAO;

import br.uff.ic.Aplicacao;
import br.uff.ic.ConexaoBD;
import br.uff.ic.Conta;
import br.uff.ic.Deposito;
import br.uff.ic.Resgate;
import br.uff.ic.Saque;
import br.uff.ic.Transacao;
import br.uff.ic.Transferencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOTransacao {

    private static Connection conexao = ConexaoBD.getInstance().getConexao();

    //método que cria uma nova transação no banco e retorna seu código
    public static int addTransacao(Transacao transacao, int tipo, int codigoContaDestino, double saldoInicialDestino) throws SQLException {
        try (PreparedStatement sql = conexao.prepareStatement("insert into transacao values(default, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            sql.setString(1, transacao.getCpfCliente());
            sql.setInt(2, transacao.getCodigoContaOrigem());
            sql.setDouble(3, Conta.getConta(transacao.getCodigoContaOrigem()).getSaldo());
            sql.setDate(4, new java.sql.Date(transacao.getCreatedAt().getTime()));
            sql.setInt(5, tipo);
            sql.setDouble(6, transacao.getValor());
            sql.setInt(7, codigoContaDestino);
            sql.setDouble(8, saldoInicialDestino);
            sql.executeUpdate();
        }
        return DAOTransacao.getUltimoCodigo();
    }

    //método que retorna o código da última transação criada
    public static int getUltimoCodigo() throws SQLException {
        try (PreparedStatement sql = conexao.prepareStatement("select * from transacao order by codigoTransacao desc")) {
            sql.setMaxRows(1);
            ResultSet resultado = sql.executeQuery();
            if (resultado.next()) {
                return resultado.getInt("codigoTransacao");
            }
        }
        return 0;
    }

    //método que retorna a descrição do tipo de uma dada transação
    public static String getTipoTransacao(Transacao transacao) throws SQLException {
        try (PreparedStatement sql = conexao.prepareStatement("select descricao from transacao t inner join tipoTransacao tt on t.tipoTransacao = tt.codigoTipo where t.codigoTransacao = ?")) {
            sql.setInt(1, transacao.getCodigoTransacao());
            ResultSet resultado = sql.executeQuery();
            if (resultado.next()) {
                return resultado.getString("descricao");
            }
        }
        return null;
    }
    
    //método que retorna o saldo inicial da conta destino de uma dada transação
    //se a transação não possuir conta destino, retorna 0.0
    public static double getSaldoInicialDestino(Transacao transacao) throws SQLException {
        try (PreparedStatement sql = conexao.prepareStatement("select saldoInicialDestino from transacao where codigoTransacao = ?")) {
            sql.setInt(1, transacao.getCodigoTransacao());
            ResultSet resultado = sql.executeQuery();
            if (resultado.next()) {
                return resultado.getDouble("saldoInicialDestino");
            }
        }
        return 0.0;
    }
    
    //método que retorna o código da conta destino de uma dada transação
    //se a transação não possuir conta destino, retorna 0
    public static int getCodigoContaDestino(Transacao transacao) throws SQLException {
        try (PreparedStatement sql = conexao.prepareStatement("select codigoContaDestino from transacao where codigoTransacao = ?")) {
            sql.setInt(1, transacao.getCodigoTransacao());
            ResultSet resultado = sql.executeQuery();
            if (resultado.next()) {
                return resultado.getInt("codigoContaDestino");
            }
        }
        return 0;
    }

    //método que retorna o cpf do cliente de uma dada transação
    static String getCpfCliente(int codigoTransacao) throws SQLException {
        try (PreparedStatement sql = conexao.prepareStatement("select * from transacao where codigoTransacao = ?")) {
            sql.setInt(1, codigoTransacao);
            ResultSet resultado = sql.executeQuery();
            if (resultado.next()) {
                return resultado.getString("cpfCliente");
            }
        }
        return null;
    }

    //método que retorna uma lista que contém as transações de uma dada conta
    public static List<Transacao> getTransacoes(int codigoConta) throws SQLException {
        List<Transacao> transacoes = new ArrayList<>();
        Transacao transacao = null;
        int codigoTransacao;
        try (PreparedStatement sql = conexao.prepareStatement("select codigoTransacao, tipoTransacao from transacao where codigoContaOrigem = ? or codigoContaDestino = ?")) {
            sql.setInt(1, codigoConta);
            sql.setInt(2, codigoConta);
            ResultSet resultado = sql.executeQuery();

            while (resultado.next()) {
                codigoTransacao = resultado.getInt("codigoTransacao");
                switch (resultado.getInt("tipoTransacao")) {
                    case 1:
                        transacao = Saque.getSaque(codigoTransacao);
                        break;
                    case 2:
                        transacao = Deposito.getDeposito(codigoTransacao);
                        break;
                    case 3:
                        transacao = Transferencia.getTransferencia(codigoTransacao);
                        break;
                    case 4:
                        transacao = Aplicacao.getAplicacao(codigoTransacao);
                        break;
                    case 5:
                        transacao = Resgate.getResgate(codigoTransacao);
                        break;
                }
                transacoes.add(transacao);
            }
        }
        return transacoes;
    }
}
