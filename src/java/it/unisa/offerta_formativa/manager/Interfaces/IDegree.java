
package it.unisa.offerta_formativa.manager.Interfaces;

import it.unisa.offerta_formativa.beans.Degree;
import it.unisa.offerta_formativa.manager.DegreeManager;

/**
 *
 * @author tony
 */
public interface IDegree {
    
    
    public boolean createDegree(Degree degree);
    public boolean updateDegree(Degree degree);
    public Degree deleteDegree(String srialNumber);
    public Degree readDegree(String srialNumber);
    
}
