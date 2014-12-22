package it.unisa.offerta_formativa.servlet.teaching;

import it.unisa.offerta_formativa.beans.ClassPartition;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.offerta_formativa.beans.Module;
import it.unisa.offerta_formativa.beans.ProfModuleClass;
import it.unisa.offerta_formativa.beans.Teaching;
import it.unisa.offerta_formativa.facade.InsertTeachingFacade;
import it.unisa.offerta_formativa.manager.ClassManager;
import it.unisa.integrazione.database.DegreeManager;
import it.unisa.offerta_formativa.manager.ModuleManager;
import it.unisa.offerta_formativa.manager.TeachingManager;
import it.unisa.offerta_formativa.moodle.manager.MoodleConnectionManager;
import it.unisa.offerta_formativa.moodle.manager.MoodleDegreeManager;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/InsertTeachingServlet")
public class InsertTeachingServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    //private MoodleDegreeManager moodleDegreeMng = null;
    //private MoodleConnectionManager moodleConnector = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertTeachingServlet() {
        super();
        //moodleConnector = MoodleConnectionManager.getInstance();
        //moodleDegreeMng = MoodleDegreeManager.getInstance(moodleConnector.getUrlMoodle(id), moodleConnector.getToken(id));

        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String matricula = "";
        int classNumber = 0;
        int moduleNumber = 0;
        ArrayList<ClassPartition> listClasses = new ArrayList<ClassPartition>();
        ArrayList<Module> listModules = new ArrayList<Module>();
        InsertTeachingFacade facade = new InsertTeachingFacade();
        try {
            if (checkFields(request).equalsIgnoreCase("")) {//i campi sono corretti
                if (request.getParameterMap().containsKey("matricula")) {
                    matricula = request.getParameter("matricula");
                    facade.setTeaching(new Teaching(request.getParameter("title"),
                            request.getParameter("abbreviation"),
                            matricula,
                            request.getParameter("link"),
                            Integer.parseInt(request.getParameter("year")),
                            Integer.parseInt(request.getParameter("semester")), true));
                }
                if (request.getParameterMap().containsKey("curriculum")) {
                    facade.setCurriculumMatricula(request.getParameter("curriculum"));
                }
                if (request.getParameterMap().containsKey("moduleNumber")) {
                    moduleNumber = Integer.parseInt(request.getParameter("moduleNumber"));
                    for (int i = 1; i <= moduleNumber; i++) {
                        listModules.add(new Module(request.getParameter("moduleName" + i), matricula));
                    }
                    facade.setModules(listModules);
                }

                if (request.getParameterMap().containsKey("classNumber")) {

                    classNumber = Integer.parseInt(request.getParameter("classNumber"));
                    for (int j = 1; j <= classNumber; j++) {
                        if (classNumber == 1) {
                            listClasses.add(new ClassPartition(matricula, request.getParameter("title")));
                        } else {
                            listClasses.add(new ClassPartition(request.getParameter("matricula"), request.getParameter("className" + j)));
                        }
                    }
                    facade.setClasses(listClasses);
                }
                ArrayList<ProfModuleClass> list = new ArrayList<ProfModuleClass>();
                for (int i = 1; i <= classNumber; i++) {
                    for (int j = 1; j <= moduleNumber; j++) {
                        list.add(new ProfModuleClass(listClasses.get(i - 1).getTitle(), matricula, listModules.get(j - 1).getTitle(), request.getParameter("docente" + i + "-" + j)));
                    }
                }
                facade.setProfModuleClass(list);

                facade.storeInDB();

            } else {
                throw new Exception(checkFields(request));//campi errati
            }

        } catch (Exception e) {
            request.setAttribute("error", true);
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/offertaFormativaJSP/amministratore/insertTeaching.jsp").forward(request, response);
        }
    }

    public String checkFields(HttpServletRequest request) {
        if (request.getParameter("matricula").length() < 10 || request.getParameter("matricula").length() > 10) {
            return "Matricola errata. Non può essere diversa da 10 caratteri.";
        }
        if (request.getParameter("abbreviation").length() < 2) {
            return "Abbreviazione errata. Deve essere composta da almeno 2 caratteri.";
        }
        if (request.getParameter("title").length() < 3) {
            return "Nome errato. Deve essere composto da almeno 4 caratteri.";
        }
        if (Integer.parseInt(request.getParameter("year")) < 1) {
            return "Anno selezionato errato. Deve essere diverso da zero.";
        }
        if (Integer.parseInt(request.getParameter("semester")) < 1) {
            return "Semestre errato. Deve essere diverso da zero.";
        }
        if (Integer.parseInt(request.getParameter("classNumber")) < 1) {
            return "Numero di classi errato. Deve essere diverso da zero.";
        }
        if (Integer.parseInt(request.getParameter("moduleNumber")) < 1) {
            return "Numero di moduli errato. Deve essere diverso da zero.";
        }
        for (int j = 1; j <= Integer.parseInt(request.getParameter("classNumber")); j++) {
            if (request.getParameter("className" + j).length() < 3) {
                return "Nome della classe " + j + " errato. Deve essere composto da almeno 4 caratteri.";
            }
        }
        for (int i = 1; i <= Integer.parseInt(request.getParameter("moduleNumber")); i++) {
            if (request.getParameter("moduleName" + i).length() < 3) {
                return "Nome del modulo " + i + " errato. Deve essere composto da almeno 4 caratteri.";
            }
        }
        return "";
    }

}
