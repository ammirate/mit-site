package it.unisa.tirocinio.servlet;

import it.unisa.integrazione.database.PersonManager;
import it.unisa.integrazione.model.Person;
import it.unisa.tirocinio.beans.Questionnaire;
import it.unisa.tirocinio.beans.RejectedTrainingMessage;
import it.unisa.tirocinio.beans.TrainingRequest;
import it.unisa.tirocinio.manager.concrete.ConcreteQuestionnaire;
import it.unisa.tirocinio.manager.concrete.ConcreteRejectedTrainingMessage;
import it.unisa.tirocinio.manager.concrete.ConcreteTrainingRequest;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Valentino
 */
@WebServlet(name = "insertTrainingQuestionnaire", urlPatterns = {"/insertTrainingQuestionnaire"})

public class insertTrainingQuestionnaire extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Here");
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        HttpSession aSession = request.getSession();

        String matricolaStudente = request.getParameter("matricolaStudente");
        String nomeAzienda = request.getParameter("nomeAzienda");
        String tipologiaAzienda = request.getParameter("tipologiaAzienda");
        String firstAnswer = request.getParameter("firstAnswer");
        String secondAnswer = request.getParameter("secondAnswer");
        String thirdAnswer = request.getParameter("thirdAnswer");
        String fourthAnswer = request.getParameter("fourthAnswer");
        String fifthAnswer = request.getParameter("fifthAnswer");
        String sixthAnswer = request.getParameter("sixthAnswer");
        String seventhAnswer = request.getParameter("seventhAnswer");

        PersonManager aPerson = PersonManager.getInstance();
        Person person = (Person) aSession.getAttribute("person");

        ConcreteTrainingRequest aTrainingRequest = ConcreteTrainingRequest.getInstance();
        TrainingRequest trainingRequest = aTrainingRequest.readTrainingRequestByStudent(person.getSsn());
        System.out.println(trainingRequest.getTrainingStatus());

        if (trainingRequest.getTrainingStatus() == 2) {

            Questionnaire aQuestionnaire = new Questionnaire(nomeAzienda, tipologiaAzienda, firstAnswer, secondAnswer, person.getSsn(), thirdAnswer, fourthAnswer, fifthAnswer, sixthAnswer, seventhAnswer);

            ConcreteQuestionnaire questionnaires = ConcreteQuestionnaire.getInstance();
            boolean toReturn = questionnaires.insertQuestionnaire(aQuestionnaire);

            ConcreteRejectedTrainingMessage aRejectedMessage = ConcreteRejectedTrainingMessage.getInstance();
            RejectedTrainingMessage messageReject = aRejectedMessage.readLastTrainingMessage(person.getSsn());
            boolean toReturnMessage = aRejectedMessage.deleteOrganization(messageReject.getIdRejectedTraingMessage());

            trainingRequest.setTrainingStatus(3);

            boolean toReturnTraining = aTrainingRequest.updateTrainingRequest(trainingRequest);

            if (toReturn && toReturnTraining && toReturnMessage) {
                aSession.setAttribute("questionnaireStatus", 1);
            } else {
                aSession.setAttribute("questionnaireStatus", 0);
            }
            response.sendRedirect(request.getContextPath() + "/tirocinio/studente/tpquestionario.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/tirocinio/studente/tpquestionario.jsp");
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
