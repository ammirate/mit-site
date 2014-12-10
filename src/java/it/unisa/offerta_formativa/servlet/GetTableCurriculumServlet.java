package it.unisa.offerta_formativa.servlet;

import it.unisa.offerta_formativa.beans.Curriculum;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.unisa.offerta_formativa.beans.Degree;
import it.unisa.offerta_formativa.manager.CurriculumManager;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Servlet implementation class GetDepartmentServlet
 */
@WebServlet("/GetTableCurriculumServlet")
public class GetTableCurriculumServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CurriculumManager curriculumMng;
    private ServletContext context;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTableCurriculumServlet() {
        super();
        curriculumMng = CurriculumManager.getInstance();
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
        String toRet = "<thead><tr><td style='font-weight: bold'>Matricola</td><td style='font-weight: bold'>Nome</td><td style='font-weight: bold'>Stato</td><td></td> </tr></thead><tbody>";
        if (request.getParameterMap().containsKey("degMatricula")) {
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            ArrayList<Curriculum> curs =curriculumMng.getCurriculumByDegree(request.getParameter("degMatricula"));
            Collections.sort(curs);
            for (int i=0;i<curs.size();i++) {
                Curriculum cu= curs.get(i);
                toRet += "<tr><td style=' color: black'>" + cu.getMatricula() + "</td><td style=' color: black'>" + cu.getTitle() + "</td><td style=' color: black'>";
                if (cu.isActive()) {
                    toRet += "Attivo";
                } else {
                    toRet += "Disattivo";
                }
                String path = request.getContextPath(); 
                toRet += "</td><td style='color: black; font-weight: bold'><a href='"+path+"/ModifyCurriculumServlet?curriculum_matricula=" + cu.getMatricula() + "'>Modifica</a></td></tr>";
            }
        }
        toRet += "</tbody>";
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
