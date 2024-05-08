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
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HP
 */
@WebServlet(name = "SearchLastnameServlet", urlPatterns = {"/SearchLastnameServlet"})
public class SearchLastnameServlet extends HttpServlet {

//    private final String SEARCH_PAGE = "search.html";
//    private final String RESULT_PAGE = "search.jsp";

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
        
        //0. Get currunt context and get siteMaps
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");

        //1. get all client information
        String searchValue = request.getParameter("txtSearchValue");
        String url = siteMaps.getProperty(ApplicationConstants.SearchFeature.SEARCH_PAGE);

        try {
//            System.out.println("Controller");
            if (!searchValue.trim().isEmpty()) {
                System.out.println("Start seacrh");
                //2. Call DAO
                //2.1 New DAO obj
                LoginnDAO dao = new LoginnDAO();
                //2.2 Call method DAO
                dao.searchLastname(searchValue);
                List<LoginnDTO> result = dao.getAccounts();
                //3. process Result
                url = siteMaps.getProperty(ApplicationConstants.SearchFeature.RESULT_PAGE);
                request.setAttribute("SEARCH_RESULT", result);
            }
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("SearchLastnameServlet _ SQL: " + msg);
        } catch (NamingException ex) {
            log("SearchLastnameServlet _ Naming: " + ex.getMessage());
        } finally {
            //4. send to View (+ request scope)
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
