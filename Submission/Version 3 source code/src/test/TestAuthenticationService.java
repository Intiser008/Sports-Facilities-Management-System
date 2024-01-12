package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;


import authentication.AuthenticationService;
import user.Customer;

public class TestAuthenticationService {
    

    @Test
    public void testLoginWithValidCredentials_ReturnsCustomer() {
    	
    	AuthenticationService authService;
        Customer existingUser;
        
        authService = AuthenticationService.getInstance();
        existingUser = new Customer("existingUser", "Abcdefg1");
        authService.addUsers(existingUser);
        
        Customer loggedInUser = authService.login(existingUser.getUserName(), existingUser.getPassword());
        assertNotNull(loggedInUser);
        assertEquals(existingUser.getUserName(), loggedInUser.getUserName());
        assertEquals(existingUser.getPassword(), loggedInUser.getPassword());
    }

    @Test
    public void testLoginWithInvalidCredentials_ReturnsNull() {
    	
    	AuthenticationService authService;
    	
    	authService = AuthenticationService.getInstance();
    	
        Customer loggedInUser = authService.login("nonExistingUser", "invalidPassword");
        assertNull(loggedInUser);
    }

    @Test
    public void testLogoutWithLoggedInCustomer() {
    	
    	AuthenticationService authService;
        Customer existingUser;
        
        authService = AuthenticationService.getInstance();
        existingUser = new Customer("existingUser", "Password123");
        authService.addUsers(existingUser);
        authService.login("existingUser", "Password123");
        
        boolean result = authService.logout(existingUser);
        assertTrue(result);
    }

    @Test
    public void testLogoutWithLoggedOutCustomer() {
    	
    	AuthenticationService authService;
    	
    	authService = AuthenticationService.getInstance();
        Customer loggedOutCustomer = new Customer("loggedOut", "Password123");
        boolean result = authService.logout(loggedOutCustomer);
        
        assertFalse(result);
    }

    @Test
    public void testRegisterNewUserWithValidPassword() {
        String newUserName = "newUser";
        String newPassword = "Password123";
        AuthenticationService authService;
    	
    	authService = AuthenticationService.getInstance();
        Customer newUser = authService.register(newUserName, newPassword);
        assertNotNull(newUser);
        assertEquals(newUserName, newUser.getUserName());
        assertEquals(newPassword, newUser.getPassword());
    }

    @Test
    public void testRegisterNewUserWithInvalidPassword() {
        String newUserName = "newUser";
        String newPassword = "weak";
        AuthenticationService authService;
    	
    	authService = AuthenticationService.getInstance();
        Customer newUser = authService.register(newUserName, newPassword);
        assertNull(newUser);
    }
    
    @Test
    public void testRegsiterWhenUserIsAlreadyRegistered() {
    	AuthenticationService authService;
        Customer existingUser;
        
        authService = AuthenticationService.getInstance();
        existingUser = new Customer("existingUser", "Password123");
        authService.addUsers(existingUser);
        
        Customer newUser = authService.register("existingUser", "Password123");
        
        assertNull(newUser);
    }
    
    @Test
    public void testValidatePasswordWithNoLowerCase() {
      
    	AuthenticationService authService = AuthenticationService.getInstance();
        String password = "ABCDEFG1";
        
        boolean result = authService.validatePassword(password);
        
        assertFalse(result);
    }
    
    @Test
    public void testValidatePasswordWithNoUpperCase() {
       
    	AuthenticationService authService = AuthenticationService.getInstance();
        String password = "abcdefg1";
        
        boolean result = authService.validatePassword(password);
        
        assertFalse(result);
    }
    
    @Test
    public void testValidatePasswordWithShortPassword() {
    	AuthenticationService authService = AuthenticationService.getInstance();
        String password = "Abc12";
        
        boolean result = authService.validatePassword(password);
        
        assertFalse(result);
    }//not that effective
    
    @Test
    public void testValidatePasswordWithNoNumber() {
       
    	AuthenticationService authService = AuthenticationService.getInstance();
        String password = "Abcdefgh";
        
        boolean result = authService.validatePassword(password);
        
        assertFalse(result);
    }
}