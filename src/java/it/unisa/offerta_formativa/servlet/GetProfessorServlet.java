/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.servlet;

import it.unisa.offerta_formativa.beans.Degree;
import it.unisa.offerta_formativa.beans.Person;
import it.unisa.offerta_formativa.manager.ProfessorManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alessandro
 */
@WebServlet(name = "GetProfessorServlet", urlPatterns = {"/GetProfessorServlet"})
public class GetProfessorServlet extends HttpServlet {

    public GetProfessorServlet(){}
    
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        ProfessorManager pm = ProfessorManager.getInstance();
        response.setContentType("text/plain");  
			response.setCharacterEncoding("UTF-8"); 
			int i=1;
			String toRet="";
			for(Person d : pm.getProfessorByDepartment(request.getParameter("abbreviation"))){
				toRet+="<option value="+d.getName()+">"+d.getName()+"</option>"; 
				i++;
			}
        response.getWriter().write(toRet);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProfessorManager pm = ProfessorManager.getInstance();
        //if(request.getParameterMap().containsKey("abbreviation")){
			response.setContentType("text/plain");  
			response.setCharacterEncoding("UTF-8"); 
			int i=1;
			String toRet="";
			for(Person d : pm.getProfessorByDepartment(request.getParameter("abbreviation"))){
				toRet+="<option value="+d.getName()+">"+d.getName()+"</option>"; 
				i++;
			}
			response.getWriter().write(toRet);
		//}
    }       

}
