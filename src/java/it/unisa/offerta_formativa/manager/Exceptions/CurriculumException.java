/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.manager.Exceptions;

/**
 *
 * @author tony
 */
public class CurriculumException extends Exception{
    
    public CurriculumException(String s){
        super(s);
    }

    public CurriculumException() {
        super();
    }
    
}
