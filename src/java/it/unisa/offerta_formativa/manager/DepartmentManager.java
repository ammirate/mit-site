/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.manager;

import java.sql.Connection;
import java.sql.SQLException;
import it.unisa.offerta_formativa.beans.Department;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Alessandro, Antonio
 */
public class DepartmentManager {

    private Connection conn = null;
    private Statement stmt;
    public static String TABLE = "department";
    public static String PKEY = "abbreviation";
    private ResultSet rs = null;

    private static DepartmentManager instance = null;

    /**
     * Constructor for Singleton pattern
     */
    private DepartmentManager() {
    }

    /**
     * Insert a Department into the DB
     *
     * @param dept
     * @return true if the insertion was successfull
     */
    public boolean createDepartment(Department dept) {
        try {
            stmt = DBConnector.openConnection();
            //            stmt = DBConnector.openConnection();

            String query = "INSERT INTO " + TABLE + "(abbreviation,title,url_moodle,token) VALUES(" + dept.toStringQueryInsert() + ")";
            if (stmt.executeUpdate(query) == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Insertion Query failed!");
        } finally {
            DBConnector.closeConnection();
            //            DBConnector.closeConnection();

        }
        return false;
    }

    /**
     * Update a department into the DB
     *
     * @return true if the update was successfull
     */
    public boolean updateDepartment(String abbrev, Department dept) {
        try {
            String esc = "\'";
            stmt = DBConnector.openConnection();
            String query = "UPDATE " + TABLE + " SET " + dept.toString() + " WHERE "
                    + PKEY + "=" + esc + abbrev + esc;
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
     * Read a given department from the DB
     *
     * @param id of the department
     * @return a department object if it is present in the DB, else empty
     * department bean
     */
    public Department readDepartment(String abbreviation) {
        if (abbreviation == null || abbreviation.equalsIgnoreCase("")) {
            throw new IllegalArgumentException("Can't read a degree from the Database using id less than one");
        } else {
            try {
                stmt = DBConnector.openConnection();
                String query = "SELECT * FROM " + TABLE
                        + " WHERE " + PKEY + "=\'" + abbreviation + "\' ";
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    return new Department(rs.getString("abbreviation"), rs.getString("title"), rs.getString("url_moodle"), rs.getString("token"));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new RuntimeException("Read Query failed!");
            } finally {
                DBConnector.closeConnection();
            }
        }
        return null;
    }

    /**
     * Delete a given department from the DB
     *
     * @param id of department
     * @return true if deleted.
     */
    public boolean deleteDepartment(String abbreviation) {
        try {
            stmt = DBConnector.openConnection();
            if (stmt.executeUpdate("DELETE FROM " + TABLE
                    + " WHERE " + PKEY + "=\"" + abbreviation + "\"") == 1) {
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
     * Get all the Department in the lists
     *
     * @return an ArrayList of Department
     */
    public ArrayList<Department> getAllDepartments() {
        ArrayList<Department> toReturn = new ArrayList<Department>();
        try {
            stmt = DBConnector.openConnection();
            rs = stmt.executeQuery("SELECT * FROM " + TABLE + " order by title");

            while (rs.next()) {
                Department b = getDepartmentFromResultSet(rs);
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
    public static DepartmentManager getInstance() {
        if (instance == null) {
            instance = new DepartmentManager();
        }
        return instance;
    }

    /**
     * Create a Department from a ResultSet object
     *
     * @param rs
     * @return
     */
    private Department getDepartmentFromResultSet(ResultSet rs) {
        try {
            String tit = rs.getString("title");
            String abbreviation = rs.getString("abbreviation");
            String url = rs.getString("url_moodle");
            String token = rs.getString("token");
            return new Department(abbreviation, tit, url, token);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
