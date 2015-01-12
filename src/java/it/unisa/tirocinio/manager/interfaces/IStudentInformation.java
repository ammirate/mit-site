/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.tirocinio.manager.interfaces;

import it.unisa.integrazione.model.Person;
import it.unisa.tirocinio.beans.StudentInformation;
import it.unisa.tirocinio.beans.StudentStatus;
import java.util.ArrayList;

/**
 *
 * @author johneisenheim
 */
public interface IStudentInformation {
    
    public boolean startTrainingRequest( String studentSSN, String CVPath, String ATPath );
    public boolean deleteStudentInformation( Person aStudent );
    public boolean updateStudentInformation( StudentInformation aStudentInformation );
    public boolean changeStudentStatus( String studentSSN, StudentStatus newStatus );
    public StudentInformation readStudentInformation( String studentSSN );
    public ArrayList<StudentInformation> getAllStudentInformations();
    
}
