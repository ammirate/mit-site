package it.unisa.offerta_formativa.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.offerta_formativa.beans.Degree;
import it.unisa.offerta_formativa.manager.DegreeManager;
import it.unisa.offerta_formativa.moodle.manager.MoodleConnectionManager;
import it.unisa.offerta_formativa.moodle.manager.MoodleDegreeManager;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/InsertDegreeServlet")
public class InsertDegreeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private MoodleDegreeManager moodleDegreeMng = null;
    private DegreeManager degreeMng = null;
    private MoodleConnectionManager moodleConnector = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertDegreeServlet() {
        super();
        //moodleConnector = MoodleConnectionManager.getInstance();
        int id = 1;
        //moodleDegreeMng = MoodleDegreeManager.getInstance(moodleConnector.getUrlMoodle(id), moodleConnector.getToken(id));
        //degreeMng = DegreeManager.getInstance();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        createDegree(request.getParameter("matricula"),
                request.getParameter("title"),
                request.getParameter("link"),
                Integer.parseInt(request.getParameter("idCycle")),
                request.getParameter("idDepartment"));
    }

    private void createDegree(String matricula, String title, String link, int cycle, String depAbb) {
        // TODO Auto-generated method stub
        //if (moodleDegreeMng.createDegree(title, cycle)) {
            degreeMng.createDegree(new Degree(matricula, link, title, cycle, depAbb));
        //}
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        createDegree(request.getParameter("matricula"),
        request.getParameter("title"),
        request.getParameter("link"),
        Integer.parseInt(request.getParameter("idCycle")),
        request.getParameter("idDepartment"));
    }

}
