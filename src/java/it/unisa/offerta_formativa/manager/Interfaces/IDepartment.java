
package it.unisa.offerta_formativa.manager.Interfaces;

import it.unisa.offerta_formativa.beans.Department;


/**
 *
 * @author tony
 */
public interface IDepartment {
    
    
    public boolean createDepartment(Department department);
    public boolean updateDepartment(Department department);
    public Department deleteDepartment(String abbreviation);
    public Department readDepartment(String abbreviation);
    
}
