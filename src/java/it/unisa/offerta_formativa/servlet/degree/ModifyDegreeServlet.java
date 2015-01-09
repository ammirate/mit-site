package it.unisa.offerta_formativa.servlet.degree;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.integrazione.model.Degree;
import it.unisa.integrazione.database.DegreeManager;



/**
 * @author Davide
 * Class to show ModifyDegree.jsp
 */
@WebServlet("/ModifyDegreeServlet")
public class ModifyDegreeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DegreeManager degreeMng;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyDegreeServlet() {
        super();
        // TODO Auto-generated constructor stub   
        degreeMng = DegreeManager.getInstance();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request, response);
    }

    private Degree GetDegree(String matricula) {
        // TODO Auto-generated method stub
            return degreeMng.readDegree(matricula);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setAttribute("degree",GetDegree(request.getParameter("degree_matricula")));
        request.getRequestDispatcher("/offertaFormativa/amministratore/degree/ModifyDegree.jsp").forward(request, response);
    }

}
