/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.servlet;

import it.unisa.offerta_formativa.manager.DepartmentManager;
import it.unisa.offerta_formativa.manager.ParserHtmlManager;
import it.unisa.offerta_formativa.moodle.manager.MoodleConnectionManager;
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
    ParserHtmlManager phm;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSyllabusServlet() {
    	super();
        phm = ParserHtmlManager.getInstance();
        // TODO Auto-generated constructor stub   
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

        private String getHtml(String url){
            return phm.getHtml(url);
        }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("htmlparser",getHtml("https://esse3web.unisa.it/unisa/Guide/PaginaADErogata.do;jsessionid=2138B215C22E5238EE45BA2DD2C3CCB9.jvm6?ad_er_id=2014*N0*N0*S1*39751*509790&ANNO_ACCADEMICO=2014&mostra_percorsi=N"));
                //request.setAttribute("htmlparser",getHtml(request.getParameter("url")));
		request.getRequestDispatcher("/showSyllabus.jsp").forward(request, response);
	}
}
