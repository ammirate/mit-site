/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.tirocinio.servlet;

import it.unisa.integrazione.database.PersonManager;
import it.unisa.integrazione.model.Person;
import it.unisa.tirocinio.beans.RejectedTrainingMessage;
import it.unisa.tirocinio.beans.TrainingRequest;
import it.unisa.tirocinio.manager.concrete.ConcreteMessageForServlet;
import it.unisa.tirocinio.manager.concrete.ConcreteRejectedTrainingMessage;
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
@WebServlet(name = "completeTraining", urlPatterns = {"/completeTraining"})

public class completeTraining extends HttpServlet {

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
        
        ConcreteMessageForServlet message = new ConcreteMessageForServlet();
        HttpSession aSession = request.getSession();
        
        String studentMatricula = request.getParameter("matricula");
        PersonManager aPerson = PersonManager.getInstance();
        Person person = aPerson.getPersonByMatricula(studentMatricula);

        ConcreteRejectedTrainingMessage rejectedMessage = ConcreteRejectedTrainingMessage.getInstance();
        RejectedTrainingMessage aRejectedMessage = rejectedMessage.readLastTrainingMessage(person.getSsn());

        if (aRejectedMessage.getDescription() == null) {
            aRejectedMessage = new RejectedTrainingMessage();
            aRejectedMessage.setDescription("Il tuo tirocinio è concluso.\n Completa il questionario posto nella tua area personale. ");
            aRejectedMessage.setPersonSSN(person.getSsn());
            rejectedMessage.createRejectedTrainingMessage(aRejectedMessage);
        } else {
            aRejectedMessage.setDescription("Il tuo tirocinio è concluso.\n Completa il questionario posto nella tua area personale.");
            rejectedMessage.updateRejectedTrainingMessage(aRejectedMessage);
        }

        ConcreteTrainingRequest aTrainingRequest = ConcreteTrainingRequest.getInstance();
        TrainingRequest trainingRequest = aTrainingRequest.readTrainingRequestByStudent(person.getSsn());

        trainingRequest.setTrainingStatus(2);

        if (aTrainingRequest.updateTrainingRequest(trainingRequest)) {
            message.setMessage("trainingComplete", 1);
        } else {
            message.setMessage("trainingComplete", 0);
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
