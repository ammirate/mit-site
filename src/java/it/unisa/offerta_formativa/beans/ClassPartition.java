/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.beans;

/**
 *
 * @author Alessandro, Antonio
 */
public class ClassPartition {

    private String title = "";
    private String teachingMatricula = "";

    public ClassPartition() {

    }

    /**
     * Constructor
     * @param teachingMatricula
     * @param titolo 
     */
    public ClassPartition(String teachingMatricula, String titolo) {
        if (teachingMatricula != null && titolo != null) {
            if (teachingMatricula.equals("") || titolo.equals("")) {
                throw new IllegalArgumentException("Parameters cannot be void");
            }
            this.title = titolo;
            this.teachingMatricula = teachingMatricula;
        } else {
            throw new IllegalArgumentException("Parameters cannot be null");
        }
    }

    
    public String getTeachingMatricula() {
        return teachingMatricula;
    }

    /**
     * 
     * @param idTeaching 
     */
    public void setTeachingMatricula(String idTeaching) {
        if (idTeaching != null) {
            if (!idTeaching.equals("")) {
                this.teachingMatricula = idTeaching;
            } else {
                throw new IllegalArgumentException("id teaching cannot be void");
            }
        } else {
            throw new IllegalArgumentException("id teaching cannot be null");
        }
    }

    public String getTitle() {
        return title;
    }

    
    /**
     * 
     * @param title 
     */
    public void setTitle(String title) {
        if (title != null) {
            if (!title.equals("")) {
                this.title = title;
            } else {
                throw new IllegalArgumentException("title cannot be void");
            }
        } else {
            throw new IllegalArgumentException("title cannot be null");
        }
    }


    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "teaching_matricula=\"" + teachingMatricula + "\", title=\"" + title + "\"";
    }

    public String toStringQueryInsert() {
        String esc = "\'";
        return esc+ teachingMatricula + esc + "," + esc + title + esc;
    }

    @Override
    public boolean equals(Object arg0) {
        // TODO Auto-generated method stub
        ClassPartition toComp = (ClassPartition) arg0;
        if (this.teachingMatricula.equalsIgnoreCase(toComp.getTeachingMatricula())
                && this.getTitle().equals(toComp.getTitle())) {
            return true;
        } else {
            return false;
        }
    }

}
