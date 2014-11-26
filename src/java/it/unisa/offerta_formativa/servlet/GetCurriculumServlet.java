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
import it.unisa.offerta_formativa.manager.CurriculumManager;

/**
 * Servlet implementation class GetDepartmentServlet
 */
@WebServlet("/GetCurriculumServlet")
public class GetCurriculumServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CurriculumManager currMng;
    private ServletContext context;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCurriculumServlet() {
        super();
        currMng = CurriculumManager.getInstance();
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
        if (request.getParameterMap().containsKey("idDegree")) {
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            int i = 1;
            String toRet = "";
            for (Curriculum c : currMng.getCurriculumByDegree(request.getParameter("idDegree"))) {
                toRet += "<option value=" + i + ">" + c.getTitle() + "</option>";
                i++;
            }
            response.getWriter().write(toRet);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

}
