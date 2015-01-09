package it.unisa.offerta_formativa.servlet.curriculum;

import it.unisa.offerta_formativa.beans.Curriculum;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.offerta_formativa.manager.CurriculumManager;
import it.unisa.offerta_formativa.manager.Exceptions.CurriculumException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

/**
 * @author Davide
 * Class to modify a curriculum in db
 */
@WebServlet("/UpdateCurriculumServlet")
public class UpdateCurriculumServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CurriculumManager cuMng;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCurriculumServlet() {
        super();
        // TODO Auto-generated constructor stub   
        cuMng = CurriculumManager.getInstance();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request, response);
    }

    private void UpdateCurriculum(String matricula, Curriculum curriculum) throws CurriculumException {
        // TODO Auto-generated method stub
        cuMng.updateCurriculum(matricula, curriculum);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if ((cuMng.readCurriculum(request.getParameter("curriculum_matricula")) != null) && (request.getParameter("curriculum_matricula").equalsIgnoreCase(request.getParameter("old_matricula")))) {

                // TODO Auto-generated method stub
                UpdateCurriculum(request.getParameter("old_matricula"), new Curriculum(request.getParameter("curriculum_matricula"), request.getParameter("title"), request.getParameter("degree_matricula"), Boolean.parseBoolean(request.getParameter("status"))));
                ServletContext sc = getServletContext();
                RequestDispatcher rd = sc.getRequestDispatcher("/ShowCurriculumServlet");
                rd.forward(request, response);

            } else if ((cuMng.readCurriculum(request.getParameter("curriculum_matricula")) == null)) {
                UpdateCurriculum(request.getParameter("old_matricula"), new Curriculum(request.getParameter("curriculum_matricula"), request.getParameter("title"), request.getParameter("degree_matricula"), Boolean.parseBoolean(request.getParameter("status"))));
                ServletContext sc = getServletContext();
                RequestDispatcher rd = sc.getRequestDispatcher("/ShowCurriculumServlet");
                rd.forward(request, response);
            } else {
                request.setAttribute("curriculum", new Curriculum(request.getParameter("curriculum_matricula"), request.getParameter("title"), request.getParameter("degree_matricula"), Boolean.parseBoolean(request.getParameter("status"))));
                request.setAttribute("exist", "true");
                request.getRequestDispatcher("/offertaFormativa/amministratore/curriculum/ModifyCurriculum.jsp").forward(request, response);
            }
        } catch (CurriculumException ex) {
            Logger.getLogger(UpdateCurriculumServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
