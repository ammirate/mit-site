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
    private static String PKEY="idClass";
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
            if(stmt.executeUpdate("INSERT INTO "+TABLE+"(idTeaching,title) VALUES("+classp.toStringQueryInsert()+")")==1)return true;
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
    public ClassPartition readClass(int idClass){
        try {
            rs = stmt.executeQuery("SELECT * FROM "+TABLE+" WHERE "+PKEY+"="+idClass);
            while(rs.next())
            	return new ClassPartition(rs.getInt("idClass"),rs.getString("idTeaching"),rs.getString("title"));
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
        try {
            if(stmt.executeUpdate("UPDATE "+TABLE+" SET "+classp.toString() + "WHERE "+PKEY+"="+classp.getIdClass())==1)return true;
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
    public boolean deleteClass(int idClass){
        try {
            if(stmt.executeUpdate("DELETE FROM "+TABLE+" WHERE "+PKEY+"="+idClass)==1)return true;
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
	            rs = stmt.executeQuery("SELECT * FROM "+TABLE+" WHERE idTeaching=\""+idTeaching+"\"");
	            while(rs.next()){
	            	toReturn.add(new ClassPartition(rs.getInt("idClass"),rs.getString("idTeaching"),rs.getString("title")));
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
            	toReturn.add(new ClassPartition(rs.getInt("idClass"),rs.getString("idTeaching"),rs.getString("title")));
            }  
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Query fail");
        }
        return toReturn;
	}
}
