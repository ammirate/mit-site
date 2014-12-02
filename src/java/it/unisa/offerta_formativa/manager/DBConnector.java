/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alessandro
 */
public class DBConnector {
    private static Connection conn=null;
    private static String CONNECTION_STRING="jdbc:mysql://localhost:3306/offerta_didattica_db";
    
    public static java.sql.Connection getConnection(){
        try {
        	Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(CONNECTION_STRING,"root","");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DegreeManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
}
