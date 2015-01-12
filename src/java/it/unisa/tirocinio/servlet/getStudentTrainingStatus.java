/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.tirocinio.servlet;

import it.unisa.integrazione.model.Person;
import it.unisa.tirocinio.beans.StudentInformation;
import it.unisa.tirocinio.beans.StudentStatus;
import it.unisa.tirocinio.beans.TrainingRequest;
import it.unisa.tirocinio.manager.concrete.ConcreteMessageForServlet;
import it.unisa.tirocinio.manager.concrete.ConcreteStudentInformation;
import it.unisa.tirocinio.manager.concrete.ConcreteStudentStatus;
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
@WebServlet(name = "getStudentTrainingStatus", urlPatterns = {"/getStudentTrainingStatus"})

public class getStudentTrainingStatus extends HttpServlet {

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

        Person person = (Person) aSession.getAttribute("person");
        
        ConcreteStudentInformation aStudentInformation = ConcreteStudentInformation.getInstance();
        StudentInformation studentInformation = aStudentInformation.readStudentInformation(person.getSsn());

        int studentStatus = studentInformation.getStudentStatus();
        ConcreteStudentStatus studentStatusConcrete = ConcreteStudentStatus.getInstance();
        StudentStatus sStudent = studentStatusConcrete.readStudentStatus(studentStatus);
        
        ConcreteTrainingRequest aConcreteTrainingRequest = ConcreteTrainingRequest.getInstance();
        TrainingRequest aTrainingRequest = aConcreteTrainingRequest.readTrainingRequestByStudent(person.getSsn());
                
        message.setMessage("status", 1);
        message.setMessage("description", sStudent.getDescription());
        message.setMessage("idStudentStatus", studentStatus);
        message.setMessage("enableQuestionnaire", aTrainingRequest.getTrainingStatus());
        aSession.setAttribute("message", message);

        response.sendRedirect(request.getContextPath() + "/tirocinio/studente/tphome.jsp");

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
