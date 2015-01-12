package it.unisa.tirocinio.manager.concrete;

import it.unisa.integrazione.database.CycleManager;
import it.unisa.integrazione.database.DBConnection;
import it.unisa.integrazione.database.DepartmentManager;
import it.unisa.integrazione.database.PersonManager;
import it.unisa.integrazione.database.exception.ConnectionException;
import it.unisa.integrazione.model.Account;
import it.unisa.integrazione.model.Person;
import it.unisa.tirocinio.beans.TrainingOffer;
import it.unisa.tirocinio.manager.interfaces.ITrainingOffer;
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
public class ConcreteTrainingOffer implements ITrainingOffer {

    private static ConcreteTrainingOffer instance = null;

    /**
     *
     * @param aTrainingOffer
     * @return true if creation of an inner training offer is correct, false
     * otherwise
     */
    @Override
    public boolean createInnerTrainingOffer(TrainingOffer aTrainingOffer) {
        Connection connection = null;
        CallableStatement aCallableStatement = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            if (aTrainingOffer == null) {
                throw new NullPointerException("OfferTraining is null!");
            }

            aCallableStatement = connection.prepareCall("{call insertInnerTraining(?,?,?)}");
            aCallableStatement.setString("trainingDescription", aTrainingOffer.getDescription());
            aCallableStatement.setString("FK_PersonSSN", aTrainingOffer.getPersonSSN());
            aCallableStatement.setString("FK_Department", aTrainingOffer.getDepartment());

            int check = aCallableStatement.executeUpdate();
            connection.commit();

            return check > 0;

        } catch (SQLException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (ConnectionException ex) {
            Logger.getLogger(ConcreteTrainingOffer.class.getName()).log(Level.SEVERE, null, ex);
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
     * @param aTrainingOffer
     * @return true if creation of an outer training offer is correct, false
     * otherwise
     */
    @Override
    public boolean createOuterTrainingOffer(TrainingOffer aTrainingOffer) {
        Connection connection = null;
        CallableStatement aCallableStatement = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            if (aTrainingOffer == null) {
                throw new NullPointerException("OfferTraining is null!");
            }

            aCallableStatement = connection.prepareCall("{call insertOuterTraining(?,?,?,?)}");
            aCallableStatement.setString("trainingDescription", aTrainingOffer.getDescription());
            aCallableStatement.setString("FK_Organization", aTrainingOffer.getOrganization());
            aCallableStatement.setString("FK_PersonSSN", aTrainingOffer.getPersonSSN());
            aCallableStatement.setString("FK_Department", aTrainingOffer.getDepartment());
            int check = aCallableStatement.executeUpdate();
            aCallableStatement.close();

            connection.commit();
            return check > 0;

        } catch (SQLException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (ConnectionException ex) {
            Logger.getLogger(ConcreteTrainingOffer.class.getName()).log(Level.SEVERE, null, ex);
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
     * @param aTrainingOffer
     * @return true if a certain training offer is successfully updated, false
     * otherwise
     */
    @Override
    public boolean updateTrainingOffer(TrainingOffer aTrainingOffer) {
        Connection connection = null;
        CallableStatement aCallableStatement = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            if (aTrainingOffer == null) {
                throw new NullPointerException("OfferTraining is null!");
            }

            aCallableStatement = connection.prepareCall("{call updateTrainingOffer(?,?,?,?,?)}");
            aCallableStatement.setInt("idTrainingOffer", aTrainingOffer.getIdTrainingOffer());
            aCallableStatement.setString("description", aTrainingOffer.getDescription());
            aCallableStatement.setString("FK_Organization", aTrainingOffer.getOrganization());
            aCallableStatement.setString("FK_PersonSSN", aTrainingOffer.getPersonSSN());
            aCallableStatement.setString("FK_Department", aTrainingOffer.getDepartment());
            int check = aCallableStatement.executeUpdate();

            connection.commit();
            return check > 0;

        } catch (SQLException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (ConnectionException ex) {
            Logger.getLogger(ConcreteTrainingOffer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                aCallableStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConcreteTrainingOffer.class.getName()).log(Level.SEVERE, null, ex);
            }
            DBConnection.releaseConnection(connection);
        }
        return false;
    }

    /**
     *
     * @param idTrainingOffer
     * @return a TrainingOffer object if reading operation from Database is
     * correct, null otherwise
     */
    @Override
    public TrainingOffer readTrainingOffer(int idTrainingOffer) {

        TrainingOffer aTrainingOffer = new TrainingOffer();
        ConcreteOrganization anOrganization = ConcreteOrganization.getInstance();
        PersonManager aPerson = PersonManager.getInstance();
        DepartmentManager aDepartment = DepartmentManager.getInstance();
        Connection connection = null;
        CallableStatement aCallableStatement = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            aCallableStatement = connection.prepareCall("{call getTrainingOffer(?)}");
            aCallableStatement.setInt("pkTrainingOffer", idTrainingOffer);
            ResultSet rs = aCallableStatement.executeQuery();

            while (rs.next()) {
                aTrainingOffer.setIdOfferTraining(rs.getInt("id_training_offer"));
                aTrainingOffer.setDescription(rs.getString("description"));
                aTrainingOffer.setOrganization(anOrganization.readOrganization(rs.getString("fk_organization")).getVATNumber());
                aTrainingOffer.setDepartment(aDepartment.readDepartment(rs.getString("fk_department")).getAbbreviation());
                aTrainingOffer.setPersonSSN(aPerson.readPerson(rs.getString("fk_person")).getSsn());
                aTrainingOffer.setContact(anOrganization.readOrganization(rs.getString("fk_organization")).getEmail());
                aTrainingOffer.setOrganizationVAT(rs.getString("fk_organization"));
            }
            rs.close();
            return aTrainingOffer;
        } catch (SQLException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ConnectionException ex) {
            Logger.getLogger(ConcreteTrainingOffer.class.getName()).log(Level.SEVERE, null, ex);
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
     * @param personSSN
     * @return an ArrayList of TrainingOffer which contains all inner training
     * offers published by a certain person, null otherwise
     */
    @Override
    public ArrayList<TrainingOffer> readInnerTrainingOffer(String personSSN) {

        ArrayList<TrainingOffer> allTraining = new ArrayList<TrainingOffer>();
        TrainingOffer aTrainingOffer = null;

        ConcreteOrganization anOrganization = ConcreteOrganization.getInstance();
        PersonManager aPerson = PersonManager.getInstance();
        DepartmentManager aDepartment = DepartmentManager.getInstance();
        Connection connection = null;
        CallableStatement aCallableStatement = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            aCallableStatement = connection.prepareCall("{call getInnerTrainingOffer(?)}");
            aCallableStatement.setString("pkProfessor", personSSN);
            ResultSet rs = aCallableStatement.executeQuery();

            while (rs.next()) {
                aTrainingOffer = new TrainingOffer();
                aTrainingOffer.setIdOfferTraining(rs.getInt("id_training_offer"));
                aTrainingOffer.setDescription(rs.getString("description"));
                aTrainingOffer.setOrganization(anOrganization.readOrganization(rs.getString("fk_organization")).getCompanyName());
                aTrainingOffer.setDepartment(aDepartment.readDepartment(rs.getString("fk_department")).getAbbreviation());
                String tmp = "";
                tmp += aPerson.readPerson(rs.getString("fk_person")).getName() + " ";
                tmp += aPerson.readPerson(rs.getString("fk_person")).getSurname();
                aTrainingOffer.setPersonSSN(tmp);
                aTrainingOffer.setContact(anOrganization.readOrganization(rs.getString("fk_organization")).getEmail());

                allTraining.add(aTrainingOffer);

            }
            rs.close();
            return allTraining;
        } catch (SQLException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ConnectionException ex) {
            Logger.getLogger(ConcreteTrainingOffer.class.getName()).log(Level.SEVERE, null, ex);
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
     * @param vatNumber
     * @return an ArrayList of TrainingOffer which contains all outer training
     * offers published by a certain person, null otherwise
     */
    @Override
    public ArrayList<TrainingOffer> readOuterTrainingOffer(String vatNumber) {

        ArrayList<TrainingOffer> allTraining = new ArrayList<TrainingOffer>();
        TrainingOffer aTrainingOffer = null;
        ConcreteOrganization anOrganization = ConcreteOrganization.getInstance();
        PersonManager aPerson = PersonManager.getInstance();
        DepartmentManager aDepartment = DepartmentManager.getInstance();
        Connection connection = null;
        CallableStatement aCallableStatement = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            aCallableStatement = connection.prepareCall("{call getOuterTrainingOffer(?)}");
            aCallableStatement.setString("pkOrganization", vatNumber);
            ResultSet rs = aCallableStatement.executeQuery();

            while (rs.next()) {
                aTrainingOffer = new TrainingOffer();
                aTrainingOffer.setIdOfferTraining(rs.getInt("id_training_offer"));
                aTrainingOffer.setDescription(rs.getString("description"));
                aTrainingOffer.setOrganization(anOrganization.readOrganization(rs.getString("fk_organization")).getCompanyName());
                aTrainingOffer.setDepartment(aDepartment.readDepartment(rs.getString("fk_department")).getAbbreviation());
                String tmp = "";
                tmp += aPerson.readPerson(rs.getString("fk_person")).getName() + " ";
                tmp += aPerson.readPerson(rs.getString("fk_person")).getSurname();
                aTrainingOffer.setPersonSSN(tmp);
                aTrainingOffer.setContact(anOrganization.readOrganization(rs.getString("fk_organization")).getEmail());
                allTraining.add(aTrainingOffer);
            }
            rs.close();
            return allTraining;
        } catch (SQLException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ConnectionException ex) {
            Logger.getLogger(ConcreteTrainingOffer.class.getName()).log(Level.SEVERE, null, ex);
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
     * @return an ArrayList of TrainingOffer which contains all training offers
     */
    @Override
    public ArrayList<TrainingOffer> getAllTrainingOffers() {
        ArrayList<TrainingOffer> allTraining = new ArrayList<TrainingOffer>();
        TrainingOffer aTrainingOffer = null;
        Connection connection = null;
        CallableStatement aCallableStatement = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            ConcreteOrganization anOrganization = ConcreteOrganization.getInstance();
            DepartmentManager aDepartment = DepartmentManager.getInstance();
            PersonManager aPerson = PersonManager.getInstance();
            Person externalTutor = null;
            aCallableStatement = connection.prepareCall("{call getAllTrainingOffers()}");
            ResultSet rs = aCallableStatement.executeQuery();
            String innerTraining = null;

            while (rs.next()) {
                aTrainingOffer = new TrainingOffer();
                aTrainingOffer.setIdOfferTraining(rs.getInt("id_training_offer"));
                aTrainingOffer.setDescription(rs.getString("description"));
                aTrainingOffer.setOrganization(anOrganization.readOrganization(rs.getString("fk_organization")).getCompanyName());
                aTrainingOffer.setDepartment(aDepartment.readDepartment(rs.getString("fk_department")).getAbbreviation());
                externalTutor = aPerson.readPerson(rs.getString("fk_person"));
                aTrainingOffer.setPersonSSN(externalTutor.getName() + " " + externalTutor.getSurname());
                innerTraining = anOrganization.readOrganization(rs.getString("fk_organization")).getEmail();
                if (innerTraining == null) {
                    Account account = externalTutor.getAccount();
                    aTrainingOffer.setContact(account.getEmail());

                } else {
                    aTrainingOffer.setContact(innerTraining);
                }
                aTrainingOffer.setOrganizationVAT(rs.getString("fk_organization"));
                allTraining.add(aTrainingOffer);
            }
            rs.close();
            return allTraining;

        } catch (SQLException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ConnectionException ex) {
            Logger.getLogger(ConcreteTrainingOffer.class.getName()).log(Level.SEVERE, null, ex);
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
     * @return a ConcreteTrainingOffer instance if there are no
     * ConcreteTrainingOffer instances alive
     */
    public static synchronized ConcreteTrainingOffer getInstance() {
        if (instance == null) {
            instance = new ConcreteTrainingOffer();
        }
        return instance;
    }

    /**
     *
     * @param idTrainingOffer
     * @return
     */
    @Override
    public boolean deleteTrainingOffer(int idTrainingOffer) {
        Connection connection = null;
        CallableStatement aCallableStatement = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            aCallableStatement = connection.prepareCall("{call deleteTrainingOffer(?)}");
            aCallableStatement.setInt("pkTraining", idTrainingOffer);

            int check = aCallableStatement.executeUpdate();
            connection.commit();

            return check > 0;

        } catch (SQLException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (ConnectionException ex) {
            Logger.getLogger(ConcreteTrainingOffer.class.getName()).log(Level.SEVERE, null, ex);
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

}
