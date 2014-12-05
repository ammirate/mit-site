/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.manager;

import it.unisa.offerta_formativa.beans.Degree;
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
 * @author Alessandro, Antonio
 */
public class TeachingManager {

   
    private static String TABLE = "teaching";
    private static String PKEY = "matricula";
    public static String TABLE_LINK = "curriculum_teaching";
    private Connection conn = null;
    private Statement stmt, stmt2;
    private ResultSet rs;

    private static TeachingManager instance = null;

    /**
     * Constructor
     */
    private TeachingManager() {
        conn = DBConnector.getConnection();
        if (conn == null) {
            throw new RuntimeException("Unable to connect to the DB");
        }
        try {
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Statement Creation failed");
        }
    }

    /**
     *
     * @param ins
     * @return
     */
    public boolean createTeaching(Teaching ins) {
        if (ins == null) {
            throw new IllegalArgumentException("The teaching which you're trying to insert is null");
        } else {
            try {
                if (stmt.executeUpdate("INSERT INTO " + TABLE + "(matricula,title,abbreviation,link,year,semester,active) VALUES(" + ins.toStringQueryInsert() + ")") == 1) {
                    return true;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new RuntimeException("Insert Query Failed");
            }
        }
        return false;
    }

    /**
     *
     * @param ins
     * @return
     */
    public boolean deleteTeaching(String matricula) {
        if (matricula == null || matricula.equals("") || matricula.length() > 10) {
            throw new IllegalArgumentException("Matricula format incorrect");
        } else {
            try {
                if (stmt.executeUpdate("DELETE FROM " + TABLE + " WHERE " + PKEY + "=\"" + matricula + "\"") == 1) {
                    return true;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new RuntimeException("Delete Query failed!");
            }
        }
        return false;
    }

    /**
     *
     * @param ins
     * @return
     */
    public boolean updateTeaching(Teaching ins) {
        if (ins == null) {
            throw new IllegalArgumentException("The teaching which you're trying to update is null");
        } else {
            try {
                if (stmt.executeUpdate("UPDATE " + TABLE + " SET " + ins.toString() + " WHERE " + PKEY + "=" + ins.getMatricula()) == 1) {
                    return true;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new RuntimeException("Update Query Failed");
            }
        }
        return false;
    }

    /**
     *
     * @param matricula
     * @return
     */
    public Teaching readTeaching(String matricula) {
        String esc = "\"";
        ResultSet rs2;
        if (matricula == null || matricula.equals("") || matricula.length() > 10) {
            throw new IllegalArgumentException("Matricula format incorrect");
        } else {
            try {
                rs = stmt.executeQuery("SELECT * FROM " + TABLE
                        + " WHERE " + PKEY + "=\"" + matricula + "\"");
                while (rs.next()) {
                    rs2 = stmt.executeQuery("SELECT * FROM " + TABLE_LINK + " WHERE teaching_matricula=" + esc + matricula + esc);

                    return getTeachingFromResultSet(rs, rs2);
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Read Query failed!");
            }
        }
        return null;
    }

    

    /**
     *
     */
    public static TeachingManager getInstance() {
        if (instance == null) {
            instance = new TeachingManager();
        }
        return instance;
    }

    /**
     * Get all the teachings in the lists
     * 
     * @return an ArrayList of teachings
     */
    public ArrayList<Teaching> getAllTeachings() {
        ArrayList<Teaching> toReturn = new ArrayList<Teaching>();
        ResultSet rs2;
        String esc = "\"";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM " + TABLE);
            while (rs.next()) {

                rs2 = stmt.executeQuery("SELECT * FROM " + TABLE_LINK + " WHERE teaching_matricula=" + esc + rs.getString("matricula") + esc);
                toReturn.add(getTeachingFromResultSet(rs, rs2));
            }
        } catch (SQLException e) {
        }
        return toReturn;
    }

    /**
     * Get all the bachelor in the lists with a given year
     * @param year of study
     * @return an ArrayList of teaching at the year @param year
     */
    public ArrayList<Teaching> getTeachingsByYear(int year) {
        ResultSet rs2;
        String esc = "\"";
        ArrayList<Teaching> toReturn = new ArrayList<Teaching>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM " + TABLE + " WHERE year=" + year);
            while (rs.next()) {
                rs2 = stmt.executeQuery("SELECT * FROM " + TABLE_LINK + " WHERE teaching_matricula=" + esc + rs.getString("matricula") + esc);
                Teaching t = getTeachingFromResultSet(rs, rs2);
                toReturn.add(t);
            }
        } catch (SQLException e) {
        }
        return toReturn;
    }

    /**
     * Get all the bachelor in the lists with a given semester
     * @param semester 
     * @return an ArrayList of teaching at the year @param semester
     */
    public ArrayList<Teaching> getTeachingsBySemester(int semester) {
        ResultSet rs2;
        String esc = "\"";
        ArrayList<Teaching> toReturn = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM " + TABLE + " WHERE semester=" + semester);
            while (rs.next()) {
                rs2 = stmt.executeQuery("SELECT * FROM " + TABLE_LINK + " WHERE teaching_matricula=" + esc + rs.getString("matricula") + esc);
                Teaching t = getTeachingFromResultSet(rs,rs2);
                toReturn.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toReturn;
    }
    
    public ArrayList<Teaching> getTeachingsByCurriculum(String curriculum_matricula) {
        ResultSet rs2;
        String esc = "\"";
        ArrayList<Teaching> toReturn = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            stmt2 = conn.createStatement();
            rs2 = stmt.executeQuery("SELECT * FROM " + TABLE_LINK + " WHERE curriculum_matricula=" + esc + curriculum_matricula + esc);
            
            while (rs2.next()) {
                
                rs = stmt2.executeQuery("SELECT * FROM " + TABLE + " WHERE matricula=" + esc + rs2.getString("teaching_matricula")+ esc );
                if(rs.next()){
                    Teaching t = getTeachingFromResultSet(rs);
                    toReturn.add(t);
                
                }
                
               
            }

        } catch (SQLException e) {
        }
        return toReturn;
    }
    
    
    /**
     * Create a Teaching from a ResultSet object
     *
     * @param rs - result set of teachings
     * @return
     */
    private Teaching getTeachingFromResultSet(ResultSet rs) {
        try {
            String tit = rs.getString("title");
            String matr = rs.getString("matricula");
            String abb = rs.getString("abbreviation");
            String link = rs.getString("link");
            int year = rs.getInt("year");
            int sem = rs.getInt("semester");
            Boolean active = rs.getBoolean("active");
            return new Teaching(tit, abb, matr, link, year, sem, active);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
        
    }
    
    /**
     * 
     * @param teaching_matricula
     * @return 
     */
    public String getHtmlSyllabus(String teaching_matricula){
        String htmlToReturn = null;
        String esc = "\"";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT esse3_content FROM " + TABLE + " WHERE matricula=" + esc + teaching_matricula + esc);
            
            while (rs.next()) {
                htmlToReturn = rs.getString("esse3_content");   
            }
        } catch (SQLException e) {
        }
        return htmlToReturn;
    }
}
