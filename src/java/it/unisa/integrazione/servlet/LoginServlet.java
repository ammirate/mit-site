/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.integrazione.servlet;

import it.unisa.integrazione.database.AccountManager;
import it.unisa.integrazione.database.exception.ConnectionException;
import it.unisa.model.Person;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gemmacatolino
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            AccountManager accountManager = AccountManager.getInstance();
            Person person = accountManager.login(username, password);

            if (person != null) {
                if (!person.getAccount().isActive()) {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Account non attivo');");
                    out.println("location='login.jsp';");
                    out.println("</script>");
                } else if (person.getAccount().getTypeOfAccount().equals("admin")) {
                    response.addCookie(loginMoodle(username, password));
                    session.removeAttribute("loginError");
                    session.setAttribute("person", person);
                    response.sendRedirect("indexLog.jsp");
                } else if (person.getAccount().getTypeOfAccount().equals("Bstudent")) {
                    response.addCookie(loginMoodle(username, password));
                    session.removeAttribute("loginError");
                    session.setAttribute("person", person);
                    response.sendRedirect("indexLog.jsp");
                } else if (person.getAccount().getTypeOfAccount().equals("Mstudent")) {
                    response.addCookie(loginMoodle(username, password));
                    session.removeAttribute("loginError");
                    session.setAttribute("person", person);
                    response.sendRedirect("indexLog.jsp");
                } else if (person.getAccount().getTypeOfAccount().equals("phd")) {
                    response.addCookie(loginMoodle(username, password));
                    session.removeAttribute("loginError");
                    session.setAttribute("person", person);
                    response.sendRedirect("indexLog.jsp");
                } else if (person.getAccount().getTypeOfAccount().equals("professor")) {
                    response.addCookie(loginMoodle(username, password));
                    session.removeAttribute("loginError");
                    session.setAttribute("person", person);
                    response.sendRedirect("indexLog.jsp");
                } else if (person.getAccount().getTypeOfAccount().equals("company")) {
                    response.addCookie(loginMoodle(username, password));
                    session.removeAttribute("loginError");
                    session.setAttribute("person", person);
                    response.sendRedirect("indexLog.jsp");
                } else {
                    session.setAttribute("loginError", "error");
                    response.sendRedirect("login.jsp");
                }
            } else {
                session.setAttribute("loginError", "error");
                response.sendRedirect("login.jsp");
            }
        } catch (SQLException sqlException) {
            out.print("<h1>SQL Exception: </h1>" + sqlException.getMessage());
        } catch (ConnectionException connectionException) {
            out.print("<h1>Connection Exception</h1>");
        } catch (Exception ex) {
            out.print("<h1>Moodle Login error</h1>");
        } finally {
            out.close();
        }
    }

    // HTTP GET request
	private Cookie loginMoodle(String username, String password) throws Exception {
                String USER_AGENT = "Mozilla/5.0";
		String url = "http://"+this.getServletContext().getVirtualServerName()+"/moodle/singlelogin.php?username="+username+"&password="+password;
		URL obj = new URL(url);
                System.out.println(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
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
                String value = resp.toString().replace(resp.toString().split("=")[0]+"=", "");
                Cookie cookie = new Cookie(name, value.split(";")[0]);
                System.out.println(cookie.getValue());
                cookie.setPath("/");
                cookie.setMaxAge(-1);
                return cookie;
 
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
        doPost(request, response);
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
