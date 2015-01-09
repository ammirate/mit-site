/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.gestionetesi.servlet;

import it.unisa.integrazione.database.DegreeManager;
import it.unisa.integrazione.database.DepartmentManager;
import it.unisa.integrazione.database.PersonManager;
import it.unisa.integrazione.database.exception.ConnectionException;
import it.unisa.integrazione.model.Person;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author CosimoAlessandro
 */

@WebServlet(name = "RecuperaDatiUtente", urlPatterns = {"./RecuperaDatiUtente"})
public class RecuperaDatiUtente extends HttpServlet {
    private final DegreeManager managerDegree= DegreeManager.getInstance();
    private final DepartmentManager managerDepartment= DepartmentManager.getInstance();
    PersonManager manager_utente;

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */

            String ssn = request.getParameter("ssn");
            String tipo = request.getParameter("tipo");

            Person p = new Person();
            manager_utente = new PersonManager();
            p = manager_utente.getPersonBySSN(ssn);
            String titolo_corso_laurea= managerDegree.readDegree(p.getDegree().getMatricula()).getTitle();
            String titolo_dipartimento= managerDepartment.getDepartmentByAbbreviation(p.getDepartment().getAbbreviation()).getTitle();
            
            JSONObject user_data = new JSONObject();

            user_data.put("nome", p.getName());
            user_data.put("cognome", p.getSurname());
            user_data.put("matricola", p.getMatricula());
            user_data.put("università", p.getUniversity());
            user_data.put("corso_laurea", titolo_corso_laurea);
            user_data.put("dipartimento", titolo_dipartimento);
            
            out.print(user_data.toString());

        } catch (Exception ex) {
            Logger.getLogger(RecuperaDatiUtente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
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
