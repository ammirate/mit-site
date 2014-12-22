/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.servlet.getter;

import it.unisa.model.Degree;
import it.unisa.model.Person;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alessandro
 */
@WebServlet(name = "GetProfessorServlet", urlPatterns = {"/GetProfessorServlet"})
public class GetProfessorServlet extends HttpServlet {

    public GetProfessorServlet(){}
    
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        doPost(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        try {
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            String toRet="";
            toRet+="<option value=fferrucci@unisa.it>Filomena Ferrucci</option>";
            toRet+="<option value=adelucia@unisa.it>Andrea De Lucia</option>";
            response.getWriter().write(toRet);
        } catch (IOException ex) {
            Logger.getLogger(GetProfessorServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }       

}
