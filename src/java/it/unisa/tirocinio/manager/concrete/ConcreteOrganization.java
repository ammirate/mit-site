package it.unisa.tirocinio.manager.concrete;

import it.unisa.integrazione.database.AccountManager;
import it.unisa.integrazione.database.CycleManager;
import it.unisa.integrazione.database.DBConnection;
import it.unisa.integrazione.database.PersonManager;
import it.unisa.integrazione.database.exception.ConnectionException;
import it.unisa.integrazione.model.Person;
import it.unisa.tirocinio.beans.Organization;
import it.unisa.tirocinio.manager.interfaces.IOrganization;
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
public class ConcreteOrganization implements IOrganization {

    private static ConcreteOrganization instance = null;

    /**
     *
     * @param organization
     * @return true if organization is created successfully, false otherwise
     */
    @Override
    public boolean createOrganization(Organization organization) {
        Connection connection = null;
        CallableStatement aCallableStatement = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            if (organization == null) {
                throw new NullPointerException("Organization is null!");
            }

            aCallableStatement = connection.prepareCall("{call insertOrganization(?,?,?,?,?,?,?,?,?)}");
            aCallableStatement.setString("vatNumber", organization.getVATNumber());
            aCallableStatement.setString("companyName", organization.getCompanyName());
            aCallableStatement.setString("city", organization.getCity());
            aCallableStatement.setString("address", organization.getAddress());
            aCallableStatement.setString("phone", organization.getPhone());
            aCallableStatement.setString("email", organization.getEmail());
            aCallableStatement.setString("personSSN", organization.getProfessor());
            aCallableStatement.setString("accountEmail", organization.getAccount());
            aCallableStatement.setString("tutorSSN", organization.getExternalTutor());
            int check = aCallableStatement.executeUpdate();
            connection.commit();
            return check > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (ConnectionException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
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
     * @param VATNumber
     * @return true if organization delete operation is correct, false otherwise
     */
    @Override
    public boolean deleteOrganization(String VATNumber) {
        Connection connection = null;
        CallableStatement aCallableStatement = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            aCallableStatement = connection.prepareCall("{call deleteOrganization(?)}");
            aCallableStatement.setString("vatNumber", VATNumber);
            int check = aCallableStatement.executeUpdate();
            connection.commit();
            return check > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (ConnectionException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
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
     * @param organization
     * @return true if organization update operation is correct, false otherwise
     */
    @Override
    public boolean updateOrganization(Organization organization) {
        Connection connection = null;
        CallableStatement aCallableStatement = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            if (organization == null) {
                throw new NullPointerException("Organization is null!");
            }

            aCallableStatement = connection.prepareCall("{call updateOrganization(?,?,?,?,?,?,?,?,?)}");
            aCallableStatement.setString("vatNumber", organization.getVATNumber());
            aCallableStatement.setString("companyName", organization.getCompanyName());
            aCallableStatement.setString("city", organization.getCity());
            aCallableStatement.setString("address", organization.getAddress());
            aCallableStatement.setString("phone", organization.getPhone());
            aCallableStatement.setString("email", organization.getEmail());
            aCallableStatement.setString("personSSN", organization.getProfessor());
            aCallableStatement.setString("accountEmail", organization.getAccount());
            aCallableStatement.setString("tutorSSN", organization.getExternalTutor());
            int check = aCallableStatement.executeUpdate();
            connection.commit();
            return check > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (ConnectionException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
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
     * @param VATNumber
     * @return an Organization object if reading operation from Database is
     * correct, null otherwise
     */
    @Override
    public Organization readOrganization(String VATNumber) {
        Organization anOrganization = new Organization();
        AccountManager anAccount = AccountManager.getInstance();
        PersonManager aProfessor = PersonManager.getInstance();
        PersonManager aTutor = PersonManager.getInstance();
        Connection connection = null;
        CallableStatement aCallableStatement = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            aCallableStatement = connection.prepareCall("{call getOrganizationByPrimaryKey(?)}");
            aCallableStatement.setString("vatNumber", VATNumber);
            ResultSet rs = aCallableStatement.executeQuery();

            while (rs.next()) {
                anOrganization.setVATNumber(rs.getString("vat_number"));
                anOrganization.setCompanyName(rs.getString("company_name"));
                anOrganization.setCity(rs.getString("city"));
                anOrganization.setAddress(rs.getString("address"));
                anOrganization.setPhone(rs.getString("phone"));
                anOrganization.setEmail(rs.getString("email"));
                anOrganization.setAccount(anAccount.readAccount(rs.getString("fk_account")).getEmail());
                anOrganization.setProfessor(aProfessor.readPerson(rs.getString("fk_professor")).getSsn());
                anOrganization.setExternalTutor(aTutor.readPerson(rs.getString("fk_external_tutor")).getSsn());
            }
            rs.close();
            return anOrganization;
        } catch (SQLException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ConnectionException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
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
     * @return an ArrayList of Organization if reading operation from Database
     * is correct, null otherwise
     */
    @Override
    public ArrayList<Organization> getAllOrganizations() {
        ArrayList<Organization> organizations = new ArrayList<Organization>();
        Organization anOrganization = null;
        Connection connection = null;
        CallableStatement aCallableStatement = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            aCallableStatement = connection.prepareCall("{call getAllOrganizations()}");
            ResultSet rs = aCallableStatement.executeQuery();

            while (rs.next()) {
                anOrganization = new Organization();
                anOrganization.setVATNumber(rs.getString("vat_number"));
                anOrganization.setCompanyName(rs.getString("company_name"));
                anOrganization.setCity(rs.getString("city"));
                anOrganization.setAddress(rs.getString("address"));
                anOrganization.setPhone(rs.getString("phone"));
                anOrganization.setEmail(rs.getString("email"));
                anOrganization.setAccount(rs.getString("fk_account"));
                anOrganization.setProfessor(rs.getString("fk_professor"));
                anOrganization.setExternalTutor(rs.getString("fk_external_tutor"));
                organizations.add(anOrganization);
            }
            rs.close();
            return organizations;

        } catch (SQLException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ConnectionException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
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
     * @param professorSSN
     * @return an ArrayList of Organization that contains every organization a
     * professor handle
     */
    @Override
    public ArrayList<Organization> getOwnOrganizations(String professorSSN) {
        ArrayList<Organization> organizations = new ArrayList<Organization>();
        Organization anOrganization = null;
        Connection connection = null;
        CallableStatement aCallableStatement = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            AccountManager anAccount = AccountManager.getInstance();
            PersonManager aProfessor = PersonManager.getInstance();
            PersonManager aTutor = PersonManager.getInstance();
            aCallableStatement = connection.prepareCall("{call getOwnOrganizations(?)}");
            aCallableStatement.setString("professorSSN", professorSSN);
            ResultSet rs = aCallableStatement.executeQuery();

            while (rs.next()) {
                anOrganization = new Organization();
                anOrganization.setVATNumber(rs.getString("vat_number"));
                anOrganization.setCompanyName(rs.getString("company_name"));
                anOrganization.setCity(rs.getString("city"));
                anOrganization.setAddress(rs.getString("address"));
                anOrganization.setPhone(rs.getString("phone"));
                anOrganization.setEmail(rs.getString("email"));
                anOrganization.setAccount(anAccount.readAccount(rs.getString("fk_account")).getEmail());
                anOrganization.setProfessor(aProfessor.readPerson(rs.getString("fk_professor")).getSsn());
                anOrganization.setExternalTutor(aTutor.readPerson(rs.getString("fk_external_tutor")).getSsn());
                organizations.add(anOrganization);
            }
            rs.close();
            return organizations;

        } catch (SQLException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ConnectionException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
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
     * @return a Person object that contains informations about professor who
     * handle a certain organization
     */
    @Override
    public Person getProfessorOrganization(String VATNumber) {
        Organization anOrganization = this.readOrganization(VATNumber);
        PersonManager person = PersonManager.getInstance();
        return person.readPerson(anOrganization.getProfessor());
    }

    /**
     *
     * @param VATNumber
     * @return a Person object that contains informations about external tutor
     */
    @Override
    public Person getExternalTutor(String VATNumber) {
        Organization anOrganization = this.readOrganization(VATNumber);
        PersonManager person = PersonManager.getInstance();
        return person.readPerson(anOrganization.getExternalTutor());
    }

    /**
     *
     * @param accountEmail
     * @return an Organization object which contains informations about the
     * organization through its email account
     */
    @Override
    public Organization getOrganizationByAccount(String accountEmail) {
        Organization anOrganization = new Organization();
        AccountManager anAccount = AccountManager.getInstance();
        PersonManager aProfessor = PersonManager.getInstance();
        PersonManager aTutor = PersonManager.getInstance();
        Connection connection = null;
        CallableStatement aCallableStatement = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            aCallableStatement = connection.prepareCall("{call getOrganizationByAccount(?)}");
            aCallableStatement.setString("fkAccount", accountEmail);
            ResultSet rs = aCallableStatement.executeQuery();

            while (rs.next()) {
                anOrganization.setVATNumber(rs.getString("vat_number"));
                anOrganization.setCompanyName(rs.getString("company_name"));
                anOrganization.setCity(rs.getString("city"));
                anOrganization.setAddress(rs.getString("address"));
                anOrganization.setPhone(rs.getString("phone"));
                anOrganization.setEmail(rs.getString("email"));
                anOrganization.setAccount(anAccount.readAccount(rs.getString("fk_account")).getEmail());
                anOrganization.setProfessor(aProfessor.readPerson(rs.getString("fk_professor")).getSsn());
                anOrganization.setExternalTutor(aTutor.readPerson(rs.getString("fk_external_tutor")).getSsn());
            }
            rs.close();
            return anOrganization;
        } catch (SQLException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ConnectionException ex) {
            Logger.getLogger(ConcreteOrganization.class.getName()).log(Level.SEVERE, null, ex);
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
     * @return a ConcreteOrganization object if currently there is no
     * ConcreteOrganization objects alive
     */
    public static synchronized ConcreteOrganization getInstance() {
        if (instance == null) {
            instance = new ConcreteOrganization();
        }
        return instance;
    }

}
