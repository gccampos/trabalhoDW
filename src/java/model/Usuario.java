/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import DAO.UsuarioDAO;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.jboss.weld.util.LazyValueHolder.Serializable;

/**
 *
 * @author Salle
 */
@Entity
@Table(name = "USUARIO")
public class Usuario extends Serializable {
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
    private List<Usuario> amigos;

    public Usuario(String nome, String endereco, String telefone, String email) {
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
    
    public List<Usuario> getAmigos() {
        return amigos;
    }
    
    public void addAmigo(Usuario user) {
        this.amigos.add(user);
    }

    public void addUsuario() throws SQLException {
        UsuarioDAO.criaUsuario(this);
    }

    @Override
    protected Object computeValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
