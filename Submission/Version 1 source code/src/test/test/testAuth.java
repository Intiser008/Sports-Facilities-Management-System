 package test;
 import static org.junit.Assert.*;
 import java.io.ByteArrayOutputStream;
 import java.io.PrintStream;
 import org.junit.Test;

 import authentication.*;
 import user.*;

 public class testAuth {
	 AuthenticationService auth =AuthenticationService.getInstance();
     @Test
     public void testLogin_when_userNotNull(){
       
         Customer user1 = new Customer("Aisha","Password123");
         Customer user2 = new Customer("Taswar", "SecondPassword12");
         Customer user3 = new Customer("User3","ThirdPassword");
         Admin admin = new Admin("Adminboy","AdminPass12");

         auth.addUsers(user1);
         auth.addUsers(user2);
         auth.addUsers(user3);

         User loggedInUser = auth.login("Aisha", "Password123");

         assertEquals(user1,loggedInUser);
     }

     @Test
     public void testLogin_when_userIsNull(){
       
         Customer user1 = new Customer("Aisha","Password123");
         Customer user2 = new Customer("Taswar", "SecondPassword12");
         Customer user3 = new Customer("User3","ThirdPassword");
         Admin admin = new Admin("Adminboy","AdminPass12");

         auth.addUsers(user1);
         auth.addUsers(user2);
         auth.addUsers(user3);

         User loggedInUser = auth.login("NonUser", "Password123");

         assertEquals(null,loggedInUser);
     }

    
 }
