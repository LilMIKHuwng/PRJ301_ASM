/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunglq.order;

import hunglq.util.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import javax.naming.NamingException;

/**
 *
 * @author TungK
 */
public class OrderDAO implements Serializable {

    public boolean addOrder(OrderDTO order) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            // 1. Get connection
            con = DBHelper.getConnection();
            // 2. Sql string
            String sql = "insert into tbl_Order(id,date,customer,address,total) "
                    + "values (?,?,?,?,?) ";
            // 3. execute query
            stm = con.prepareStatement(sql);
            stm.setString(1, order.getId());
            stm.setTimestamp(2, Timestamp.valueOf(order.getDate()));
            stm.setString(3, order.getCustomer());
            stm.setString(4, order.getAddress());
            stm.setDouble(5, order.getTotal());
            int affectRows = stm.executeUpdate();
            // 4. process result
            if (affectRows > 0) {
                result = true;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }

            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public boolean updateTotal(String idOrder, double total) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            // 1. Get connection
            con = DBHelper.getConnection();
            // 2. Sql string
            String sql = "update tbl_Order "
                    + "Set total=? "
                    + "where id=? ";
            // 3. execute query
            stm = con.prepareStatement(sql);
            stm.setDouble(1, total);
            stm.setString(2, idOrder);

            int affectRows = stm.executeUpdate();
            // 4. process result
            if (affectRows > 0) {
                result = true;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }

            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public OrderDTO searchOrderId(String orderId)
            throws SQLException, /*ClassNotFoundException*/ NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        OrderDTO result = null;

        try {
            //1. get Connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "select id, date, customer, address, total from tbl_Order "
                        + "Where id = ?";
                //3. create Statement Obj
                stm = con.prepareStatement(sql);
                stm.setString(1, orderId);
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process Result
                while (rs.next()) {
                    //5.1 get data from ResultSet
                    String id = rs.getString("id");
                    Timestamp timestamp = rs.getTimestamp("date");
                    String customer = rs.getString("customer");
                    String address = rs.getString("address");
                    double total = rs.getDouble("total");
                    LocalDateTime localDateTime = timestamp.toLocalDateTime();
                    //5.2 set data to DTO properties
                    result = new OrderDTO(id, localDateTime, customer, address, total);

                }//end account has existed
            }//end connection has been available

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;
    }

    public String getOrderId()
            throws SQLException, /*ClassNotFoundException*/ NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String result = null;
        String orderId = null;

        try {
            //1. get Connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "SELECT TOP 1 id "
                        + "FROM tbl_Order "
                        + "ORDER BY id DESC";
                //3. create Statement Obj
                stm = con.prepareStatement(sql);
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process Result
                while (rs.next()) {
                    //5.1 get data from ResultSet
                    orderId = rs.getString("id");
                    //5.2 set data to DTO properties

                }//end account has existed
                if (orderId == null) {
                    result = "HD001";
                } else {
                    result = "HD";
                    String numberPart = orderId.substring(2);
                    int number = Integer.parseInt(numberPart);

                    number++;
                    int length = String.valueOf(number).length();
                    if (length == 1) {
                        result = result + "00" + number;
                    } else if (length == 2) {
                        result = result + "0" + number;
                    } else {
                        result = result + "" + number;
                    }
                }
            }//end connection has been available

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;
    }
}
