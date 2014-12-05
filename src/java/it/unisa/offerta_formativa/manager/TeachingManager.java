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
    }

    /**
     *
     * @param ins
     * @return
     */
    public boolean createTeaching(Teaching ins) {
        stmt = DBConnector.openConnection();

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
            } finally {
                DBConnector.closeConnection();
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
        stmt = DBConnector.openConnection();

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
            } finally {
                DBConnector.closeConnection();
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
        stmt = DBConnector.openConnection();

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
            } finally {
                DBConnector.closeConnection();
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
        stmt = DBConnector.openConnection();

        String esc = "\"";
        if (matricula == null || matricula.equals("") || matricula.length() > 10) {
            throw new IllegalArgumentException("Matricula format incorrect");
        } else {
            try {
                rs = stmt.executeQuery("SELECT * FROM " + TABLE
                        + " WHERE " + PKEY + "=" + esc + matricula + esc);
                while (rs.next()) {
                    return getTeachingFromResultSet(rs);
                }

            } catch (SQLException ex) {
                throw new RuntimeException("Read Query failed!");
            } finally {
                DBConnector.closeConnection();
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
            stmt = DBConnector.openConnection();
            rs = stmt.executeQuery("SELECT * FROM " + TABLE);
            while (rs.next()) {

                rs2 = stmt.executeQuery("SELECT * FROM " + TABLE_LINK + " WHERE teaching_matricula=" + esc + rs.getString("matricula") + esc);
                toReturn.add(getTeachingFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.closeConnection();
        }
        return toReturn;
    }

    /**
     * Get all the bachelor in the lists with a given year
     *
     * @param year of study
     * @return an ArrayList of teaching at the year @param year
     */
    public ArrayList<Teaching> getTeachingsByYear(int year) {
        ResultSet rs2;
        String esc = "\"";
        ArrayList<Teaching> toReturn = new ArrayList<Teaching>();
        try {
            stmt = DBConnector.openConnection();
            rs = stmt.executeQuery("SELECT * FROM " + TABLE + " WHERE year=" + year);
            while (rs.next()) {
                rs2 = stmt.executeQuery("SELECT * FROM " + TABLE_LINK + " WHERE teaching_matricula=" + esc + rs.getString("matricula") + esc);
                Teaching t = getTeachingFromResultSet(rs);
                toReturn.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.closeConnection();
        }
        return toReturn;
    }

    /**
     * Get all the bachelor in the lists with a given semester
     *
     * @param semester
     * @return an ArrayList of teaching at the year @param semester
     */
    public ArrayList<Teaching> getTeachingsBySemester(int semester) {
        ResultSet rs2;
        String esc = "\"";
        ArrayList<Teaching> toReturn = new ArrayList<>();
        try {
            stmt = DBConnector.openConnection();
            rs = stmt.executeQuery("SELECT * FROM " + TABLE + " WHERE semester=" + semester);
            while (rs.next()) {
                rs2 = stmt.executeQuery("SELECT * FROM " + TABLE_LINK + " WHERE teaching_matricula=" + esc + rs.getString("matricula") + esc);
                Teaching t = getTeachingFromResultSet(rs);
                toReturn.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.closeConnection();
        }
        return toReturn;
    }

    public ArrayList<Teaching> getTeachingsByCurriculum(String curriculum_matricula) {
        ResultSet rs2, rs3;
        String esc = "\"";
        ArrayList<Teaching> toReturn = new ArrayList<>();
        try {
            stmt = DBConnector.openConnection();
            stmt2 = conn.createStatement();
            rs2 = stmt.executeQuery("SELECT * FROM " + TABLE_LINK + " WHERE curriculum_matricula=" + esc + curriculum_matricula + esc);

            while (rs2.next()) {

                rs = stmt2.executeQuery("SELECT * FROM " + TABLE + " WHERE matricula=" + esc + rs2.getString("teaching_matricula") + esc);
                Teaching t = getTeachingFromResultSet(rs);
                toReturn.add(t);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.closeConnection();
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
        } finally {
            DBConnector.closeConnection();
        }
        return null;
    }
}
