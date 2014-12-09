package it.unisa.offerta_formativa.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.offerta_formativa.beans.Degree;
import it.unisa.offerta_formativa.manager.CycleManager;
import it.unisa.offerta_formativa.manager.DegreeManager;
import it.unisa.offerta_formativa.manager.DepartmentManager;


/**
 * Servlet implementation class Servlet
 */
@WebServlet("/ModifyDegreeServlet")
public class ModifyDegreeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DegreeManager degreeMng;
    private DepartmentManager dm;
    private CycleManager cym;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyDegreeServlet() {
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

    private Degree GetDegree(String matricula) {
        // TODO Auto-generated method stub
            return degreeMng.readDegree(matricula);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setAttribute("degree",GetDegree(request.getParameter("degree_matricula")));
        request.setAttribute("cycles", cym.getAllCycles());
        request.setAttribute("departments", dm.getAllDepartments());
        request.getRequestDispatcher("/ModifyDegree.jsp").forward(request, response);
    }

}
