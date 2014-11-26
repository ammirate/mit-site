package it.unisa.offerta_formativa.manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import it.unisa.offerta_formativa.beans.Curriculum;

public class CurriculumManager {
	private Connection conn=null;
    private Statement stmt;
    public static String TABLE="curriculum";
    public static String PKEY="id";
    private ResultSet rs = null;
    
    private static CurriculumManager instance = null;
    
    /**
     * Constructor for Singleton pattern
     */
    private CurriculumManager(){
        conn = DBConnector.getConnection();
        if(conn == null){
        	throw new RuntimeException("Unable to connect to the DB");
        }
    }
    
    /**
     * Insert a Curriculum into the DB
     * @param curriculum to insert into the DB
     * @return true if the insertion was successfull
     */
    public boolean createCurriculum(Curriculum curr){
        try {
            stmt = conn.createStatement();
            String query = "INSERT INTO " + TABLE + "(title,idDegree) VALUES(" +curr.toStringQueryInsert()+")";
            if(stmt.executeUpdate(query) == 1){
            	return true;
            } 
        } catch (SQLException ex) {
        	ex.printStackTrace();
        	throw new RuntimeException("Insertion Query failed!");
        }
        return false;
    }
    
    
    /**
     * Update a curriculum into the DB
     * @param curriculum to update into the DB
     * @return true if the update was successfull
     */
    public boolean updateCurriculum(Curriculum curr){
        try {
            stmt = conn.createStatement();
            if(stmt.executeUpdate("UPDATE " + TABLE +" SET " + curr.toString() + " WHERE " 
            + PKEY + "=" + curr.getId()) == 1){
            	return true;
            }
        } catch (SQLException ex) {
        	ex.printStackTrace();
        	throw new RuntimeException("Update Query failed!");
        }
        return false;
    }
    
    
    /**
     * Read a given curriculum from the DB
     * @param id of the curriculum
     * @return a curriculum object if it is present in the DB, else empty curriculum bean
     */
    public Curriculum readCurriculum(int id){
    	if(id<=0){
			throw new IllegalArgumentException("Can't read a degree from the Database using id less than one");
		}else{
	        try {
	            stmt = conn.createStatement();
	            rs= stmt.executeQuery("SELECT * FROM " + TABLE 
	            		+ " WHERE " + PKEY + "=\"" + id+"\"");
	            while(rs.next()) {
	            	return new Curriculum(rs.getInt("id"),rs.getString("title"),rs.getString("idDegree"));
	            } 
	        } catch (SQLException ex) {
	        	ex.printStackTrace();
	        	throw new RuntimeException("Read Query failed!");
	        }
		}
	    return new Curriculum();
    }
    

	/**
     * Delete a given curriculum from the DB
     * @param id of curriculum
     * @return true if deleted.
     */
    public boolean deleteCurriculum(int id){
        try {
            stmt = conn.createStatement();
            if(stmt.executeUpdate("DELETE FROM " + TABLE + 
            		" WHERE " + PKEY + "=\"" + id + "\"") == 1){
            	return true;
            }
        } catch (SQLException ex) {
        	ex.printStackTrace();
        	throw new RuntimeException("Delete Query failed!");
        }
        return false;
    }
    
    
    /**
     * Get all the Curriculum in the lists
     * @return an ArrayList of Curriculum
     */
    public ArrayList<Curriculum> getAllDepartments(){
		ArrayList<Curriculum> toReturn = new ArrayList<Curriculum>();
        try {
			stmt = conn.createStatement();
			rs= stmt.executeQuery("SELECT * FROM " + TABLE);
           
			while(rs.next()) {
				Curriculum b = getCurriculumFromResultSet(rs);
				toReturn.add(b);
            } 
            
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return toReturn;
    }
    
    
    /**
	 * Insert all the curriculum in the list into the Database
	 * @param list is the list of curriculum to insert
	 * @return the list of curriculum which had an error during the insertion process. 
	 * So, check if this list is empty to make sure of the insertion process.
	 */
	public ArrayList<Curriculum> insertCurriculum(ArrayList<Curriculum> list){
		ArrayList<Curriculum> notInserted = new ArrayList<Curriculum>();
		
		for(Curriculum b : list){
			if(!createCurriculum(b)){
				notInserted.add(b);
			}
		}
		return notInserted;
	}
    
    /**
     * used to get the unique instance of this class
     */
    public static CurriculumManager getInstance(){
    	if(instance == null){
    		instance = new CurriculumManager();
    	}
    	return instance;
    }
    
    /**
     * Create a Curriculum from a ResultSet object
     * @param rs
     * @return
     */
    private Curriculum getCurriculumFromResultSet(ResultSet rs){
    	try {
			String tit = rs.getString("title");
			int id = rs.getInt("id");
			String idDegree = rs.getString("idDegree");
	    	return new Curriculum(id, tit, idDegree);
		} 
    	catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
    }

	public ArrayList<Curriculum> getCurriculumByDegree(String degree) {
		// TODO Auto-generated method stub
		ArrayList<Curriculum> toReturn = new ArrayList<Curriculum>();
        try {
			stmt = conn.createStatement();
			rs= stmt.executeQuery("SELECT * FROM " + TABLE + "WHERE idDegree=\""+degree+"\"");
           
			while(rs.next()) {
				Curriculum b = getCurriculumFromResultSet(rs);
				toReturn.add(b);
            } 
            
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return toReturn;
	}
}
