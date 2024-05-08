/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunglq.util;

/**
 *
 * @author HP
 */
public class ApplicationConstants {

    public class DispatchFeature {

        public static final String LOGIN_PAGE = "";
        public static final String LOGIN_CONTROLLER = "loginController";
        public static final String SEARCH_LASTNAME_CONTROLLER = "searchController";
        public static final String DELETE_ACCOUNT_CONTROLLER = "deleteAccountController";
        public static final String STARTUP_CONTROLLER = "startupController";
        public static final String UPDATE_ACCOUNT_CONTROLLER = "updateAccountControllert";
        public static final String ADD_ITEM_TO_CART_CONTROLLER = "addItemController";
        public static final String REMOVE_ITEM_FROM_CART_CONTROLLER = "removeItemController";
        public static final String CREATE_NEW_ACCOUNT_CONTROLLER = "createAccountController";
        public static final String LOGOUT_CONTROLLER = "logoutController";
        public static final String PRODUCT_CONTROLLER = "productController";
        public static final String CHECKOUT_CONTROLLER = "checkoutController";

        public static final String VIEW_ITEM_PAGE = "viewItemPage";
    }

    public class LoginFeature {

        public static final String INVAID_PAGE = "invalidPage";
        public static final String SEARCH_PAGE = "homePage";
    }

    public class SearchFeature {

        public static final String SEARCH_PAGE = "searchPage";
        public static final String RESULT_PAGE = "homePage";
    }

    public class AddItemFeature {

        public static final String ERROR_PAGE = "errorPage";
        public static final String PRODUCT_PAGE = "productController";
    }

    public class CreateAccountFeature {

        public static final String ERROR_PAGE = "errorAccountPage";
        public static final String LOGIN_PAGE = "loginPage";
    }
    
    public class DeleteAccountFeature{
        
        public static final String ERROR_PAGE = "errorPage";
    }
    
    public class LogoutFeature{
        
        public static final String LOGIN_PAGE = "";
    }
    
    public class ProductFeature{
        
        public static final String PRODUCT_PAGE = "productPage";
    }
    
    public class StartupFeature{
        
        public static final String LOGIN_PAGE = "";
        public static final String SEARCH_PAGE = "homePage";
    }
    
    public class UpdateAccountFeature {
        
        public static final String ERROR_PAGE = "errorPage";
    }
    
    public class CheckoutFeature {
        
        public static final String ERROR_PAGE = "viewItemPage";
        public static final String COMPLETE_PAGE = "completePage";
    }
}
