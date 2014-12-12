package it.unisa.offerta_formativa.servlet;

import it.unisa.offerta_formativa.beans.Curriculum;
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
import it.unisa.offerta_formativa.manager.CurriculumManager;
import it.unisa.offerta_formativa.manager.CycleManager;
import it.unisa.offerta_formativa.manager.DegreeManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Servlet implementation class GetDepartmentServlet
 */
@WebServlet("/GetSelectDegreeByCurriculumServlet")
public class GetSelectDegreeByCurriculumServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DegreeManager degreeMng;
    private CycleManager cyMng;
    private CurriculumManager cuMng;
    private ServletContext context;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSelectDegreeByCurriculumServlet() {
        super();
        degreeMng = DegreeManager.getInstance();
        cyMng = CycleManager.getInstance();
        cuMng = CurriculumManager.getInstance();

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
        String toRet = "<option value='NoDeg'>Seleziona corso di laurea</option>";
        if (request.getParameterMap().containsKey("curriculum_matricula")) {
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            Curriculum cu = GetCurriculum(request.getParameter("curriculum_matricula"));
            ArrayList<Degree> degs = GetDegrees(cu);
            for (int i = 0; i < degs.size(); i++) {
                Degree d = degs.get(i);
                Cycle cy = cyMng.readCycle(d.getCycle());
                toRet += "<option value=" + d.getMatricula() + ">" + cy.getTitle() + " " + d.getTitle() + "</option>";
            }
        }
        response.getWriter().write(toRet);
    }

    private Curriculum GetCurriculum(String matricula) {
        // TODO Auto-generated method stub
        return cuMng.readCurriculum(matricula);
    }

    private ArrayList<Degree> GetDegrees(Curriculum cu) {
        ArrayList<Degree> toRet = new ArrayList<Degree>();
        ArrayList<Degree> degs = degreeMng.getAllDegrees();
        for (int i = 0; i < degs.size(); i++) {
            if (cu.getDegreeMatricula().equalsIgnoreCase(degs.get(i).getMatricula())) {
                toRet.add(degs.get(i));
            }
        }
        Collections.sort(toRet);
        return toRet;
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

}
