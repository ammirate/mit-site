/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.manager;

import it.unisa.offerta_formativa.beans.ClassPartition;
import it.unisa.offerta_formativa.beans.Teaching;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alessandro
 */
public class ClassManager {
    private final Connection conn;
    private static String TABLE = "class";
    private Statement stmt;
    private ResultSet rs;
    
    private static ClassManager instance = null;
    
    private ClassManager() {
        conn = DBConnector.getConnection();
        if(conn == null){
        	throw new RuntimeException("Unable to connect to the DB");
        }
        try {
			stmt = conn.createStatement();
		} catch (SQLException ex) {
			ex.printStackTrace();
        	throw new RuntimeException("Statement creation failed");
		}
    }
    
    
    
    /**
     * Create a new ClassPartition record
     * @param classp - An instance of the ClassPartition bean
     * @return true if created
     */
    public boolean createClass(ClassPartition classp){
        try {
            if(stmt.executeUpdate("INSERT INTO "+TABLE+"(teaching_matricula,title) VALUES("+classp.toStringQueryInsert()+")")==1){
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Query failed to create a class");
        }
        return false;
    }
    
    /**
     * Read the ClassPartition entity with the given idClass
     * @param idClass of ClassPartition entity
     * @return ClassPartition read. empty instance if not found
     */
    public ClassPartition readClass(String teachingMatricula, String title){
        String esc = "\'";
        try {
            String query = "SELECT * FROM "+TABLE+" WHERE title=" +esc + title + esc + " and " + 
                  "teaching_matricula" + esc +  teachingMatricula + esc;
            rs = stmt.executeQuery(query);
            while(rs.next())
            	return new ClassPartition(rs.getString("teaching_matricula"),rs.getString("title"));
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Query failed to read a class");
        }
        return new ClassPartition();
    }
    
    /**
     * Updates the record with the given ClassPartition id and the given changes into the bean.
     * @param classp - Instance of ClassPartition bean
     * @return true if done.
     */
    public boolean updateClass(ClassPartition classp){
        String esc = "\'";
        try {
            String query = "UPDATE "+TABLE+" SET "+classp.toString() + "WHERE title=" +esc + classp.getTitle() + esc + " and " + 
                  "teaching_matricula" + esc +  classp.getMatricula() + esc;
            if(stmt.executeUpdate(query)==1)return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Query failed to update class");
        }
        return false;
    }
    
    /**
     * Delete the record from DB with the given id
     * @param idClass of ClassPartition bean
     * @return true if done.
     */
    public boolean deleteClass(String teachingMatricula, String title){
        String esc = "\'";
        try {
            String query = "DELETE FROM "+TABLE+" WHERE title=" +esc + title + esc + " and " + 
                  "teaching_matricula" + esc +  teachingMatricula + esc;
            if(stmt.executeUpdate(query)==1){
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Query failed to delete class");
        }
        return false;   
    }
    
    
    /**
     * Return an instance if it exists. Otherwise creates it.
     * 
     */
    public static ClassManager getInstance(){
    	if(instance == null){
    		instance = new ClassManager();
    	}
    	return instance;
    }

    /**
     * Given an idTeaching returns a collection of related ClassPartition
     * @param idTeaching of the Teaching entity
     * @return an ArrayList of ClassPartition. Empty if not found any.
     */
	public ArrayList<ClassPartition> getClassesByTeaching(String idTeaching) {
		// TODO Auto-generated method stub
		ArrayList<ClassPartition> toReturn = new ArrayList<ClassPartition>();
		if(idTeaching==null){
			throw new IllegalArgumentException("Id cannot be null!");
		}else{
			try {
                     String query = "SELECT * FROM "+TABLE+" WHERE teaching_matricula=\""+idTeaching+"\"";
	            rs = stmt.executeQuery(query);
	            while(rs.next()){
	            	toReturn.add(new ClassPartition(idTeaching, rs.getString("title")));
	            }  
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            throw new RuntimeException("Query fail");
	        }
		}
        return toReturn;
	}


	/**
     * Returns all the inserted Classes
     * @return an ArrayList of ClassPartition. Empty if not found any.
     */
	public ArrayList<ClassPartition> getAllClasses() {
		ArrayList<ClassPartition> toReturn = new ArrayList<ClassPartition>();
		try {
            rs = stmt.executeQuery("SELECT * FROM "+TABLE);
            while(rs.next()){
            	toReturn.add(new ClassPartition(rs.getString("teaching_matricula"),rs.getString("title")));
            }  
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Query fail");
        }
        return toReturn;
	}
}
