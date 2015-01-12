/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.integrazione.servlet;

import it.unisa.integrazione.database.AccountManager;
import it.unisa.integrazione.database.exception.ConnectionException;
import it.unisa.integrazione.model.Account;
import it.unisa.integrazione.model.Person;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alessandro
 */
@WebServlet(name = "MoodleLoginServlet", urlPatterns = {"/MoodleLoginServlet"})
public class MoodleLoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ConnectionException {
        Cookie killMyCookie = new Cookie("Eliminator", null);
        killMyCookie.setMaxAge(0);
        killMyCookie.setPath("/");
        response.addCookie(killMyCookie);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Account userAccount = AccountManager.getInstance().getAccoutnByEmail(request.getParameter("email"));
        Person p = AccountManager.getInstance().login(userAccount.getEmail(), userAccount.getPassword());
        String USER_AGENT = "Mozilla/5.0";
        URL obj = new URL(p.getDepartment().getUrlMoodle() + "\\singlelogin.php?username=" + userAccount.getEmail() + "&password=" + userAccount.getPassword());

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        System.out.println(p.getDepartment().getUrlMoodle() + "\\singlelogin.php?username=" + "admin" + "&password=" + userAccount.getPassword());
        //add reuqest header
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        System.out.println(in);
        StringBuffer resp = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            resp.append(inputLine);
        }
        in.close();
        System.out.println(resp.toString());
        String name = resp.toString().split("=")[0];
        String value = resp.toString().replace(resp.toString().split("=")[0] + "=", "");
        Cookie cookie = new Cookie(name, value.split(";")[0]);
        System.out.println(cookie.getValue());
        cookie.setPath("/");
        cookie.setMaxAge(-1);
        response.addCookie(cookie);
        out.println("<script type=\"text/javascript\">");
        //out.println("alert('Account non attivo');");
        out.println("location='" + p.getDepartment().getUrlMoodle() + "';");
        out.println("</script>");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(MoodleLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConnectionException ex) {
            Logger.getLogger(MoodleLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(MoodleLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConnectionException ex) {
            Logger.getLogger(MoodleLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
