/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.model;

/**
 *
 * @author Antonio
 */
public class Cycle implements Comparable<Cycle> {

    private int number;
    private String title;

    public Cycle() {
    }

    public Cycle(int id, String title) {
        if (id <= 0) {
            throw new IllegalArgumentException("id cannot be less than 1");
        }
        if (title.equalsIgnoreCase("")) {
            throw new IllegalArgumentException("title cannot be void");
        }
        this.number = id;
        this.title = title;
    }

    public int getNumber() {
        return number;
    }

    /**
     * Adapter
     *
     * @return
     */
    public int getCycleNumber() {
        return getNumber();
    }

    public void setNumber(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("id cannot be less than 1");
        }
        this.number = number;
    }

    /**
     * adapter
     *
     * @param number
     */
    public void setCycleNumber(int number) {
        this.setNumber(number);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title.equalsIgnoreCase("")) {
            throw new IllegalArgumentException("title cannot be void");
        }
        this.title = title;
    }

    public String getInsertQuery() {
        String esc = "\'";
        return number + " , " + esc + title + esc;
    }

    @Override
    public String toString() {
        String esc = "\'";

        return "cycle_number=" + number + ", title=" + esc + title + esc;
    }

    @Override
    public int compareTo(Cycle o) {
        int result;
        if (this.getNumber() > o.getNumber()) {
            result = 1;
        } else if (this.getNumber() == o.getNumber()) {
            result = 0;
        } else {
            result = -1;
        }
        return result;
    }

}
