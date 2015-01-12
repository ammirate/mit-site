/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.tirocinio.servlet;

import it.unisa.integrazione.database.PersonManager;
import it.unisa.integrazione.model.Person;
import it.unisa.tirocinio.manager.concrete.ConcreteMessageForServlet;
import it.unisa.tirocinio.manager.concrete.ConcreteStudentInformation;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Valentino
 */
@WebServlet(name = "studentUploadFiles", urlPatterns = {"/studentUploadFiles"})

public class studentUploadFiles extends HttpServlet {

    private PrintWriter out = null;
    private boolean isMultipart;
    private String rootDirectory = null;
    private String filePath;
    private ConcreteMessageForServlet message = null;

    public void init() {
        rootDirectory = getServletContext().getRealPath("/");
        filePath = rootDirectory+"/studentsFiles";
        message = new ConcreteMessageForServlet();
        File directoryCheck = new File(filePath);
        if (!directoryCheck.exists()) {
            directoryCheck.mkdir();
        }
    }

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
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = response.getWriter();
        HttpSession aSession = request.getSession();
        try {
            Person pers = (Person) aSession.getAttribute("person");
            String primaryKey = pers.getAccount().getEmail();
            PersonManager aPerson = PersonManager.getInstance();
            Person person = aPerson.getStudent(primaryKey);
            ConcreteStudentInformation aStudentInformation = ConcreteStudentInformation.getInstance();
            out = response.getWriter();

            String studentSubfolderPath = filePath + "/" + reverseSerialNumber(person.getMatricula());
            File subfolderFile = new File(studentSubfolderPath);
            if (!subfolderFile.exists()) {
                subfolderFile.mkdir();
            }

            isMultipart = ServletFileUpload.isMultipartContent(request);
            String serialNumber = null;
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List fileItems = upload.parseRequest(request);
            Iterator i = fileItems.iterator();
            File fileToStore = null;
            String CVPath = "", ATPath = "";

            while (i.hasNext()) {

                FileItem fi = (FileItem) i.next();
                if (!fi.isFormField()) {
                    // Get the uploaded file parameters
                    String fieldName = fi.getFieldName();
                    String fileName = fi.getName();
                    if (fieldName.equals("cv")) {
                        fileToStore = new File(studentSubfolderPath + "/" + "CV.pdf");
                        CVPath = fileToStore.getAbsolutePath();
                    } else if (fieldName.equals("doc")) {
                        fileToStore = new File(studentSubfolderPath + "/" + "ES.pdf");
                        ATPath = fileToStore.getAbsolutePath();
                    }
                    fi.write(fileToStore);

                    // out.println("Uploaded Filename: " + fieldName + "<br>");
                } else {
                    //out.println("It's not formfield");
                    //out.println(fi.getString());

                }

            }

            if (aStudentInformation.startTrainingRequest(person.getSsn(), CVPath, ATPath)) {
                message.setMessage("status", 1);
            } else {
                message.setMessage("status", 0);
            }
            aSession.setAttribute("message", message);
            response.sendRedirect(request.getContextPath() + "/tirocinio/studente/tprichiestatirocinio.jsp");
            //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/tirocinio/studente/tprichiestatirocinio.jsp");
            //dispatcher.forward(request,response);
        } catch (Exception ex) {
            Logger.getLogger(studentUploadFiles.class.getName()).log(Level.SEVERE, null, ex);
            message.setMessage("status", -1);
            aSession.setAttribute("message", message);
        } finally {
            out.close();
        }
    }

    private String reverseSerialNumber(String primaryKey) {
        String reversedSerialNumber = "";
        for (int i = primaryKey.length() - 1; i >= 0; i--) {
            reversedSerialNumber += (primaryKey.charAt(i));
        }

        return reversedSerialNumber;
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
