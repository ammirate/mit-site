package it.unisa.tirocinio.manager.concrete;

import it.unisa.integrazione.database.CycleManager;
import it.unisa.integrazione.database.DBConnection;
import it.unisa.integrazione.database.PersonManager;
import it.unisa.integrazione.database.exception.ConnectionException;
import it.unisa.tirocinio.beans.PendingAcceptance;
import it.unisa.tirocinio.manager.interfaces.IPendingAcceptance;
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
public class ConcretePendingAcceptance implements IPendingAcceptance {

    private static ConcretePendingAcceptance instance = null;

    /**
     *
     * @param idPendingAcceptance
     * @return a PendingAcceptance object which contains informations about a
     * certain pending acceptance id
     */
    @Override
    public PendingAcceptance readStudentInPendingAcceptance(int idPendingAcceptance) {
        PendingAcceptance aStudentInPendingAcceptance = new PendingAcceptance();
        PersonManager aPerson = PersonManager.getInstance();
        Connection connection = null;
        CallableStatement aCallableStatement = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }
            aCallableStatement = connection.prepareCall("{call getPendingStudents(?)}");
            aCallableStatement.setInt("pkPersonSSN", idPendingAcceptance);
            ResultSet rs = aCallableStatement.executeQuery();

            while (rs.next()) {
                aStudentInPendingAcceptance.setIdPendingAcceptance(rs.getInt("id_pending_acceptance"));
                aStudentInPendingAcceptance.setRequestDate(rs.getDate("request_date"));
                aStudentInPendingAcceptance.setPersonSSN(aPerson.readPerson(rs.getString("fk_person")).getSsn());
            }
            rs.close();
            return aStudentInPendingAcceptance;
        } catch (SQLException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ConnectionException ex) {
            Logger.getLogger(ConcretePendingAcceptance.class.getName()).log(Level.SEVERE, null, ex);
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
     * @return an ArrayList of PendingAcceptance which contains all students
     * that are in pending acceptance status
     */
    @Override
    public ArrayList<PendingAcceptance> getAllStudentsInPendingAcceptance() {
        ArrayList<PendingAcceptance> studentsInPendingAcceptance = new ArrayList<PendingAcceptance>();
        PendingAcceptance aStudentInPendingAcceptance = null;
         Connection connection = null;
        CallableStatement aCallableStatement = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }
            PersonManager aPerson = PersonManager.getInstance();
            aCallableStatement = connection.prepareCall("{call getAllOrganizations()}");
            ResultSet rs = aCallableStatement.executeQuery();

            while (rs.next()) {
                aStudentInPendingAcceptance = new PendingAcceptance();
                aStudentInPendingAcceptance.setIdPendingAcceptance(rs.getInt("id_pending_acceptance"));
                aStudentInPendingAcceptance.setRequestDate(rs.getDate("request_date"));
                aStudentInPendingAcceptance.setPersonSSN(aPerson.readPerson(rs.getString("fk_person")).getSsn());
                studentsInPendingAcceptance.add(aStudentInPendingAcceptance);
            }
            rs.close();
            return studentsInPendingAcceptance;

        } catch (SQLException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ConnectionException ex) {
            Logger.getLogger(ConcretePendingAcceptance.class.getName()).log(Level.SEVERE, null, ex);
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
     * @return a ConcretePendingAcceptance object if there are no currently
     * PendingAcceptance objects alive
     */
    public static synchronized ConcretePendingAcceptance getInstance() {
        if (instance == null) {
            instance = new ConcretePendingAcceptance();
        }
        return instance;
    }

}
