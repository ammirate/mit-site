/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.gestionetesi.servlet;

import it.unisa.integrazione.database.DegreeManager;
import it.unisa.integrazione.database.exception.ConnectionException;
import it.unisa.integrazione.model.Degree;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
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
 * @author Daniele
 */
@WebServlet(name = "RecuperaDatiDegree", urlPatterns = {"/RecuperaDatiDegree"})
public class RecuperaDatiDegree extends HttpServlet {
       
    DegreeManager degree_manager;
    private JSONArray jarray;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, ConnectionException, JSONException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String data = request.getParameter("data");
            degree_manager = DegreeManager.getInstance();
            Collection<Degree> R = new ArrayList<Degree>();
               
	    jarray = new JSONArray();
	    
            if(data.equals("ALL")) {
                R = degree_manager.getAllDegrees();
            } else {
                R.add(degree_manager.getDegreeByTitle(data));
	    }
            
            supervisorDataToHTML(R);
            out.println(jarray);
            
        } finally {
            out.close();
        }
    }
    
    private void supervisorDataToHTML(Collection<Degree> D) throws JSONException{
	JSONObject degree_data = null;
	int i = 0;
        for(Degree degree : D) {
            degree_data = new JSONObject();
	    degree_data.put("degree_matricula", degree.getMatricula());
	    degree_data.put("degree_title", degree.getTitle());
	    jarray.put(i++, degree_data);
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RecuperaDatiDegree.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RecuperaDatiDegree.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(RecuperaDatiDegree.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(RecuperaDatiDegree.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConnectionException ex) {
	    Logger.getLogger(RecuperaDatiDegree.class.getName()).log(Level.SEVERE, null, ex);
	} catch (JSONException ex) {
	    Logger.getLogger(RecuperaDatiDegree.class.getName()).log(Level.SEVERE, null, ex);
	}
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RecuperaDatiDegree.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RecuperaDatiDegree.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(RecuperaDatiDegree.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(RecuperaDatiDegree.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConnectionException ex) {
	    Logger.getLogger(RecuperaDatiDegree.class.getName()).log(Level.SEVERE, null, ex);
	} catch (JSONException ex) {
	    Logger.getLogger(RecuperaDatiDegree.class.getName()).log(Level.SEVERE, null, ex);
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
