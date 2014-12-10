package it.unisa.offerta_formativa.servlet;

import it.unisa.offerta_formativa.beans.Curriculum;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.offerta_formativa.manager.CurriculumManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;


/**
 * 
 * @author Davide
 */
@WebServlet("/UpdateCurriculumServlet")
public class UpdateCurriculumServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CurriculumManager cuMng;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCurriculumServlet() {
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

    private void UpdateCurriculum(String matricula,Curriculum curriculum) {
        // TODO Auto-generated method stub
            cuMng.updateCurriculum(matricula, curriculum);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub 
        UpdateCurriculum(request.getParameter("curriculum_matricula"),new Curriculum(request.getParameter("curriculum_matricula"), request.getParameter("title"), request.getParameter("degree_matricula"),Boolean.parseBoolean(request.getParameter("status"))));
        ServletContext sc = getServletContext();  
        RequestDispatcher rd = sc.getRequestDispatcher("/ShowCurriculumServlet");  
        rd.forward(request, response); 
    }

}
