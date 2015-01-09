/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.servlet.profmoduleclass;

import it.unisa.integrazione.database.DegreeManager;
import it.unisa.offerta_formativa.beans.ProfModuleClass;
import it.unisa.offerta_formativa.manager.ClassManager;
import it.unisa.offerta_formativa.manager.CurriculumManager;
import it.unisa.offerta_formativa.manager.Exceptions.CurriculumException;
import it.unisa.offerta_formativa.manager.ModuleManager;
import it.unisa.offerta_formativa.manager.ProfModuleClassManager;
import it.unisa.offerta_formativa.manager.TeachingManager;
import it.unisa.integrazione.database.PersonManager;
import it.unisa.integrazione.database.exception.ConnectionException;
import it.unisa.offerta_formativa.manager.Exceptions.ClassPartitionException;
import it.unisa.offerta_formativa.manager.Exceptions.ModuleException;
import it.unisa.offerta_formativa.manager.Exceptions.TeachingException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
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
@WebServlet(name = "ModifyProfAssociationServlet", urlPatterns = {"/ModifyProfAssociationServlet"})
public class ModifyProfAssociationServlet extends HttpServlet {
    private final ProfModuleClassManager pmcMng;
    private final ClassManager classMng;
    private final ModuleManager modMng;
    private final TeachingManager teachingMng;
    private final PersonManager personMng;

    public ModifyProfAssociationServlet() {
        pmcMng = ProfModuleClassManager.getInstance();
        classMng = ClassManager.getInstance();
        modMng = ModuleManager.getInstance();
        teachingMng = TeachingManager.getInstance();
        personMng = PersonManager.getInstance();
    }

    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path="/offertaFormativa/amministratore/classmodule/";  
        if(request.getParameterMap().containsKey("matricula") &&
            request.getParameterMap().containsKey("module")&&
            request.getParameterMap().containsKey("class")&&
            request.getParameterMap().containsKey("oldprofmail") &&
            request.getParameterMap().containsKey("prof")){
            try {
                pmcMng.updateProfessor(request.getParameter("prof"), new ProfModuleClass(request.getParameter("class"),
                        request.getParameter("matricula"),
                        request.getParameter("module"),
                        request.getParameter("oldprofmail")));
                request.setAttribute("success", true);
                request.setAttribute("successMessage", "Modifica del docente avvenuta con successo.");
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
                request.getRequestDispatcher(path+"listClassModule.jsp").forward(request,response);
            }catch (SQLException ex) {
                Logger.getLogger(ModifyProfAssociationServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ConnectionException ex) {
                Logger.getLogger(ModifyProfAssociationServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassPartitionException ex) {
                Logger.getLogger(ModifyProfAssociationServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ModuleException ex) {
                Logger.getLogger(ModifyProfAssociationServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TeachingException ex) {
                Logger.getLogger(ModifyProfAssociationServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
