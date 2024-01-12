package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import authentication.Session;
import user.Customer;

public class TestSession {
    @Test
    public void testSessionValidity() {
        Customer user = new Customer("testUser", "password123");
        Session session = new Session(user);
        assertTrue(session.getIsValid());
        session.setIsValid(false);
        assertFalse(session.getIsValid());
    }

    @Test
    public void testGetUser() {
        Customer user = new Customer("testUser", "password123");
        Session session = new Session(user);
        assertEquals(user, session.getUser());
    }
}
