/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.tirocinio.servlet;

import it.unisa.integrazione.database.PersonManager;
import it.unisa.integrazione.database.exception.ConnectionException;
import it.unisa.integrazione.model.Person;
import it.unisa.tirocinio.beans.RejectedTrainingMessage;
import it.unisa.tirocinio.beans.StudentInformation;
import it.unisa.tirocinio.manager.concrete.ConcreteMessageForServlet;
import it.unisa.tirocinio.manager.concrete.ConcreteRejectedTrainingMessage;
import it.unisa.tirocinio.manager.concrete.ConcreteStudentInformation;
import java.io.IOException;
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
@WebServlet(name = "addErrorToStudentInformation", urlPatterns = {"/addErrorToStudentInformation"})

public class addErrorToStudentInformation extends HttpServlet {

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
        String curriculumDescription = request.getParameter("curriculumDescription");
        String accademicTranscriptDescription = request.getParameter("accademicTrainscriptDescription");
        String cfuDescription = request.getParameter("cfuDescription");
        String otherErrorsDescription = request.getParameter("otherDescription");

        String matriculaStudent = request.getParameter("errors");
        String tmp = "";
        if (curriculumDescription != null) {
            tmp = tmp + " " + curriculumDescription + ";";
        }
        if (accademicTranscriptDescription != null) {
            tmp = tmp + " " + accademicTranscriptDescription + ";";
        }
        if (cfuDescription != null) {
            tmp = tmp + " " + cfuDescription + ";";
        }
        if (otherErrorsDescription != null) {
            tmp = tmp + " " + otherErrorsDescription;
        }

        PersonManager aPerson = PersonManager.getInstance();
        Person person = aPerson.getPersonByMatricula(matriculaStudent);

        ConcreteRejectedTrainingMessage aRejectedMessage = ConcreteRejectedTrainingMessage.getInstance();
        RejectedTrainingMessage aMessage = aRejectedMessage.readLastTrainingMessage(person.getSsn());

        if (aMessage.getDescription() == null) {
            aMessage = new RejectedTrainingMessage();
            aMessage.setDescription(tmp);
            aMessage.setPersonSSN(person.getSsn());
            aRejectedMessage.createRejectedTrainingMessage(aMessage);
        } else {
            aMessage.setDescription(tmp);
            aRejectedMessage.updateRejectedTrainingMessage(aMessage);
        }

        ConcreteStudentInformation aStudentInformation = ConcreteStudentInformation.getInstance();
        StudentInformation studentInformation = null;
        try {
            studentInformation = aStudentInformation.readAStudentInformation(person.getSsn());
        } catch (ConnectionException ex) {
            Logger.getLogger(addErrorToStudentInformation.class.getName()).log(Level.SEVERE, null, ex);
        }

        studentInformation.setStudentStatus(1);
        boolean toReturn = aStudentInformation.updateStudentInformation(studentInformation);

        if (toReturn) {
            message.setMessage("status", 1);
        } else {
            message.setMessage("status", 0);
        }
        aSession.setAttribute("message", message);
        response.sendRedirect(request.getContextPath() + "/tirocinio/amministratore/tpamministratore.jsp");

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
