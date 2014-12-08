/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.servlet;

import it.unisa.offerta_formativa.beans.Curriculum;
import it.unisa.offerta_formativa.beans.Teaching;
import it.unisa.offerta_formativa.manager.ClassManager;
import it.unisa.offerta_formativa.manager.CurriculumManager;
import it.unisa.offerta_formativa.manager.DegreeManager;
import it.unisa.offerta_formativa.manager.ModuleManager;
import it.unisa.offerta_formativa.manager.TeachingManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alessandro
 */
@WebServlet(name = "ModifyTeachingServlet", urlPatterns = {"/ModifyTeachingServlet"})
public class ModifyTeachingServlet extends HttpServlet {

    private DegreeManager degreeMng = null;
    private ClassManager classMng =null;
    private ModuleManager moduleMng=null;
    private TeachingManager teachingMng = null;
    private CurriculumManager currMng;

    public ModifyTeachingServlet() {
        degreeMng = DegreeManager.getInstance();
        classMng = ClassManager.getInstance();
        moduleMng = ModuleManager.getInstance();
        teachingMng = TeachingManager.getInstance();
        currMng = CurriculumManager.getInstance();
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameterMap().containsKey("oldMatricula")){
            String oldMatricula = request.getParameter("oldMatricula");
            String newMatricula = request.getParameter("newMatricula");
            Teaching t = new Teaching(
                        request.getParameter("title"),
                        request.getParameter("abbreviation"),
                        request.getParameter("oldMatricula"),
                        request.getParameter("link"),
                        Integer.parseInt(request.getParameter("year")),
                        Integer.parseInt(request.getParameter("semester")),
                        (Integer.parseInt(request.getParameter("active"))==1)?true:false);
            //ArrayList<Curriculum> currList = currMng.getCurriculumByTeaching(oldMatricula);
            if(!request.getParameter("newMatricula").equalsIgnoreCase(oldMatricula)){
                t.setMatricula(newMatricula);
                teachingMng.updateTeaching(t);
                //teachingMng.updateTeaching(oldMatricula,new Teaching());
            }else{
                teachingMng.updateTeaching(t);
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
