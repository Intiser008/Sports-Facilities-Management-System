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
    
    @Test
    public void testRemoveSessionWithInvalidSession() {
        SessionManager sessionManager = new SessionManager();
        Customer user = new Customer("testUser", "password123");
        Session session = new Session(user);
        session.setIsValid(false); // Mark session as invalid
        
        sessionManager.addSession(session);
        
        boolean result = sessionManager.removeSession(user);
        
        assertFalse(result);
    }
    
    @Test
    public void testRemoveSessionWithMultipleSessions() {
        SessionManager sessionManager = new SessionManager();
        Customer user1 = new Customer("user1", "password123");
        Session session1 = new Session(user1);
        Customer user2 = new Customer("user2", "password456");
        Session session2 = new Session(user2);
        
        sessionManager.addSession(session1);
        sessionManager.addSession(session2);
        
        boolean result = sessionManager.removeSession(user1);
        
        assertTrue(result);
        
    }
}