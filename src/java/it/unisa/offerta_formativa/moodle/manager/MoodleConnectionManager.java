/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.moodle.manager;

import it.unisa.offerta_formativa.manager.DBConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Davide
 */
public class MoodleConnectionManager {

    private static MoodleConnectionManager instance = null;
    private Connection conn = null;
    private Statement stmt = null;
    public static String TABLE = "department";
    public static String PKEY = "id";
    private ResultSet rs = null;

    /**
     * Constructor for Singleton pattern
     */
    private MoodleConnectionManager() {
    }

    public String getUrlMoodle(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Can't read a degree from the Database using id less than one");
        } else {
            try {
                stmt = DBConnector.openConnection();
                rs = stmt.executeQuery("SELECT urlMoodle FROM " + TABLE
                        + " WHERE " + PKEY + "=\"" + id + "\"");
                while (rs.next()) {
                    return rs.getString("urlMoodle");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new RuntimeException("Read Query failed!");
            } finally {
                DBConnector.closeConnection();
            }
        }
        return "error";
    }

    public String getToken(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Can't read a degree from the Database using id less than one");
        } else {
            try {
                stmt = DBConnector.openConnection();
                rs = stmt.executeQuery("SELECT token FROM " + TABLE
                        + " WHERE " + PKEY + "=\"" + id + "\"");
                while (rs.next()) {
                    return rs.getString("token");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new RuntimeException("Read Query failed!");
            }finally {
                DBConnector.closeConnection();
            }
        }
        return "error";
    }

    /**
     * used to get the unique instance of this class
     */
    public static MoodleConnectionManager getInstance() {
        if (instance == null) {
            instance = new MoodleConnectionManager();
        }
        return instance;
    }
}
