/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.tirocinio.servlet;

import it.unisa.integrazione.database.PersonManager;
import it.unisa.integrazione.database.exception.ConnectionException;
import it.unisa.integrazione.model.Person;
import it.unisa.tirocinio.beans.*;
import it.unisa.tirocinio.manager.concrete.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 *
 * @author Valentino
 */
@WebServlet(name = "downloadFile", urlPatterns = {"/downloadFile"})

public class downloadFile extends HttpServlet {
    static final long serialVersionUID = 1L;

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
        
        String matricula = request.getParameter("matricula");
        String tipologia = request.getParameter("typology");
        
        PersonManager aPerson = PersonManager.getInstance();
        Person person = aPerson.getPersonByMatricula(matricula);
         
        String filepath = "";
        String fileName = "";
        
        ConcreteStudentInformation aStudentInformation = ConcreteStudentInformation.getInstance();
        StudentInformation studentInformation = null;
        try {
            studentInformation = aStudentInformation.readAStudentInformation(person.getSsn());
        } catch (ConnectionException ex) {
            Logger.getLogger(downloadFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(tipologia.equals("CV")){
            filepath = studentInformation.getCVPath();
            fileName = "CV-"+matricula+".pdf";
        }
        if(tipologia.equals("ES")){
            filepath = studentInformation.getATPath();
            fileName = "ES-"+matricula+".pdf";
        }
        
        response.setHeader("Content-disposition", "attachment; filename="+fileName);
        ServletContext ctx = getServletContext();
        
        File my_file = new File(filepath);
        ServletOutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(my_file);
        System.out.println(in.available());
        String mimeType = ctx.getMimeType(my_file.getAbsolutePath());
        System.out.println(mimeType);
        response.setContentType(mimeType != null? mimeType:"application/pdf");
        response.setContentLength((int) my_file.length());
        byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
            System.out.println(in.available());
        }
        in.close();
        out.flush();
        out.close();
       
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
