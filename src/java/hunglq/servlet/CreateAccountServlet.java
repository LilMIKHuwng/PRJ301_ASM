/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunglq.servlet;

import hunglq.registration.LoginnCreateError;
import hunglq.registration.LoginnDAO;
import hunglq.registration.LoginnDTO;
import hunglq.util.ApplicationConstants;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {
    
//    private final String ERROR_PAGE = "createAccount.jsp";
//    private final String LOGIN_PAGE = "login.jsp";

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
        
        //1. get all parameters
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String fullname = request.getParameter("txtFullname");
        String confirm = request.getParameter("txtConfirm");
        boolean foundError = false;
        LoginnCreateError errors = new LoginnCreateError();
        String url = siteMaps.getProperty(ApplicationConstants.CreateAccountFeature.ERROR_PAGE);
        
        try  {
            //2. check users errors
            if (username.trim().length() < 6 || username.trim().length()>20){
                foundError = true;
                errors.setUsernameLengthError("Username is required typing from 6 to 20 characters");
            }
            if (password.trim().length() < 6 || password.trim().length()>30){
                foundError = true;
                errors.setPasswordLengthError("Password is required typing from 6 to 30 characters");
            }
            if (fullname.trim().length() < 2 || fullname.trim().length()>40){
                foundError = true;
                errors.setFullNameLengthError("Full name is required typing from 2 to 40 characters");
            }
            if (!confirm.trim().equals(password.trim())){
                foundError = true;
                errors.setConfirmLengthError("Confirm much match Password");
            }
            if (foundError){
                //catching to specific attribute then go to error page to show
                request.setAttribute("CREATE_ERRORS", errors);
            } else {
                //3. call DAO 
                LoginnDAO dao = new LoginnDAO();
                LoginnDTO dto = new LoginnDTO(username, password, fullname, false);
                boolean result = dao.createAccount(dto);
                //4. process result
                if (result){
                    request.setAttribute("MSG", fullname);
                    url = siteMaps.getProperty(ApplicationConstants.CreateAccountFeature.LOGIN_PAGE);
                }
            }//
            
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("CreateAccountServlet _ SQL: " + msg);
            if (msg.contains("duplicate")){
                errors.setUsernameIsExisted(username + " is existed");
                request.setAttribute("CREATE_ERRORS", errors);
            }//username existed
        } catch (NamingException ex) {
            log("CreateAccountServlet _ Naming: " + ex.getMessage());
        } finally {
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
