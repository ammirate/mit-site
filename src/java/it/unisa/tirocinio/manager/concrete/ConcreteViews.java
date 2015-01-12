/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.tirocinio.manager.concrete;

import it.unisa.integrazione.database.DBConnection;
import it.unisa.integrazione.database.PersonManager;
import it.unisa.integrazione.database.exception.ConnectionException;
import it.unisa.tirocinio.beans.ViewsResult;
import it.unisa.tirocinio.manager.interfaces.IView;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlosborges
 */
public class ConcreteViews implements IView {

    private static ConcreteViews instance = null;

    @Override
    public ViewsResult getNumberOfTrainingForOrganization(String OrgVatNumber) {
        Connection connect;
        CallableStatement aCallableStatement;
        ViewsResult aView = new ViewsResult();
        try {
            connect = DBConnection.getConnection();
            if (OrgVatNumber == null) {
                throw new NullPointerException("Organization VAT number is null!");
            }

            aCallableStatement = connect.prepareCall("{call getNumberOfStudentByOrg(?)}");
            aCallableStatement.setString("vat_number", OrgVatNumber);
            ResultSet rs = aCallableStatement.executeQuery();
            while (rs.next()) {
                aView.setCount(rs.getInt("counter"));
            }
            return aView;
        } catch (SQLException ex) {
            Logger.getLogger(ConcreteViews.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ViewsResult getAdminStudentEndedTraining() {
        Connection connect;
        CallableStatement aCallableStatement;
        ViewsResult aView = new ViewsResult();
        try {
            connect = DBConnection.getConnection();
            PersonManager aPerson = PersonManager.getInstance();//TODO modificare quando integrato
            aCallableStatement = connect.prepareCall("{call getStudentTrainingAdminView(?)}");
            aCallableStatement.setInt("functionID", 3);
            ResultSet rs = aCallableStatement.executeQuery();
            while (rs.next()) {
                aView.addPerson(aPerson.readPerson(rs.getString("student_information_SSN")));
            }
            return aView;
        } catch (SQLException ex) {
            Logger.getLogger(ConcreteViews.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ViewsResult getAdminStudentActiveTraining() {
        Connection connect;
        CallableStatement aCallableStatement;
        ViewsResult aView = new ViewsResult();
        try {
            connect = DBConnection.getConnection();
            PersonManager aPerson = PersonManager.getInstance();//TODO modificare quando integrato
            aCallableStatement = connect.prepareCall("{call getStudentTrainingAdminView(?)}");
            aCallableStatement.setInt("functionID", 2);
            ResultSet rs = aCallableStatement.executeQuery();
            while (rs.next()) {
                aView.addPerson(aPerson.readPerson(rs.getString("student_information_SSN")));
            }
            return aView;
        } catch (SQLException ex) {
            Logger.getLogger(ConcreteViews.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ViewsResult getAdminStudentWaitEnded() {
        Connection connect;
        CallableStatement aCallableStatement;
        ViewsResult aView = new ViewsResult();
        try {
            connect = DBConnection.getConnection();
            PersonManager aPerson = PersonManager.getInstance();//TODO modificare quando integrato
            aCallableStatement = connect.prepareCall("{call getStudentTrainingAdminView(?)}");
            aCallableStatement.setInt("functionID", 4);
            ResultSet rs = aCallableStatement.executeQuery();
            while (rs.next()) {
                aView.addPerson(aPerson.readPerson(rs.getString("student_information_SSN")));
            }
            return aView;
        } catch (SQLException ex) {
            Logger.getLogger(ConcreteViews.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ViewsResult getStudentInactiveTraining() {
        Connection connect;
        CallableStatement aCallableStatement;
        ViewsResult aView = new ViewsResult();
        try {
            connect = DBConnection.getConnection();
            PersonManager aPerson = PersonManager.getInstance();//TODO modificare quando integrato
            aCallableStatement = connect.prepareCall("{call getStudentTrainingAdminView(?,?)}");
            aCallableStatement.setInt("functionID", 1);
            ResultSet rs = aCallableStatement.executeQuery();
            while (rs.next()) {
                aView.addPerson(aPerson.readPerson(rs.getString("SSN")));
            }
            return aView;
        } catch (SQLException ex) {
            Logger.getLogger(ConcreteViews.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ViewsResult getProfessorStudentEndedTraining(String SSN) {
        Connection connect;
        CallableStatement aCallableStatement;
        ViewsResult aView = new ViewsResult();
        try {
            connect = DBConnection.getConnection();
            PersonManager aPerson = PersonManager.getInstance();//TODO modificare quando integrato
            aCallableStatement = connect.prepareCall("{call getStudentTrainingProfessorView(?,?)}");
            aCallableStatement.setInt("functionID", 3);
            aCallableStatement.setString("professorSSN", SSN);
            ResultSet rs = aCallableStatement.executeQuery();
            while (rs.next()) {
                aView.addPerson(aPerson.readPerson(rs.getString("student_information_SSN")));
            }
            return aView;
        } catch (SQLException ex) {
            Logger.getLogger(ConcreteViews.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ViewsResult getProfessorStudentActiveTraining(String SSN) {
              Connection connect;
        CallableStatement aCallableStatement;
        ViewsResult aView = new ViewsResult();
        try {
            connect = DBConnection.getConnection();
            PersonManager aPerson = PersonManager.getInstance();//TODO modificare quando integrato
            aCallableStatement = connect.prepareCall("{call getStudentTrainingProfessorView(?,?)}");
            aCallableStatement.setInt("functionID", 2);
            aCallableStatement.setString("professorSSN", SSN);
            ResultSet rs = aCallableStatement.executeQuery();
            while (rs.next()) {
                aView.addPerson(aPerson.readPerson(rs.getString("student_information_SSN")));
            }
            return aView;
        } catch (SQLException ex) {
            Logger.getLogger(ConcreteViews.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ViewsResult getProfessorStudentWaitEnded(String SSN) {
                Connection connect;
        CallableStatement aCallableStatement;
        ViewsResult aView = new ViewsResult();
        try {
            connect = DBConnection.getConnection();
            PersonManager aPerson = PersonManager.getInstance();//TODO modificare quando integrato
            aCallableStatement = connect.prepareCall("{call getStudentTrainingProfessorView(?,?)}");
            aCallableStatement.setInt("functionID", 4);
            aCallableStatement.setString("professorSSN", SSN);
            ResultSet rs = aCallableStatement.executeQuery();
            while (rs.next()) {
                aView.addPerson(aPerson.readPerson(rs.getString("SSN")));
            }
            return aView;
        } catch (SQLException ex) {
            Logger.getLogger(ConcreteViews.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     *
     * @return a ConcreteView instance if there are no
     * ConcreteView instances currently alive
     */
    public static synchronized ConcreteViews getInstance() {
        if (instance == null) {
            instance = new ConcreteViews();
        }
        return instance;
    }

}
