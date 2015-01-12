/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.tirocinio.beans;

import it.unisa.integrazione.model.Person;
import java.util.ArrayList;


/**
 *
 * @author carlosborges
 */
public class ViewsResult {

    public ArrayList<Person> persons;//TODO modificare quando integrato
    public int count;

    public ViewsResult() {
        this.persons = new ArrayList<Person>();//TODO modificare quando integrato
    }

    public ViewsResult(int count) {//TODO modificare quando integrato
        this.persons = new ArrayList<Person>();
        this.count = count;
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

    /**
     *
     * @param i index of array
     * @return
     */
    public Person getPerson(int i) {
        return persons.get(i);
    }

    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }

    public void addPerson(Person aPerson) {
        persons.add(aPerson);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
