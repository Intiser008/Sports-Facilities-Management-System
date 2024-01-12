package authentication;

import main.*;
import user.Customer;

public class AuthenticationService_Stub {
    public static Customer register(String username, String password){
        return new Customer_MainStub(username, password);
    }

    public static Customer login(String username, String password) {
        return new Customer_MainStub(username, password);
    }
}
