package it.unisa.tirocinio.servlet;

import it.unisa.tirocinio.manager.concrete.ConcreteMessageForServlet;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Valentino
 */
@WebServlet(name = "adminUploadFiles", urlPatterns = {"/adminUploadFiles"})
public class adminUploadFiles extends HttpServlet {

    private boolean isMultipart;
    private String rootDirectoryPath = null;
    private String fullPath = null;
    private String fileSeparator = null;

    @Override
    public void init() {
        rootDirectoryPath = getServletContext().getRealPath("/");
        File directory = new File(rootDirectoryPath + "/files");
        if (!directory.exists()) {
             directory.mkdir();
        }
        fullPath = directory.getAbsolutePath();
        fileSeparator = System.getProperty("file.separator");
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        HttpSession aSession = request.getSession();
        ConcreteMessageForServlet aMessage = new ConcreteMessageForServlet();

        try {
            isMultipart = ServletFileUpload.isMultipartContent(request);
            /*Properties prop = new Properties();
             ClassLoader loader = Thread.currentThread().getContextClassLoader();  
             InputStream stream = loader.getResourceAsStream("properties/dbconfiguration.properties");
             prop.load(stream);*/
            Properties props = new Properties();

            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List fileItems = upload.parseRequest(request);
            Iterator i = fileItems.iterator();
            File fileToStore = null;
            String department = null;
            
            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (!fi.isFormField()) {
                    String fieldName = fi.getFieldName();
                    if (fieldName.equals("trainingPlan")) {
                        fileToStore = new File(fullPath + fileSeparator + "module.pdf");
                        props.setProperty("modulePath", fileToStore.getAbsolutePath());
                    } else if (fieldName.equals("examsCarriedOut")) {
                        fileToStore = new File(fullPath + fileSeparator + "register.pdf");
                        props.setProperty("registerPath", fileToStore.getAbsolutePath());
                    } else if (fieldName.equals("questionnaire")) {
                        fileToStore = new File(fullPath + fileSeparator + "questionnaire.pdf");
                        props.setProperty("questionnairePath", fileToStore.getAbsolutePath());
                    }
                    fi.write(fileToStore);
                } else {
                    File departmentFolder = new File(fullPath + fileSeparator + fi.getString());
                    if (!departmentFolder.exists()) {
                        departmentFolder.mkdir();
                    }
                    fullPath = departmentFolder.getAbsolutePath();
                    department = fi.getString();
                }
            }
            
            aMessage.setMessage("status", 1);
            aSession.setAttribute("message", aMessage);
            response.sendRedirect(request.getContextPath() + "/tirocinio/amministratore/tpinserimentofileamministratore.jsp");
        } catch (FileUploadException ex) {
            Logger.getLogger(adminUploadFiles.class.getName()).log(Level.SEVERE, null, ex);
            aMessage.setMessage("status", 0);
            aSession.setAttribute("message", aMessage);
            response.sendRedirect(request.getContextPath() + "/tirocinio/amministratore/tpinserimentofileamministratore.jsp");
        } catch (NullPointerException ex) {
            Logger.getLogger(adminUploadFiles.class.getName()).log(Level.SEVERE, null, ex);
            aMessage.setMessage("status", 0);
            aSession.setAttribute("message", aMessage);
            response.sendRedirect(request.getContextPath() + "/tirocinio/amministratore/tpinserimentofileamministratore.jsp");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(adminUploadFiles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(adminUploadFiles.class.getName()).log(Level.SEVERE, null, ex);
        }
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
