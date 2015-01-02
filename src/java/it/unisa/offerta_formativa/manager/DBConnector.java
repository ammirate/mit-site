/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alessandro
 */
public class DBConnector {

    private static Connection conn = null;
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/db_distra";
    private static final String TEST_CONNECTION_STRING = "jdbc:mysql://localhost:3306/db_test_distra";
    
    

//    public Connection getConnection() {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection(CONNECTION_STRING, "root", "");
//        } catch (SQLException | ClassNotFoundException ex) {
//            Logger.getLogger(DegreeManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return conn;
//    }
    
    /**
     * Open and handle the connection to the DB
     * @return 
     */
    public static Statement openConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(CONNECTION_STRING, "root", "");
        } catch (SQLException | ClassNotFoundException ex) {
            //Logger.getLogger(DegreeManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            return conn.createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * close the connection to the DB
     */
    public static void closeConnection() {
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
}
