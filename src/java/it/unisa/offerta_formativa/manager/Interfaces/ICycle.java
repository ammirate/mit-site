
package it.unisa.offerta_formativa.manager.Interfaces;

import it.unisa.offerta_formativa.beans.Cycle;


/**
 *
 * @author tony
 */
public interface ICycle {
    
    
    public boolean createCycle(Cycle cycle);
    public boolean updateCycle(Cycle cycle);
    public Cycle deleteCycle(String id);
    public Cycle readCycle(String id);
    
}
