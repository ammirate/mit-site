package it.unisa.offerta_formativa.servlet.department;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.offerta_formativa.beans.Department;
import it.unisa.offerta_formativa.manager.DepartmentManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/UpdateDepartmentServlet")
public class UpdateDepartmentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DepartmentManager departmentMng;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateDepartmentServlet() {
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

    private void UpdateDepartment(String abbreviation, Department department) {
        // TODO Auto-generated method stub
        departmentMng.updateDepartment(abbreviation, department);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        if ((departmentMng.readDepartment(request.getParameter("abbreviation")) != null) && (request.getParameter("abbreviation").equalsIgnoreCase(request.getParameter("old_abbreviation")))) {
            UpdateDepartment(request.getParameter("old_abbreviation"), new Department(request.getParameter("abbreviation"), request.getParameter("title"), request.getParameter("url_moodle"), request.getParameter("token")));
            ServletContext sc = getServletContext();
            RequestDispatcher rd = sc.getRequestDispatcher("/ShowDepartmentServlet");
            rd.forward(request, response);
        } else if(departmentMng.readDepartment(request.getParameter("abbreviation")) == null){
            UpdateDepartment(request.getParameter("old_abbreviation"), new Department(request.getParameter("abbreviation"), request.getParameter("title"), request.getParameter("url_moodle"), request.getParameter("token")));
            ServletContext sc = getServletContext();
            RequestDispatcher rd = sc.getRequestDispatcher("/ShowDepartmentServlet");
            rd.forward(request, response);
        } else{
            request.setAttribute("department", new Department(request.getParameter("abbreviation"), request.getParameter("title"), request.getParameter("url_moodle"), request.getParameter("token")));
            request.setAttribute("exist", "true");
            request.getRequestDispatcher("/offertaFormativa/amministratore/department/ModifyDepartment.jsp").forward(request, response);
        }
    }

}
