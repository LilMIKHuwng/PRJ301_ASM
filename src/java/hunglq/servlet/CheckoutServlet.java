/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunglq.servlet;

import hunglq.cart.CartObject;
import hunglq.order.OrderCreateError;
import hunglq.order.OrderDAO;
import hunglq.order.OrderDTO;
import hunglq.orderdetail.OrderDetailDAO;
import hunglq.orderdetail.OrderDetailDTO;
import hunglq.product.ProductDTO;
import hunglq.util.ApplicationConstants;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HP
 */
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/CheckoutServlet"})
public class CheckoutServlet extends HttpServlet {

//    private final String ERROR_PAGE = "errors.html";
//    private final String COMPLETE_PAGE = "complete.jsp";
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

        // 1. Get user input
        String name = request.getParameter("txtName");
        String address = request.getParameter("txtAddress");
        String url = siteMaps.getProperty(ApplicationConstants.CheckoutFeature.ERROR_PAGE);

        boolean foundError = false;
        OrderCreateError errors = new OrderCreateError();

        try {

            if (name.trim().length() < 6 || name.trim().length() > 30) {
                foundError = true;
                errors.setNameLengthError("Name is required typing from 6 to 30 characters");
            }

            if (address.trim().length() < 10 || address.trim().length() > 50) {
                foundError = true;
                errors.setAddressLengthError("Address is required typing from 10 to 50 characters");
            }

            if (foundError) {
                
                request.setAttribute("ORDER_ERRORS", errors);

            } else {
                // 2. Cus goes to Cart Place
                HttpSession session = request.getSession(false); // Check có session mới check out đc nên (false)
                if (session != null) {
                    // 3. Cus take Cart
                    CartObject cart = (CartObject) session.getAttribute("CART");
                    if (cart != null) {
                        // 4. cus take items
                        Map<String, ProductDTO> items = cart.getItems();
                        // 5. call dao
                        OrderDAO orderDAO = new OrderDAO();
                        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                        // 5.1. call method of dao
                        // insert order table
                        String orderId = orderDAO.getOrderId();
                        OrderDTO orderDTO = new OrderDTO(orderId, LocalDateTime.now(), name, address, 0);
                        orderDAO.addOrder(orderDTO);
                        // insert orderDetail table
                        double total = 0;
                        for (ProductDTO productDTO : items.values()) {
                            total += productDTO.getQuantity() * productDTO.getUnitPrice();
                            OrderDetailDTO orderDetailDTO = new OrderDetailDTO(productDTO.getSku(), productDTO.getUnitPrice(), productDTO.getQuantity(), productDTO.getQuantity() * productDTO.getUnitPrice(), orderId);
                            orderDetailDAO.addOrderDetail(orderDetailDTO, productDTO.getSku(), orderId);
                        }
                        // update Total for [t_Order] 
                        orderDAO.updateTotal(orderId, total);
                        session.removeAttribute("CART");
                        url = siteMaps.getProperty(ApplicationConstants.CheckoutFeature.COMPLETE_PAGE);

                        OrderDTO order = orderDAO.searchOrderId(orderId);
                        request.setAttribute("ORDER", order);

                        orderDetailDAO.searchOrderId(orderId);
                        List<OrderDetailDTO> orderDetailList = orderDetailDAO.getOrderDetailList();
                        request.setAttribute("ORDER_DETAIL", orderDetailList);
                    }// end check card
                } // end check card place
            }

        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("CheckoutServlet _ SQL: " + msg);
            //ex.printStackTrace();
        } catch (NamingException ex) {
            log("CheckoutServlet _ Naming: " + ex.getMessage());
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
