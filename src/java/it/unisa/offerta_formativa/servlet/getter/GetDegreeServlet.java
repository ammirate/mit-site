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

import it.unisa.offerta_formativa.beans.Degree;
import it.unisa.offerta_formativa.manager.DegreeManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Servlet implementation class GetDepartmentServlet
 */
@WebServlet("/GetDegreeServlet")
public class GetDegreeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DegreeManager degreeMng;
	private ServletContext context;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDegreeServlet() {
        super();
        degreeMng= DegreeManager.getInstance();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // TODO Auto-generated method stub
            ArrayList<HashMap<String,String>> arrlist = new ArrayList<HashMap<String,String>>();
            HashMap<String,String> map;
            ArrayList<Degree> degrees = new ArrayList<Degree>();
            if(request.getParameterMap().containsKey("cycle") && request.getParameterMap().containsKey("department")){
                degrees = (ArrayList<Degree>)degreeMng.getDegreesByDepartmentAndCycle(request.getParameter("department"),Integer.parseInt(request.getParameter("cycle")));
            }else if(request.getParameterMap().containsKey("cycle")){
                degrees = degreeMng.getDegreesByCycle(Integer.parseInt(request.getParameter("cycle")));  
            }else if(request.getParameterMap().containsKey("department")){
                degrees = degreeMng.getDegreesByDepartment(request.getParameter("department"));
            } else degrees = degreeMng.getAllDegrees();
            Collections.sort(degrees);
            for(Degree d : degrees){
                    map = new HashMap<String,String>();
                    map.put("departmentAbbreviation", d.getDepartmentAbbreviation());
                    map.put("title", d.getTitle());
                    map.put("matricula", d.getMatricula());
                    map.put("link", d.getLink());
                    if(d.isActive()){
                        map.put("status", "Attivo");
                    } else map.put("status", "Disattivo");
                    arrlist.add(map);
                }
            String finalJSON = new Gson().toJson(arrlist);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(finalJSON);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
            doGet(request, response);
	}

}
