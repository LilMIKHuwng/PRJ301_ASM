/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunglq.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


/**
 *
 * @author HP
 */
public class DBHelper {
    public static Connection getConnection()
    throws /*ClassNotFoundException,*/ SQLException, NamingException {
//        //1. Load Driver (Driver available)
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2. create Connection String
//        String url = "jdbc:sqlserver://"
//                + "localhost:1433;"
//                + "databaseName=Loginn";
//        //3. Open Connection
//        Connection con = DriverManager.getConnection(url, "sa", "123");
//        
//        return con;
        
        //1. Get current context
        Context currentContext = new InitialContext();
        //2.Lookup tomcat context
        Context tomcatContext = (Context)currentContext.lookup("java:comp/env");
        //3.Lookup DS
        DataSource ds = (DataSource)tomcatContext.lookup("DS007");
        //4. Open connection DS
        Connection con = ds.getConnection();
        
        return con;
       
    }
}
