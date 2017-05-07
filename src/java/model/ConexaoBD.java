package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Classe que efetua a conexão com o BD
 */
public class ConexaoBD {

    //variável estática que conterá a instância do método
    private static ConexaoBD instance;

    public Connection conexao = null;

    //construtor privado que suprime o construtor público padrão
    private ConexaoBD() {
        init();
    }

    //método público estático de acesso único ao objeto
    public static ConexaoBD getInstance() {
        if (instance == null) {
            //se ainda não houver nenhuma conexão criada
            instance = new ConexaoBD(); //o valor é retornado para quem está pedindo
        }
        return instance; //retorna o a instância do objeto
    }

    public Connection getConexao() {
        return conexao;
    }

    public void init() {
        try {
            conexao = DriverManager.getConnection("jdbc:derby://localhost:1527/Eleven", "grupo11", "root");
        } catch (Exception ex) {
        }
    }

    public void destroy() {
        try {
            conexao.close();
        } catch (Exception ex) {
        }
    }
}
