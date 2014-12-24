package it.unisa.offerta_formativa.servlet.curriculum;

import it.unisa.integrazione.database.CycleManager;
import it.unisa.integrazione.database.DegreeManager;
import it.unisa.integrazione.database.DepartmentManager;
import it.unisa.model.Degree;
import it.unisa.offerta_formativa.beans.Curriculum;
import it.unisa.offerta_formativa.manager.CurriculumManager;
import it.unisa.offerta_formativa.manager.Exceptions.CurriculumException;
import it.unisa.offerta_formativa.moodle.manager.MoodleCategoryManager;
import it.unisa.offerta_formativa.moodle.moodle_rest.MoodleRestException;
import java.io.IOException;
import static java.lang.System.out;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

/**
 *
 * @author Davide
 */
@WebServlet("/InsertCurriculumServlet")
public class InsertCurriculumServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CurriculumManager cuMng;
    private DegreeManager degreeMng;
    private DepartmentManager depMng;
    private CycleManager cyMng;
    private MoodleCategoryManager mDeMng;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertCurriculumServlet() {
        super();
        // TODO Auto-generated constructor stub   
        cuMng = CurriculumManager.getInstance();
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

    private void InsertCurriculum(Curriculum cur) {
        // TODO Auto-generated method stub
        Degree degree = degreeMng.readDegree(cur.getDegreeMatricula());
        mDeMng = MoodleCategoryManager.getInstance(depMng.getDepartmentByAbbreviation(degree.getDepartmentAbbreviation()).getUrlMoodle(), depMng.getDepartmentByAbbreviation(degree.getDepartmentAbbreviation()).getToken());
        String cycleName = cyMng.getCycleByCycleNumber(degree.getCycle()).getTitle();
        //////////////
        int idDeg = 0;
        try {
            idDeg = mDeMng.getIdCategoryByParent(cycleName,degree.getTitle());
        } catch (MoodleRestException ex) {
            Logger.getLogger(InsertCurriculumServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        mDeMng.createCategory(cur.getTitle(), idDeg);
        cuMng.createCurriculum(cur);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // TODO Auto-generated method stub
            if (cuMng.readCurriculum(request.getParameter("curriculum_matricula")) == null) {
                InsertCurriculum(new Curriculum(request.getParameter("curriculum_matricula"), request.getParameter("title"), request.getParameter("degree_matricula"), Boolean.parseBoolean(request.getParameter("status"))));
                ServletContext sc = getServletContext();
                RequestDispatcher rd = sc.getRequestDispatcher("/ShowCurriculumServlet");
                rd.forward(request, response);
            } else {
                request.setAttribute("curriculum", new Curriculum(request.getParameter("curriculum_matricula"), request.getParameter("title"), request.getParameter("degree_matricula"), Boolean.parseBoolean(request.getParameter("status"))));
                request.setAttribute("exist", "true");
                request.getRequestDispatcher("/offertaFormativa/amministratore/curriculum/insertCurriculum.jsp").forward(request, response);
            }
        } catch (CurriculumException ex) {
            out.print("<h1>Moodle Exception: </h1>" + ex.getMessage());
        }
    }
}
