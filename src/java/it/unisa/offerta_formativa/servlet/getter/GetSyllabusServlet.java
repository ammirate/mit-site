/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.servlet.getter;

import it.unisa.offerta_formativa.manager.TeachingManager;
import java.io.IOException;
import java.util.ArrayList;
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

        private ArrayList<String> getHtml(String teaching_matricula){
            ArrayList<String> toReturn=new ArrayList<String>();
            String html= tm.getHtmlSyllabus(teaching_matricula);
            String title = html.substring(html.indexOf("<h1>") + 4, html.indexOf("</h1>"));
            String toReplace=html.substring(html.indexOf("<h1>") , html.indexOf("</h1>"));
            toReturn.add(title);
            toReturn.add( html.replace(toReplace, ""));
            return toReturn;
        }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
                request.setAttribute("htmlSyllabus",getHtml(request.getParameter("teaching_matricula")));
		request.getRequestDispatcher("/offertaFormativaJSP/showSyllabus.jsp").forward(request, response);
	}
}
