/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.servlet.classpartition;

import it.unisa.offerta_formativa.servlet.module.*;
import it.unisa.integrazione.database.CycleManager;
import it.unisa.integrazione.database.DepartmentManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alessandro
 */
@WebServlet(name = "ShowInsertClassServlet", urlPatterns = {"/ShowInsertClassServlet"})
public class ShowInsertClassServlet extends HttpServlet {
    private final DepartmentManager deptMng;
    private final CycleManager cycleMng;

    public ShowInsertClassServlet() {
        deptMng = DepartmentManager.getInstance();
        cycleMng = CycleManager.getInstance();
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
        String path="/offertaFormativaJSP/amministratore/";
        request.setAttribute("departments",deptMng.getAllDepartments());
        request.setAttribute("cycles", cycleMng.getAllCycles());
        request.getRequestDispatcher(path+"insertClass.jsp").forward(request, response);
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
