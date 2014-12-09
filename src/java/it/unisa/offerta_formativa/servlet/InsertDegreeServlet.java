package it.unisa.offerta_formativa.servlet;

import it.unisa.offerta_formativa.beans.Degree;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.offerta_formativa.manager.CycleManager;
import it.unisa.offerta_formativa.manager.DegreeManager;
import it.unisa.offerta_formativa.manager.DepartmentManager;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;


/**
 * Servlet implementation class Servlet
 */
@WebServlet("/InsertDegreeServlet")
public class InsertDegreeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DegreeManager degreeMng;
    private DepartmentManager dm;
    private CycleManager cym;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertDegreeServlet() {
        super();
        // TODO Auto-generated constructor stub   
        dm = DepartmentManager.getInstance();
        degreeMng = DegreeManager.getInstance();
        cym = CycleManager.getInstance();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request, response);
    }

    private void InsertDegree(Degree degree) {
        // TODO Auto-generated method stub
            ArrayList<Degree> deg = new ArrayList<Degree>();
            deg.add(degree);
            degreeMng.insertDegree(deg);
    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        InsertDegree(new Degree(request.getParameter("degree_matricula"), request.getParameter("link"), request.getParameter("title"), Integer.parseInt(request.getParameter("cycle")), request.getParameter("departmentAbb"),Boolean.parseBoolean(request.getParameter("status"))));
        ServletContext sc = getServletContext();  
        RequestDispatcher rd = sc.getRequestDispatcher("/ShowDegreeServlet");  
        rd.forward(request, response); 
    }

}
