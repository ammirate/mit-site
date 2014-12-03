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
@WebServlet("/InsertTeachingServlet")
public class InsertTeachingServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private MoodleDegreeManager moodleDegreeMng = null;
    private DegreeManager degreeMng = null;
    private MoodleConnectionManager moodleConnector = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertTeachingServlet() {
        super();
        moodleConnector = MoodleConnectionManager.getInstance();
        int id = 1;
        moodleDegreeMng = MoodleDegreeManager.getInstance(moodleConnector.getUrlMoodle(id), moodleConnector.getToken(id));
        degreeMng = DegreeManager.getInstance();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        
    }

    

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        
    }

}
