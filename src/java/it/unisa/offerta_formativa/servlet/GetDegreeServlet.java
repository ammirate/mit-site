package it.unisa.offerta_formativa.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.offerta_formativa.beans.Degree;
import it.unisa.offerta_formativa.beans.Department;
import it.unisa.offerta_formativa.manager.DegreeManager;
import it.unisa.offerta_formativa.manager.DepartmentManager;

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
		if(request.getParameterMap().containsKey("idCycle")){
			response.setContentType("text/plain");  
			response.setCharacterEncoding("UTF-8"); 
			String toRet="<option value=0>Seleziona corso di laurea</option>";
			for(Degree d : degreeMng.getDegreesByCycle(Integer.parseInt(request.getParameter("idCycle")))){
				toRet+="<option value="+d.getMatricula()+">"+d.getTitle()+"</option>"; 
			}
			response.getWriter().write(toRet);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
