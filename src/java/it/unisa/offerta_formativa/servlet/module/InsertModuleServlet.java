/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.servlet.module;

import it.unisa.integrazione.database.DegreeManager;
import it.unisa.offerta_formativa.beans.ClassPartition;
import it.unisa.offerta_formativa.beans.Module;
import it.unisa.offerta_formativa.beans.ProfModuleClass;
import it.unisa.offerta_formativa.beans.Teaching;
import it.unisa.offerta_formativa.manager.ClassManager;
import it.unisa.offerta_formativa.manager.Exceptions.ClassPartitionException;
import it.unisa.offerta_formativa.manager.Exceptions.ModuleException;
import it.unisa.offerta_formativa.manager.Exceptions.TeachingException;
import it.unisa.offerta_formativa.manager.ModuleManager;
import it.unisa.offerta_formativa.manager.ProfModuleClassManager;
import it.unisa.offerta_formativa.manager.TeachingManager;
import it.unisa.offerta_formativa.moodle.manager.MoodleConnectionManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "InsertModuleServlet", urlPatterns = {"/InsertModuleServlet"})
public class InsertModuleServlet extends HttpServlet {
    private final ModuleManager modMng;
    private final TeachingManager teachingMng;
    private final ClassManager classMng;
    private final ProfModuleClassManager pmcMng;
    private final DegreeManager degreeMng;
    private final MoodleConnectionManager moodleConn;
    private ArrayList<ClassPartition> mlist;

    public InsertModuleServlet() {
        teachingMng = TeachingManager.getInstance();
        modMng = ModuleManager.getInstance();
        classMng = ClassManager.getInstance();
        pmcMng = ProfModuleClassManager.getInstance();
        degreeMng = DegreeManager.getInstance();
        moodleConn = MoodleConnectionManager.getInstance();
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
        if(request.getParameterMap().containsKey("teaching") && request.getParameterMap().containsKey("modulename")){
            try {
                if(!checkFields(request).equalsIgnoreCase("")) throw new ClassPartitionException(checkFields(request));
                String matricula = request.getParameter("teaching");
                String modulename = request.getParameter("modulename");
                String degree_matricula = request.getParameter("degree");
                String department_abbreviation = request.getParameter("department");
            
                if(modMng.readModule(modulename,matricula)!=null) throw new ModuleException("Esiste già un modulo con questo nome");
                modMng.createModule(new Module(request.getParameter("modulename"),matricula));
                mlist = classMng.getClassesByTeaching(matricula);
                
                
                
                Teaching teaching = teachingMng.readTeaching(matricula);
                for(ClassPartition m : mlist){
                    
                    pmcMng.create(new ProfModuleClass(m.getTitle(),
                                                    matricula,
                                                    request.getParameter("modulename"),
                                                    request.getParameter(m.getTitle())
                    ));
                }
                
            } catch (ClassPartitionException ex) {
                request.setAttribute("errorMessage", "Errore classe: "+ex.getMessage());
                request.setAttribute("error", true);
                request.getRequestDispatcher(path+"insertClass.jsp").forward(request, response);
            } catch (ModuleException ex) {
                request.setAttribute("errorMessage", "Errore lettura moduli: "+ex.getMessage());
                request.setAttribute("error", true);
                request.getRequestDispatcher(path+"insertClass.jsp").forward(request, response);
            } catch (TeachingException ex) {
                request.setAttribute("errorMessage", "Errore lettura insegnamento: "+ex.getMessage());
                request.setAttribute("error", true);
                request.getRequestDispatcher(path+"insertClass.jsp").forward(request, response);
            }
            request.setAttribute("success", true);
            request.setAttribute("successMessage", "Inserimento effettuato con successo");
            request.getRequestDispatcher(path+"insertClass.jsp").forward(request, response);
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

    private String checkFields(HttpServletRequest request) {
        if(request.getParameter("teaching").length()!=10) return "La matricola deve essere di 10 caratteri.";
        if(request.getParameter("modulename").length()<=2) return "Il titolo del modulo deve essere composto da almeno 2 caratteri.";
        return "";
    }
    
}
