package manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import beans.ProfModuleClass;

/**
 * 
 * @author Alessandro
 *
 */
public class ProfModuleClassManager {
	private static String TABLE = "prof_module_class";
	private Connection conn=null;
    private Statement stmt;
    private ResultSet rs;
    
    private static ProfModuleClassManager instance=null;
    
    private ProfModuleClassManager(){
    	conn = DBConnector.getConnection();
        if(conn == null){
        	throw new RuntimeException("Unable to connect to the DB");
        }
        try {
			stmt = conn.createStatement();
		} catch (SQLException ex) {
			ex.printStackTrace();
        	throw new RuntimeException("Statement Creation failed");
		}
    }
    
    public boolean create(ProfModuleClass pmc){
    	try {
            if(stmt.executeUpdate("INSERT INTO "+TABLE+"(idProfessor,idClass,idModule) VALUES("+pmc.toStringInsertQuery()+")")==1){
            	return true;
            }
        } catch (SQLException ex) {
        	ex.printStackTrace();
        	throw new RuntimeException("Insert Query Failed");
        }
        return false;
    }
    
    
    
    public static ProfModuleClassManager getInstance(){
    	if(instance==null)instance=new ProfModuleClassManager();
    	return instance;
    }
    
    
}
