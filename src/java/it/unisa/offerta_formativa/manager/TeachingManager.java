/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.manager;

import it.unisa.offerta_formativa.beans.Teaching;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alessandro, Antonio
 */
public class TeachingManager {

    private static final String TABLE = "teaching";
    private static final String PKEY = "matricula";
    public static String TABLE_LINK = "curriculum_teaching";
    private final Connection conn = null;
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

        if (ins == null) {
            throw new IllegalArgumentException("The teaching which you're trying to insert is null");
        } else {
            try {
                stmt = DBConnection.getConnection().createStatement();

                String query = "INSERT INTO " + TABLE
                        + "(matricula,title,abbreviation,link,year,semester,active) VALUES("
                        + ins.toStringQueryInsert() + ")";

                if (stmt.executeUpdate(query) == 1) {
                    return true;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new RuntimeException("Insert Query Failed");
            } finally {
                DBConnection.releaseConnection(conn);
            }
        }
        return false;
    }

    /**
     *
     * @param matricula
     * @return
     */
    public boolean deleteTeaching(String matricula) {

        if (matricula == null || matricula.equals("") || matricula.length() > 10) {
            throw new IllegalArgumentException("Matricula format incorrect");
        } else {
            try {
                stmt = DBConnection.getConnection().createStatement();

                String query = "DELETE FROM " + TABLE
                        + " WHERE " + PKEY + "=\"" + matricula + "\"";
                if (stmt.executeUpdate(query) == 1) {
                    return true;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new RuntimeException("Delete Query failed!");
            } finally {
                DBConnection.releaseConnection(conn);
            }
        }
        return false;
    }

    /**
     *
     * @param ins
     * @return
     */
    public boolean updateTeaching(String teachingMatricula, Teaching ins) {

        if (ins == null) {
            throw new IllegalArgumentException("The teaching which you're trying to update is null");
        } else {
            try {
                stmt = DBConnection.getConnection().createStatement();

                String esc = "\'";
                String query = "UPDATE " + TABLE + " SET " + ins.toString()
                        + " WHERE " + PKEY + "=" + esc + teachingMatricula + esc;

                if (stmt.executeUpdate(query) == 1) {
                    return true;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new RuntimeException("Update Query Failed");
            } finally {
                DBConnection.releaseConnection(conn);
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
        if (matricula == null || matricula.equals("") || matricula.length() > 10) {
            throw new IllegalArgumentException("Matricula format incorrect");
        } else {
            try {
                stmt = DBConnection.getConnection().createStatement();

                String query = "SELECT * FROM " + TABLE
                        + " WHERE " + PKEY + "=" + esc + matricula + esc;

                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    return getTeachingFromResultSet(rs);
                }

            } catch (SQLException ex) {
                throw new RuntimeException("Read Query failed!");
            } finally {
                DBConnection.releaseConnection(conn);
            }
        }
        return null;
    }

    /**
     *
     * @return
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
        ArrayList<Teaching> toReturn = new ArrayList<>();
        String esc = "\"";
        try {
            stmt = DBConnection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM " + TABLE + " order by title");
            while (rs.next()) {
                toReturn.add(getTeachingFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.releaseConnection(conn);
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
        ArrayList<Teaching> toReturn = new ArrayList<>();
        try {
            stmt = DBConnection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM " + TABLE + " WHERE year=" + year);
            while (rs.next()) {
                Teaching t = getTeachingFromResultSet(rs);
                toReturn.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.releaseConnection(conn);
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
            stmt = DBConnection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM " + TABLE + " WHERE semester=" + semester);
            while (rs.next()) {
                Teaching t = getTeachingFromResultSet(rs);
                toReturn.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.releaseConnection(conn);
        }
        return toReturn;
    }

    /**
     *
     * @param curriculum_matricula
     * @return
     */
    public List<Teaching> getTeachingsByCurriculum(String curriculum_matricula) {
        String esc = "\'";
        List<Teaching> toReturn = new ArrayList<>();
        String whichTeachingQUery = "SELECT * FROM "
                + TABLE_LINK + " WHERE curriculum_matricula = "
                + esc + curriculum_matricula + esc;

        String tmpMatricula = "";
        List<String> matriculas = new ArrayList<>();

        try {
            stmt = DBConnection.getConnection().createStatement();

            rs = stmt.executeQuery(whichTeachingQUery);
            while (rs.next()) {
                matriculas.add(rs.getString("teaching_matricula"));
            }
            rs.close();

            for (int i = 0; i < matriculas.size(); i++) {
                String s = matriculas.get(i);

                String query = "SELECT * FROM " + TABLE
                        + " WHERE " + PKEY + "= " + esc + s + esc;
                System.out.println(query);

                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Teaching t = getTeachingFromResultSet(rs);
                    toReturn.add(t);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(CurriculumManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.releaseConnection(conn);
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
    public String getHtmlSyllabus(String teaching_matricula) {
        String htmlToReturn = null;
        String esc = "\"";
        try {
            stmt = DBConnection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT esse3_content FROM " + TABLE + " WHERE matricula=" + esc + teaching_matricula + esc);

            while (rs.next()) {
                htmlToReturn = rs.getString("esse3_content");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return htmlToReturn;
    }

    /**
     * store the esse3content for a given teaching into the DB
     *
     * @param teachingMatricula
     * @param content
     * @return true if the update has success, else false
     */
    public boolean setEsse3ContentForTeaching(String teachingMatricula, String content) {
        try {
            String esc = "\'";
            String query = "UPDATE " + TABLE + " SET " + esc + "esse3_content" + esc
                    + " WHERE " + PKEY + "=" + esc + teachingMatricula + esc;
//UPDATE teaching SET `esse3_content`="new content" WHERE `matricula`='0222500002'
            if (stmt.executeUpdate(query) == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Update Query Failed");
        } finally {
            DBConnection.releaseConnection(conn);
        }
        return false;
    }

    public boolean isActive(String teachingMatricula) {
        return false;
    }

}
