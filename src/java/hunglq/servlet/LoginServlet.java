/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunglq.servlet;

import hunglq.registration.LoginnDAO;
import hunglq.registration.LoginnDTO;
import hunglq.util.ApplicationConstants;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HP
 */
public class LoginServlet extends HttpServlet {

//    private final String INVAID_PAGE = "invalid.html";
//    private final String INVAID_PAGE = "invalidPage";
//    //private final String SEARCH_PAGE = "search.html";
////    private final String SEARCH_PAGE = "search.jsp";
//    private final String SEARCH_PAGE = "homePage";

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
        //0. Get currunt context and get siteMaps
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        
        //1. get all client info
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
//        String url = INVAID_PAGE;
        String url = siteMaps.getProperty(ApplicationConstants.LoginFeature.INVAID_PAGE);

        try {
            //2. call model/DAO
            //2.1 new DAO obj
            LoginnDAO dao = new LoginnDAO();
            //2.2 call method DAO
            LoginnDTO result = dao.checkLogin(username, password);
            //3. process result
            if (result != null) {
//                url = SEARCH_PAGE;
                url = siteMaps.getProperty(ApplicationConstants.LoginFeature.SEARCH_PAGE);

                HttpSession session = request.getSession();
                session.setAttribute("USER", result);
                //write cookies
//                Cookie cookie = new Cookie(username, password);
//                cookie.setMaxAge(60*3);
//                response.addCookie(cookie);
            }
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("LoginServlet _ SQL: " + msg);
        } catch (NamingException ex) {
            log("LoginServlet _ Naming: " + ex.getMessage());
        } finally {
//           response.sendRedirect(url);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
