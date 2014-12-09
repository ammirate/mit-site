package it.unisa.offerta_formativa.servlet.getter;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.offerta_formativa.beans.Curriculum;
import it.unisa.offerta_formativa.beans.Cycle;
import it.unisa.offerta_formativa.beans.Department;
import it.unisa.offerta_formativa.manager.CurriculumManager;
import it.unisa.offerta_formativa.manager.CycleManager;
import it.unisa.offerta_formativa.manager.DepartmentManager;

/**
 * Servlet implementation class GetDepartmentServlet
 */
@WebServlet("/GetDepartmentServlet")
public class GetDepartmentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DepartmentManager deptMng;
    private ServletContext context;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDepartmentServlet() {
        super();
        deptMng = DepartmentManager.getInstance();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        String toRet = "<option value=0>Seleziona il dipartimento</option>";
        if (request.getParameterMap().containsKey("departmentAbbreviation")) {
            Department d = deptMng.readDepartment(request.getParameter("departmentAbbreviation"));
        }else{ //get all cycles
            for (Department d : deptMng.getAllDepartments()) {
                toRet+="<option value="+d.getAbbreviation()+">"+d.getTitle()+"</option>";
            }
        }
        response.getWriter().write(toRet);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

}
