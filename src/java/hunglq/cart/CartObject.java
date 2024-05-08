/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunglq.cart;

import hunglq.product.ProductDAO;
import hunglq.product.ProductDTO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;

/**
 *
 * @author HP
 */
public class CartObject implements Serializable{
    private Map<String, ProductDTO> items; // ngan chua do

    public Map<String, ProductDTO> getItems() {
        return items;
    }



    // ko có thể set giỏ đồ
    public boolean addItemToCart(String id) throws SQLException, NamingException { // bỏ dồ vào ngăn chưa đồ
        boolean result = false;

        //1. check valid id (home: check id, quantity>0, status=true)
        if (id == null) {
            return result;
        }
        if (id.trim().isEmpty()) {
            return result;
        }

        //2. Check items is exist
        if (this.items == null) {
            this.items = new HashMap<>();
        }
    
        //3. Check existed item (exist + quantity, not exist add to cart)

        int quantity = 1;
        
        
        if (this.items.containsKey(id)) {
            quantity = this.getItems().get(id).getQuantity() + 1;
        }
        
        
        //4. Drop item to itmes
        ProductDTO productDTO = new ProductDAO().findProductById(id);
        productDTO.setQuantity(quantity);
        this.items.put(id,  productDTO);
        result = true;

        return result;
    }
    
    

    public boolean removeItemFromCart(String id) {
        boolean result = false;

        // 1. Check existed items
        if (this.items != null) {
            // 2. Check existed item
            if (this.items.containsKey(id)) {
                // 3. Remove from items
                this.items.remove(id);
                if(this.items.isEmpty()){
                    this.items = null;
                } // ko còn item thì remove cho nó ko tồn tại luôn thì lúc check thì chỉ cần check != null mà ko cần check size > 0
                result = true;
            }// end check exist item
        }// end check exist items

        return result;
    }
    
}
