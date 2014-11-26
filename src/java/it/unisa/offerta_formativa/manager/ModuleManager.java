package it.unisa.offerta_formativa.manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import it.unisa.offerta_formativa.beans.Module;
import it.unisa.offerta_formativa.beans.Teaching;

/**
 * 
 * @author Alessandro
 *
 */
public class ModuleManager {
	
	private Connection conn;
	private Statement stmt;
	private static String TABLE = "module";
	private static String PKEY="idModule";
	private ResultSet rs;
	
	private static ModuleManager instance = null;
	
	private ModuleManager(){
		conn = DBConnector.getConnection();
        if(conn == null){
        	throw new RuntimeException("Unable to connect to the DB");
        }
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Create a new module DB record
	 * @param module bean
	 * @return true if done.
	 */
	public boolean createModule(Module m){
		try {
			if(stmt.executeUpdate("INSERT INTO "+TABLE+"(idTeaching,title) VALUES("+m.toStringQueryInsert()+")")==1){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Get the module with the given id
	 * @param idModule
	 * @return Module bean read. Empty if not found any.
	 */
	public Module readModule(int idModule){
		try {
			rs=stmt.executeQuery("SELECT * FROM "+TABLE+" WHERE "+PKEY+"="+idModule);
			while(rs.next())
				return new Module(rs.getInt("idModule"),rs.getString("idTeaching"),rs.getString("title"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Module();
	}
	
	/**
	 * Update Module information with the given Module bean
	 * @param module bean
	 * @return true if done.
	 */
	public boolean updateModule(Module m){
		try {
			if(stmt.executeUpdate("UPDATE "+TABLE+" SET "+m.toString()+" WHERE "+PKEY+"="+m.getIdModule())==1){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
	/**
	 * Delete the record with the given idModule field
	 * @param idModule
	 * @return true if deleted.
	 */
	public boolean deleteModule(int idModule){
		try {
			if(stmt.executeUpdate("DELETE FROM "+TABLE+" WHERE "+PKEY+"="+idModule)==1){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static ModuleManager getInstance(){
		if(instance == null){
    		instance = new ModuleManager();
    	}
    	return instance;
	}

	/**
	 * Given the idTeaching returns a collection of related Modules
	 * @param idTeaching
	 * @return ArrayList<Module>, empty if it has not found any
	 */
	public ArrayList<Module> getModulesByTeaching(String idTeaching) {
		// TODO Auto-generated method stub
		
		ArrayList<Module> toReturn = new ArrayList<Module>();
		if(idTeaching==null){
			throw new IllegalArgumentException("id cannot be null!");
		}else{
			try {
				rs=stmt.executeQuery("SELECT * FROM "+TABLE+" WHERE idTeaching=\""+idTeaching+"\"");
				while(rs.next()){
					toReturn.add(new Module(rs.getInt("idModule"),rs.getString("idTeaching"),rs.getString("title")));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return toReturn;
	}

	/**
	 * Get the collection of Modules
	 * @return ArrayList of Module. Empty if not found any.
	 */
	public ArrayList<Module> getAllModules() {
		// TODO Auto-generated method stub
		ArrayList<Module> toReturn = new ArrayList<Module>();
		try {
			rs=stmt.executeQuery("SELECT * FROM "+TABLE);
			while(rs.next()){
				toReturn.add(new Module(rs.getInt("idModule"),rs.getString("idTeaching"),rs.getString("title")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return toReturn;
	}

	
	
	
	
}
