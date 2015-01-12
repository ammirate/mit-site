/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.tirocinio.manager.interfaces;

import it.unisa.integrazione.model.Person;
import it.unisa.tirocinio.beans.Organization;
import java.util.ArrayList;

/**
 *
 * @author johneisenheim
 */
public interface IOrganization {
    
    public boolean createOrganization( Organization organization );
    public boolean deleteOrganization( String VATNumber );
    public boolean updateOrganization( Organization organization );
    public Organization readOrganization( String VATNumber );
    public Person getProfessorOrganization( String VATNumber );
    public Person getExternalTutor( String VATNumber );
    public Organization getOrganizationByAccount( String accountEmail );
    public ArrayList<Organization> getOwnOrganizations( String professorSSN ); 
    public ArrayList<Organization> getAllOrganizations();
    
}
