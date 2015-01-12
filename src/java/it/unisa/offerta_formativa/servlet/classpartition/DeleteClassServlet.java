/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.servlet.classpartition;

import it.unisa.offerta_formativa.manager.ClassManager;
import it.unisa.offerta_formativa.beans.ProfModuleClass;
import it.unisa.offerta_formativa.manager.Exceptions.ClassPartitionException;
import it.unisa.offerta_formativa.manager.Exceptions.ModuleException;
import it.unisa.offerta_formativa.manager.Exceptions.TeachingException;
import it.unisa.offerta_formativa.manager.ModuleManager;
import it.unisa.offerta_formativa.manager.ProfModuleClassManager;
import it.unisa.offerta_formativa.manager.TeachingManager;
import it.unisa.integrazione.database.PersonManager;
import it.unisa.integrazione.database.exception.ConnectionException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alessandro
 */
@WebServlet(name = "DeleteClassServlet", urlPatterns = {"/DeleteClassServlet"})
public class DeleteClassServlet extends HttpServlet {

    private ModuleManager moduleMng;
    private ClassManager classMng;
    private TeachingManager teachingMng;
    private final ProfModuleClassManager pmcMng;
    private final PersonManager personMng;
    private final ModuleManager modMng;

    public DeleteClassServlet() {
        super();
        moduleMng = ModuleManager.getInstance();
        classMng = ClassManager.getInstance();
        teachingMng = TeachingManager.getInstance();
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
        String path = "/offertaFormativa/amministratore/classmodule/";
        try {

            if (request.getParameterMap().containsKey("matricula") && request.getParameterMap().containsKey("title")) {
                if(!checkFields(request).equalsIgnoreCase(""))throw new ClassPartitionException(checkFields(request));
                classMng.deleteClass( request.getParameter("matricula"),request.getParameter("title"));
                pmcMng.deleteByClass(request.getParameter("title"), request.getParameter("matricula"));
                request.setAttribute("matricula", request.getAttribute("matricula"));
                request.setAttribute("successMessage", "Eliminazione della classe avvenuta con successo");
                request.setAttribute("success", true);
                String matricula = request.getParameter("matricula");
                request.setAttribute("teaching", teachingMng.readTeaching(matricula));
                request.setAttribute("modules", modMng.getModulesByTeaching(matricula));
                request.setAttribute("classes", classMng.getClassesByTeaching(matricula));
                HashMap<ProfModuleClass, String> map = new HashMap<>();
                for (ProfModuleClass pmc : pmcMng.getByTeaching(matricula)) {
                    map.put(pmc, personMng.getPersonByEmail(pmc.getProfEmail()).getName() + " " + personMng.getPersonByEmail(pmc.getProfEmail()).getSurname());
                }
                request.setAttribute("profmoduleclass", map);
                request.getRequestDispatcher(path + "listClassModule.jsp").forward(request, response);
            }
        } catch (TeachingException ex) {
            request.setAttribute("errorMessage", "Errore in insegnamento: " + ex.getMessage());
            request.setAttribute("error", true);
            request.getRequestDispatcher(path + "listClassModule.jsp").forward(request, response);
        } catch (ClassPartitionException ex) {
            request.setAttribute("errorMessage", "Errore nell'eliminazione della classe: " + ex.getMessage());
            request.setAttribute("error", true);
            request.getRequestDispatcher(path + "listClassModule.jsp").forward(request, response);
        } catch (ModuleException ex) {
            request.setAttribute("errorMessage", "Errore nel modulo: " + ex.getMessage());
            request.setAttribute("error", true);
            request.getRequestDispatcher(path + "listClassModule.jsp").forward(request, response);
        } catch (SQLException ex) {
            request.setAttribute("errorMessage", "Errore in DB: " + ex.getMessage());
            request.setAttribute("error", true);
            request.getRequestDispatcher(path + "listClassModule.jsp").forward(request, response);
        } catch (ConnectionException ex) {
            request.setAttribute("errorMessage", "Errore connessione al DB: " + ex.getMessage());
            request.setAttribute("error", true);
            request.getRequestDispatcher(path + "listClassModule.jsp").forward(request, response);
        }
    }
    
    private String checkFields(HttpServletRequest request) {
        if(request.getParameter("matricula").length()!=10) return "La matricola deve essere di 10 caratteri.";
        if(request.getParameter("title").length()>2) return "Il titolo della classe deve essere composto da almeno 2 caratteri.";
        return "";
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
