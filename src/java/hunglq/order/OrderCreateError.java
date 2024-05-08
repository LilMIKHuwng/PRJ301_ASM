/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunglq.order;

import java.io.Serializable;

/**
 *
 * @author HP
 */
public class OrderCreateError implements Serializable{
    protected String nameLengthError;
    protected String addressLengthError;

    public OrderCreateError() {
    }

    public String getNameLengthError() {
        return nameLengthError;
    }

    public void setNameLengthError(String nameLengthError) {
        this.nameLengthError = nameLengthError;
    }

    public String getAddressLengthError() {
        return addressLengthError;
    }

    public void setAddressLengthError(String addressLengthError) {
        this.addressLengthError = addressLengthError;
    }
    
    
}
