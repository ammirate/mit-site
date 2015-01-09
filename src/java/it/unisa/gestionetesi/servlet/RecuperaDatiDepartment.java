/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.gestionetesi.servlet;

import it.unisa.gestionetesi.beans.Tesi;
import it.unisa.integrazione.database.DepartmentManager;
import it.unisa.integrazione.database.exception.ConnectionException;
import it.unisa.integrazione.model.Department;
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
@WebServlet(name = "RecuperaDatiDepartment", urlPatterns = {"/RecuperaDatiDepartment"})
public class RecuperaDatiDepartment extends HttpServlet {
       
    DepartmentManager department_manager;
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
     * @throws it.unisa.integrazione.database.exception.ConnectionException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, ConnectionException, JSONException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String data = request.getParameter("data");
            department_manager = DepartmentManager.getInstance();
            Collection<Department> D = new ArrayList<Department>();
	    
	    jarray = new JSONArray();
                    
            if(data.equals("ALL")) {
                D = department_manager.getAllDepartments();
            } else {
                D.add(department_manager.getDepartmentByAbbreviation(data));
	    }
            
            supervisorDataToJSON(D);
            out.println(jarray);
            
        } finally {
            out.close();
        }
    }
    
    private void supervisorDataToJSON(Collection<Department> D) throws JSONException{
	JSONObject department_data = null;
	int i = 0;
        for(Department department : D) {
            department_data = new JSONObject();
	    department_data.put("department_abbrevation", department.getAbbreviation());
	    department_data.put("department_title", department.getTitle());
	    jarray.put(i++, department_data);
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
            Logger.getLogger(RecuperaDatiDepartment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RecuperaDatiDepartment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(RecuperaDatiDepartment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(RecuperaDatiDepartment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConnectionException ex) {
	    Logger.getLogger(RecuperaDatiDepartment.class.getName()).log(Level.SEVERE, null, ex);
	} catch (JSONException ex) {
	    Logger.getLogger(RecuperaDatiDepartment.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RecuperaDatiDepartment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RecuperaDatiDepartment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(RecuperaDatiDepartment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(RecuperaDatiDepartment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConnectionException ex) {
	    Logger.getLogger(RecuperaDatiDepartment.class.getName()).log(Level.SEVERE, null, ex);
	} catch (JSONException ex) {
	    Logger.getLogger(RecuperaDatiDepartment.class.getName()).log(Level.SEVERE, null, ex);
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
