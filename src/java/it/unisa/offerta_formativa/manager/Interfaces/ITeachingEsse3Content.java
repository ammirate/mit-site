
package it.unisa.offerta_formativa.manager.Interfaces;

import it.unisa.offerta_formativa.beans.TeachingEsse3Content;



/**
 *
 * @author tony
 */
public interface ITeachingEsse3Content {
    
    
    public boolean createTeachingContent(TeachingEsse3Content cycle);
    public boolean updateTeachingContent(TeachingEsse3Content cycle);
    public TeachingEsse3Content deleteTeachingContent(String id);
    public TeachingEsse3Content readTeachingContent(String id);
    
}
