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
public class Cronologia {
    
    /*
    tipo può valere i seguenti valori: richiesta, accetta, rifiuta, modifica  
    */
    
    private int id_cronologia;
    private String testo, data_notifica, id_studente, id_docente, tipo;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

   

    public Cronologia(int id_cronologia, String testo, String data_notifica, String id_studente,String id_docente,String tipo) {
       
        this.id_cronologia = id_cronologia;
        this.testo = testo;
        this.data_notifica = data_notifica;
        this.id_studente = id_studente;
        this.id_docente = id_docente;
        this.tipo = tipo;
        
    }

    public Cronologia() {
       
    }
    
    public int getId_cronologia() {
        return id_cronologia;
    }

    public void setId_cronologia(int id_cronologia) {
        this.id_cronologia = id_cronologia;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public String getData_notifica() {
        return data_notifica;
    }

    public void setData_notifica(String data_notifica) {
        this.data_notifica = data_notifica;
    }

    public String getId_studente() {
        return id_studente;
    }

    public void setId_studente(String id_studente) {
        this.id_studente = id_studente;
    }

    public String getId_docente() {
        return id_docente;
    }

    public void setId_docente(String id_docente) {
        this.id_docente = id_docente;
    }
    
    
    
}
