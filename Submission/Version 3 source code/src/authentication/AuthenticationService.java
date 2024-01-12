package authentication;

import java.util.ArrayList;
import java.util.List;
import user.*;

public class AuthenticationService {
    // Assuming Customer and SessionManager are defined in the user package or appropriately imported.
    private Admin admin;
    private List<Customer> users;
    private SessionManager sessMgr;
    // private static Admin admin; // This line can be removed if Admin is not used.
    private static AuthenticationService instance;

    private AuthenticationService() {
        sessMgr = new SessionManager();
        users = new ArrayList<>();
        admin = (Admin.getInstance());
    }

    public void addUsers(Customer customer){
        users.add(customer);
    }

    public static AuthenticationService getInstance(){
        if(instance==null){
            instance = new AuthenticationService();
        }
        return instance;
    }

    public Admin findAdmin(String password){
        if (admin.getPassword().equals(password))
            return admin;
        return null;
    }

    public Customer findUser(String userName, String password) {
        if (users != null) {
            for (Customer user : users) {
                if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                    return user;
                }
            }
        }
        return null;
    }

    public boolean validatePassword(String password) {
        if (password.length() < 8) {
            return false;
        }
    
        boolean hasNumber = false;
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
    
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasNumber = true;
            } else if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            }
    
            if (hasNumber && hasUpperCase && hasLowerCase) {
                break;
            }
        }
    
        return hasNumber && hasUpperCase && hasLowerCase;
    }

    public Customer login(String userName, String password){
        Customer result=null;
        // if(admin.getUserName().equals(userName) && admin.getPassword().equals(password)){
        result = findUser(userName, password);
        if(result!=null)sessMgr.createSession(result);
        
        return result;
    }

    public boolean logout(Customer user) {
        return sessMgr.removeSession(user);
    }

    public Customer register(String userName, String password) {
        Customer user = findUser(userName, password);
        if (user != null) return null; // User already exists

        boolean goodPassword = validatePassword(password);

        if (goodPassword) {
            Customer newUser = new Customer(userName, password); // Assuming Customer constructor is defined.
            users.add(newUser);
            return newUser; // Return the new user instead of null
        }

        return null;
    }

    public boolean findUsername(String userName) {
        if (users != null) {
            for (Customer user : users) {
                if (user.getUserName().equals(userName)) {
                    return true;
                }
            }
        }
        return false; // This line was outside the method due to an extra brace.
    }

    public Admin adminLogin(String password) {
        return findAdmin(password);
    }

    // public static Admin registerAdmin(String username, String password){

    // }



}