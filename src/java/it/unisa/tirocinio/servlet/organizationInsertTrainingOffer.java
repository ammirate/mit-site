/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.tirocinio.servlet;

import it.unisa.integrazione.database.PersonManager;
import it.unisa.integrazione.model.Department;
import it.unisa.integrazione.model.Person;
import it.unisa.tirocinio.beans.Organization;
import it.unisa.tirocinio.beans.TrainingOffer;
import it.unisa.tirocinio.manager.concrete.ConcreteMessageForServlet;
import it.unisa.tirocinio.manager.concrete.ConcreteOrganization;
import it.unisa.tirocinio.manager.concrete.ConcreteTrainingOffer;
import java.io.IOException;
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
@WebServlet(name = "organizationInsertTrainingOffer", urlPatterns = {"/organizationInsertTrainingOffer"})

public class organizationInsertTrainingOffer extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        request.setCharacterEncoding("UTF-8");
        
        ConcreteMessageForServlet message = new ConcreteMessageForServlet();
        String description = request.getParameter("description");
        HttpSession aSession = request.getSession();
        Person anOrganization = (Person) aSession.getAttribute("person");
        String organizationEmail = (String) anOrganization.getAccount().getEmail();

        ConcreteOrganization aOrganization = ConcreteOrganization.getInstance();
        Organization organization = aOrganization.getOrganizationByAccount(organizationEmail);

        PersonManager aPerson = PersonManager.getInstance();
        Person person = aPerson.readPerson(organization.getProfessor());

        ConcreteTrainingOffer aTrainingOffer = ConcreteTrainingOffer.getInstance();
        TrainingOffer trainingOffer = new TrainingOffer();
        Department departmentAbb = person.getDepartment();
        trainingOffer.setDepartment(departmentAbb.getAbbreviation());
        trainingOffer.setDescription(organization.getCompanyName() + " - " + description);
        trainingOffer.setOrganization(organization.getVATNumber());
        trainingOffer.setPersonSSN(person.getSsn());

        if (aTrainingOffer.createOuterTrainingOffer(trainingOffer)) {
            message.setMessage("status", 1);
        } else {
            message.setMessage("status", 0);
        }
        aSession.setAttribute("message", message);
        response.sendRedirect(request.getContextPath() + "/tirocinio/organizzazione/tporganizzazione.jsp");

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
        processRequest(request, response);
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
        processRequest(request, response);
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
