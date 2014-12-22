/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.servlet.teaching;

import it.unisa.offerta_formativa.beans.Curriculum;
import it.unisa.offerta_formativa.beans.Degree;
import it.unisa.offerta_formativa.manager.CurriculumManager;
import it.unisa.integrazione.database.CycleManager;
import it.unisa.integrazione.database.DegreeManager;
import it.unisa.integrazione.database.DepartmentManager;
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
@WebServlet(name = "ShowModifyTeachingServlet", urlPatterns = {"/ShowModifyTeachingServlet"})
public class ShowModifyTeachingServlet extends HttpServlet {
    private final DegreeManager degreeMng;
    private final CycleManager cycleMng;
    private final TeachingManager teachingMng;
    private final CurriculumManager currMng;
    private final DepartmentManager deptMng;

    public ShowModifyTeachingServlet() {
        degreeMng = DegreeManager.getInstance();
        cycleMng = CycleManager.getInstance();
        teachingMng = TeachingManager.getInstance();
        currMng = CurriculumManager.getInstance();
        deptMng = DepartmentManager.getInstance();
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
