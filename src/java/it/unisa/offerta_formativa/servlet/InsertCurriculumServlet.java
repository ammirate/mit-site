package it.unisa.offerta_formativa.servlet;

import it.unisa.offerta_formativa.beans.Curriculum;
import it.unisa.offerta_formativa.manager.CurriculumManager;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;


/**
 * 
 * @author Davide
 */
@WebServlet("/InsertCurriculumServlet")
public class InsertCurriculumServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CurriculumManager cuMng;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertCurriculumServlet() {
        super();
        // TODO Auto-generated constructor stub   
        cuMng = CurriculumManager.getInstance();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request, response);
    }

    private void InsertCurriculum(Curriculum cur) {
        // TODO Auto-generated method stub
            cuMng.createCurriculum(cur);
    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        InsertCurriculum(new Curriculum(request.getParameter("degree_matricula"), request.getParameter("title"), request.getParameter("degree_matricula"),Boolean.parseBoolean(request.getParameter("status"))));
        ServletContext sc = getServletContext();  
        RequestDispatcher rd = sc.getRequestDispatcher("/ShowCurriculumServlet");  
        rd.forward(request, response); 
    }

}
