
package it.unisa.offerta_formativa.manager.Interfaces;

import it.unisa.offerta_formativa.beans.ClassPartition;


/**
 *
 * @author tony
 */
public interface IClassPartition {
    
    
    public boolean createClassPartition(ClassPartition _class);
    public boolean updateClassPartition(ClassPartition _class);
    public ClassPartition deleteClassPartition(String id);
    public ClassPartition readClassPartition(String id);
    
}
