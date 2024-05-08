/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunglq.orderdetail;

import hunglq.registration.LoginnDTO;
import hunglq.util.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;


/**
 *
 * @author TungK
 */
public class OrderDetailDAO implements Serializable{
     
    public boolean addOrderDetail(OrderDetailDTO orderDetail, String productId, String orderId) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            // 1. Get connection
            con = DBHelper.getConnection();
            // 2. Sql string
            String sql = "insert into OrderDetail(productId,unitPrice,quantity,total,orderId) "
                    + "values (?,?,?,?,?) ";
            // 3. execute query
            stm = con.prepareStatement(sql);
            stm.setString(1, productId);
            stm.setDouble(2, orderDetail.getUnitPrice());
            stm.setInt(3, orderDetail.getQuantity());
            stm.setDouble(4, orderDetail.getTotal());
            stm.setString(5, orderId);
            int affectRows = stm.executeUpdate();
            // 4. process result
            if(affectRows>0){
                result = true;
            }
        } finally {
            if(stm!=null){
                stm.close();
            }
            
            if(con!=null){
                con.close();
            }
        }
        return  result;
    }
    
    private List<OrderDetailDTO> orderDetailList;

    public List<OrderDetailDTO> getOrderDetailList() {
        return orderDetailList;
    }
    
    public void searchOrderId(String orderId)
            throws SQLException, /*ClassNotFoundException*/ NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. get Connection
            con = DBHelper.getConnection();
            if (con!=null){
                //2. create SQL String
                String sql = "select productId, unitPrice, quantity, total, orderId from OrderDetail "
                        +"where orderId = ?";
                //3. create Statement Obj
                stm = con.prepareStatement(sql);
                stm.setString(1, orderId);
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process Result
                while (rs.next()){
                    //5.1 get data from ResultSet
                    String productId = rs.getString("productId");
                    Double unitPrice = rs.getDouble("unitPrice");
                    int quangtity = rs.getInt("quantity");
                    Double total = rs.getDouble("total");
                    //5.2 set data to DTO properties
                    OrderDetailDTO dto = new OrderDetailDTO(productId, unitPrice, quangtity, total, orderId);
                    if (this.orderDetailList == null){
                        this.orderDetailList = new ArrayList<>();
                    }//accounts have not EXISTED
                    this.orderDetailList.add(dto);
                    
                }//end account has existed
            }//end connection has been available
        } finally {
            if (rs != null){
                rs.close();
            }
            if (stm != null){
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
