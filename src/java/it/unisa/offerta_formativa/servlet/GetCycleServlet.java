package it.unisa.offerta_formativa.servlet;

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
import it.unisa.offerta_formativa.manager.CurriculumManager;
import it.unisa.offerta_formativa.manager.CycleManager;

/**
 * Servlet implementation class GetDepartmentServlet
 */
@WebServlet("/GetCycleServlet")
public class GetCycleServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CycleManager cycleMng;
    private ServletContext context;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCycleServlet() {
        super();
        cycleMng = CycleManager.getInstance();
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
        String toRet = "<option value=0>Seleziona il ciclo</option>";
        if (request.getParameterMap().containsKey("cycleNumber")) {
            Cycle c = cycleMng.readCycle(Integer.parseInt(request.getParameter("cycleNumber")));
        }else{ //get all cycles
            for (Cycle c : cycleMng.getAllCycles()) {
                toRet+="<option value="+c.getNumber()+">"+c.getTitle()+"</option>";
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
