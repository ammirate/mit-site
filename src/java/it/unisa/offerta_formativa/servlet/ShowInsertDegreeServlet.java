package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.CycleManager;
import manager.DepartmentManager;

/**
 * Servlet implementation class ShowInsertDegreeServlet
 */
@WebServlet("/ShowInsertDegreeServlet")
public class ShowInsertDegreeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DepartmentManager dm;
	private CycleManager cm;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowInsertDegreeServlet() {
        super();
        // TODO Auto-generated constructor stub
        dm = DepartmentManager.getInstance();
        cm = CycleManager.getInstance();
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
		request.getRequestDispatcher("/insertDegree.jsp").forward(request, response);
	}

}
