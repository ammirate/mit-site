/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.manager;

import it.unisa.offerta_formativa.beans.Person;
import it.unisa.offerta_formativa.beans.Professor;
import java.util.ArrayList;

/**
 *
 * @author Alessandro
 */
public class ProfessorManager {
    private static ProfessorManager instance=null;
    
    private ProfessorManager(){
    }
    
    public static ProfessorManager getInstance(){
        if(instance==null){
            instance= new ProfessorManager();
        }
        return instance;
    }
    
    public ArrayList<Person> getProfessorByDepartment(String abbreviation){
        ArrayList<Person> toRet = new ArrayList<Person>();
        Person p= new Person();
        p.setName("Andrea De Lucia");
        toRet.add(p);
        p.setName("Filomena Ferrucci");
        toRet.add(p);
        return toRet;
    }
    
    
    
}
