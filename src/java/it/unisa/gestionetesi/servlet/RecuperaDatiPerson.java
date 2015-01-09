/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.gestionetesi.servlet;

import it.unisa.gestionetesi.beans.Tesi;
import it.unisa.integrazione.database.PersonManager;
import it.unisa.integrazione.database.exception.ConnectionException;
import it.unisa.integrazione.model.Person;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "RecuperaDatiPerson", urlPatterns = {"/RecuperaDatiPerson"})
public class RecuperaDatiPerson extends HttpServlet {
       
    PersonManager person_manager;
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
            String type = request.getParameter("type");
            String data = request.getParameter("data");
            person_manager = new PersonManager();
            List<Person> R = new ArrayList<Person>();
            
	    jarray = new JSONArray();
	    
	    if(type.equalsIgnoreCase("professor")) {
		if(data.equals("ALL_Professors")) {
		    R = person_manager.getAllSupervisors();
		} else {
		    R.add(person_manager.getProfessorByThesisID(Integer.getInteger(data)));
		}
	    } else if(type.equalsIgnoreCase("student")) {
		if(data.equals("ALL_Students")) {
		    R = person_manager.getAllStudents();
		} else {
		    R.add(person_manager.getStudentByID(data));
		}
	    }
            fillJSONArray(R, type);
            out.print(jarray.toString());
	    
	    System.out.println("contenuto jarray: " + jarray.toString());
        } finally {
            out.close();
        }
    }
    
    private void fillJSONArray(List<Person> R, String type) throws JSONException{
        JSONObject person_data = null;
	
	int i = 0;
	
	if(type.equalsIgnoreCase("professor")) {
	    for(Person professor : R) {
		person_data = new JSONObject();
		person_data.put("professor_ssn", professor.getSsn());
		person_data.put("professor_name", professor.getName());
		person_data.put("professor_surname", professor.getSurname());
		jarray.put(i++, person_data);
	    }
	} else if(type.equalsIgnoreCase("student")) {
	    for(Person student : R) {
		person_data = new JSONObject();
		person_data.put("student_ssn", student.getSsn());
		person_data.put("student_name", student.getName());
		person_data.put("student_surname", student.getSurname());
		jarray.put(i++, person_data);
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RecuperaDatiPerson.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RecuperaDatiPerson.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(RecuperaDatiPerson.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(RecuperaDatiPerson.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConnectionException ex) {
	    Logger.getLogger(RecuperaDatiPerson.class.getName()).log(Level.SEVERE, null, ex);
	} catch (JSONException ex) {
	    Logger.getLogger(RecuperaDatiPerson.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RecuperaDatiPerson.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RecuperaDatiPerson.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(RecuperaDatiPerson.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(RecuperaDatiPerson.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConnectionException ex) {
	    Logger.getLogger(RecuperaDatiPerson.class.getName()).log(Level.SEVERE, null, ex);
	} catch (JSONException ex) {
	    Logger.getLogger(RecuperaDatiPerson.class.getName()).log(Level.SEVERE, null, ex);
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
