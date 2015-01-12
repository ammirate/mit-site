package it.unisa.integrazione.database;

import it.unisa.integrazione.database.exception.ConnectionException;
import it.unisa.integrazione.model.Account;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import it.unisa.integrazione.model.Person;
import it.unisa.tirocinio.manager.concrete.ConcreteOrganization;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gemmacatolino
 */
public class AccountManager {

    private static AccountManager instance;

    public static AccountManager getInstance() {

        if (instance == null) {
            instance = new AccountManager();
        }
        return instance;

    }

    public Person login(String pUsername, String pPassword) throws SQLException, ConnectionException {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        Person person = null;

        String query = "select * from account where email='" + pUsername + "' and password='" + pPassword + "'";

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);

            if (rs.next()) {
                Account account = new Account();
                account.setEmail(rs.getString("email"));
                account.setPassword(rs.getString("password"));
                account.setTypeOfAccount(rs.getString("typeOfAccount"));
                account.setActive(rs.getBoolean("active"));
                
                person = PersonManager.getInstance().getPersonByEmail(account.getEmail());
                
            }

        } finally {
            DBConnection.releaseConnection(connection);
        }
        
        return person;
    }

    public void add(Account pAccount) throws SQLException {
        Connection connect = DBConnection.getConnection();

        String sql = "INSERT INTO account (email, account.password, typeOfAccount, account.active) VALUES ('" + pAccount.getEmail() + "','" + pAccount.getPassword() + "','" + pAccount.getTypeOfAccount() + "'," + pAccount.isActive() + ")";

        try {
            Statement stmt = connect.createStatement();
            stmt.executeUpdate(sql);
            connect.commit();
        } finally {
            DBConnection.releaseConnection(connect);
        }
    } 
    
    public Account getAccoutnByEmail(String pEmail) throws SQLException, ConnectionException {
        Statement stmt = null;
        ResultSet rs = null;
        Connection connection = null;
        Account account = null;

        String query = "select * from account where email = '" + pEmail + "'";

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);

            if (rs.next()) {
                account = new Account();
                
                account.setActive(rs.getBoolean("active"));
                account.setEmail(rs.getString("email"));
                account.setPassword(rs.getString("password"));
                account.setTypeOfAccount(rs.getString("typeOfAccount"));
            }
        } finally {

         DBConnection.releaseConnection(connection);
        }

        return account;
    }
    
    /* --- Tirocinio --- */
    /**
     *
     * @param account
     * @return true if account is created, false otherwise
     */
    public boolean createAccount(Account account) throws SQLException {
        Connection connection = null;
        CallableStatement aCallableStatement = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            if (account == null) {
                throw new NullPointerException("Account is null!");
            }

            aCallableStatement = connection.prepareCall("{call insertAccount(?,?,?,?)}");
            aCallableStatement.setString("emailInput", account.getEmail());
            aCallableStatement.setString("passwordInput", account.getPassword());
            aCallableStatement.setString("typology", account.getTypeOfAccount());
            aCallableStatement.setBoolean("activeInput", account.isActive());
            int check = aCallableStatement.executeUpdate();
            connection.commit();
            return check > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (ConnectionException ex) {
            Logger.getLogger(AccountManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            aCallableStatement.close();
            DBConnection.releaseConnection(connection);
        }
        return false;
    }

    /**
     *
     * @param email
     * @return an Account object if reading operation is correct, null otherwise
     */
    public Account readAccount(String email) throws SQLException {
        Connection connection = null;
        CallableStatement aCallableStatement = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            Account anAccount = new Account();
            aCallableStatement = connection.prepareCall("{call getAccount(?)}");
            aCallableStatement.setString("pkAccount", email);
            ResultSet rs = aCallableStatement.executeQuery();

            while (rs.next()) {
                anAccount.setEmail(rs.getString("email"));
                anAccount.setPassword(rs.getString("password"));
                anAccount.setTypeOfAccount(rs.getString("typeOfAccount"));
                anAccount.setActive(rs.getBoolean("active"));
            }
            rs.close();
            return anAccount;
        } catch (SQLException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ConnectionException ex) {
            Logger.getLogger(AccountManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            aCallableStatement.close();
            DBConnection.releaseConnection(connection);
        }
        return null;
    }

    /**
     *
     * @return an ArrayList of Account if DB select is correct, null otherwise
     */
    public ArrayList<Account> getAllAccounts() throws ConnectionException, SQLException {

        ArrayList<Account> accounts = new ArrayList<Account>();
        Account anAccount = null;
        Connection connection = null;
        CallableStatement aCallableStatement = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            aCallableStatement = connection.prepareCall("{call getAllAccounts()}");
            ResultSet rs = aCallableStatement.executeQuery();

            while (rs.next()) {
                anAccount = new Account();
                anAccount.setEmail(rs.getString("email"));
                anAccount.setPassword(rs.getString("password"));
                anAccount.setTypeOfAccount(rs.getString("typeOfAccount"));
                anAccount.setActive(rs.getBoolean("active"));
                accounts.add(anAccount);
            }
            rs.close();
            return accounts;

        } catch (SQLException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            aCallableStatement.close();
            DBConnection.releaseConnection(connection);
        }
    }

    /**
     *
     * @param email
     * @return type of account
     */
    public String getTypeOfAccount(String email) throws SQLException {
        Connection connection = null;
        CallableStatement aCallableStatement = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            String aTypeOfAccount = null;
            aCallableStatement = connection.prepareCall("{call getTypeOfAccount(?)}");
            aCallableStatement.setString("pkAccount", email);
            ResultSet rs = aCallableStatement.executeQuery();

            while (rs.next()) {
                aTypeOfAccount = rs.getString("typeOfAccount");
            }
            rs.close();
            return aTypeOfAccount;
        } catch (SQLException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ConnectionException ex) {
            Logger.getLogger(AccountManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            aCallableStatement.close();
            DBConnection.releaseConnection(connection);
        }
        return null;
    }
}
