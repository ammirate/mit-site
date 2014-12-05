/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.servlet;

import it.unisa.offerta_formativa.manager.TeachingManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetSyllabusServlet
 * @author Davide
 */
@WebServlet("/GetSyllabusServlet")
public class GetSyllabusServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    TeachingManager tm;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSyllabusServlet() {
    	super();
        tm = TeachingManager.getInstance();
        // TODO Auto-generated constructor stub   
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

        private String getHtml(String teaching_matricula){
            String ret= tm.getHtmlSyllabus(teaching_matricula).replace("“", "\"");
            String toReturn= ret.replace("”", "\"");
            return toReturn.replace("’", "'");
        }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
                request.setAttribute("htmlSyllabus",getHtml(request.getParameter("teaching_matricula")));
		request.getRequestDispatcher("/showSyllabus.jsp").forward(request, response);
	}
}
