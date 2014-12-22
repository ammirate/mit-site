package it.unisa.offerta_formativa.servlet.department;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.Department;
import it.unisa.integrazione.database.DepartmentManager;


/**
 * Servlet implementation class Servlet
 */
@WebServlet("/ModifyDepartmentServlet")
public class ModifyDepartmentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DepartmentManager departmentMng;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyDepartmentServlet() {
        super();
        // TODO Auto-generated constructor stub   
        departmentMng = DepartmentManager.getInstance();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request, response);
    }

    private Department GetDepartment(String abbreviation) {
        // TODO Auto-generated method stub
            return departmentMng.readDepartment(abbreviation);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setAttribute("department",GetDepartment(request.getParameter("abbreviation")));
        request.getRequestDispatcher("/offertaFormativa/amministratore/department/ModifyDepartment.jsp").forward(request, response);
    }

}
