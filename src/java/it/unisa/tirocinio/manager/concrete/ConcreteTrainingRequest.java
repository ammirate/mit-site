package it.unisa.tirocinio.manager.concrete;

import it.unisa.integrazione.database.CycleManager;
import it.unisa.integrazione.database.DBConnection;
import it.unisa.integrazione.database.PersonManager;
import it.unisa.integrazione.database.exception.ConnectionException;
import it.unisa.tirocinio.beans.TrainingRequest;
import it.unisa.tirocinio.beans.TrainingStatus;
import it.unisa.tirocinio.manager.interfaces.ITrainingRequest;
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
public class ConcreteTrainingRequest implements ITrainingRequest {

    private static ConcreteTrainingRequest instance = null;

    /**
     *
     * @param aTrainingRequest
     * @return true if a training request is successfully created, false
     * otherwise
     */
    @Override
    public boolean createTrainingRequest(TrainingRequest aTrainingRequest) {
        Connection connection = null;
        CallableStatement aCallableStatement = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            if (aTrainingRequest == null) {
                throw new NullPointerException("TrainingRequest is null!");
            }

            aCallableStatement = connection.prepareCall("{call insertTrainingRequest(?,?,?,?,?,?)}");
            aCallableStatement.setString("trainingDescription", aTrainingRequest.getDescription());
            aCallableStatement.setString("title", aTrainingRequest.getTitle());
            aCallableStatement.setInt("FK_TrainingStatus", aTrainingRequest.getTrainingStatus());
            aCallableStatement.setString("FK_Person", aTrainingRequest.getPersonSSN());
            aCallableStatement.setString("FK_Organization", aTrainingRequest.getOrganizationVATNumber());
            aCallableStatement.setString("FK_StudentInformationSSN", aTrainingRequest.getStudentSSN());
            int check = aCallableStatement.executeUpdate();
            connection.commit();
            return check > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (ConnectionException ex) {
            Logger.getLogger(ConcreteTrainingRequest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                aCallableStatement.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    /**
     *
     * @param idTraininngRequest
     * @return true if a training request is successfully deleted, false
     * otherwise
     */
    @Override
    public boolean deleteTrainingRequest(int idTraininngRequest) {
        Connection connection = null;
        CallableStatement aCallableStatement = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            aCallableStatement = connection.prepareCall("{call deleteTrainingRequest(?)}");
            aCallableStatement.setInt("idTrainingRequest", idTraininngRequest);
            int check = aCallableStatement.executeUpdate();
            connection.commit();
            return check > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (ConnectionException ex) {
            Logger.getLogger(ConcreteTrainingRequest.class.getName()).log(Level.SEVERE, null, ex);
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
     * @param aTrainingRequest
     * @return true if a training request is successfully updated, false
     * otherwise
     */
    @Override
    public boolean updateTrainingRequest(TrainingRequest aTrainingRequest) {
        Connection connection = null;
        CallableStatement aCallableStatement = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            if (aTrainingRequest == null) {
                throw new NullPointerException("TrainingRequest is null!");
            }

            aCallableStatement = connection.prepareCall("{call updateTrainingRequest(?,?,?,?,?,?,?)}");
            aCallableStatement.setInt("ID", aTrainingRequest.getIdTrainingRequest());
            aCallableStatement.setString("trainingDescription", aTrainingRequest.getDescription());
            aCallableStatement.setString("titl", aTrainingRequest.getTitle());
            aCallableStatement.setInt("FK_TrainingStatus", aTrainingRequest.getTrainingStatus());
            aCallableStatement.setString("FK_Perso", aTrainingRequest.getPersonSSN());
            aCallableStatement.setString("FK_Organizatio", aTrainingRequest.getOrganizationVATNumber());
            aCallableStatement.setString("FK_StudentInformationSSN", aTrainingRequest.getStudentSSN());
            int check = aCallableStatement.executeUpdate();
            connection.commit();
            return check > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (ConnectionException ex) {
            Logger.getLogger(ConcreteTrainingRequest.class.getName()).log(Level.SEVERE, null, ex);
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
     * @param aTrainingRequest
     * @return a TrainingRequest object if reading operation from Database is
     * successfully done, null otherwise
     */
    @Override
    public TrainingRequest readTrainingRequest(int aTrainingRequest) {
        TrainingRequest aTraining = new TrainingRequest();
        ConcreteTrainingStatus aTrainingStatus = ConcreteTrainingStatus.getInstance();
        PersonManager aPerson = PersonManager.getInstance();
        ConcreteOrganization anOrganization = ConcreteOrganization.getInstance().getInstance();
        ConcreteStudentInformation aStudentInformation = ConcreteStudentInformation.getInstance();
        Connection connection = null;
        CallableStatement aCallableStatement = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            aCallableStatement = connection.prepareCall("{call getTrainingRequest(?)}");
            aCallableStatement.setInt("idTrainingRequest", aTrainingRequest);
            ResultSet rs = aCallableStatement.executeQuery();

            while (rs.next()) {
                aTraining.setIdTrainingRequest(rs.getInt("id_training_request"));

                aTraining.setDescription(rs.getString("description"));
                aTraining.setTitle(rs.getString("title"));

                aTraining.setOrganizationVATNumber(anOrganization.readOrganization(rs.getString("fk_organization")).getVATNumber());
                aTraining.setPersonSSN(aPerson.readPerson(rs.getString("fk_person")).getSsn());
                aTraining.setStudentSSN(aStudentInformation.readStudentInformation(rs.getString("student_information_SSN")).getStudentSSN());//studentInformation
                aTraining.setTrainingStatus(aTrainingStatus.readTrainingStatus(rs.getInt("fk_training_status")).getIdTrainingStatus());

            }
            rs.close();
            return aTraining;
        } catch (SQLException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ConnectionException ex) {
            Logger.getLogger(ConcreteTrainingRequest.class.getName()).log(Level.SEVERE, null, ex);
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
     * @param VATNumber
     * @return an ArrayList of TrainingRequest which contains all training
     * request pubblished by a certain organization
     */
    @Override
    public ArrayList<TrainingRequest> readTrainingRequestByOrganization(String VATNumber) {
        ArrayList<TrainingRequest> trainingRequests = new ArrayList<TrainingRequest>();
        TrainingRequest aTrainingRequest = null;
        Connection connection = null;
        CallableStatement aCallableStatement = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            ConcreteTrainingStatus aTrainingStatus = ConcreteTrainingStatus.getInstance();
            PersonManager aPerson = PersonManager.getInstance();
            ConcreteOrganization anOrganization = ConcreteOrganization.getInstance().getInstance();
            ConcreteStudentInformation aStudentInformation = ConcreteStudentInformation.getInstance();

            aCallableStatement = connection.prepareCall("{call getOwnTrainingRequests(?)}");
            aCallableStatement.setString("vatNumber", VATNumber);
            ResultSet rs = aCallableStatement.executeQuery();

            while (rs.next()) {
                aTrainingRequest = new TrainingRequest();
                aTrainingRequest.setIdTrainingRequest(rs.getInt("id_training_request"));

                aTrainingRequest.setDescription(rs.getString("description"));
                aTrainingRequest.setTitle(rs.getString("title"));

                aTrainingRequest.setOrganizationVATNumber(anOrganization.readOrganization(rs.getString("fk_organization")).getVATNumber());
                aTrainingRequest.setPersonSSN(aPerson.readPerson(rs.getString("fk_person")).getSsn());
                aTrainingRequest.setStudentSSN(aStudentInformation.readStudentInformation(rs.getString("student_information_SSN")).getStudentSSN());//studentInformation
                aTrainingRequest.setTrainingStatus(aTrainingStatus.readTrainingStatus(rs.getInt("fk_training_status")).getIdTrainingStatus());

                trainingRequests.add(aTrainingRequest);
            }
            rs.close();
            return trainingRequests;

        } catch (SQLException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ConnectionException ex) {
            Logger.getLogger(ConcreteTrainingRequest.class.getName()).log(Level.SEVERE, null, ex);
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
     * @return an ArrayList of TrainingRequest which contains all training
     * requests
     */
    @Override
    public ArrayList<TrainingRequest> getAllTrainingRequests() {
        ArrayList<TrainingRequest> trainingRequests = new ArrayList<TrainingRequest>();
        TrainingRequest aTrainingRequest = null;
        Connection connection = null;
        CallableStatement aCallableStatement = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            ConcreteTrainingStatus aTrainingStatus = ConcreteTrainingStatus.getInstance();
            PersonManager aPerson = PersonManager.getInstance();
            ConcreteOrganization anOrganization = ConcreteOrganization.getInstance().getInstance();
            ConcreteStudentInformation aStudentInformation = ConcreteStudentInformation.getInstance();

            aCallableStatement = connection.prepareCall("{call getAllTrainingRequests()}");
            ResultSet rs = aCallableStatement.executeQuery();

            while (rs.next()) {
                aTrainingRequest = new TrainingRequest();
                aTrainingRequest.setIdTrainingRequest(rs.getInt("id_training_request"));

                aTrainingRequest.setDescription(rs.getString("description"));
                aTrainingRequest.setTitle(rs.getString("title"));

                aTrainingRequest.setOrganizationVATNumber(anOrganization.readOrganization(rs.getString("fk_organization")).getVATNumber());
                aTrainingRequest.setPersonSSN(aPerson.readPerson(rs.getString("fk_person")).getSsn());
                aTrainingRequest.setStudentSSN(aStudentInformation.readStudentInformation(rs.getString("student_information_SSN")).getStudentSSN());//studentInformation
                aTrainingRequest.setTrainingStatus(aTrainingStatus.readTrainingStatus(rs.getInt("fk_training_status")).getIdTrainingStatus());

                trainingRequests.add(aTrainingRequest);
            }
            rs.close();
            return trainingRequests;

        } catch (SQLException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ConnectionException ex) {
            Logger.getLogger(ConcreteTrainingRequest.class.getName()).log(Level.SEVERE, null, ex);
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
     * @param idTrainingRequest
     * @param aStatus
     * @return true if a certain training request is successfully updated, false
     * otherwise
     */
    @Override
    public boolean changeTrainingStatus(int idTrainingRequest, TrainingStatus aStatus) {
        Connection connection = null;
        CallableStatement aCallableStatement = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            if (aStatus == null) {
                throw new NullPointerException("Status is null!");
            }

            aCallableStatement = connection.prepareCall("{call changeTrainingStatus(?,?)}");
            aCallableStatement.setInt("ID", idTrainingRequest);
            aCallableStatement.setInt("FK_TrainingStatus", aStatus.getIdTrainingStatus());
            int check = aCallableStatement.executeUpdate();
            connection.commit();
            return check > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (ConnectionException ex) {
            Logger.getLogger(ConcreteTrainingRequest.class.getName()).log(Level.SEVERE, null, ex);
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
     * @param idTrainingRequest
     * @return return true if a certain training request is internship, false
     * otherwise
     */
    @Override
    public boolean isInternship(int idTrainingRequest) {
        boolean toReturn = false;
        Connection connection = null;
        CallableStatement aCallableStatement = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            aCallableStatement = connection.prepareCall("{call getIsInternships(?)}");
            aCallableStatement.setInt("idTrainingRequest", idTrainingRequest);
            ResultSet rs = aCallableStatement.executeQuery();

            while (rs.next()) {
                toReturn = true;
                break;
            }
            rs.close();
            return toReturn;

        } catch (SQLException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (ConnectionException ex) {
            Logger.getLogger(ConcreteTrainingRequest.class.getName()).log(Level.SEVERE, null, ex);
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
     * @return an ArrayList of TrainingRequest which contains all internship
     */
    @Override
    public ArrayList<TrainingRequest> getAllInternships() {
        ArrayList<TrainingRequest> trainingRequests = new ArrayList<TrainingRequest>();
        TrainingRequest aTrainingRequest = null;
        Connection connection = null;
        CallableStatement aCallableStatement = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            ConcreteTrainingStatus aTrainingStatus = ConcreteTrainingStatus.getInstance();
            PersonManager aPerson = PersonManager.getInstance();
            ConcreteOrganization anOrganization = ConcreteOrganization.getInstance().getInstance();
            ConcreteStudentInformation aStudentInformation = ConcreteStudentInformation.getInstance();

            aCallableStatement = connection.prepareCall("{call getAllInternships()}");
            ResultSet rs = aCallableStatement.executeQuery();

            while (rs.next()) {
                aTrainingRequest = new TrainingRequest();
                aTrainingRequest.setIdTrainingRequest(rs.getInt("id_training_request"));

                aTrainingRequest.setDescription(rs.getString("description"));
                aTrainingRequest.setTitle(rs.getString("title"));

                aTrainingRequest.setOrganizationVATNumber(anOrganization.readOrganization(rs.getString("fk_organization")).getVATNumber());
                aTrainingRequest.setPersonSSN(aPerson.readPerson(rs.getString("fk_person")).getSsn());
                aTrainingRequest.setStudentSSN(aStudentInformation.readStudentInformation(rs.getString("student_information_SSN")).getStudentSSN());//studentInformation
                aTrainingRequest.setTrainingStatus(aTrainingStatus.readTrainingStatus(rs.getInt("fk_training_status")).getIdTrainingStatus());

                trainingRequests.add(aTrainingRequest);
            }
            rs.close();
            return trainingRequests;

        } catch (SQLException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ConnectionException ex) {
            Logger.getLogger(ConcreteTrainingRequest.class.getName()).log(Level.SEVERE, null, ex);
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
     * @param SSN
     * @return an ArrayList of TrainingRequest which contains all training
     * requests published by a professor
     */
    @Override
    public ArrayList<TrainingRequest> readTrainingRequestByProfessor(String SSN) {
        ArrayList<TrainingRequest> trainingRequests = new ArrayList<TrainingRequest>();
        TrainingRequest aTrainingRequest = null;
        Connection connection = null;
        CallableStatement aCallableStatement = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            ConcreteTrainingStatus aTrainingStatus = ConcreteTrainingStatus.getInstance();
            PersonManager aPerson = PersonManager.getInstance();
            ConcreteOrganization anOrganization = ConcreteOrganization.getInstance().getInstance();
            ConcreteStudentInformation aStudentInformation = ConcreteStudentInformation.getInstance();

            aCallableStatement = connection.prepareCall("{call getInnerTrainingRequests(?)}");
            aCallableStatement.setString("SSN", SSN);
            ResultSet rs = aCallableStatement.executeQuery();

            while (rs.next()) {
                aTrainingRequest = new TrainingRequest();
                aTrainingRequest.setIdTrainingRequest(rs.getInt("id_training_request"));

                aTrainingRequest.setDescription(rs.getString("description"));
                aTrainingRequest.setTitle(rs.getString("title"));

                aTrainingRequest.setOrganizationVATNumber(anOrganization.readOrganization(rs.getString("fk_organization")).getVATNumber());
                aTrainingRequest.setPersonSSN(aPerson.readPerson(rs.getString("fk_person")).getSsn());
                aTrainingRequest.setStudentSSN(aStudentInformation.readStudentInformation(rs.getString("student_information_SSN")).getStudentSSN());//studentInformation
                aTrainingRequest.setTrainingStatus(aTrainingStatus.readTrainingStatus(rs.getInt("fk_training_status")).getIdTrainingStatus());

                trainingRequests.add(aTrainingRequest);
            }
            rs.close();
            return trainingRequests;

        } catch (SQLException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ConnectionException ex) {
            Logger.getLogger(ConcreteTrainingRequest.class.getName()).log(Level.SEVERE, null, ex);
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
     * @param SSN
     * @return a TrainingRequest object if reading operation from Database is
     * correct, null otherwise
     */
    @Override
    public TrainingRequest readTrainingRequestByStudent(String SSN) {
        TrainingRequest aTraining = new TrainingRequest();
        Connection connection = null;
        CallableStatement aCallableStatement = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            aCallableStatement = connection.prepareCall("{call getStudentInformationTrainingRequests(?)}");
            aCallableStatement.setString("SSN", SSN);
            ResultSet rs = aCallableStatement.executeQuery();

            while (rs.next()) {
                aTraining.setIdTrainingRequest(rs.getInt("id_training_request"));

                aTraining.setDescription(rs.getString("description"));
                aTraining.setTitle(rs.getString("title"));

                aTraining.setOrganizationVATNumber(rs.getString("fk_organization"));
                aTraining.setPersonSSN(rs.getString("fk_person"));
                aTraining.setStudentSSN(rs.getString("student_information_SSN"));//studentInformation
                aTraining.setTrainingStatus(rs.getInt("fk_training_status"));

            }
            rs.close();
            return aTraining;
        } catch (SQLException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ConnectionException ex) {
            Logger.getLogger(ConcreteTrainingRequest.class.getName()).log(Level.SEVERE, null, ex);
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
     * @return a ConcreteTrainingRequest instance if there are no
     * ConcreteTrainingRequest instances currently alive
     */
    public static synchronized ConcreteTrainingRequest getInstance() {
        if (instance == null) {
            instance = new ConcreteTrainingRequest();
        }
        return instance;
    }

}
