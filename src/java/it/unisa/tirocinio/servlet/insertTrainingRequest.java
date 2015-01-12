/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.tirocinio.servlet;

import it.unisa.integrazione.database.PersonManager;
import it.unisa.integrazione.model.Person;
import it.unisa.tirocinio.beans.Organization;
import it.unisa.tirocinio.beans.TrainingRequest;
import it.unisa.tirocinio.manager.concrete.ConcreteMessageForServlet;
import it.unisa.tirocinio.manager.concrete.ConcreteOrganization;
import it.unisa.tirocinio.manager.concrete.ConcreteTrainingRequest;
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
@WebServlet(name = "insertTrainingRequest", urlPatterns = {"/insertTrainingRequest"})

public class insertTrainingRequest extends HttpServlet {

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
        HttpSession aSession = request.getSession();
        ConcreteMessageForServlet message = new ConcreteMessageForServlet();
        String title = request.getParameter("titleTraining");
        String description = request.getParameter("descriptionTraining");
        String professor = request.getParameter("professorSSN");
        String student = request.getParameter("studentSSN");
        String organiz = request.getParameter("organizationVAT");

        PersonManager aStudent = PersonManager.getInstance();
        Person studentSSN = aStudent.readPerson(student);

        PersonManager aProfessor = PersonManager.getInstance();
        Person professorSSN = aProfessor.readPerson(professor);

        ConcreteOrganization anOrganization = ConcreteOrganization.getInstance();
        Organization organization = anOrganization.readOrganization(organiz);

        ConcreteTrainingRequest aTrainingRequest = ConcreteTrainingRequest.getInstance();
        TrainingRequest trainingRequest = aTrainingRequest.readTrainingRequestByStudent(student);

        if (trainingRequest.getStudentSSN() == null) {
            trainingRequest = new TrainingRequest();
            trainingRequest.setDescription(description);
            trainingRequest.setOrganizationVATNumber(organization.getVATNumber());
            trainingRequest.setPersonSSN(professorSSN.getSsn());
            trainingRequest.setStudentSSN(studentSSN.getSsn());
            trainingRequest.setTitle(title);
            trainingRequest.setTrainingStatus(1);
        }

        if (aTrainingRequest.createTrainingRequest(trainingRequest)) {
            message.setMessage("status", 1);
        } else {
            message.setMessage("status", 0);
        }

        aSession.setAttribute("message", message);
        response.sendRedirect(request.getContextPath() + "/tirocinio/amministratore/tpaggiungistudentetraining.jsp");

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
