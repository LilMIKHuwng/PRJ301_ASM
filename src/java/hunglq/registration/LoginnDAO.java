/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunglq.registration;

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
 * @author HP
 */
public class LoginnDAO implements Serializable {

    public LoginnDTO checkLogin(String username, String password) 
        throws SQLException, /*ClassNotFoundException*/ NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        LoginnDTO result = null;

        try {
            //1. get Connection
            con = DBHelper.getConnection();
            if (con!=null){
                //2. create SQL String
                String sql = "Select lastname, isAdmin "
                        + "From UserLogin "
                        + "Where username = ? "
                        + "And password = ?";
                //3. create Statement Obj
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process Result
                if (rs.next()){
                    //map --> get data from result, set data to DTO's property
                    String fullname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    result = new LoginnDTO(username, null, fullname, role);
                }//end username and password are verified
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
        
        return result;
    }
    
    private List<LoginnDTO> accounts;

    public List<LoginnDTO> getAccounts() {
        return accounts;
    }
    
    public void searchLastname(String searchValue)
            throws SQLException, /*ClassNotFoundException*/ NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. get Connection
            con = DBHelper.getConnection();
            if (con!=null){
                //2. create SQL String
                String sql = "Select username, password, lastname, isAdmin "
                        +"From UserLogin "
                        +"Where lastname Like ?";
                //3. create Statement Obj
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process Result
                while (rs.next()){
                    //5.1 get data from ResultSet
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    //5.2 set data to DTO properties
                    LoginnDTO dto = new LoginnDTO(
                            username, password, fullName, role);
                    if (this.accounts == null){
                        this.accounts = new ArrayList<>();
                    }//accounts have not EXISTED
                    this.accounts.add(dto);
                    
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
    
    public boolean deleteAccount(String username) 
        throws SQLException, /*ClassNotFoundException*/ NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1. get Connection
            con = DBHelper.getConnection();
            if (con!=null){
                //2. create SQL String
                String sql = "Delete From UserLogin "
                        + "Where username = ?";
                //3. create Statement Obj
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //4. Execute Query
                int effectRows = stm.executeUpdate();
                //5. Process Result
                if (effectRows > 0 ){
                    result = true;                
                }//end username and password are verified
            }//end connection has been available
        } finally {
            if (stm != null){
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        
        return result;
    }
    
    public boolean updateAccount(String username, String password, boolean isAdmin)
        throws SQLException, /*ClassNotFoundException*/ NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1. get Connection
            con = DBHelper.getConnection();
            if (con!=null){
                //2. create SQL String
                String sql = "UPDATE UserLogin SET password=?, isAdmin=? WHERE username=?";
                //3. create Statement Obj
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, isAdmin);
                stm.setString(3, username);
                //4. Execute Query
                int effectRows = stm.executeUpdate();
                //5. Process Result
                if (effectRows > 0 ){
                    result = true;                
                }//end username and password are verified
            }//end connection has been available
        } finally {
            if (stm != null){
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        
        return result;
    }
    
    public boolean createAccount(LoginnDTO account)
        throws SQLException, /*ClassNotFoundException*/ NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1. get Connection
            con = DBHelper.getConnection();
            if (con!=null){
                //2. create SQL String
                String sql = "Insert into UserLogin("
                        + "username, password, lastname, isAdmin"
                        + ") Values("
                        + "?, ?, ?, ?"
                        + ")";
                //3. create Statement Obj
                stm = con.prepareStatement(sql);
                stm.setString(1, account.getUsername());
                stm.setString(2, account.getPassword());
                stm.setString(3, account.getFullName());
                stm.setBoolean(4, account.isRole());
                //4. Execute Query
                int effectRows = stm.executeUpdate();
                //5. Process Result
                if (effectRows > 0 ){
                    result = true;                
                }//end username and password are verified
            }//end connection has been available
        } finally {
            if (stm != null){
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        
        return result;
    }
}
