package it.unisa.tirocinio.manager.concrete;

import it.unisa.integrazione.database.CycleManager;
import it.unisa.integrazione.database.DBConnection;
import it.unisa.integrazione.database.exception.ConnectionException;
import it.unisa.tirocinio.beans.Questionnaire;
import it.unisa.tirocinio.manager.interfaces.IQuestionnaire;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Valentino
 */
public class ConcreteQuestionnaire implements IQuestionnaire {

    private static ConcreteQuestionnaire instance = null;

    /**
     *
     * @return a ConcreteQuestionnaire instance if there are no
     * ConcreteQuestionnaire instances currently alive
     */
    public static synchronized ConcreteQuestionnaire getInstance() {
        if (instance == null) {
            instance = new ConcreteQuestionnaire();
        }
        return instance;
    }

    /**
     *
     * @param aQuestionnaire
     * @return true if insert into Database operation is correct, false
     * otherwise
     */
    @Override
    public boolean insertQuestionnaire(Questionnaire aQuestionnaire) {
        Connection connection = null;
        CallableStatement aCallableStatement = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }
            if (aQuestionnaire == null) {
                throw new NullPointerException("OfferTraining is null!");
            }

            aCallableStatement = connection.prepareCall("{call insertQuestionnaire(?,?,?,?,?,?,?,?,?,?)}");
            aCallableStatement.setString("studentSSN", aQuestionnaire.getStudentSSN());
            aCallableStatement.setString("companyName", aQuestionnaire.getOrganizationName());
            aCallableStatement.setString("typologyOrganization", aQuestionnaire.getOrganizationType());
            aCallableStatement.setString("firs", aQuestionnaire.getFirstQuestion());
            aCallableStatement.setString("secon", aQuestionnaire.getSecondQuestion());
            aCallableStatement.setString("third", aQuestionnaire.getThirdQuestion());
            aCallableStatement.setString("fourth", aQuestionnaire.getFourthQuestion());
            aCallableStatement.setString("fifth", aQuestionnaire.getFifthQuestion());
            aCallableStatement.setString("sixth", aQuestionnaire.getSixthQuestion());
            aCallableStatement.setString("seventh", aQuestionnaire.getSeventhQuestion());

            int check = aCallableStatement.executeUpdate();
            connection.commit();
            aCallableStatement.close();

            return check > 0;

        } catch (SQLException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (ConnectionException ex) {
            Logger.getLogger(ConcreteQuestionnaire.class.getName()).log(Level.SEVERE, null, ex);
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
     * @param SSN
     * @return a Questionnaire object if reading operation from Database is
     * correct, null otherwise
     */
    @Override
    public Questionnaire readQuestionnaire(String SSN) {

        Questionnaire aQuestionnaire = null;
        Connection connection = null;
        CallableStatement aCallableStatement = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }
            aCallableStatement = connection.prepareCall("{call selectQuestionnaire(?)}");
            aCallableStatement.setString("studentSSN", SSN);
            ResultSet rs = aCallableStatement.executeQuery();

            while (rs.next()) {
                aQuestionnaire = new Questionnaire();
                aQuestionnaire.setOrganizationName(rs.getString("company_name"));
                aQuestionnaire.setFirstQuestion(rs.getString("first_question"));
                aQuestionnaire.setFourthQuestion(rs.getString("fourth_question"));
                aQuestionnaire.setFifthQuestion(rs.getString("fifth_question"));
                aQuestionnaire.setSecondQuestion(rs.getString("second_question"));
                aQuestionnaire.setSixthQuestion(rs.getString("sixth_question"));
                aQuestionnaire.setSeventhQuestion(rs.getString("seventh_question"));
                aQuestionnaire.setStudentSSN(rs.getString("student_ssn"));
                aQuestionnaire.setThirdQuestion(rs.getString("third_question"));
                aQuestionnaire.setOrganizationType(rs.getString("typology_organization"));

            }
            rs.close();
            return aQuestionnaire;

        } catch (SQLException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ConnectionException ex) {
            Logger.getLogger(ConcreteQuestionnaire.class.getName()).log(Level.SEVERE, null, ex);
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
     * @return an ArrayList of Questionnaire containing all questionnaires if
     * reading operation from Database is correct, null otherwise
     */
    @Override
    public ArrayList<Questionnaire> readAllQuestionnaire() {

        ArrayList<Questionnaire> allQuestionnaires = new ArrayList<Questionnaire>();
        Questionnaire aQuestionnaire = null;
        Connection connection = null;
        CallableStatement aCallableStatement = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }
            aCallableStatement = connection.prepareCall("{call selectAllQuestionnaire()}");
            ResultSet rs = aCallableStatement.executeQuery();

            while (rs.next()) {
                aQuestionnaire = new Questionnaire();
                aQuestionnaire.setOrganizationName(rs.getString("company_name"));
                aQuestionnaire.setFirstQuestion(rs.getString("first_question"));
                aQuestionnaire.setFourthQuestion(rs.getString("fourth_question"));
                aQuestionnaire.setFifthQuestion(rs.getString("fifth_question"));
                aQuestionnaire.setSecondQuestion(rs.getString("second_question"));
                aQuestionnaire.setSixthQuestion(rs.getString("sixth_question"));
                aQuestionnaire.setSeventhQuestion(rs.getString("seventh_question"));
                aQuestionnaire.setStudentSSN(rs.getString("student_ssn"));
                aQuestionnaire.setThirdQuestion(rs.getString("third_question"));
                aQuestionnaire.setOrganizationType(rs.getString("typologt_organization"));

                allQuestionnaires.add(aQuestionnaire);
            }
            rs.close();
            return allQuestionnaires;

        } catch (SQLException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ConnectionException ex) {
            Logger.getLogger(ConcreteQuestionnaire.class.getName()).log(Level.SEVERE, null, ex);
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
