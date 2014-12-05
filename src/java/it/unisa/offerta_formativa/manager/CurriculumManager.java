package it.unisa.offerta_formativa.manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import it.unisa.offerta_formativa.beans.Curriculum;
import it.unisa.offerta_formativa.beans.Teaching;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Antonio
 */
public class CurriculumManager {

    private Connection conn = null;
    private Statement stmt;
    public static String TABLE = "curriculum";
    public static String PKEY = "matricula";
    public static String TABLE_LINK = "curriculum_teaching";
    private ResultSet rs = null;

    String esc = "\'";

    private static CurriculumManager instance = null;

    /**
     * Constructor for Singleton pattern
     */
    private CurriculumManager() {

    }

    /**
     * Insert a Curriculum into the DB
     *
     * @return true if the insertion was successfull
     */
    public boolean createCurriculum(Curriculum curr) {
        try {
            stmt = DBConnector.openConnection();
            String query = "INSERT INTO " + TABLE + "(matricula,title,degree_matricula,active) VALUES(" + curr.toStringQueryInsert() + ")";
            if (stmt.executeUpdate(query) == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Insertion Query failed!");
        } finally {
            DBConnector.closeConnection();
        }
        return false;
    }

    /**
     * Update a curriculum into the DB
     *
     * @param curriculum to update into the DB
     * @return true if the update was successfull
     */
    public boolean updateCurriculum(String oldCurrMatricula, Curriculum newCurriculum) {

        stmt = DBConnector.openConnection();

        try {
            String query = "UPDATE " + TABLE + " SET " + newCurriculum.toString() + " WHERE "
                    + "matricula=" + esc + oldCurrMatricula + esc;
            System.out.println("update QUERY " + query);

            if (stmt.executeUpdate(query) == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Update Query failed!");
        } finally {
            DBConnector.closeConnection();
        }
        return false;
    }

    /**
     * Read a given curriculum from the DB
     *
     * @param matricula
     * @return a curriculum object if it is present in the DB, else empty
     * curriculum bean
     */
    public Curriculum readCurriculum(String matricula) {
        stmt = DBConnector.openConnection();

        try {

            String query = "SELECT * FROM " + TABLE + " WHERE " + PKEY + "=" + esc + matricula + esc;
//            System.out.println("READ QUERY" + query);
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                return getCurriculumFromResultSet(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CurriculumManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnector.closeConnection();
        }
        return null;
    }

    /**
     * Delete a given curriculum from the DB
     *
     * @param matricula
     * @return true if deleted.
     */
    public boolean deleteCurriculum(String matricula) {
        stmt = DBConnector.openConnection();

        try {

            String query = "DELETE FROM " + TABLE + " WHERE " + PKEY + "= " + esc + matricula + esc;
            //            System.out.println("READ QUERY" + query);
//
            if (stmt.executeUpdate(query) == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Delete Query failed!");
        } finally {
            DBConnector.closeConnection();
        }
        return false;
    }

    /**
     * Get all the Curriculum in the lists
     *
     * @return an ArrayList of Curriculum
     */
    public ArrayList<Curriculum> getAllCurriculums() {
        stmt = DBConnector.openConnection();

        ArrayList<Curriculum> toReturn = new ArrayList<Curriculum>();
        try {
            rs = stmt.executeQuery("SELECT * FROM " + TABLE);

            while (rs.next()) {
                Curriculum b = getCurriculumFromResultSet(rs);
                toReturn.add(b);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.closeConnection();
        }
        return toReturn;
    }

    /**
     * used to get the unique instance of this class
     */
    public static CurriculumManager getInstance() {
        if (instance == null) {
            instance = new CurriculumManager();
        }
        return instance;
    }

    /**
     * Create a Curriculum from a ResultSet object
     *
     * @param rs
     * @return
     */
    private Curriculum getCurriculumFromResultSet(ResultSet rs) {
        try {
            String matr = rs.getString("matricula");
            String tit = rs.getString("title");
            String idDegree = rs.getString("degree_matricula");
            int active = rs.getInt("active");
            Curriculum c = new Curriculum(matr, tit, idDegree);
            c.setActive(active);
            return c;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * give all the curriculms which belong to a given degree
     *
     * @param degreeMatricula the matricula of the degree
     * @return a list of curriculm
     */
    public ArrayList<Curriculum> getCurriculumByDegree(String degreeMatricula) {
        ArrayList<Curriculum> toReturn = new ArrayList<Curriculum>();
        stmt = DBConnector.openConnection();

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM " + TABLE+ " WHERE degree_matricula="+esc+degree+esc + "ORDER BY title");

            while (rs.next()) {
                Curriculum b = getCurriculumFromResultSet(rs);
                toReturn.add(b);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.closeConnection();
        }
        return toReturn;
    }

    /**
     * check if a teaching belongs to a curriculum
     *
     * @param curriculum_matricula
     * @param teaching_matricula
     * @return true if a teaching belongs to a curriculum, else false
     */
    public boolean curriculumHasTeaching(String curriculum_matricula, String teaching_matricula) {
        stmt = DBConnector.openConnection();

        try {

            String query = "SELECT * FROM " + TABLE_LINK
                    + " WHERE teaching_matricula=" + esc + teaching_matricula + esc
                    + " AND curriculum_matricula=" + esc + curriculum_matricula + esc;
            rs = stmt.executeQuery(query);
            rs.last();
            return (rs.getRow() != 0);
        } catch (SQLException ex) {
            Logger.getLogger(CurriculumManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnector.closeConnection();
        }
        return false;
    }

    /**
     * return the teachings which belong to a curriculum
     *
     * @param curriculum_matricula
     * @param teaching_matricula
     * @return true if a teaching belongs to a curriculum, else false
     */
    public List<Teaching> getTeachingsByCurriculm(String curriculum_matricula) {
        stmt = DBConnector.openConnection();

        try {

            String query = "SELECT * FROM " + TABLE_LINK
                    + " WHERE curriculum_matricula=" + esc + curriculum_matricula + esc;
            rs = stmt.executeQuery(query);
            System.out.println("getTeachingsByCurriculm query " + query);

            List<String> teachingsMatricula = new ArrayList<>();
            List<Teaching> toReturn = new ArrayList<>();

            TeachingManager tm = TeachingManager.getInstance();

            while (rs.next()) {
//                System.out.println(rs.getString("teaching_matricula"));
                Teaching t = tm.readTeaching(rs.getString("teaching_matricula"));
//                System.out.println(t);
                toReturn.add(t);
            }
            return toReturn;

        } catch (SQLException ex) {
            Logger.getLogger(CurriculumManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnector.closeConnection();
        }
        return new ArrayList<>();
    }

    /**
     *
     * @param teaching_matricula
     * @param curriculum_matricula
     */
    public boolean setTeachingInCurriculum(String teaching_matricula, String curriculum_matricula) {
        stmt = DBConnector.openConnection();

        try {

            String query = "INSERT INTO " + TABLE_LINK
                    + "(teaching_matricula, curriculum_matricula) VALUES("
                    + esc + teaching_matricula + esc + ","
                    + esc + curriculum_matricula + esc + ")";
            if (stmt.executeUpdate(query) == 1) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(CurriculumManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnector.closeConnection();
        }
        return false;
    }

    /**
     * Create a Teaching from a ResultSet object
     *
     * @param rs - result set of teachings
     * @return
     */
    private Teaching getTeachingFromResultSet(ResultSet rs) {
        System.out.println("getTeachings FROM RESULT SET");

        try {
            String matr = rs.getString("matricula");
            String tit = rs.getString("title");
            String abb = rs.getString("abbreviation");
            String link = rs.getString("link");
            int year = rs.getInt("year");
            int sem = rs.getInt("semester");
            int active = rs.getInt("active");
            return new Teaching(tit, abb, matr, link, year, sem, (active > 0));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.closeConnection();
        }
        return null;
    }

}
