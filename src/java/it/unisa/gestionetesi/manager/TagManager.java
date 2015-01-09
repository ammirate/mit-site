/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.gestionetesi.manager;

import it.unisa.gestionetesi.beans.Tag;
import it.unisa.integrazione.database.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Daniele
 */
public class TagManager {

    private Connection conn = null;
    private Statement stmt;
    public static String TABLE = "tag";
    public static String PKEY = "id";
    private ResultSet rs = null;

    private static TagManager instance = null;

    String esc = "\'";

    /**
     * Constructor for Singleton pattern
     */
    public TagManager() {
    }
    
    /**
     * used to get the unique instance of this class
     *
     * @return
     */
    public static TagManager getInstance() {
        if (instance == null) {
            instance = new TagManager();
        }
        return instance;
    }
    

    /**
     * Get all the Degrees in the lists
     *
     * @return an ArrayList of Degrees
     */
    public List<Tag> getAllTags() {
        List<Tag> T =  new ArrayList<Tag>();
	Tag tag = null;
	String query = "";
	
        try {
	    conn = DBConnection.getConnection();
            stmt = conn.createStatement();
	    query = "SELECT * FROM " + TABLE;
            rs = stmt.executeQuery(query);
	    
	    System.out.println("Query di ricerca per tag:\n" + query);
            
	    while (rs.next()) {
                tag = new Tag();
		tag.setId(rs.getInt("id"));
		tag.setNomeTag(rs.getString("name"));
                T.add(tag);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.releaseConnection(conn);
        }
        return T;
    }

    public Tag getTagById(String id) {
	Tag tag = new Tag();
	String query = "";
	
        try {
	    conn = DBConnection.getConnection();
            stmt = conn.createStatement();
	    query = "SELECT * FROM " + TABLE + " " +
		"WHERE id = " + id;
            rs = stmt.executeQuery(query);
	    
            while (rs.next()) {
                tag.setId(rs.getInt("id"));
		tag.setNomeTag(rs.getString("nome"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.releaseConnection(conn);
        }
        return tag;
    }

    public List<Tag> getTagsByThesisId(int thesisId) {
	List<Tag> T =  new ArrayList<Tag>();
	Tag tag = new Tag();
	String query = "";
	
        try {
	    conn = DBConnection.getConnection();
            stmt = conn.createStatement();
	    query = "SELECT tmp_tag.id_tag, tmp_tag.tag_name " +
	    "FROM thesis JOIN " +
	    "	(SELECT ID_thesis, ID_tag, Name as tag_name " +
	    "    FROM thesis_tag JOIN tag ON ID_tag = tag.id) AS tmp_tag " +
	    "ON thesis.id = tmp_tag.ID_thesis " +
	    "WHERE tmp_tag.id_thesis = " + thesisId;
            rs = stmt.executeQuery(query);
	    
	    System.out.println("Query di ricerca per tag:\n" + query);
	    
            while (rs.next()) {
                tag.setId(rs.getInt("id_tag"));
		tag.setNomeTag(rs.getString("tag_name"));
                T.add(tag);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.releaseConnection(conn);
        }
        return T;
    }

}
