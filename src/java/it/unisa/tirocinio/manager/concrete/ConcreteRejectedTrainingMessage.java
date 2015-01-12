package it.unisa.tirocinio.manager.concrete;

import it.unisa.integrazione.database.CycleManager;
import it.unisa.integrazione.database.DBConnection;
import it.unisa.integrazione.database.PersonManager;
import it.unisa.integrazione.database.exception.ConnectionException;
import it.unisa.tirocinio.beans.RejectedTrainingMessage;
import it.unisa.tirocinio.manager.interfaces.IRejectedTrainingMessage;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author johneisenheim
 */
public class ConcreteRejectedTrainingMessage implements IRejectedTrainingMessage {

    private static ConcreteRejectedTrainingMessage instance = null;

    /**
     *
     * @param aRejectedTrainingMessage
     * @return true if a RejectedTrainingMessage is created without errors,
     * false otherwise
     */
    @Override
    public boolean createRejectedTrainingMessage(RejectedTrainingMessage aRejectedTrainingMessage) {
        Connection connection = null;
        CallableStatement aCallableStatement = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            if (aRejectedTrainingMessage == null) {
                throw new NullPointerException("RejectedMessage is null!");
            }

            aCallableStatement = connection.prepareCall("{call insertRejectedTrainingMessage(?,?)}");
            aCallableStatement.setString("message", aRejectedTrainingMessage.getDescription());
            aCallableStatement.setString("personSSN", aRejectedTrainingMessage.getPersonSSN());
            int check = aCallableStatement.executeUpdate();
            connection.commit();
            return check > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (ConnectionException ex) {
            Logger.getLogger(ConcreteRejectedTrainingMessage.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                aCallableStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(CycleManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            DBConnection.releaseConnection(connection);
        }
        return false;
    }

    /**
     *
     * @param aRejectedTrainingMessage
     * @return true if a RejectedTrainingMessage object is correctly updated
     */
    @Override
    public boolean updateRejectedTrainingMessage(RejectedTrainingMessage aRejectedTrainingMessage) {
        Connection connection = null;
        CallableStatement aCallableStatement = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            if (aRejectedTrainingMessage == null) {
                throw new NullPointerException("RejectedMessage is null!");
            }

            aCallableStatement = connection.prepareCall("{call updateRejectedTrainingMessage(?,?)}");
            aCallableStatement.setString("message", aRejectedTrainingMessage.getDescription());
            aCallableStatement.setString("personSSN", aRejectedTrainingMessage.getPersonSSN());
            int check = aCallableStatement.executeUpdate();
            connection.commit();
            return check > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (ConnectionException ex) {
            Logger.getLogger(ConcreteRejectedTrainingMessage.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                aCallableStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(CycleManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            DBConnection.releaseConnection(connection);
        }
        return false;
    }

    /**
     *
     * @param idRejectedTrainingMessage
     * @return true if a certain RejectedTrainingMessage object is successfully
     * deleted, false otherwise
     */
    @Override
    public boolean deleteOrganization(int idRejectedTrainingMessage) {
        Connection connection = null;
        CallableStatement aCallableStatement = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            aCallableStatement = connection.prepareCall("{call deleteRejectedTrainingMessage(?)}");
            aCallableStatement.setInt("pkRejectedTraining", idRejectedTrainingMessage);
            int check = aCallableStatement.executeUpdate();
            connection.commit();
            return check > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (ConnectionException ex) {
            Logger.getLogger(ConcreteRejectedTrainingMessage.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                aCallableStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(CycleManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            DBConnection.releaseConnection(connection);
        }
        return false;
    }

    /**
     *
     * @param idRejectedTraingMessage
     * @return a RejectedTrainingMessage object if reading operation from
     * Database is correct, null otherwise
     */
    @Override
    public RejectedTrainingMessage readTrainingMessage(int idRejectedTraingMessage) {

        RejectedTrainingMessage aRejectedMessage = new RejectedTrainingMessage();
        PersonManager aPerson = PersonManager.getInstance();
        Connection connection = null;
        CallableStatement aCallableStatement = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            aCallableStatement = connection.prepareCall("{call getRejectedTrainingMessage(?)}");
            aCallableStatement.setInt("pkRejectedTrainingMessage", idRejectedTraingMessage);
            ResultSet rs = aCallableStatement.executeQuery();

            while (rs.next()) {
                aRejectedMessage.setDescription(rs.getString("description"));
                aRejectedMessage.setIdRejectedTraingMessage(rs.getInt("id_rejected_training_message"));
                aRejectedMessage.setPersonSSN(aPerson.readPerson(rs.getString("fk_person")).getSsn());
            }
            rs.close();
            return aRejectedMessage;
        } catch (SQLException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ConnectionException ex) {
            Logger.getLogger(ConcreteRejectedTrainingMessage.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                aCallableStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(CycleManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            DBConnection.releaseConnection(connection);
        }
        return null;
    }

    /**
     *
     * @return an ArrayList of RejectedTrainingMessage which contains all
     * rejected training messages
     */
    @Override
    public ArrayList<RejectedTrainingMessage> getAllTrainingMessages() {
        ArrayList<RejectedTrainingMessage> rejectedMessages = new ArrayList<RejectedTrainingMessage>();
        RejectedTrainingMessage aRejectedMessage = null;
        Connection connection = null;
        CallableStatement aCallableStatement = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            PersonManager aPerson = PersonManager.getInstance();
            aCallableStatement = connection.prepareCall("{call getAllRejectedTrainingMessage()}");
            ResultSet rs = aCallableStatement.executeQuery();

            while (rs.next()) {
                aRejectedMessage.setDescription(rs.getString("description"));
                aRejectedMessage.setIdRejectedTraingMessage(rs.getInt("id_rejected_training_message"));
                aRejectedMessage.setPersonSSN(aPerson.readPerson(rs.getString("fk_person")).getSsn());
                rejectedMessages.add(aRejectedMessage);
            }
            rs.close();
            return rejectedMessages;
        } catch (SQLException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ConnectionException ex) {
            Logger.getLogger(ConcreteRejectedTrainingMessage.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                aCallableStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(CycleManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            DBConnection.releaseConnection(connection);
        }
        return null;
    }

    /**
     *
     * @return a ConcreteRejectedTrainingMessage object if there are no
     * instances of ConcreteRejectedTrainingMessage currently alive
     */
    public static synchronized ConcreteRejectedTrainingMessage getInstance() {
        if (instance == null) {
            instance = new ConcreteRejectedTrainingMessage();
        }
        return instance;
    }

    /**
     *
     * @param ssn
     * @return a RejectedTrainingMessage if reading operation from Database is
     * correct, null otherwise
     */
    @Override
    public RejectedTrainingMessage readLastTrainingMessage(String ssn) {
        RejectedTrainingMessage aRejectedMessage = null;
        Connection connection = null;
        CallableStatement aCallableStatement = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            aRejectedMessage = new RejectedTrainingMessage();
            aCallableStatement = connection.prepareCall("{call getRejectedTrainingMessageBySSN(?)}");
            aCallableStatement.setString("ssn", ssn);
            ResultSet rs = aCallableStatement.executeQuery();

            while (rs.next()) {
                aRejectedMessage.setDescription(rs.getString("description"));
                aRejectedMessage.setIdRejectedTraingMessage(rs.getInt("id_rejected_training_message"));
                aRejectedMessage.setPersonSSN(ssn);
            }
            rs.close();
            return aRejectedMessage;
        } catch (SQLException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ConnectionException ex) {
            Logger.getLogger(ConcreteRejectedTrainingMessage.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                aCallableStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(CycleManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            DBConnection.releaseConnection(connection);
        }
        return null;
    }
}
