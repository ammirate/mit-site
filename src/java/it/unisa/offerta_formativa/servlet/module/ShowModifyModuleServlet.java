/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.servlet.module;

import it.unisa.offerta_formativa.manager.Exceptions.TeachingException;
import it.unisa.offerta_formativa.manager.TeachingManager;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alessandro
 */
@WebServlet(name = "ShowModifyModuleServlet", urlPatterns = {"/ShowModifyModuleServlet"})
public class ShowModifyModuleServlet extends HttpServlet {
    private final TeachingManager teachingMng;

    public ShowModifyModuleServlet() {
        teachingMng = TeachingManager.getInstance();
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path="/offertaFormativa/amministratore/classmodule/";
        if(request.getParameterMap().containsKey("matricula") && request.getParameterMap().containsKey("moduleTitle")){
            try {
                String matricula = request.getParameter("matricula");
                request.setAttribute("matricula", matricula);
                request.setAttribute("title", request.getParameter("moduleTitle"));
                request.setAttribute("teaching",teachingMng.readTeaching(matricula));
                request.getRequestDispatcher(path+"modifyModule.jsp").forward(request, response);
            } catch (TeachingException ex) {
                Logger.getLogger(ShowModifyModuleServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
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
