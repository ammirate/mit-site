/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.servlet;

import it.unisa.offerta_formativa.beans.Curriculum;
import it.unisa.offerta_formativa.beans.Degree;
import it.unisa.offerta_formativa.beans.ProfModuleClass;
import it.unisa.offerta_formativa.manager.ClassManager;
import it.unisa.offerta_formativa.manager.CurriculumManager;
import it.unisa.offerta_formativa.manager.CycleManager;
import it.unisa.offerta_formativa.manager.DegreeManager;
import it.unisa.offerta_formativa.manager.DepartmentManager;
import it.unisa.offerta_formativa.manager.ModuleManager;
import it.unisa.offerta_formativa.manager.ProfModuleClassManager;
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
@WebServlet(name = "ShowPagesServlet", urlPatterns = {"/ShowPagesServlet"})
public class ShowPagesServlet extends HttpServlet {
    
    private DepartmentManager deptMng;
    private CycleManager cycleMng;
    private DegreeManager degreeMng;
    private TeachingManager teachingMng;
    private CurriculumManager currMng;
    private final ModuleManager modMng;
    private final ClassManager classMng;
    private final ProfModuleClassManager pmcMng;
    
    public ShowPagesServlet() {
        super();
        deptMng = DepartmentManager.getInstance();
        cycleMng = CycleManager.getInstance();
        degreeMng = DegreeManager.getInstance();
        teachingMng = TeachingManager.getInstance();
        currMng = CurriculumManager.getInstance();
        classMng = ClassManager.getInstance();
        modMng = ModuleManager.getInstance();
        pmcMng = ProfModuleClassManager.getInstance();
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
                if(page.equalsIgnoreCase("insertClass")){
                    request.setAttribute("departments",deptMng.getAllDepartments());
                    request.setAttribute("cycles", cycleMng.getAllCycles());
                    request.getRequestDispatcher(path+"insertClass.jsp").forward(request, response);
                    //request.getRequestDispatcher(path+"insertTeaching.jsp").forward(request, response);
                }
                if(page.equalsIgnoreCase("insertModule")){
                    request.setAttribute("departments",deptMng.getAllDepartments());
                    request.setAttribute("cycles", cycleMng.getAllCycles());
                    request.getRequestDispatcher(path+"insertModule.jsp").forward(request, response);
                    //request.getRequestDispatcher(path+"insertTeaching.jsp").forward(request, response);
                }
                if(page.equalsIgnoreCase("listModuleClass")){
                    if(request.getParameterMap().containsKey("matricula")){
                        request.setAttribute("teaching", teachingMng.readTeaching(request.getParameter("matricula")));
                        request.setAttribute("modules", modMng.getModulesByTeaching(request.getParameter("matricula")));
                        request.setAttribute("classes", classMng.getClassesByTeaching(request.getParameter("matricula")));
                        //request.setAttribute("profmoduleclass", pmcMng.);
                        request.getRequestDispatcher(path+"listClassModule.jsp").forward(request, response);
                    }
                }
                if(page.equalsIgnoreCase("modifyClass")){
                    if(request.getParameterMap().containsKey("matricula") && request.getParameterMap().containsKey("classTitle")){
                        String matricula = request.getParameter("matricula");
                        request.setAttribute("matricula", matricula);
                        request.setAttribute("title", request.getParameter("classTitle"));
                        request.setAttribute("teaching",teachingMng.readTeaching(matricula));
                        request.getRequestDispatcher(path+"modifyClass.jsp").forward(request, response);
                    }
                }
                if(page.equalsIgnoreCase("modifyModule")){
                    if(request.getParameterMap().containsKey("matricula") && request.getParameterMap().containsKey("moduleTitle")){
                        String matricula = request.getParameter("matricula");
                        request.setAttribute("matricula", matricula);
                        request.setAttribute("title", request.getParameter("moduleTitle"));
                        request.setAttribute("teaching",teachingMng.readTeaching(matricula));
                        request.getRequestDispatcher(path+"modifyModule.jsp").forward(request, response);
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
