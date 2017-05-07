package model;

import DAO.DAOContaCliente;
import java.sql.SQLException;
import java.util.List;

/**
 * Classe que representa a associação entre uma conta e um cliente
 */
public class ContaCliente {
    
    private String cpfCliente;
    private int codigoConta;
    private String senha;
    
    //método construtor
    public ContaCliente(String cpfCliente, int codigoConta, String senha){
        this.cpfCliente = cpfCliente;
        this.codigoConta = codigoConta;
        this.senha = senha;
    }
    
    //método que inclui uma nova associação no BD
    public void addContaCliente() throws SQLException{
        DAOContaCliente.addContaCliente(this);
    }
    
    //método que retorna uma associação entre um cliente e uma conta e também a senha desta associação
    public static ContaCliente getContaCliente(String cpfCliente, int codigoConta, String senha) throws SQLException{
        return DAOContaCliente.getContaCliente(cpfCliente, codigoConta, senha);
    }
    
    //método que retorna a lista de clientes associados a uma conta
    public List<Cliente> getClientes() throws SQLException{
        return DAOContaCliente.getClientes(this);
    }
    
    //método que verifica se uma conta é ou não conjunta
    public boolean contaIsConjunta() throws SQLException{
        return (this.getClientes().size() > 1); 
    }
    
    //método que retorna o cpf do cliente em uma associação
    public String getCpfCliente(){
        return this.cpfCliente;
    }
    
    //método que retorna o código da conta de uma associação
    public int getCodigoConta(){
        return this.codigoConta;
    }
    
    //método que retorna a senha de uma associação
    public String getSenha(){
        return this.senha;
    }
    
}
