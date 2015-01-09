/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.gestionetesi.beans;

/**
 *
 * @author ciro
 */
public class Allegati {
    
    private int id, stato,idTesi;
    private String linkOggetto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStato() {
        return stato;
    }

    public void setStato(int stato) {
        this.stato = stato;
    }

    public int getIdTesi() {
        return idTesi;
    }

    public void setIdTesi(int idTesi) {
        this.idTesi = idTesi;
    }

    public String getLinkOggetto() {
        return linkOggetto;
    }

    public void setLinkOggetto(String linkOggetto) {
        this.linkOggetto = linkOggetto;
    }
}
