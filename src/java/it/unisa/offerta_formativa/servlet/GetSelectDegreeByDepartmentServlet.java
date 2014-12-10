package it.unisa.offerta_formativa.servlet;

import it.unisa.offerta_formativa.beans.Cycle;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.unisa.offerta_formativa.beans.Degree;
import it.unisa.offerta_formativa.manager.CycleManager;
import it.unisa.offerta_formativa.manager.DegreeManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Servlet implementation class GetDepartmentServlet
 */
@WebServlet("/GetSelectDegreeByDepartmentServlet")
public class GetSelectDegreeByDepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DegreeManager degreeMng;
        private CycleManager cyMng;
	private ServletContext context;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSelectDegreeByDepartmentServlet() {
        super();
        degreeMng= DegreeManager.getInstance();
        cyMng = CycleManager.getInstance();
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
		String toRet="<option value='NoDeg'>Seleziona corso di laurea</option>";
                if(request.getParameterMap().containsKey("depAbb")){
			response.setContentType("text/plain");  
			response.setCharacterEncoding("UTF-8");
                        ArrayList<Degree> degs = degreeMng.getDegreesByDepartment(request.getParameter("depAbb"));
                        Collections.sort(degs);
			for(int i=0;i<degs.size();i++){
                                Degree d=degs.get(i);
                                Cycle cy= cyMng.readCycle(d.getCycle());
				toRet+="<option value="+d.getMatricula()+">"+cy.getTitle()+ " " +d.getTitle()+"</option>"; 
			}
		}
                response.getWriter().write(toRet);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
