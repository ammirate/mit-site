/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.tirocinio.servlet;

import it.unisa.integrazione.database.PersonManager;
import it.unisa.integrazione.model.Person;
import it.unisa.tirocinio.beans.TrainingRequest;
import it.unisa.tirocinio.manager.concrete.ConcreteMessageForServlet;
import it.unisa.tirocinio.manager.concrete.ConcreteTrainingRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "getProfessorTrainingRequests", urlPatterns = {"/getProfessorTrainingRequests"})

public class getProfessorTrainingRequests extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try {
            HttpSession aSession = request.getSession();
            Person pers = (Person) aSession.getAttribute("person");
            
            String professorSSN = pers.getSsn();

            PersonManager aPerson = PersonManager.getInstance();
            Person person = aPerson.readPersonByAccount(professorSSN);

            ConcreteTrainingRequest aTrainingRequest = ConcreteTrainingRequest.getInstance();
            ArrayList<TrainingRequest> trainingRequest = aTrainingRequest.readTrainingRequestByProfessor(person.getSsn());

            if (trainingRequest == null) {
                message.setMessage("status", 0);
            } else {
                message.setMessage("status", 1);
                message.setMessage("Object", trainingRequest);
                for (TrainingRequest trainingRequest1 : trainingRequest) {
                    out.println("\n ----------------\n");
                    out.println("id " + trainingRequest1.getIdTrainingRequest());
                    out.println("desc " + trainingRequest1.getDescription());
                    out.println("titl " + trainingRequest1.getTitle());
                    out.println("org " + trainingRequest1.getOrganizationVATNumber());
                    out.println("prof " + trainingRequest1.getPersonSSN());
                    out.println("stud " + trainingRequest1.getStudentSSN());
                    out.println("stat " + trainingRequest1.getTrainingStatus());
                }
            }
        } finally {
            out.close();
        }
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
