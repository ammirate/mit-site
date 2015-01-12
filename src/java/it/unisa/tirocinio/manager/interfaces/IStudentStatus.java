/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.tirocinio.manager.interfaces;

import it.unisa.tirocinio.beans.StudentStatus;
import java.util.ArrayList;

/**
 *
 * @author johneisenheim
 */
public interface IStudentStatus {
    
    public boolean createStudentStatus( StudentStatus aStudentStatus );
    public StudentStatus readStudentStatus( int idStudentStatus );
    public ArrayList<StudentStatus> getAllStudentStatus();
    
}
