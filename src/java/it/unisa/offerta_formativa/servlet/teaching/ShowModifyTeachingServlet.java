/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.servlet.teaching;

import it.unisa.offerta_formativa.beans.Curriculum;
import it.unisa.integrazione.model.Degree;
import it.unisa.offerta_formativa.manager.CurriculumManager;
import it.unisa.integrazione.database.CycleManager;
import it.unisa.integrazione.database.DegreeManager;
import it.unisa.integrazione.database.DepartmentManager;
import it.unisa.offerta_formativa.manager.Exceptions.CurriculumException;
import it.unisa.offerta_formativa.manager.Exceptions.TeachingException;
import it.unisa.offerta_formativa.manager.TeachingManager;
import java.io.IOException;
import java.io.PrintWriter;
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
        String path="/offertaFormativa/amministratore/teaching/";
        if(request.getParameterMap().containsKey("matricula") && request.getParameterMap().containsKey("curriculum")){
            try {
                String matricula = request.getParameter("matricula");
                String curriculumMatricula = request.getParameter("curriculum");
                Curriculum c = currMng.readCurriculum(curriculumMatricula);
                request.setAttribute("curriculum", c);
                request.setAttribute("teaching", teachingMng.readTeaching(matricula));
                Degree d = degreeMng.readDegree(c.getDegreeMatricula());
                request.setAttribute("degree",d);
                request.setAttribute("cycle", cycleMng.getCycleByCycleNumber(d.getCycle()));
                request.setAttribute("department", deptMng.getDepartmentByAbbreviation(d.getDepartmentAbbreviation()));
                if(request.getParameterMap().containsKey("success")){
                    
                }
                request.getRequestDispatcher(path+"modifyTeaching.jsp").forward(request, response);
            } catch (CurriculumException ex) {
                Logger.getLogger(ShowModifyTeachingServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TeachingException ex) {
                Logger.getLogger(ShowModifyTeachingServlet.class.getName()).log(Level.SEVERE, null, ex);
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
