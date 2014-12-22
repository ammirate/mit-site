package it.unisa.offerta_formativa.servlet.getter;

import com.google.gson.Gson;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.Department;
import it.unisa.integrazione.database.DepartmentManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Servlet implementation class GetDepartmentServlet
 * @author Davide
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
        ArrayList<HashMap<String,String>> arrlist = new ArrayList<HashMap<String,String>>();
            HashMap<String,String> map;
            ArrayList<Department> departments = new ArrayList<Department>();
            departments = deptMng.getAllDepartments();
            
            Collections.sort(departments);
            for(Department d : departments){
                    map = new HashMap<String,String>();
                    map.put("departmentAbbreviation", d.getAbbreviation());
                    map.put("title", d.getTitle());
                    arrlist.add(map);
                }
            String finalJSON = new Gson().toJson(arrlist);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(finalJSON);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
