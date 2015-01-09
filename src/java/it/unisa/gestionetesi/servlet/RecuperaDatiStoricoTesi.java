/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.gestionetesi.servlet;

import it.unisa.gestionetesi.beans.Tag;
import it.unisa.gestionetesi.beans.Tesi;
import it.unisa.integrazione.database.DegreeManager;
import it.unisa.integrazione.database.DepartmentManager;
import it.unisa.gestionetesi.manager.ManagerTesi;
import it.unisa.gestionetesi.manager.TagManager;
import it.unisa.integrazione.database.PersonManager;
import it.unisa.integrazione.database.exception.ConnectionException;
import it.unisa.integrazione.model.Degree;
import it.unisa.integrazione.model.Department;
import it.unisa.integrazione.model.Person;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@WebServlet(name = "RecuperaDatiStoricoTesi", urlPatterns = {"/RecuperaDatiStoricoTesi"})
public class RecuperaDatiStoricoTesi extends HttpServlet {

    ManagerTesi manager_tesi;
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
            manager_tesi = new ManagerTesi();
	    person_manager = new PersonManager();

	    String[] researchCriteria = request.getParameterValues("researchCriteria[]");
            List<Tesi> T = manager_tesi.getThesisByResearchCriteria(researchCriteria);
            
	    jarray = new JSONArray();
            
	    if(researchCriteria == null) {
                fillJSONArray(T);
            } else {
                fillJSONArray2(T);
            }
	    
	    out.print(jarray.toString());
		
	    System.out.println("contenuto jarray: " + jarray.toString());
        } finally {
            out.close();
        }
    }
    
    private void fillJSONArray(List<Tesi> T) throws JSONException{
	JSONObject thesis_data = null;
	
	int i = 0;
	for(Tesi tesi : T) {
	    thesis_data = new JSONObject();
	    thesis_data.put("thesis_id", tesi.getId_tesi());
	    thesis_data.put("thesis_title", tesi.getTitolo());
	    jarray.put(i++, thesis_data);
        }
    }
    
    private void fillJSONArray2(List<Tesi> T) throws SQLException, ConnectionException, JSONException {
	JSONObject thesis_data = null;

	Map<String, String> DEP = new HashMap<String, String>();
	Map<String, String> DEG = new HashMap<String, String>();
	
        int i = 0;
        for(Tesi tesi : T) {
	    thesis_data = new JSONObject();
            Person professor = person_manager.getProfessorByThesisID(tesi.getId_tesi());
            Person student = person_manager.getStudentByID(tesi.getId_studente());
	    Department department = DepartmentManager.getInstance().getDepartmentByAbbreviation(professor.getDepartment().getAbbreviation());
	    List<Degree> degrees = DegreeManager.getInstance().getDegreesByDepartment(professor.getDepartment().getAbbreviation());
	    List<Tag> tags = TagManager.getInstance().getTagsByThesisId(tesi.getId_tesi());
	    
	    if(!DEP.containsKey(department.getAbbreviation())) {
		DEP.put(department.getAbbreviation(), department.getTitle());
		thesis_data.put("department_abbreviation", department.getAbbreviation());
		thesis_data.put("department_title", department.getTitle());
	    }
	    
	    for(Degree degree : degrees) {
		if(!DEG.containsKey(degree.getMatricula())) {
		    DEG.put(degree.getMatricula(), degree.getMatricula() + "] " + degree.getTitle());
		    thesis_data.put("degree_matricula", degree.getMatricula());
		    thesis_data.put("degree_title", degree.getTitle());
		} 
	    }
	    
	    thesis_data.put("professor_ssn", professor.getSsn());
	    thesis_data.put("professor_name", professor.getName());
	    thesis_data.put("professor_surname", professor.getSurname());
	    
	    thesis_data.put("student_ssn", student.getSsn());
	    thesis_data.put("student_name", student.getName());
	    thesis_data.put("student_surname", student.getSurname());
	    
	    thesis_data.put("thesis_id", tesi.getId_tesi());
	    thesis_data.put("thesis_title", tesi.getTitolo());
	    
	    for(Tag tag : tags) {
		thesis_data.put("tag_id", tag.getId());
		thesis_data.put("tag_name", tag.getNomeTag());
	    }
	    
	    String[] data = tesi.getData_fine().split("-");
	    thesis_data.put("degree_date", data[2] + "/" + data[1] + "/" + data[0]);
	    thesis_data.put("thesis_abstract", tesi.getAbstract_tesi());
	    thesis_data.put("thesis_description", tesi.getDescrizione());
	    
	    
	    jarray.put(i++, thesis_data);
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
            Logger.getLogger(RecuperaDatiStoricoTesi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RecuperaDatiStoricoTesi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(RecuperaDatiStoricoTesi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(RecuperaDatiStoricoTesi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConnectionException ex) {
	    Logger.getLogger(RecuperaDatiStoricoTesi.class.getName()).log(Level.SEVERE, null, ex);
	} catch (JSONException ex) {
	    Logger.getLogger(RecuperaDatiStoricoTesi.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RecuperaDatiStoricoTesi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RecuperaDatiStoricoTesi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(RecuperaDatiStoricoTesi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(RecuperaDatiStoricoTesi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConnectionException ex) {
	    Logger.getLogger(RecuperaDatiStoricoTesi.class.getName()).log(Level.SEVERE, null, ex);
	} catch (JSONException ex) {
	    Logger.getLogger(RecuperaDatiStoricoTesi.class.getName()).log(Level.SEVERE, null, ex);
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
