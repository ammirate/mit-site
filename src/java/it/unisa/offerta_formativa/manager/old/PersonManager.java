/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.manager.old;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import it.unisa.integrazione.model.Person;

/**
 *
 * @author Antonio
 */
public class PersonManager {

    private final Connection conn = null;
    private Statement stmt;
    public static String TABLE = "person";
    public static String TABLE_ACCOUNT = "account";
    public static String PKEY = "SSN";
    public static String FKEY = "Account_email";

    private ResultSet rs = null;

    private static PersonManager instance = null;

    private PersonManager() {
    }

    /**
     * Singleton pattern
     *
     * @return
     */
    public static PersonManager getInstance() {
        if (instance == null) {
            instance = new PersonManager();
        }
        return instance;
    }

    /**
     *
     * @param email
     * @return
     */
    public Person getPersonByEmail(String email) {
        try {
            stmt = DBConnector.openConnection();

            String esc = "\'";
            String query = "SELECT * FROM " + TABLE + " WHERE "
                    + FKEY + "= " + esc + email + esc;
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                return getPersonFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.closeConnection();
        }
        return null;
    }

    /**
     *
     * @param rs
     * @return
     */
    private Person getPersonFromResultSet(ResultSet rs) {
        String email;
        try {
            String ssn = rs.getString("SSN");
            String name = rs.getString("name");
            String surname = rs.getString("surname");
            String phone = rs.getString("phone");
            String city = rs.getString("city");
            String address = rs.getString("address");
            String zipCode = rs.getString("zip_code");
            String cityzenship = rs.getString("citizenship");
            String gender = rs.getString("gender");
            String accountEmail = rs.getString("Account_email");
            String departmentAbbreviation = rs.getString("Department_abbreviation");
            String webPage = rs.getString("web_page");
            String university = rs.getString("university");
            String matricula = rs.getString("matricula");
            String position = rs.getString("position");
            int cyle = rs.getInt("cycle");

            Person p = new Person();
            p.setSsn(ssn);
           // p.setAccount(AccountManager.getInstance().read(accountEmail));
            p.setName(name);
            p.setSurname(surname);
            return p;

        } catch (SQLException ex) {
            Logger.getLogger(PersonManager.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param depAbbreviation
     * @return
     */
    public List<Person> getProfessorsByDepartment(String depAbbreviation) {
//        SELECT * FROM person,account WHERE 
//        person.Account_email=account.email 
//        AND  account.typeOfAccount = 'professor' 
//        AND person.Department_abbreviation ='DISTRA'

        List<Person> toReturn = new ArrayList<>();
        String esc = "\'";
        String query = "SELECT * FROM " + TABLE + "," + TABLE_ACCOUNT
                + " WHERE " + " person.Account_email=account.email "
                + " AND account.typeOfAccount = " + esc + "professor" + esc
                + " AND person.Department_abbreviation =" + esc + depAbbreviation + esc;
        try {
            stmt = DBConnector.openConnection();

            rs = stmt.executeQuery(query);
            System.out.println(query);

            while (rs.next()) {
                toReturn.add(getPersonFromResultSet(rs));

            }

        } catch (SQLException ex) {
            Logger.getLogger(PersonManager.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnector.closeConnection();
        }

        return toReturn;
    }

    /**
     *
     * @param person
     *
     */
    /*
     public void create(Person person) {
     stmt = DBConnector.openConnection();

     String sql = "INSERT INTO person (SSN, person.name, surname, phone, "
     + "city, address, zip_code, gender, citizenship, Account_email, "
     + "Department_abbreviation, web_page, university, matricula, "
     + "position, cycle) VALUES ('" + person.getSSN() + "','"
     + person.getName() + "','" + person.getSurname() + "','"
     + person.getPhone() + "','" + person.getCity() + "','"
     + person.getAddress() + "','" + person.getZipCode() + "','"
     + person.getGender() + "','" + person.getCityzenship() + "','"
     + person.getAccountEmail() + "','"
     + person.getDepartmentAbbreviation() + "','"
     + person.getWebPage() + "','" + person.getUniversity() + "','"
     + person.getMatricula() + "','" + person.getPosition()+ "',"
     + person.getCyle()+ ")";

     try {
     stmt.executeUpdate(sql);
     } catch (SQLException ex) {
     Logger.getLogger(PersonManager.class.getName()).log(Level.SEVERE, null, ex);
     } finally {
     DBConnector.closeConnection();
     }
     }
     */
}
