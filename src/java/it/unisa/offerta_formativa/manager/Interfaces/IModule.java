
package it.unisa.offerta_formativa.manager.Interfaces;

import it.unisa.offerta_formativa.beans.Module;


/**
 *
 * @author tony
 */
public interface IModule {
    
    
    public boolean createModule(Module module);
    public boolean updateModule(Module module);
    public Module deleteModule(String id);
    public Module readModule(String id);
    
}
