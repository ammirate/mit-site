package it.unisa.offerta_formativa.servlet;

import it.unisa.offerta_formativa.beans.Curriculum;
import it.unisa.offerta_formativa.beans.Degree;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.offerta_formativa.manager.CurriculumManager;
import it.unisa.offerta_formativa.manager.CycleManager;
import it.unisa.offerta_formativa.manager.DegreeManager;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/ModifyCurriculumServlet")
public class ModifyCurriculumServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CurriculumManager cuMng;
    private CycleManager cyMng;
    private DegreeManager deMng;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyCurriculumServlet() {
        super();
        // TODO Auto-generated constructor stub   
        cuMng = CurriculumManager.getInstance();
        deMng = DegreeManager.getInstance();
        cyMng = CycleManager.getInstance();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request, response);
    }

    private Curriculum GetCurriculum(String matricula) {
        // TODO Auto-generated method stub
        return cuMng.readCurriculum(matricula);
    }

    private ArrayList<Degree> GetDegrees(Curriculum cu) {
        ArrayList<Degree> toRet = new ArrayList<Degree>();
        ArrayList<Degree> degs = deMng.getAllDegrees();
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
        request.setAttribute("curriculum", GetCurriculum(request.getParameter("curriculum_matricula")));
        request.setAttribute("cycles", cyMng.getAllCycles());
        request.setAttribute("degrees", GetDegrees(GetCurriculum(request.getParameter("curriculum_matricula"))));
        request.getRequestDispatcher("/offertaFormativaJSP/amministratore/ModifyCurriculum.jsp").forward(request, response);
    }

}
