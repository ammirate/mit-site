package it.unisa.offerta_formativa.servlet.degree;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.Degree;
import it.unisa.integrazione.database.DegreeManager;
import it.unisa.offerta_formativa.manager.ParserHtmlManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/UpdateDegreeServlet")
public class UpdateDegreeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DegreeManager degreeMng;
    private ParserHtmlManager parseMng;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateDegreeServlet() {
        super();
        // TODO Auto-generated constructor stub   
        degreeMng = DegreeManager.getInstance();
        parseMng = ParserHtmlManager.getInstance();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request, response);
    }

    private void UpdateDegree(String matricula, Degree degree) {
        // TODO Auto-generated method stub
        degree.setEsse3Content(parseMng.getHtml(degree.getLink(), "infobox"));
        degreeMng.updateDegree(matricula, degree);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        if ((degreeMng.readDegree(request.getParameter("degree_matricula")) != null) && (request.getParameter("degree_matricula").equalsIgnoreCase(request.getParameter("old_matricula")))) {
            UpdateDegree(request.getParameter("old_matricula"), new Degree(request.getParameter("degree_matricula"), request.getParameter("link"), request.getParameter("title"), Integer.parseInt(request.getParameter("cycle")), request.getParameter("departmentAbb"), Boolean.parseBoolean(request.getParameter("status"))));
            ServletContext sc = getServletContext();
            RequestDispatcher rd = sc.getRequestDispatcher("/ShowDegreeServlet");
            rd.forward(request, response);
        } else if(degreeMng.readDegree(request.getParameter("degree_matricula")) == null){
            UpdateDegree(request.getParameter("old_matricula"), new Degree(request.getParameter("degree_matricula"), request.getParameter("link"), request.getParameter("title"), Integer.parseInt(request.getParameter("cycle")), request.getParameter("departmentAbb"), Boolean.parseBoolean(request.getParameter("status"))));
            ServletContext sc = getServletContext();
            RequestDispatcher rd = sc.getRequestDispatcher("/ShowDegreeServlet");
            rd.forward(request, response);
        } else{
            request.setAttribute("degree", new Degree(request.getParameter("degree_matricula"), request.getParameter("link"), request.getParameter("title"), Integer.parseInt(request.getParameter("cycle")), request.getParameter("departmentAbb"), Boolean.parseBoolean(request.getParameter("status"))));
            request.setAttribute("exist", "true");
            request.getRequestDispatcher("/offertaFormativa/amministratore/degree/ModifyDegree.jsp").forward(request, response);
        }
    }

}
