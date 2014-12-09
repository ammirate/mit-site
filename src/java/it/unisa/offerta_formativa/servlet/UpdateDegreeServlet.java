package it.unisa.offerta_formativa.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.offerta_formativa.beans.Degree;
import it.unisa.offerta_formativa.manager.DegreeManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;


/**
 * Servlet implementation class Servlet
 */
@WebServlet("/UpdateDegreeServlet")
public class UpdateDegreeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DegreeManager degreeMng;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateDegreeServlet() {
        super();
        // TODO Auto-generated constructor stub   
        degreeMng = DegreeManager.getInstance();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request, response);
    }

    private void UpdateDegree(String matricula,Degree degree) {
        // TODO Auto-generated method stub
            degreeMng.updateDegree(matricula, degree);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        UpdateDegree(request.getParameter("degree_matricula"),new Degree(request.getParameter("degree_matricula"), request.getParameter("link"), request.getParameter("title"), Integer.parseInt(request.getParameter("cycle")), request.getParameter("departmentAbb"),Boolean.parseBoolean(request.getParameter("status"))));
        ServletContext sc = getServletContext();  
        RequestDispatcher rd = sc.getRequestDispatcher("/ShowDegreeServlet");  
        rd.forward(request, response); 
    }

}
