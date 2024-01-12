package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import user.Customer;
import authentication.Session;
import authentication.SessionManager;

public class TestSessionManager {
    


    @Test
    public void testCreateSession() {
    	
    	SessionManager sessionManager;
        Customer user;
        
    	sessionManager = new SessionManager();
        user = new Customer("testUser", "password123");
        sessionManager.createSession(user);
        assertEquals(1, sessionManager.getSessionCount());
    }

    @Test
    public void testRemoveSessionWithExistingUser() {

    	SessionManager sessionManager;
        Customer user;
        
    	sessionManager = new SessionManager();
        user = new Customer("testUser", "password123");
        sessionManager.addSession(new Session(user));
        
        boolean result = sessionManager.removeSession(user);
        
        assertTrue(result);
    }

    @Test
    public void testRemoveSessionWithNonExistingUser() {
    	SessionManager sessionManager;
        Customer user;
        
    	sessionManager = new SessionManager();
        user = new Customer("testUser", "password123");
        
        boolean result = sessionManager.removeSession(user);
        assertFalse(result);
    }
}
