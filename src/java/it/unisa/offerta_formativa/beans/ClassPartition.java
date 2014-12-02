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
    private String teachingSN = "";

    public ClassPartition() {

    }

    public ClassPartition(String idTeaching, String titolo) {
        if (idTeaching != null && titolo != null) {
            if (idTeaching.equals("") || titolo.equals("")) {
                throw new IllegalArgumentException("Parameters cannot be void");
            }
            this.title = titolo;
        } else {
            throw new IllegalArgumentException("Parameters cannot be null");
        }
    }

    public String getTeacgingSN() {
        return teachingSN;
    }

    public void setTeachingSN(String idTeaching) {
        if (idTeaching != null) {
            if (!idTeaching.equals("")) {
                this.teachingSN = idTeaching;
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
        return "idTeaching=\"" + teachingSN + "\", title=\"" + title + "\"";
    }

    public String toStringQueryInsert() {
        return "\"" + teachingSN + "\", \"" + title + "\"";
    }

    @Override
    public boolean equals(Object arg0) {
        // TODO Auto-generated method stub
        ClassPartition toComp = (ClassPartition) arg0;
        if (this.teachingSN.equalsIgnoreCase(toComp.getTeacgingSN())
                && this.getTitle().equals(toComp.getTitle())) {
            return true;
        } else {
            return false;
        }
    }

}
