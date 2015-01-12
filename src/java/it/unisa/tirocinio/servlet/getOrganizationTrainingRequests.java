/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.tirocinio.servlet;

import it.unisa.integrazione.model.Person;
import it.unisa.tirocinio.beans.Organization;
import it.unisa.tirocinio.beans.TrainingRequest;
import it.unisa.tirocinio.manager.concrete.ConcreteMessageForServlet;
import it.unisa.tirocinio.manager.concrete.ConcreteOrganization;
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
@WebServlet(name = "getOrganizationTrainingRequests", urlPatterns = {"/getOrganizationTrainingRequests"})

public class getOrganizationTrainingRequests extends HttpServlet {

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
            Person aOrganization = (Person) aSession.getAttribute("person");
            String organizationAccount = (String) aOrganization.getAccount().getEmail();

            /* TODO output your page here. You may use following sample code. */
            ConcreteOrganization anOrganization = ConcreteOrganization.getInstance();
            Organization organization = anOrganization.getOrganizationByAccount(organizationAccount);

            ConcreteTrainingRequest aTrainingRequest = ConcreteTrainingRequest.getInstance();
            ArrayList<TrainingRequest> trainingRequest = aTrainingRequest.readTrainingRequestByOrganization(organization.getVATNumber());

            if (trainingRequest == null) {
                message.setMessage("status", 0);
            } else {
                message.setMessage("status", 1);
                message.setMessage("Object", trainingRequest);
                for (int i = 0; i < trainingRequest.size(); i++) {
                    out.println("\n ----------------\n");
                    out.println("id " + trainingRequest.get(i).getIdTrainingRequest());
                    out.println("desc " + trainingRequest.get(i).getDescription());
                    out.println("titl " + trainingRequest.get(i).getTitle());
                    out.println("org " + trainingRequest.get(i).getOrganizationVATNumber());
                    out.println("prof " + trainingRequest.get(i).getPersonSSN());
                    out.println("stud " + trainingRequest.get(i).getStudentSSN());
                    out.println("stat " + trainingRequest.get(i).getTrainingStatus());
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
