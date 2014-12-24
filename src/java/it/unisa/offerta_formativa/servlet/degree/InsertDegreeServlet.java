package it.unisa.offerta_formativa.servlet.degree;

import it.unisa.integrazione.database.CycleManager;
import it.unisa.model.Degree;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.unisa.integrazione.database.DegreeManager;
import it.unisa.integrazione.database.DepartmentManager;
import it.unisa.offerta_formativa.manager.ParserHtmlManager;
import it.unisa.offerta_formativa.moodle.manager.MoodleCategoryManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/InsertDegreeServlet")
public class InsertDegreeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DegreeManager degreeMng;
    private DepartmentManager depMng;
    private CycleManager cyMng;
    private MoodleCategoryManager mDeMng;
    private ParserHtmlManager parseMng;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertDegreeServlet() {
        super();
        // TODO Auto-generated constructor stub 
        parseMng = ParserHtmlManager.getInstance();
        degreeMng = DegreeManager.getInstance();
        cyMng = CycleManager.getInstance();
        depMng = DepartmentManager.getInstance();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request, response);
    }

    private void InsertDegree(Degree degree) {
        // TODO Auto-generated method stub
        mDeMng = MoodleCategoryManager.getInstance(depMng.getDepartmentByAbbreviation(degree.getDepartmentAbbreviation()).getUrlMoodle(), depMng.getDepartmentByAbbreviation(degree.getDepartmentAbbreviation()).getToken());
        String cycleName = cyMng.getCycleByCycleNumber(degree.getCycle()).getTitle();
        int idCat = mDeMng.getIdCategory(cycleName);
        mDeMng.createCategory(degree.getTitle(), idCat);
        degree.setEsse3Content(parseMng.getHtml(degree.getLink(), "infobox"));
        degreeMng.createDegree(degree);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        if (degreeMng.readDegree(request.getParameter("degree_matricula")) == null) {
            InsertDegree(new Degree(request.getParameter("degree_matricula"), request.getParameter("link"), request.getParameter("title"), Integer.parseInt(request.getParameter("cycle")), request.getParameter("departmentAbb"), Boolean.parseBoolean(request.getParameter("status"))));
            ServletContext sc = getServletContext();
            RequestDispatcher rd = sc.getRequestDispatcher("/ShowDegreeServlet");
            rd.forward(request, response);
        } else {
            request.setAttribute("degree", new Degree(request.getParameter("degree_matricula"), request.getParameter("link"), request.getParameter("title"), Integer.parseInt(request.getParameter("cycle")), request.getParameter("departmentAbb"), Boolean.parseBoolean(request.getParameter("status"))));
            request.setAttribute("exist", "true");
            request.getRequestDispatcher("/offertaFormativa/amministratore/degree/insertDegree.jsp").forward(request, response);
        }
    }

}
