/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.servlet;

import it.unisa.offerta_formativa.beans.Degree;
import it.unisa.offerta_formativa.beans.Department;
import it.unisa.offerta_formativa.manager.CycleManager;
import it.unisa.offerta_formativa.manager.DegreeManager;
import it.unisa.offerta_formativa.manager.DepartmentManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetSyllabusServlet
 *
 * @author Davide
 */
@WebServlet("/ShowDegreeServlet")
public class ShowDegreeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DepartmentManager dm;
    private DegreeManager degreeMng;
    private CycleManager cym;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowDegreeServlet() {
        super();
        // TODO Auto-generated constructor stub   
        dm = DepartmentManager.getInstance();
        degreeMng = DegreeManager.getInstance();
        cym = CycleManager.getInstance();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request, response);
    }

    private HashMap CreateMap() {
        HashMap< Department, ArrayList<Degree>> map;
        map = new HashMap< Department, ArrayList<Degree>>();

        for (Department d : dm.getAllDepartments()) {

            map.put(d, new ArrayList<Degree>());

            for (Degree deg : degreeMng.getDegreesByDepartment(d.getAbbreviation())) {

                map.get(d).add(deg);

                
            }
        }
        return map;
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setAttribute("cycles", cym.getAllCycles());
        request.setAttribute("map", CreateMap());
        request.getRequestDispatcher("/offertaFormativaJSP/amministratore/ShowDegreeList.jsp").forward(request, response);
    }
}
