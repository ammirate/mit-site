/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import it.unisa.offerta_formativa.beans.Account;

/**
 *
 * @author Antonio
 */
public class AccountManager {

    private Connection conn = null;
    private Statement stmt;
    public static String TABLE = "account";
    public static String PKEY = "email";
    private ResultSet rs = null;

    private static AccountManager instance = null;

    
    private AccountManager() {}
    /**
     * Singleton pattern
     *
     * @return
     */
    public static AccountManager getInstance() {
        if (instance == null) {
            instance = new AccountManager();
        }
        return instance;
    }

    /**
     *
     * @return
     */
    public List<Account> getAllAccounts() {

        List<Account> toReturn = new ArrayList<>();
        try {
//            stmt = DBConnection.getConnection().createStatement();
            stmt = DBConnector.openConnection();

            String query = "SELECT * FROM " + TABLE ;
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                Account a = getAccountFromResultSet(rs);
                toReturn.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//            DBConnection.releaseConnection(conn);
            DBConnector.closeConnection();
        }
        return toReturn;
    }
    
    

    /**
     *
     * @param rs
     * @return
     */
    private Account getAccountFromResultSet(ResultSet rs) {
        String email;
        try {
            email = rs.getString("email");
            String password = rs.getString("password");
            String typeAccount = rs.getString("typeOfAccount");
            return new Account(email, password, typeAccount);
        } catch (SQLException ex) {
            Logger.getLogger(AccountManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
