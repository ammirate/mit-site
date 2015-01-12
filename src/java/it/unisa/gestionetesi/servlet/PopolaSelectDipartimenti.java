/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.gestionetesi.servlet;

import it.unisa.integrazione.database.DepartmentManager;
import it.unisa.integrazione.database.exception.ConnectionException;
import it.unisa.integrazione.model.Department;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet("/PopolaSelectDipartimenti")
public class PopolaSelectDipartimenti extends HttpServlet {
    private Logger logger = Logger.getLogger("db");
    private JSONObject dipartimento;
    private final DepartmentManager managerDepartment= DepartmentManager.getInstance();
    private ArrayList<Department> listaDipartimenti= null;

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

            JSONArray jarrayDipartimenti = new JSONArray();

            listaDipartimenti= (ArrayList<Department>) managerDepartment.getAllDepartments();
            
            for (int i = 0; i < listaDipartimenti.size(); i++) {
            dipartimento= new JSONObject();
            
            dipartimento.put("abbreviazione", listaDipartimenti.get(i).getAbbreviation());
            dipartimento.put("titolo", listaDipartimenti.get(i).getTitle());
            
            logger.info("Dipartimento: " + listaDipartimenti.get(i).getAbbreviation());

            
            jarrayDipartimenti.put(i,dipartimento);
  
            }
            
            JSONObject mainObj = new JSONObject();
            mainObj.put("mainOb", jarrayDipartimenti);

            out.print(mainObj.toString());
            
            
        } catch (JSONException ex) {
            Logger.getLogger(PopolaSelectDipartimenti.class.getName()).log(Level.SEVERE, null, ex);
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
