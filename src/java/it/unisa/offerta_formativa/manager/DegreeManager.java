/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.manager;
import java.sql.Connection;
import java.sql.SQLException;

import it.unisa.offerta_formativa.beans.Degree;
import it.unisa.offerta_formativa.beans.Teaching;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alessandro
 */
public class DegreeManager {
	
    private Connection conn=null;
    private Statement stmt;
    public static String TABLE="degree";
    public static String PKEY="matricula";
    private ResultSet rs = null;
    
    private static DegreeManager instance = null;
    
    /**
     * Constructor for Singleton pattern
     */
    private DegreeManager(){
        conn = DBConnector.getConnection();
        if(conn == null){
        	throw new RuntimeException("Unable to connect to the DB");
        }
    }
    
    /**
     * Insert a Degree into the DB
     * @param degree to insert into the DB
     * @return true if the insertion was successfull
     */
    public boolean createDegree(Degree degree){
        try {
            stmt = conn.createStatement();
            String query = "INSERT INTO " + TABLE + 
            		"(matricula,link,title,cycle,idDepartment) VALUES(" +
            		degree.toStringQueryInsert();
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
     * Update a Degree into the DB
     * @param degree to update into the DB
     * @return true if the update was successfull
     */
    public boolean updateDegree(Degree degree){
        try {
            stmt = conn.createStatement();
            if(stmt.executeUpdate("UPDATE " + TABLE +
            		" SET " + degree.toString() + " WHERE "
            		+ PKEY + "=\"" + degree.getMatricula() + "\"") == 1){
            	return true;
            }
        } catch (SQLException ex) {
        	ex.printStackTrace();
        	throw new RuntimeException("Update Query failed!");
        }
        return false;
    }
    
    
    /**
     * Read a given Degree from the DB
     * @param matricola id the matricula of the degree
     * @return a Degree object if it is present in the DB, else empty Degree bean
     */
    public Degree readDegree(String matricula){
    	if(matricula == null || matricula.equals("")){
			throw new IllegalArgumentException("Can't read a degree from the Database using an empty matricula");
		}else{
	        try {
	            stmt = conn.createStatement();
	            rs= stmt.executeQuery("SELECT * FROM " + TABLE 
	            		+ " WHERE " + PKEY + "=\"" + matricula+"\"");
	            while(rs.next()) {
	            	return new Degree(rs.getString("matricula"),rs.getString("link"),rs.getString("title"),rs.getInt("cycle"),rs.getInt("idDepartment"));
	            } 
	        } catch (SQLException ex) {
	        	ex.printStackTrace();
	        	throw new RuntimeException("Read Query failed!");
	        }
		}
	    return new Degree();
    }
    

	/**
     * Delete a given Degree from the DB
     * @param matricola of the degree
     * @return true if deleted.
     */
    public boolean deleteDegree(String matricula){
        try {
            stmt = conn.createStatement();
            if(stmt.executeUpdate("DELETE FROM " + TABLE + 
            		" WHERE " + PKEY + "=\"" + matricula + "\"") == 1){
            	return true;
            }
        } catch (SQLException ex) {
        	ex.printStackTrace();
        	throw new RuntimeException("Delete Query failed!");
        }
        return false;
    }
    
    
    /**
     * Get all the Degrees in the lists
     * @return an ArrayList of Degrees
     */
    public ArrayList<Degree> getAllDegrees(){
		ArrayList<Degree> toReturn = new ArrayList<Degree>();
        try {
			stmt = conn.createStatement();
			rs= stmt.executeQuery("SELECT * FROM " + TABLE);
           
			while(rs.next()) {
				Degree b = getDegreeFromResultSet(rs);
				toReturn.add(b);
            } 
            
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return toReturn;
    }
    
    
    /**
	 * Insert all the degrees in the list into the Database
	 * @param list is the list of degrees to insert
	 * @return the list of degrees which had an error during the insertion process. 
	 * So, check if this list is empty to make sure of the insertion process.
	 */
	public ArrayList<Degree> insertDegree(ArrayList<Degree> list){
		ArrayList<Degree> notInserted = new ArrayList<Degree>();
		
		for(Degree b : list){
			if(!createDegree(b)){
				notInserted.add(b);
			}
		}
		return notInserted;
	}
    
    
    /**
     * Get all the degrees in the lists with a given cycle
     * @return ArrayList containing all the related Degrees
     */
    public ArrayList<Degree> getDegreesByCycle(int cycle){
		ArrayList<Degree> toReturn = new ArrayList<Degree>();
		if(cycle<1 || cycle>3){
			throw new IllegalArgumentException("Cycle must be between 1 an 3");
		}else{
	        try {
				stmt = conn.createStatement();
				rs= stmt.executeQuery("SELECT * FROM " + TABLE + " WHERE CYCLE=" + cycle);
	           
				while(rs.next()) {
					Degree b = getDegreeFromResultSet(rs);
					toReturn.add(b);
	            } 
	            
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
        return toReturn;
        
    }
    
    /**
     * used to get the unique instance of this class
     */
    public static DegreeManager getInstance(){
    	if(instance == null){
    		instance = new DegreeManager();
    	}
    	return instance;
    }
    
    /**
     * Create a Degree from a ResultSet object
     * @param rs
     * @return
     */
    private Degree getDegreeFromResultSet(ResultSet rs){
    	try {
			String tit = rs.getString("title");
			String matr = rs.getString("matricula");
			String link = rs.getString("link");
			int cycle = rs.getInt("cycle");
			int dpt = rs.getInt("idDepartment");
	
	    	return new Degree(matr, link, tit, cycle,dpt);
		} 
    	catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
    }
    

}
