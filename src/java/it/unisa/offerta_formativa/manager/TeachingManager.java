/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.manager;

import it.unisa.integrazione.database.DBConnection;
import it.unisa.offerta_formativa.beans.Teaching;
import it.unisa.offerta_formativa.manager.Exceptions.TeachingException;
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
    public boolean createTeaching(Teaching ins) throws TeachingException {

        if (ins == null) {
            throw new IllegalArgumentException("The teaching which you're trying to insert is null");
        } else {
            Connection connection = null;
            try {
                connection = DBConnection.getConnection();
                stmt = connection.createStatement();

                String query = "INSERT INTO " + TABLE
                        + "(matricula,title,abbreviation,link,year,semester,active) VALUES("
                        + ins.toStringQueryInsert() + ")";

                if (stmt.executeUpdate(query) == 1) {
                    connection.commit();
                    return true;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new TeachingException("Insert Query Failed");
            } finally {
                DBConnection.releaseConnection(connection);
            }
        }
        return false;
    }

    /**
     *
     * @param matricula
     * @return
     */
    public boolean deleteTeaching(String matricula) throws TeachingException {

        if (matricula == null || matricula.equals("") || matricula.length() > 10) {
            throw new IllegalArgumentException("Matricula format incorrect");
        } else {
            Connection connection = null;
            try {
                connection = DBConnection.getConnection();
                stmt = connection.createStatement();

                String query = "DELETE FROM " + TABLE
                        + " WHERE " + PKEY + "=\"" + matricula + "\"";
                if (stmt.executeUpdate(query) == 1) {
                    connection.commit();
                    return true;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new TeachingException("Delete Query failed!");
            } finally {
                DBConnection.releaseConnection(connection);
            }
        }
        return false;
    }

    /**
     *
     * @param ins
     * @return
     */
    public boolean updateTeaching(String teachingMatricula, Teaching ins) throws TeachingException {

        if (ins == null) {
            throw new IllegalArgumentException("The teaching which you're trying to update is null");
        } else {
            Connection connection = null;
            try {
                connection = DBConnection.getConnection();
                stmt = connection.createStatement();

                String esc = "\'";
                String query = "UPDATE " + TABLE + " SET " + ins.toString()
                        + " WHERE " + PKEY + "=" + esc + teachingMatricula + esc;

                if (stmt.executeUpdate(query) == 1) {
                    connection.commit();
                    return true;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new TeachingException("Update Query Failed");
            } finally {
                DBConnection.releaseConnection(connection);
            }
        }
        return false;
    }

    /**
     *
     * @param matricula
     * @return
     */
    public Teaching readTeaching(String matricula) throws TeachingException {

        String esc = "\"";
        if (matricula == null || matricula.equals("") || matricula.length() > 10) {
            throw new IllegalArgumentException("Matricula format incorrect");
        } else {
            Connection connection = null;
            try {
                connection = DBConnection.getConnection();
                stmt = connection.createStatement();

                String query = "SELECT * FROM " + TABLE
                        + " WHERE " + PKEY + "=" + esc + matricula + esc;

                rs = stmt.executeQuery(query);
                connection.commit();
                while (rs.next()) {
                    return getTeachingFromResultSet(rs);
                }

            } catch (SQLException ex) {
                throw new TeachingException("Read Query failed!");
            } finally {
                DBConnection.releaseConnection(connection);
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
        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            stmt = connection.createStatement();

            rs = stmt.executeQuery("SELECT * FROM " + TABLE + " order by title");
            connection.commit();
            while (rs.next()) {
                toReturn.add(getTeachingFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.releaseConnection(connection);
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
        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            stmt = connection.createStatement();

            rs = stmt.executeQuery("SELECT * FROM " + TABLE + " WHERE year=" + year);
            connection.commit();
            while (rs.next()) {
                Teaching t = getTeachingFromResultSet(rs);
                toReturn.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.releaseConnection(connection);
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
        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            stmt = connection.createStatement();

            rs = stmt.executeQuery("SELECT * FROM " + TABLE + " WHERE semester=" + semester);
            connection.commit();
            while (rs.next()) {
                Teaching t = getTeachingFromResultSet(rs);
                toReturn.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.releaseConnection(connection);
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

        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            stmt = connection.createStatement();

            rs = stmt.executeQuery(whichTeachingQUery);
            connection.commit();
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
            DBConnection.releaseConnection(connection);
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
        String linkEsse3 = null;
        String esc = "\"";
        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM " + TABLE + " WHERE matricula=" + esc + teaching_matricula + esc);
            connection.commit();
            while (rs.next()) {
                htmlToReturn = rs.getString("esse3_content");
                linkEsse3 = rs.getString("link");
            }
            if(htmlToReturn == null){
//               ParserHtmlManager.getInstance().getHtml(linkEsse3, "<html>");
               this.setEsse3ContentForTeaching(teaching_matricula, htmlToReturn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (TeachingException ex) {
            Logger.getLogger(TeachingManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            DBConnection.releaseConnection(connection);
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
    public boolean setEsse3ContentForTeaching(String teachingMatricula, String content) throws TeachingException {
        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            stmt = connection.createStatement();

            String esc = "\'";
            String query = "UPDATE " + TABLE + " SET " + esc + "esse3_content" + esc
                    + " WHERE " + PKEY + "=" + esc + teachingMatricula + esc;
//UPDATE teaching SET `esse3_content`="new content" WHERE `matricula`='0222500002'
            if (stmt.executeUpdate(query) == 1) {
                connection.commit();
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new TeachingException("Update esse3Content Query Failed");
        } finally {
            DBConnection.releaseConnection(connection);
        }
        return false;
    }

    public boolean isActive(String teachingMatricula) {
        return false;
    }

}
