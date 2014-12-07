/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.servlet;

import it.unisa.offerta_formativa.beans.Curriculum;
import it.unisa.offerta_formativa.beans.Degree;
import it.unisa.offerta_formativa.manager.CurriculumManager;
import it.unisa.offerta_formativa.manager.CycleManager;
import it.unisa.offerta_formativa.manager.DegreeManager;
import it.unisa.offerta_formativa.manager.DepartmentManager;
import it.unisa.offerta_formativa.manager.TeachingManager;
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
    
    private DepartmentManager deptMng;
    private CycleManager cycleMng;
    private DegreeManager degreeMng;
    private TeachingManager teachingMng;
    private CurriculumManager currMng;
    
    public ShowTeachingPagesServlet() {
        super();
        deptMng = DepartmentManager.getInstance();
        cycleMng = CycleManager.getInstance();
        degreeMng = DegreeManager.getInstance();
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
            String path="/offertaFormativaJSP/amministratore/";
            String page="";
            if(request.getParameterMap().containsKey("page")){
                page =request.getParameter("page");
                request.setAttribute("departments",deptMng.getAllDepartments());
                request.setAttribute("cycles", cycleMng.getAllCycles());
                if(page.equalsIgnoreCase("insert")){
                    request.getRequestDispatcher(path+"insertTeaching.jsp").forward(request, response);
                }
                if(page.equalsIgnoreCase("list")){
                    request.getRequestDispatcher(path+"listTeaching.jsp").forward(request, response);
                }
                if(page.equalsIgnoreCase("modify")){
                    if(request.getParameterMap().containsKey("matricula") && request.getParameterMap().containsKey("curriculumMatricula")){
                        String matricula = request.getParameter("matricula");
                        String curriculumMatricula = request.getParameter("curriculumMatricula");
                        Curriculum c = currMng.readCurriculum(curriculumMatricula);
                        request.setAttribute("curriculum", c);
                        request.setAttribute("teaching", teachingMng.readTeaching(matricula));
                        Degree d = degreeMng.readDegree(c.getDegreeMatricula());
                        request.setAttribute("degree",d);
                        request.setAttribute("cycle", cycleMng.readCycle(d.getCycle()));
                        request.setAttribute("department", deptMng.readDepartment(d.getDepartmentAbbreviation()));
                        request.getRequestDispatcher(path+"modifyTeaching.jsp").forward(request, response);
                    }
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
