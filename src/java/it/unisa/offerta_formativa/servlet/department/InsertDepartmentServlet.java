package it.unisa.offerta_formativa.servlet.department;

import it.unisa.integrazione.model.Department;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.unisa.integrazione.database.DepartmentManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;


/**
 * @author Davide
 * Class to create a new department in db
 */
@WebServlet("/InsertDepartmentServlet")
public class InsertDepartmentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DepartmentManager departmentMng;
   

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertDepartmentServlet() {
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

    private void InsertDepartment(Department department) {
        // TODO Auto-generated method stub
            departmentMng.add(department);
    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        if(departmentMng.getDepartmentByAbbreviation(request.getParameter("abbreviation"))==null){
        InsertDepartment(new Department(request.getParameter("abbreviation"), request.getParameter("title"), request.getParameter("url_moodle"), request.getParameter("token")));
        ServletContext sc = getServletContext();  
        RequestDispatcher rd = sc.getRequestDispatcher("/ShowDepartmentServlet");  
        rd.forward(request, response); 
        } else {
             request.setAttribute("department",new Department(request.getParameter("abbreviation"), request.getParameter("title"), request.getParameter("url_moodle"), request.getParameter("token")));
             request.setAttribute("exist", "true");
             request.getRequestDispatcher("/offertaFormativa/amministratore/department/insertDepartment.jsp").forward(request, response);
        }
    }

}
