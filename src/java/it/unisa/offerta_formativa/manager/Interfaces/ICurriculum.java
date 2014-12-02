
package it.unisa.offerta_formativa.manager.Interfaces;

import it.unisa.offerta_formativa.beans.Curriculum;


/**
 *
 * @author tony
 */
public interface ICurriculum {
    
    
    public boolean createCurriculum(Curriculum curriculum);
    public boolean updateCurriculum(Curriculum curriculum);
    public Curriculum deleteCurriculum(String id, String title);
    public Curriculum readCurriculum(String id, String title);
    
}
