/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunglq.registration;

import java.io.Serializable;

/**
 *
 * @author HP
 */
public class LoginnCreateError implements Serializable{
    protected String usernameLengthError;
    protected String passwordLengthError;
    protected String fullNameLengthError;
    protected String confirmLengthError;
    protected String usernameIsExisted;

    public LoginnCreateError() {
    }

    public String getUsernameLengthError() {
        return usernameLengthError;
    }

    public void setUsernameLengthError(String usernameLengthError) {
        this.usernameLengthError = usernameLengthError;
    }

    public String getPasswordLengthError() {
        return passwordLengthError;
    }

    public void setPasswordLengthError(String passwordLengthError) {
        this.passwordLengthError = passwordLengthError;
    }

    public String getFullNameLengthError() {
        return fullNameLengthError;
    }

    public void setFullNameLengthError(String fullNameLengthError) {
        this.fullNameLengthError = fullNameLengthError;
    }

    public String getConfirmLengthError() {
        return confirmLengthError;
    }

    public void setConfirmLengthError(String confirmLengthError) {
        this.confirmLengthError = confirmLengthError;
    }

    public String getUsernameIsExisted() {
        return usernameIsExisted;
    }

    public void setUsernameIsExisted(String usernameIsExisted) {
        this.usernameIsExisted = usernameIsExisted;
    }
    
    
}
