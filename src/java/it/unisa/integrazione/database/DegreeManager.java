/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.integrazione.database;

import it.unisa.integrazione.database.exception.ConnectionException;
import java.sql.Connection;
import java.sql.SQLException;
import it.unisa.integrazione.model.Degree;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Antonio
 */
public class DegreeManager {

    private Statement stmt;
    public static String TABLE = "degree";
    public static String PKEY = "matricula";
    private ResultSet rs = null;

    private static DegreeManager instance = null;

    String esc = "\'";

    /**
     * Constructor for Singleton pattern
     */
    private DegreeManager() {
    }

    /**
     * Insert a Degree into the DB
     *
     * @param degree to insert into the DB
     * @return true if the insertion was successfull
     */
    public boolean createDegree(Degree degree) {
        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            stmt = connection.createStatement();
            String query = "INSERT INTO " + TABLE
                    + "(title, "
                    + "matricula,"
                    + "link,"
                    + "cycle_number,"
                    + "department_abbreviation, "
                    + "active,"
                    + "esse3_content ) VALUES("
                    + degree.toStringQueryInsert() + ")";
//            System.out.println("Insertion query: " + query);
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
     * Update a Degree into the DB
     *
     * @param matricula
     * @param degree to update into the DB
     * @return true if the update was successfull
     */
    public boolean updateDegree(String matricula, Degree degree) {
        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            stmt = connection.createStatement();
            String query = "UPDATE " + TABLE
                    + " SET " + degree.toString() + " WHERE "
                    + PKEY + "=" + esc + matricula + esc;
            System.out.println(query);

            if (stmt.executeUpdate(query) == 1) {
                connection.commit();
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Update Query failed!");
        } finally {
            DBConnection.releaseConnection(connection);
        }
        return false;
    }

    /**
     * Read a given Degree from the DB
     *
     * @param matricula id the matricula of the degree
     * @return a Degree object if it is present in the DB, else empty Degree
     * bean
     */
    public Degree readDegree(String matricula) {
        Connection connection = null;

        if (matricula == null || matricula.equals("")) {
            throw new IllegalArgumentException("Can't read a degree from the Database using an empty matricula");
        } else {
            try {
                connection = DBConnection.getConnection();
                stmt = connection.createStatement();
                rs = stmt.executeQuery("SELECT * FROM " + TABLE
                        + " WHERE " + PKEY + "=\"" + matricula + "\"");
                connection.commit();
                while (rs.next()) {
                    return getDegreeFromResultSet(rs);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new RuntimeException("Read Query failed!");
            } finally {
                DBConnection.releaseConnection(connection);
            }
        }
        return null;
    }

    /**
     * Delete a given Degree from the DB
     *
     * @param matricula of the degree
     * @return true if deleted.
     */
    public boolean deleteDegree(String matricula) {
        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            stmt = connection.createStatement();
            if (stmt.executeUpdate("DELETE FROM " + TABLE
                    + " WHERE " + PKEY + "=\"" + matricula + "\"") == 1) {
                connection.commit();
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Delete Query failed!");
        } finally {
            DBConnection.releaseConnection(connection);
        }
        return false;
    }

    /**
     * Get all the Degrees in the lists
     *
     * @return an ArrayList of Degrees
     */
    public ArrayList<Degree> getAllDegrees() {
        ArrayList<Degree> toReturn = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM " + TABLE + " order by title");
            connection.commit();
            while (rs.next()) {
                Degree b = getDegreeFromResultSet(rs);
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
     * Insert all the degrees in the list into the Database
     *
     * @param list is the list of degrees to insert
     * @return the list of degrees which had an error during the insertion
     * process. So, check if this list is empty to make sure of the insertion
     * process.
     */
    public ArrayList<Degree> insertDegree(ArrayList<Degree> list) {
        ArrayList<Degree> notInserted = new ArrayList<>();

        for (Degree b : list) {
            if (!createDegree(b)) {
                notInserted.add(b);
            }
        }
        return notInserted;
    }

    /**
     * return all the degrees which belong to a given department
     *
     * @param abbreviation the department abbreviation
     * @return a List of degree
     */
    public ArrayList<Degree> getDegreesByDepartment(String abbreviation) {
        String esc = "\"";
        ArrayList<Degree> toReturn = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM " + TABLE
                    + " WHERE department_abbreviation="
                    + esc + abbreviation + esc
                    + " order by title");
            connection.commit();
            while (rs.next()) {
                Degree b = getDegreeFromResultSet(rs);
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
     * Get all the degrees in the lists with a given cycle
     *
     * @param cycle
     * @return ArrayList containing all the related Degrees
     */
    public ArrayList<Degree> getDegreesByCycle(int cycle) {
        ArrayList<Degree> toReturn = new ArrayList<>();
        Connection connection = null;
        if (cycle < 1) {
            throw new IllegalArgumentException("Cycle must be greater than 1");
        } else {
            try {
                connection = DBConnection.getConnection();
                stmt = connection.createStatement();
                String query = "SELECT * FROM " + TABLE
                        + " WHERE cycle_number=" + cycle
                        + " order by title";
                rs = stmt.executeQuery(query);
                connection.commit();
                while (rs.next()) {
                    Degree b = getDegreeFromResultSet(rs);
                    toReturn.add(b);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBConnection.releaseConnection(connection);
            }
        }
        return toReturn;

    }

    /**
     * used to get the unique instance of this class
     *
     * @return
     */
    public static DegreeManager getInstance() {
        if (instance == null) {
            instance = new DegreeManager();
        }
        return instance;
    }

    private Degree getDegreeFromResultSet(ResultSet rs) {
        String matricula;
        try {
            matricula = rs.getString("matricula");
            String link = rs.getString("link");
            String title = rs.getString("title");
            int cycle = rs.getInt("cycle_number");
            String dep = rs.getString("department_abbreviation");
            int active = rs.getInt("active");
            return new Degree(matricula, link, title, cycle, dep, (active > 0));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Degree> getDegreesByDepartmentAndCycle(String depAbbreviation, int cycle) {
        List<Degree> toReturn = new ArrayList<>();
        Connection connection = null;
        String esc = "\'";
        if (cycle < 1) {
            throw new IllegalArgumentException("Cycle must be greater than 1");
        } else {
            try {
                connection = DBConnection.getConnection();
                stmt = connection.createStatement();
                String query = "SELECT * FROM " + TABLE
                        + " WHERE cycle_number=" + cycle + " AND "
                        + "department_abbreviation=" + esc + depAbbreviation + esc
                        + " order by title";
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Degree b = getDegreeFromResultSet(rs);
                    toReturn.add(b);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBConnection.releaseConnection(connection);
            }
        }

        return toReturn;
    }

    /**
     * store the esse3content for a given teaching into the DB
     *
     * @param content
     * @return true if the update has success, else false
     */
    public boolean setEsse3ContentForDegree(String degreeMatricula, String content) {
        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            stmt = connection.createStatement();
            String esc = "\'";
            String query = "UPDATE " + TABLE + " SET " + "esse3_content"
                    + "=" + "\"" + content + "\""
                    + " WHERE " + PKEY
                    + "=" + esc + degreeMatricula + esc;

//UPDATE `degree` SET `esse3_content`='content' WHERE `matricula`='02226'           
            System.out.println("update query: " + query);

            if (stmt.executeUpdate(query) == 1) {
                connection.commit();
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Update Query Failed");
        } finally {
            DBConnection.releaseConnection(connection);
        }
        return false;
    }

    public Degree getDegreeByTitle(String pTitle) throws ConnectionException, SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        Connection connection = null;
        Degree degree = null;

        String query = "select * from degree where title = '" + pTitle + "'";

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);

            if (rs.next()) {
                degree = this.getDegreeFromResultSet(rs);
            }
        } finally {
            DBConnection.releaseConnection(connection);
        }
        return degree;
    }

}
