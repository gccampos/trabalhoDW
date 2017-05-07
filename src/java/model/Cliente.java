package model;

import DAO.DAOCliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Classe que representa um cliente do banco
 */
public class Cliente {

    private String cpf;
    private String nome;
    private String email;
    private String endereco;
    private String telefone;    

    //método construtor
    public Cliente(String cpf, String nome, String email, String endereco, String telefone) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
    }   
    
    //método que retorna o cliente que possui um dado cpf
    //se não existir nenhum cliente com esse cpf, retorna null
    public static Cliente getCliente(String cpf) throws SQLException{
        return DAOCliente.getCliente(cpf);
    }

    //método que inclui um novo cliente no BD
    public void addCliente() throws SQLException {
        DAOCliente.criaCliente(this);
    }    
    
    //método que retorna o nome de um cliente
    public String getNome() {
        return nome;
    }

    //método que retorna o telefone de um cliente
    public String getTelefone() {
        return telefone;
    }

    //método que retorna o endereço de um cliente
    public String getEndereco() {
        return endereco;
    }

    //método que retorna o email de um cliente
    public String getEmail() {
        return email;
    }

    //método que retorna o cpf de um cliente
    public String getCpf() {
        return cpf;
    }
}
