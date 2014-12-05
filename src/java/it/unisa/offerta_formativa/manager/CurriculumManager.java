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
    public static String PKEY = "matricula";
    public static String TABLE_LINK = "curriculum_has_teaching";
    private ResultSet rs = null;

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
            String query = "INSERT INTO " + TABLE + "(matricula,title,degree_matricula) VALUES(" + curr.toStringQueryInsert() + ")";
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
    public boolean updateCurriculum(Curriculum curr) {
        String esc = "\'";
        stmt = DBConnector.openConnection();

        try {
            String query = "UPDATE " + TABLE + " SET " + curr.toString() + " WHERE " + PKEY + "=" + esc + curr.getMatricula() + esc;
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
     * @param id of the curriculum
     * @return a curriculum object if it is present in the DB, else empty
     * curriculum bean
     */
    public Curriculum readCurriculum(String matricula) {
        stmt = DBConnector.openConnection();

        try {
            String esc = "\'";
            String query = "SELECT * FROM " + TABLE + " WHERE " + PKEY + "=" + esc + matricula + esc;
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                return new Curriculum(rs.getString("matricula"), rs.getString("title"), rs.getString("degree_matricula"));
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
     * @param id of curriculum
     * @return true if deleted.
     */
    public boolean deleteCurriculum(String matricula) {
        stmt = DBConnector.openConnection();

        try {
            String esc = "\'";
            String query = "DELETE FROM " + TABLE + " WHERE " + PKEY + "=" + esc + matricula + esc;
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
    public ArrayList<Curriculum> getAllCurriculum() {
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
            String matr = rs.getString("matricula");
            String tit = rs.getString("title");
            String idDegree = rs.getString("degree_matricula");
            return new Curriculum(matr, tit, idDegree);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Curriculum> getCurriculumByDegree(String degree) {
        // TODO Auto-generated method stub
        String esc = "\"";
        ArrayList<Curriculum> toReturn = new ArrayList<Curriculum>();
        stmt = DBConnector.openConnection();

        try {
            
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

    public int getCurriculumNumberByDegree(String matricula) {
        stmt = DBConnector.openConnection();

        try {
            String esc = "\'";
            String query = "SELECT * FROM " + TABLE + " WHERE degree_matricula=" + esc + matricula + esc;
            rs = stmt.executeQuery(query);
            rs.last();
            return rs.getRow();
        } catch (SQLException ex) {
            Logger.getLogger(CurriculumManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnector.closeConnection();
        }
        return 0;
    }

    public boolean curriculumHasTeaching(String curriculum_matricula, String teaching_matricula) {
        stmt = DBConnector.openConnection();

        try {
            String esc = "\'";
            String query = "SELECT * FROM " + TABLE_LINK + " WHERE teaching_matricula="
                    + esc + teaching_matricula + esc + " AND curriculum_matricula=" + esc + curriculum_matricula + esc;
            rs = stmt.executeQuery(query);
            rs.last();
            return (rs.getRow() == 0) ? false : true;
        } catch (SQLException ex) {
            Logger.getLogger(CurriculumManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnector.closeConnection();
        }
        return false;
    }

    public void setCurriculumTeaching(String teaching_matricula, String curriculum_matricula) {
        stmt = DBConnector.openConnection();

        try {
            String esc = "\'";
            String query = "INSERT INTO " + TABLE_LINK + "(teaching_matricula, curriculum_matricula) VALUES(" + esc + teaching_matricula + esc + "," + esc + curriculum_matricula + esc + ")";
            rs = stmt.executeQuery(query);

        } catch (SQLException ex) {
            Logger.getLogger(CurriculumManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnector.closeConnection();
        }
    }
}
