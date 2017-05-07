/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import DAO.UserDAO;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Salle
 */
@Entity
public class User {
    @Id
    @GeneratedValue
    private int userId;
    private Nota nota;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private String esporteFavorito = "Surf";
    @OneToMany
    private List<User> amigos;

    public User(String nome, String endereco, String telefone, String email) {
        this.nota = new Nota(userId);
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
    }

    public int getId() {
        return userId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereço) {
        this.endereco = endereço;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEsporteFavorito() {
        return esporteFavorito;
    }
    
    public long getNotaConhecido() {
        return this.nota.getNotaConhecido();
    }
    
    public long getNotaHospedagem() {
        return this.nota.getNotaHospedagem();
    }
    
    public long getNotaEsporte() {
        return this.nota.getNotaEsporte();
    }
    
    public void setNotaConhecido(long nota) {
        this.nota.setNotaConhecido(nota);
    }
    
    public void setNotaHospedagem(long nota) {
        this.nota.setNotaHospedagem(nota);
    }
    
    public void setNotaEsporte(long nota) {
        this.nota.setNotaEsporte(nota);
    }
    
    public List<User> getAmigos() {
        return amigos;
    }
    
    public void addAmigo(User user) {
        this.amigos.add(user);
    }

    public void addUser() throws SQLException {
        UserDAO.criaUser(this);
    }
    
}
