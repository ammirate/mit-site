/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.tirocinio.manager.interfaces;

import it.unisa.tirocinio.beans.Questionnaire;
import java.util.ArrayList;

/**
 *
 * @author Valentino
 */
public interface IQuestionnaire {
    
    public boolean insertQuestionnaire(Questionnaire aQuestionnaire);
    public Questionnaire readQuestionnaire( String SSN );
    public ArrayList<Questionnaire> readAllQuestionnaire();
    
}
