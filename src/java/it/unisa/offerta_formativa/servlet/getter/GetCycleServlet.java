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

import it.unisa.integrazione.model.Cycle;
import it.unisa.integrazione.model.Degree;
import it.unisa.integrazione.database.CycleManager;
import it.unisa.integrazione.database.DegreeManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Servlet implementation class GetDepartmentServlet
 */
@WebServlet("/GetCycleServlet")
public class GetCycleServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CycleManager cycleMng;
    private DegreeManager degreeMng;
    private ServletContext context;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCycleServlet() {
        super();
        cycleMng = CycleManager.getInstance();
        degreeMng = DegreeManager.getInstance();
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
        ArrayList<HashMap<String, String>> arrlist = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map;
        ArrayList<Cycle> cycles = new ArrayList<Cycle>();
        if (request.getParameterMap().containsKey("department")) {
            ArrayList<Degree> degrees = degreeMng.getDegreesByDepartment(request.getParameter("department"));
            ArrayList<Cycle> allCycles = cycleMng.getAllCycles();
            Collections.sort(allCycles);
            ArrayList<Integer> checkCycle = new ArrayList<Integer>();
            for (Degree d : degrees) {
                for (int i = 0; i < 3; i++) {
                    Cycle c = allCycles.get(i);
                    if (d.getCycle() == c.getNumber()) {
                        if (!checkCycle.contains(c.getNumber())) {
                            cycles.add(c);
                            checkCycle.add(c.getNumber());
                        }
                    }
                }
            }
        } else {
            ArrayList<Cycle> allCycles = cycleMng.getAllCycles();
            Collections.sort(allCycles);
            for (int i = 0; i < 3; i++) {
                Cycle c = allCycles.get(i);
                cycles.add(c);
            }
        }
        Collections.sort(cycles);
        for (Cycle c : cycles) {
            map = new HashMap<String, String>();
            map.put("cycle_number", "" + c.getNumber());
            map.put("title", c.getTitle());
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
