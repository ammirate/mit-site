/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.servlet.profmoduleclass;

import it.unisa.offerta_formativa.beans.Curriculum;
import it.unisa.integrazione.model.Degree;
import it.unisa.integrazione.model.Person;
import it.unisa.offerta_formativa.beans.ProfModuleClass;
import it.unisa.offerta_formativa.manager.ClassManager;
import it.unisa.offerta_formativa.manager.CurriculumManager;
import it.unisa.integrazione.database.CycleManager;
import it.unisa.integrazione.database.DegreeManager;
import it.unisa.integrazione.database.DepartmentManager;
import it.unisa.offerta_formativa.manager.Exceptions.ClassPartitionException;
import it.unisa.offerta_formativa.manager.Exceptions.ModuleException;
import it.unisa.offerta_formativa.manager.Exceptions.TeachingException;
import it.unisa.offerta_formativa.manager.ModuleManager;
import it.unisa.integrazione.database.PersonManager;
import it.unisa.integrazione.database.exception.ConnectionException;
import it.unisa.offerta_formativa.manager.ProfModuleClassManager;
import it.unisa.offerta_formativa.manager.TeachingManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
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
@WebServlet(name = "ShowAssociationServlet", urlPatterns = {"/ShowAssociationServlet"})
public class ShowAssociationServlet extends HttpServlet {
    
    private DepartmentManager deptMng;
    private CycleManager cycleMng;
    private DegreeManager degreeMng;
    private TeachingManager teachingMng;
    private CurriculumManager currMng;
    private final ModuleManager modMng;
    private final ClassManager classMng;
    private final ProfModuleClassManager pmcMng;
    private final PersonManager personMng;
    
    public ShowAssociationServlet() {
        super();
        deptMng = DepartmentManager.getInstance();
        cycleMng = CycleManager.getInstance();
        degreeMng = DegreeManager.getInstance();
        teachingMng = TeachingManager.getInstance();
        currMng = CurriculumManager.getInstance();
        classMng = ClassManager.getInstance();
        modMng = ModuleManager.getInstance();
        pmcMng = ProfModuleClassManager.getInstance();
        personMng = PersonManager.getInstance();
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
            String path="/offertaFormativa/amministratore/classmodule/";  
            if(request.getParameterMap().containsKey("matricula")){
                try {
                    String matricula=request.getParameter("matricula");
                    request.setAttribute("teaching", teachingMng.readTeaching(matricula));
                    request.setAttribute("modules", modMng.getModulesByTeaching(matricula));
                    request.setAttribute("classes", classMng.getClassesByTeaching(matricula));
                    HashMap<ProfModuleClass,String> map = new HashMap<>();
                    for(ProfModuleClass pmc :pmcMng.getByTeaching(matricula)){
                        map.put(pmc, personMng.getPersonByEmail(pmc.getProfEmail()).getName()+" "+personMng.getPersonByEmail(pmc.getProfEmail()).getSurname());
                    }
                    request.setAttribute("profmoduleclass", map);
                    request.getRequestDispatcher(path+"listClassModule.jsp").forward(request, response);
                } catch (TeachingException ex) {
                    Logger.getLogger(ShowAssociationServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ModuleException ex) {
                    Logger.getLogger(ShowAssociationServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassPartitionException ex) {
                    Logger.getLogger(ShowAssociationServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ShowAssociationServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ConnectionException ex) {
                    Logger.getLogger(ShowAssociationServlet.class.getName()).log(Level.SEVERE, null, ex);
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
