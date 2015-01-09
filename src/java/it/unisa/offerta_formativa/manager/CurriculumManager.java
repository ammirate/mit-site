package it.unisa.offerta_formativa.manager;

import it.unisa.integrazione.database.DBConnection;
import it.unisa.offerta_formativa.manager.old.DBConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import it.unisa.offerta_formativa.beans.Curriculum;
import it.unisa.offerta_formativa.beans.Teaching;
import it.unisa.offerta_formativa.manager.Exceptions.CurriculumException;
import it.unisa.offerta_formativa.manager.Exceptions.TeachingException;
import java.util.List;

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
        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            stmt = connection.createStatement();
            String query = "INSERT INTO " + TABLE + "(matricula,title,degree_matricula,active) VALUES(" + curr.toStringQueryInsert() + ")";
            if (stmt.executeUpdate(query) == 1) {
                connection.commit();
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Insertion Query failed!");
        } finally {
            DBConnection.releaseConnection(connection);
        }
        return false;
    }

    /**
     * Update a curriculum into the DB
     *
     * @param curriculum to update into the DB
     * @return true if the update was successfull
     */
    public boolean updateCurriculum(String oldCurrMatricula, Curriculum newCurriculum) throws CurriculumException {

        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            stmt = connection.createStatement();

            String query = "UPDATE " + TABLE + " SET " + newCurriculum.toString() + " WHERE "
                    + "matricula=" + esc + oldCurrMatricula + esc;
            System.out.println("update QUERY " + query);

            if (stmt.executeUpdate(query) == 1) {
                connection.commit();
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new CurriculumException("Update Query failed!");
        } finally {
            DBConnection.releaseConnection(connection);
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
    public Curriculum readCurriculum(String matricula) throws CurriculumException {

        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            stmt = connection.createStatement();

            String query = "SELECT * FROM " + TABLE + " WHERE " + PKEY + "=" + esc + matricula + esc;
//            System.out.println("READ QUERY" + query);
            rs = stmt.executeQuery(query);
            connection.commit();
            while (rs.next()) {
                return getCurriculumFromResultSet(rs);
            }
        } catch (SQLException ex) {
            throw new CurriculumException();
        } finally {
            DBConnection.releaseConnection(connection);
        }
        return null;
    }

    /**
     * Delete a given curriculum from the DB
     *
     * @param matricula
     * @return true if deleted.
     */
    public boolean deleteCurriculum(String matricula) throws CurriculumException {

        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            stmt = connection.createStatement();

            String query = "DELETE FROM " + TABLE + " WHERE "
                    + PKEY + "= " + esc + matricula + esc;
            //            System.out.println("READ QUERY" + query);
//
            if (stmt.executeUpdate(query) == 1) {
                connection.commit();
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new CurriculumException("Delete Query failed!");
        } finally {
            DBConnection.releaseConnection(connection);
        }
        return false;
    }

    /**
     * Get all the Curriculum in the lists
     *
     * @return an ArrayList of Curriculum
     */
    public ArrayList<Curriculum> getAllCurriculums() {

        ArrayList<Curriculum> toReturn = new ArrayList<Curriculum>();
        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            stmt = connection.createStatement();

            rs = stmt.executeQuery("SELECT * FROM " + TABLE + " order by title");
            connection.commit();
            while (rs.next()) {
                Curriculum b = getCurriculumFromResultSet(rs);
                toReturn.add(b);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.releaseConnection(connection);
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
        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            stmt = connection.createStatement();

            stmt = DBConnector.openConnection();
            rs = stmt.executeQuery("SELECT * FROM " + TABLE
                    + " WHERE degree_matricula=" + esc + degreeMatricula
                    + esc + "ORDER BY title");
            connection.commit();
            while (rs.next()) {
                Curriculum c = getCurriculumFromResultSet(rs);
                toReturn.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.releaseConnection(connection);
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
    public boolean curriculumHasTeaching(String curriculum_matricula, String teaching_matricula) throws CurriculumException {

        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            stmt = connection.createStatement();

            String query = "SELECT * FROM " + TABLE_LINK
                    + " WHERE teaching_matricula=" + esc + teaching_matricula + esc
                    + " AND curriculum_matricula=" + esc + curriculum_matricula + esc;
            rs = stmt.executeQuery(query);
            connection.commit();
            rs.last();
            return (rs.getRow() != 0);
        } catch (SQLException ex) {
            throw new CurriculumException();
        } finally {
            DBConnection.releaseConnection(connection);
        }
    }

    /**
     * return the teachings which belong to a curriculum
     *
     * @param curriculum_matricula
     * @param teaching_matricula
     * @return true if a teaching belongs to a curriculum, else false
     */
    public List<Teaching> getTeachingsByCurriculm(String curriculum_matricula) throws TeachingException, CurriculumException {

        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            stmt = connection.createStatement();

            String query = "SELECT * FROM " + TABLE_LINK
                    + " WHERE curriculum_matricula=" + esc
                    + curriculum_matricula + esc;
            rs = stmt.executeQuery(query);
//            System.out.println("getTeachingsByCurriculm query " + query);
            connection.commit();
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
            throw new CurriculumException();
        } finally {
            DBConnection.releaseConnection(connection);
        }
    }

    /**
     *
     * @param teaching_matricula
     * @param curriculum_matricula
     */
    public boolean setTeachingInCurriculum(String teaching_matricula, String curriculum_matricula) throws CurriculumException {

        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            stmt = connection.createStatement();

            String query = "INSERT INTO " + TABLE_LINK
                    + "(teaching_matricula, curriculum_matricula) VALUES("
                    + esc + teaching_matricula + esc + ","
                    + esc + curriculum_matricula + esc + ")";
            System.out.println(query);
            if (stmt.executeUpdate(query) == 1) {
                connection.commit();
                return true;
            }

        } catch (SQLException ex) {
            throw new CurriculumException();
        } finally {
            DBConnection.releaseConnection(connection);
        }
        return false;
    }

    /**
     * update an element fron the table Curriculum_Has_Teaching in the DB
     *
     * @param oldCurrMatricula the curriculum matricula to update
     * @param oldTeachMatricula the teaching matricula to update
     * @param newCurrMatr the new curriculum matricula
     * @param newTeachMatr the new teaching matricula
     * @return true if the update has success, else false
     * @throws TeachingException
     * @throws CurriculumException
     */
    public boolean updateCurriculumhasTeaching(String oldCurrMatricula, String oldTeachMatricula,
            String newCurrMatr, String newTeachMatr) throws TeachingException, CurriculumException {
        deleteCurriculumhasTeaching(oldTeachMatricula, oldCurrMatricula);
        return setTeachingInCurriculum(newTeachMatr, newCurrMatr);
    }

    /**
     * delete a row from the table Curriculum_Has_Teaching in the DB
     *
     * @param teaching_matricula
     * @param curriculum_matricula
     * @return
     * @throws TeachingException
     */
    public boolean deleteCurriculumhasTeaching(String teaching_matricula, String curriculum_matricula) throws TeachingException {
        String query = "DELETE FROM " + TABLE_LINK + " WHERE "
                + "curriculum_matricula" + "= "
                + esc + curriculum_matricula + esc
                + " AND " + "teaching_matricula" + "="
                + esc + teaching_matricula + esc;
        System.out.println(query);
        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            stmt = connection.createStatement();

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
        return false;
    }

    /**
     * Create a Teaching from a ResultSet object
     *
     * @param rs - result set of teachings
     * @return
     */
    private Teaching getTeachingFromResultSet(ResultSet rs) {
//        System.out.println("getTeachings FROM RESULT SET");

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
        }
        return null;
    }

    public List<Curriculum> getCurriculumsByTeaching(String teachingMatricula) throws CurriculumException {

        List<Curriculum> toReturn = new ArrayList<>();
        String whichCurriculumQUery = "SELECT * FROM "
                + TABLE_LINK + " WHERE teaching_matricula = "
                + esc + teachingMatricula + esc;

        String tmpMatricula = "";
        List<String> matriculas = new ArrayList<>();

        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            stmt = connection.createStatement();

            rs = stmt.executeQuery(whichCurriculumQUery);
            connection.commit();
            while (rs.next()) {
                matriculas.add(rs.getString("curriculum_matricula"));
            }

            for (String s : matriculas) {
                String query = "SELECT * FROM " + TABLE
                        + " WHERE " + PKEY + "= " + esc + s + esc;
                System.out.println(query);

                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Curriculum c = getCurriculumFromResultSet(rs);
                    System.out.println("Curriculum: " + c);
                    toReturn.add(c);
                }
            }

        } catch (SQLException ex) {
            throw new CurriculumException();
        } finally {
            DBConnection.releaseConnection(connection);
        }
        return toReturn;
    }
}
