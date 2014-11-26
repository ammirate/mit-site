/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;
import java.sql.Connection;
import java.sql.SQLException;

import beans.Degree;
import beans.Department;
import beans.Teaching;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alessandro
 */
public class DepartmentManager {
	
    private Connection conn=null;
    private Statement stmt;
    public static String TABLE="department";
    public static String PKEY="id";
    private ResultSet rs = null;
    
    private static DepartmentManager instance = null;
    
    /**
     * Constructor for Singleton pattern
     */
    private DepartmentManager(){
        conn = DBConnector.getConnection();
        if(conn == null){
        	throw new RuntimeException("Unable to connect to the DB");
        }
    }
    
    /**
     * Insert a Department into the DB
     * @param department to insert into the DB
     * @return true if the insertion was successfull
     */
    public boolean createDepartment(Department dept){
        try {
            stmt = conn.createStatement();
            String query = "INSERT INTO " + TABLE + "(title,urlMoodle,token) VALUES(" +dept.toStringQueryInsert()+")";
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
     * Update a department into the DB
     * @param department to update into the DB
     * @return true if the update was successfull
     */
    public boolean updateDepartment(Department dept){
        try {
            stmt = conn.createStatement();
            if(stmt.executeUpdate("UPDATE " + TABLE +" SET " + dept.toString() + " WHERE " 
            + PKEY + "=" + dept.getId()) == 1){
            	return true;
            }
        } catch (SQLException ex) {
        	ex.printStackTrace();
        	throw new RuntimeException("Update Query failed!");
        }
        return false;
    }
    
    
    /**
     * Read a given department from the DB
     * @param id of the department
     * @return a department object if it is present in the DB, else empty department bean
     */
    public Department readDepartment(int id){
    	if(id<=0){
			throw new IllegalArgumentException("Can't read a degree from the Database using id less than one");
		}else{
	        try {
	            stmt = conn.createStatement();
	            rs= stmt.executeQuery("SELECT * FROM " + TABLE 
	            		+ " WHERE " + PKEY + "=\"" + id+"\"");
	            while(rs.next()) {
	            	return new Department(rs.getInt("id"),rs.getString("title"),rs.getString("urlMoodle"),rs.getString("token"));
	            } 
	        } catch (SQLException ex) {
	        	ex.printStackTrace();
	        	throw new RuntimeException("Read Query failed!");
	        }
		}
	    return new Department();
    }
    

	/**
     * Delete a given department from the DB
     * @param id of department
     * @return true if deleted.
     */
    public boolean deleteDepartment(int id){
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
     * Get all the Department in the lists
     * @return an ArrayList of Department
     */
    public ArrayList<Department> getAllDepartments(){
		ArrayList<Department> toReturn = new ArrayList<Department>();
        try {
			stmt = conn.createStatement();
			rs= stmt.executeQuery("SELECT * FROM " + TABLE);
           
			while(rs.next()) {
				Department b = getDepartmentFromResultSet(rs);
				toReturn.add(b);
            } 
            
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return toReturn;
    }
    
    
    /**
	 * Insert all the department in the list into the Database
	 * @param list is the list of department to insert
	 * @return the list of department which had an error during the insertion process. 
	 * So, check if this list is empty to make sure of the insertion process.
	 */
	public ArrayList<Department> insertDepartment(ArrayList<Department> list){
		ArrayList<Department> notInserted = new ArrayList<Department>();
		
		for(Department b : list){
			if(!createDepartment(b)){
				notInserted.add(b);
			}
		}
		return notInserted;
	}
    
    /**
     * used to get the unique instance of this class
     */
    public static DepartmentManager getInstance(){
    	if(instance == null){
    		instance = new DepartmentManager();
    	}
    	return instance;
    }
    
    /**
     * Create a Department from a ResultSet object
     * @param rs
     * @return
     */
    private Department getDepartmentFromResultSet(ResultSet rs){
    	try {
			String tit = rs.getString("title");
			int id = rs.getInt("id");
			String url = rs.getString("urlMoodle");
			String token = rs.getString("token");
	    	return new Department(id, tit, url, token);
		} 
    	catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
    }
    

}
