/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.servlet;

import it.unisa.offerta_formativa.manager.CycleManager;
import it.unisa.offerta_formativa.manager.DegreeManager;
import it.unisa.offerta_formativa.manager.DepartmentManager;
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
@WebServlet(name = "ShowTeachingPagesServlet", urlPatterns = {"/ShowTeachingPagesServlet"})
public class ShowTeachingPagesServlet extends HttpServlet {
    
    private DepartmentManager dm;
    private CycleManager cm;
    private DegreeManager degreeMng;
    
    public ShowTeachingPagesServlet() {
        super();
        dm = DepartmentManager.getInstance();
        cm = CycleManager.getInstance();
        degreeMng = DegreeManager.getInstance();
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
            doPost(request,response);
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
            String page="";
            if(request.getParameterMap().containsKey("page")){
                page =request.getParameter("page");
                request.setAttribute("departments",dm.getAllDepartments());
                request.setAttribute("cycles", cm.getAllCycles());
                //request.setAttribute("degrees", degreeMng.getDegreesByCycle(2));
                if(page.equalsIgnoreCase("insert")){
                    request.getRequestDispatcher("/offertaFormativaJSP/amministratore/insertTeaching.jsp").forward(request, response);
                }
                if(page.equalsIgnoreCase("list")){
                    request.getRequestDispatcher("/offertaFormativaJSP/amministratore/listTeaching.jsp").forward(request, response);
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
