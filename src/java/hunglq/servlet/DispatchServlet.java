/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunglq.servlet;

import hunglq.util.ApplicationConstants;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
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
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {
////    private final String LOGIN_PAGE = "login.html";
//    private final String LOGIN_PAGE = "";
////    private final String LOGIN_CONTROLLER = "LoginServlet";
//    private final String LOGIN_CONTROLLER = "loginController";
////    private final String SEARCH_LASTNAME_CONTROLLER = "SearchLastnameServlet";
//    private final String SEARCH_LASTNAME_CONTROLLER = "searchController";
//    private final String DELETE_ACCOUNT_CONTROLLER = "DeleteAccountServlet";
//    private final String STARTUP_CONTROLLER = "StartupServlet";
//    private final String UPDATE_ACCOUNT_CONTROLLER = "UpdateAccountServlet";
//    private final String ADD_ITEM_TO_CART_CONTROLLER = "AddItemServlet";
//    private final String REMOVE_ITEM_FROM_CART_CONTROLLER = "RemoveItemServlet";
//    private final String CREATE_NEW_ACCOUNT_CONTROLLER = "CreateAccountServlet";
//    private final String LOGOUT_CONTROLLER = "LogoutServlet";
//    private final String PRODUCT_CONTROLLER = "ProductServlet";
//    private final String CHECKOUT_CONTROLLER = "CheckoutServlet";
//    
//    private final String VIEW_ITEM_PAGE = "viewCart.jsp";
    

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
        
        //1. Which button did user click?
        String button = request.getParameter("btAction");
//        String url = LOGIN_PAGE;
        String url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.LOGIN_PAGE);
        
        try  {
            if (button == null){// first time or app starts up
                //transfer login page
                //check cookies to determine which page is transfered
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.STARTUP_CONTROLLER);
            } else if (button.equals("Login")){
//                url = LOGIN_CONTROLLER;
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.LOGIN_CONTROLLER);
            } else if (button.equals("Search")){
//                url = SEARCH_LASTNAME_CONTROLLER;
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.SEARCH_LASTNAME_CONTROLLER);
//                System.out.println("Da di toi day");
            } else if (button.equals("delete")) {
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.DELETE_ACCOUNT_CONTROLLER);
            } else if (button.equals("Update")) {
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.UPDATE_ACCOUNT_CONTROLLER);
            } else if (button.equals("Add Book to Your Cart")){
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.ADD_ITEM_TO_CART_CONTROLLER);
            } else if (button.equals("View Your Cart")){
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.VIEW_ITEM_PAGE);
            } else if (button.equals("Remove Selected Items")){
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.REMOVE_ITEM_FROM_CART_CONTROLLER);
            } else if (button.equals("Create New Account")){
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.CREATE_NEW_ACCOUNT_CONTROLLER);
            } else if (button.equals("LOGOUT")){
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.LOGOUT_CONTROLLER);;
            } else if(button.equals("Product")){
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.PRODUCT_CONTROLLER);
            } else if(button.equals("Check out")){
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.CHECKOUT_CONTROLLER);
            }
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
