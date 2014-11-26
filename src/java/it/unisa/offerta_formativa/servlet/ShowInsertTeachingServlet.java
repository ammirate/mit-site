package it.unisa.offerta_formativa.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.offerta_formativa.manager.CycleManager;
import it.unisa.offerta_formativa.manager.DegreeManager;
import it.unisa.offerta_formativa.manager.DepartmentManager;

/**
 * Servlet implementation class ShowInsertTeachingServlet
 */
@WebServlet("/ShowInsertTeachingServlet")
public class ShowInsertTeachingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DepartmentManager dm;
	private CycleManager cm;
	private DegreeManager degreeMng;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowInsertTeachingServlet() {
    	super();
        // TODO Auto-generated constructor stub
        dm = DepartmentManager.getInstance();
        cm = CycleManager.getInstance();
        degreeMng = DegreeManager.getInstance();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("departments",dm.getAllDepartments());
		request.setAttribute("cycles", cm.getAllCycles());
		request.setAttribute("degrees", degreeMng.getDegreesByCycle(2));
		request.getRequestDispatcher("/insertTeaching.jsp").forward(request, response);
	}

}
