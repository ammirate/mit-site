/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.manager;

import it.unisa.integrazione.database.DBConnection;
import it.unisa.offerta_formativa.manager.old.DBConnector;
import it.unisa.offerta_formativa.beans.ClassPartition;
import it.unisa.offerta_formativa.manager.Exceptions.ClassPartitionException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Alessandro, Antonio
 */
public class ClassManager {

    private final Connection conn = null;
    private static final String TABLE = "class";
    private Statement stmt;
    private ResultSet rs;
    private static ClassManager instance = null;

    private String esc = "\'";

    private ClassManager() {

    }

    /**
     * Create a new ClassPartition record
     *
     * @param classp - An instance of the ClassPartition bean
     * @return true if created
     */
    public boolean createClass(ClassPartition classp) throws ClassPartitionException {

        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            stmt = connection.createStatement();

            String query = "INSERT INTO " + TABLE + "(teaching_matricula,title) VALUES(" + classp.toStringQueryInsert() + ")";
//            System.out.println("Insert query : " + query);
            if (stmt.executeUpdate(query) == 1) {
                connection.commit();
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new ClassPartitionException("Query failed to create a class");
        } finally {
            DBConnection.releaseConnection(connection);
        }
        return false;
    }

    /**
     * Read the ClassPartition entity with the given idClass
     *
     * @param teachingMatricula
     * @param title
     * @return ClassPartition read. empty instance if not found
     */
    public ClassPartition readClass(String teachingMatricula, String title) throws ClassPartitionException {

        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            stmt = connection.createStatement();

            String query = "SELECT * FROM " + TABLE + " WHERE title=" + esc + title + esc + " and "
                    + "teaching_matricula=" + esc + teachingMatricula + esc;
            rs = stmt.executeQuery(query);
            connection.commit();

            while (rs.next()) {
                return new ClassPartition(rs.getString("teaching_matricula"), rs.getString("title"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new ClassPartitionException("Query failed to read a class");
        } finally {
            DBConnection.releaseConnection(connection);
        }
        return new ClassPartition();
    }

    /**
     * Updates the record with the given ClassPartition id and the given changes
     * into the bean.
     *
     * @param classp - Instance of ClassPartition bean
     * @return true if done.
     */
    public boolean updateClass(ClassPartition oldClass, ClassPartition newClass) throws ClassPartitionException {

        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            stmt = connection.createStatement();

            String query = "UPDATE " + TABLE + " SET " + newClass.toString() + " WHERE "
                    + "title=" + esc + oldClass.getTitle() + esc + " and "
                    + "teaching_matricula=" + esc + oldClass.getTeachingMatricula() + esc;
            System.out.println(query);
            if (stmt.executeUpdate(query) == 1) {
                connection.commit();

                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new ClassPartitionException("Query failed to update class");
        } finally {
            DBConnection.releaseConnection(connection);
        }
        return false;
    }

    /**
     * Delete the record from DB with the given id
     *
     * @param teachingMatricula
     * @param title
     * @return true if done.
     */
    public boolean deleteClass(String teachingMatricula, String title) throws ClassPartitionException {
        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            stmt = connection.createStatement();

            String query = "DELETE FROM " + TABLE + " WHERE title=" + esc + title + esc + " and "
                    + "teaching_matricula=" + esc + teachingMatricula + esc;
            if (stmt.executeUpdate(query) == 1) {
                connection.commit();

                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new ClassPartitionException("Query failed to delete class");
        } finally {
            DBConnection.releaseConnection(connection);
        }
        return false;
    }

    /**
     * Return an instance if it exists. Otherwise creates it.
     *
     * @return
     */
    public static ClassManager getInstance() {
        if (instance == null) {
            instance = new ClassManager();
        }
        return instance;
    }

    /**
     * Given an idTeaching returns a collection of related ClassPartition
     *
     * @param idTeaching of the Teaching entity
     * @return an ArrayList of ClassPartition. Empty if not found any.
     */
    public ArrayList<ClassPartition> getClassesByTeaching(String idTeaching) throws ClassPartitionException {
        ArrayList<ClassPartition> toReturn = new ArrayList<ClassPartition>();
        if (idTeaching == null) {
            throw new IllegalArgumentException("Id cannot be null!");
        } else {
            Connection connection = null;
            try {
                connection = DBConnection.getConnection();
                stmt = connection.createStatement();

                String query = "SELECT * FROM " + TABLE
                        + " WHERE teaching_matricula=\"" + idTeaching + "\""
                        + " order by title";
                rs = stmt.executeQuery(query);
                connection.commit();

                while (rs.next()) {
                    toReturn.add(new ClassPartition(idTeaching, rs.getString("title")));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new ClassPartitionException("Query getClassByTeaching failed");
            } finally {
                DBConnection.releaseConnection(connection);
            }
        }
        return toReturn;
    }

    /**
     * Returns all the inserted Classes
     *
     * @return an ArrayList of ClassPartition. Empty if not found any.
     */
    public ArrayList<ClassPartition> getAllClasses() throws ClassPartitionException {

        ArrayList<ClassPartition> toReturn = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            stmt = connection.createStatement();

            rs = stmt.executeQuery("SELECT * FROM " + TABLE + " order by title");
            connection.commit();

            while (rs.next()) {
                toReturn.add(new ClassPartition(rs.getString("teaching_matricula"), rs.getString("title")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new ClassPartitionException("Query getAll failed");
        } finally {
            DBConnection.releaseConnection(connection);
        }
        return toReturn;
    }
}
