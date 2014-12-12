package it.unisa.offerta_formativa.servlet.getter;

import com.google.gson.Gson;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.offerta_formativa.beans.Curriculum;
import it.unisa.offerta_formativa.manager.CurriculumManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Servlet implementation class GetDepartmentServlet
 *
 * @author Davide
 */
@WebServlet("/GetCurriculumServlet")
public class GetCurriculumServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CurriculumManager currMng;
    private ServletContext context;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCurriculumServlet() {
        super();
        currMng = CurriculumManager.getInstance();
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
//        if (request.getParameterMap().containsKey("degreeMatricula")) {
//            response.setContentType("text/plain");
//            response.setCharacterEncoding("UTF-8");
//            int i = 1;
//            String toRet = "<option value=0></option>";
//            for (Curriculum c : currMng.getCurriculumByDegree(request.getParameter("degreeMatricula"))) {
//                toRet += "<option value=" + c.getMatricula() + ">" + c.getTitle() + "</option>";
//                i++;
//            }
//            response.getWriter().write(toRet);
//        }
        ArrayList<HashMap<String, String>> arrlist = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map;
        ArrayList<Curriculum> curriculums = new ArrayList<Curriculum>();
        if (request.getParameterMap().containsKey("degree")) {
            curriculums = currMng.getCurriculumByDegree(request.getParameter("degree"));
        } else {
            curriculums = currMng.getAllCurriculums();
        }
        Collections.sort(curriculums);
        for (Curriculum cu : curriculums) {
            map = new HashMap<String, String>();
            map.put("matricula", cu.getMatricula());
            map.put("title", cu.getTitle());
            map.put("degree_matricula", cu.getDegreeMatricula());
            if (cu.isActive()) {
                map.put("status", "Attivo");
            } else {
                map.put("status", "Disattivo");
            }
            arrlist.add(map);
        }
        String finalJSON = new Gson().toJson(arrlist);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(finalJSON);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);

    }

}
