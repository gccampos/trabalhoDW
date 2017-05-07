/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;

/**
 *
 * @author Salle
 */
@Entity
public class Nota {
    private List<Long> notaConhecido;
    private List<Long> notaHospedagem;
    private List<Long> notaEsporte;
    private int idUser;
    
    public Nota(int idUser) {
        notaConhecido = new ArrayList<>();
        notaHospedagem = new ArrayList<>();
        notaEsporte = new ArrayList<>();
        this.idUser = idUser;
    }

    public long getNotaConhecido() {
        long media = 0;
        media = notaConhecido.stream().map((nota) -> nota).reduce(media, (accumulator, _item) -> accumulator + _item);
        return media/notaConhecido.size();
    }

    public void setNotaConhecido(long nota) {
        this.notaConhecido.add(nota);
    }

    public long getNotaHospedagem() {
        long media = 0;
        media = notaHospedagem.stream().map((nota) -> nota).reduce(media, (accumulator, _item) -> accumulator + _item);
        return media/notaHospedagem.size();
    
    }

    public void setNotaHospedagem(long nota) {
        this.notaHospedagem.add(nota);
    }

    public long getNotaEsporte() {
        long media = 0;
        media = notaEsporte.stream().map((nota) -> nota).reduce(media, (accumulator, _item) -> accumulator + _item);
        return media/notaEsporte.size();
    }

    public void setNotaEsporte(long nota) {
        this.notaEsporte.add(nota);
    }
    
    public int getIdUser() {
        return idUser;
    }
}
