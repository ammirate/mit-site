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


/**
 * @author Davide
 * Class to show ModifyCurriculum.jsp
 */

@WebServlet("/ModifyCurriculumServlet")
public class ModifyCurriculumServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CurriculumManager cuMng;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyCurriculumServlet() {
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

    private Curriculum GetCurriculum(String matricula) throws CurriculumException {
        // TODO Auto-generated method stub
        return cuMng.readCurriculum(matricula);
    }

    
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // TODO Auto-generated method stub
            request.setAttribute("curriculum", GetCurriculum(request.getParameter("curriculum_matricula")));
        } catch (CurriculumException ex) {
            Logger.getLogger(ModifyCurriculumServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("/offertaFormativa/amministratore/curriculum/ModifyCurriculum.jsp").forward(request, response);
    }

}
