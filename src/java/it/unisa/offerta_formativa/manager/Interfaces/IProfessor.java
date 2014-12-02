
package it.unisa.offerta_formativa.manager.Interfaces;

import it.unisa.offerta_formativa.beans.Professor;


/**
 *
 * @author tony
 */
public interface IProfessor {
    
    
    public boolean createProfessor(Professor prof);
    public boolean updateProfessor(Professor prof);
    public Professor deleteProfessor(String id);
    public Professor readProfessor(String id);
    
}
