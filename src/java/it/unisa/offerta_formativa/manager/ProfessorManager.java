/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.manager;
    
    private static String TABLE = "professor";
    private static String PKEY="email";
    private Connection conn=null;
    private Statement stmt;
    private ResultSet rs;
    
    private static ProfessorManager instance = null;

/**
 *
 * @author Alessandro
 */
public class ProfessorManager {
    
    public static ProfessorManager getInstance(){
        if(instance==null){
            instance= new ProfessorManager();
        }
        return instance;
    }
    
}
