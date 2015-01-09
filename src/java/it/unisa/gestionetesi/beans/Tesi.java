/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.gestionetesi.beans;

/**
 *
 * @author Damiano
 */
public class Tesi {

    private int id_tesi;
    private String data_inizio;
    private String data_fine;
    private String data_fine_prevista;
    private String titolo;
    private String abstract_tesi;
    private String descrizione;
    private String id_studente;
    private String stato_tesi;

    
    public Tesi(int id_tesi, String data_inizio, String data_fine, String data_fine_prevista, String titolo, String abstract_tesi, String descrizione, String id_studente, String stato_tesi) {
        this.id_tesi = id_tesi;
        this.data_inizio = data_inizio;
        this.data_fine = data_fine;
        this.data_fine_prevista = data_fine_prevista;
        this.titolo = titolo;
        this.abstract_tesi = abstract_tesi;
        this.descrizione = descrizione;
        this.id_studente = id_studente;
        this.stato_tesi = stato_tesi;
    }
    
    public Tesi(String descrizione, String id_studente){
        this.descrizione = descrizione;
        this.id_studente=id_studente;
    }

    public int getId_tesi() {
        return id_tesi;
    }

    public void setId_tesi(int id_tesi) {
        this.id_tesi = id_tesi;
    }

    public String getData_inizio() {
        return data_inizio;
    }

    public void setData_inizio(String data_inizio) {
        this.data_inizio = data_inizio;
    }

    public String getData_fine() {
        return data_fine;
    }

    public void setData_fine(String data_fine) {
        this.data_fine = data_fine;
    }

    public String getData_fine_prevista() {
        return data_fine_prevista;
    }

    public void setData_fine_prevista(String data_fine_prevista) {
        this.data_fine_prevista = data_fine_prevista;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getAbstract_tesi() {
        return abstract_tesi;
    }

    public void setAbstract_tesi(String abstract_tesi) {
        this.abstract_tesi = abstract_tesi;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getId_studente() {
        return id_studente;
    }

    public void setId_studente(String id_studente) {
        this.id_studente = id_studente;
    }

    public String getStato_tesi() {
        return stato_tesi;
    }

    public void setStato_tesi(String stato_tesi) {
        this.stato_tesi = stato_tesi;
    }

}
