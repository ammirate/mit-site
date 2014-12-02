
package it.unisa.offerta_formativa.manager.Interfaces;

import it.unisa.offerta_formativa.beans.Person;


/**
 *
 * @author tony
 */
public interface IPerson {
    
    
    public boolean createPerson(Person person);
    public boolean updatePerson(Person person);
    public Person deletePerson(String fiscalCode);
    public Person readPerson(String fiscalCode);
    
}
