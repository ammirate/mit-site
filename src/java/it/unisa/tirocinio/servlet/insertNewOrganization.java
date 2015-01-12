/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.tirocinio.servlet;

import it.unisa.integrazione.database.AccountManager;
import it.unisa.integrazione.database.PersonManager;
import it.unisa.integrazione.database.exception.ConnectionException;
import it.unisa.integrazione.database.exception.MissingDataException;
import it.unisa.integrazione.model.Account;
import it.unisa.integrazione.model.Cycle;
import it.unisa.integrazione.model.Department;
import it.unisa.integrazione.model.Person;
import it.unisa.tirocinio.beans.Organization;
import it.unisa.tirocinio.manager.concrete.ConcreteOrganization;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Valentino
 */
@WebServlet(name = "insertNewOrganization", urlPatterns = {"/insertNewOrganization"})

public class insertNewOrganization extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ConnectionException, MissingDataException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession aSession = request.getSession();
        Account account = new Account();
        Organization organization = new Organization();
        Person person = new Person();
        PersonManager professorManager = PersonManager.getInstance();
        
        organization.setVATNumber(request.getParameter("vatNumber"));
        organization.setCompanyName(request.getParameter("companyName"));
        organization.setCity(request.getParameter("city"));
        organization.setAddress(request.getParameter("address"));
        //organization.setPhone(request.getParameter("phone"));
        String[] tmp = request.getParameter("phone").split("_");
        organization.setPhone(tmp[0]);
        organization.setEmail(request.getParameter("email"));
        account.setEmail(request.getParameter("emailAccount"));
        account.setPassword(request.getParameter("password"));
        account.setTypeOfAccount("organization");
        account.setActive(true);
        Department department = new Department();
        Cycle cycle = new Cycle();
        cycle.setCycleNumber(5);
        //department.setAbbreviation("distra");
        Person professor = professorManager.getPersonBySSN(request.getParameter("professorSSN"));
        department.setAbbreviation(professor.getDepartment().getAbbreviation());
        person.setName(request.getParameter("nameLiable"));
        person.setSurname(request.getParameter("surnameLiable"));
        person.setSsn(request.getParameter("ssnLiable"));
        person.setAccount(account);
        person.setDepartment(department);
        person.setCycle(cycle);
        person.setGender(request.getParameter("gender"));
        PersonManager aPerson = PersonManager.getInstance();
        aPerson.add(person);
        
        organization.setAccountEmail(account.getEmail());

        organization.setProfessor(request.getParameter("professorSSN"));

        AccountManager anAccount = AccountManager.getInstance();
        anAccount.createAccount(account);

        ConcreteOrganization anOrganization = ConcreteOrganization.getInstance();
        
        if(anOrganization.createOrganization(organization))
            aSession.setAttribute("status", 1);
        else aSession.setAttribute("status", 0);
        
        response.sendRedirect(request.getContextPath() + "/tirocinio/amministratore/tpregisteranorganization.jsp");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(insertNewOrganization.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConnectionException ex) {
            Logger.getLogger(insertNewOrganization.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MissingDataException ex) {
            Logger.getLogger(insertNewOrganization.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(insertNewOrganization.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConnectionException ex) {
            Logger.getLogger(insertNewOrganization.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MissingDataException ex) {
            Logger.getLogger(insertNewOrganization.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
