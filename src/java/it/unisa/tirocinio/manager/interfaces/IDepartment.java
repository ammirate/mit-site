/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.tirocinio.manager.interfaces;

import it.unisa.integrazione.model.Department;
import java.util.ArrayList;

/**
 *
 * @author johneisenheim
 */
public interface IDepartment {
   
    public Department readDepartment( String department );
    public ArrayList<Department> getAllDepartments();
    
}
