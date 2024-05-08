/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunglq.product;

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
public class ProductDAO implements Serializable{
    private List<ProductDTO> listProduct;

 
    public List<ProductDTO> getListProduct() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            // 1. Get connection
            con = DBHelper.getConnection();
            // 2. create string sql
            String sql = "select Sku, name, description, unit_price, quantity, status "
                    + "from tbl_Product ";
            // 3. execute query
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            // 4. process result
            while (rs.next()) {
                String sku = rs.getString("Sku");
                String name = rs.getString("name");
                String description = rs.getString("description");
                double unitPrice = rs.getDouble("unit_price");
                int quantity = rs.getInt("quantity");
                boolean status = rs.getBoolean("status");
                ProductDTO productDTO = new ProductDTO(sku, name, description, unitPrice, quantity, status);
                if (listProduct == null) {
                    listProduct = new ArrayList<>();
                }
                this.listProduct.add(productDTO);
            }
            return  this.listProduct;
        } finally {
            if(rs!=null){
                rs.close();
            }
            if(stm!=null){
                stm.close();
            }
            if(con!=null){
                con.close();
            }
        }
    }
    
        
    public  ProductDTO findProductById(String id) throws SQLException, NamingException{
        List<ProductDTO> list = this.getListProduct();
        for (ProductDTO productDTO : list) {
            if(productDTO.getSku().equals(id)){
                return productDTO;
            }
            System.out.println(productDTO.getSku());
        }
        return null;
        
    }
}
