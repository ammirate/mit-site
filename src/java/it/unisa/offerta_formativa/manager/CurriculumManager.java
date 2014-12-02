package it.unisa.offerta_formativa.manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import it.unisa.offerta_formativa.beans.Curriculum;
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
    private ResultSet rs = null;

    private static CurriculumManager instance = null;

    /**
     * Constructor for Singleton pattern
     */
    private CurriculumManager() {
        conn = DBConnector.getConnection();
        if (conn == null) {
            throw new RuntimeException("Unable to connect to the DB");
        }
    }

    /**
     * Insert a Curriculum into the DB
     *
     * @param curriculum to insert into the DB
     * @return true if the insertion was successfull
     */
    public boolean createCurriculum(Curriculum curr) {
        try {
            stmt = conn.createStatement();
            String query = "INSERT INTO " + TABLE + "(title,degree_matricula) VALUES(" + curr.toStringQueryInsert() + ")";
            if (stmt.executeUpdate(query) == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Insertion Query failed!");
        }
        return false;
    }

    /**
     * Update a curriculum into the DB
     *
     * @param curriculum to update into the DB
     * @return true if the update was successfull
     */
    public boolean updateCurriculum(Curriculum curr) {
        String esc = "\'";
        try {
            String query = "UPDATE " + TABLE + " SET " + curr.toString() + " WHERE title ="
                    + esc + curr.getTitle() + esc + " and "
                    + "teaching_matricula=" + esc + curr.getDegreeId() + esc;
            stmt = conn.createStatement();
            if (stmt.executeUpdate(query) == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Update Query failed!");
        }
        return false;
    }

    /**
     * Read a given curriculum from the DB
     *
     * @param id of the curriculum
     * @return a curriculum object if it is present in the DB, else empty
     * curriculum bean
     */
    public Curriculum readCurriculum(String title, String degreeMatricula) {

        try {
            String esc = "\'";
            String query = "SELECT * FROM " + TABLE
                    + " WHERE title ="
                    + esc + title + esc + " and "
                    + "teaching_matricula=" + esc + degreeMatricula + esc;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                return new Curriculum(rs.getString("title"), rs.getString("degree_matricula"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CurriculumManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Delete a given curriculum from the DB
     *
     * @param id of curriculum
     * @return true if deleted.
     */
    public boolean deleteCurriculum(String title, String degreeMatricula) {
        try {
            
            String esc = "\'";
            String query = "DELETE FROM " + TABLE
                    + " WHERE title ="+ esc + title + esc + " and "
                    + "teaching_matricula=" + esc + degreeMatricula + esc;
            
            stmt = conn.createStatement();
            if (stmt.executeUpdate(query) == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Delete Query failed!");
        }
        return false;
    }

    /**
     * Get all the Curriculum in the lists
     *
     * @return an ArrayList of Curriculum
     */
    public ArrayList<Curriculum> getAllDepartments() {
        ArrayList<Curriculum> toReturn = new ArrayList<Curriculum>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM " + TABLE);

            while (rs.next()) {
                Curriculum b = getCurriculumFromResultSet(rs);
                toReturn.add(b);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    /**
     * Insert all the curriculum in the list into the Database
     *
     * @param list is the list of curriculum to insert
     * @return the list of curriculum which had an error during the insertion
     * process. So, check if this list is empty to make sure of the insertion
     * process.
     */
    public ArrayList<Curriculum> insertCurriculum(ArrayList<Curriculum> list) {
        ArrayList<Curriculum> notInserted = new ArrayList<Curriculum>();

        for (Curriculum b : list) {
            if (!createCurriculum(b)) {
                notInserted.add(b);
            }
        }
        return notInserted;
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
            String tit = rs.getString("title");
            String idDegree = rs.getString("degree_matricula");
            return new Curriculum(tit, idDegree);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Curriculum> getCurriculumByDegree(String degree) {
        // TODO Auto-generated method stub
        ArrayList<Curriculum> toReturn = new ArrayList<Curriculum>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM " + TABLE + "WHERE idDegree=\"" + degree + "\"");

            while (rs.next()) {
                Curriculum b = getCurriculumFromResultSet(rs);
                toReturn.add(b);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toReturn;
    }
}
