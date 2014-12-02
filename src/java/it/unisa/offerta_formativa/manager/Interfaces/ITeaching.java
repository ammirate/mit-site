
package it.unisa.offerta_formativa.manager.Interfaces;

import it.unisa.offerta_formativa.beans.Teaching;
import it.unisa.offerta_formativa.manager.TeachingManager;

/**
 *
 * @author tony
 */
public interface ITeaching {
    
    
    public boolean createTeaching(Teaching teaching);
    public boolean updateTeaching(Teaching teaching);
    public Teaching deleteTeaching(String srialNumber);
    public Teaching readTeaching(String srialNumber);
    
}
